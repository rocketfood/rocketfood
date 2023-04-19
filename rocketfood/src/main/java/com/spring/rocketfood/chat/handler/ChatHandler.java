package com.spring.rocketfood.chat.handler;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring.rocketfood.chat.service.ChatService;
import com.spring.rocketfood.chat.vo.ChatMessageVO;
import com.spring.rocketfood.chat.vo.ChatRoomVO;

// WebSocket을 사용하기 위해서는 반드시 TextWebSocketHandler를 상속해줘야 함

@Component("chatHandler")
public class ChatHandler extends TextWebSocketHandler implements WebSocketConfigurer {
	// synchronizedSet : 동기화된 set은 반환해주는 메소드
    // 멀티스레드 환경에서 하나의 컬렉션요소에 여러 스레드가 동시에 접근하면 충돌이 발생할 수 있으므로 동기화를 충돌이 안나도록 진행
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	@Autowired
	ChatService chatService;

	
	// 사용자가 접속 했을 때 호출되는 메소드
	@Override	
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// WebSocketSession : 웹소켓에 접속/요청한 클라이언트의 세션
		
		// 전달받은 webSocketSession을 set에 추가
		sessions.add(session); 
		
	}
	
	// 사용자가 메세지를 보냈을 때 호출되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		// TextMessage : 웹소켓을 이용해 전달된 텍스트가 담겨있는 객체
		
		// Jackson-databind : ObjectMapper 를 이용해서 JSON형태로 넘어온 데이터를 특정VO필드에 맞게 자동매핑
		ObjectMapper objectMapper = new ObjectMapper();
		        
		ChatMessageVO chatMessageVO = objectMapper.readValue(msg.getPayload(), ChatMessageVO.class);
		chatMessageVO.setCreated_date(new Timestamp(System.currentTimeMillis()));
				
		// 전달 받은 채팅메세지를 db에 삽입
		int result = chatService.addMessage(chatMessageVO);		
		if(result > 0) {
			// 같은방에 접속중인 클라이언트에게 전달받은 메세지를 보내기
			for(WebSocketSession s : sessions) {
				//반복을 진행중인 websocketSession안에 담겨있는 방번호
				int chat_room_id = (Integer) s.getAttributes().get("chat_room_id");
            	
				//메세지에 담겨있는 채팅방 번호와 chatRoomNo가 같은지 비교
				if(chatMessageVO.getChat_room_id() == chat_room_id) {
					
					//만약 관리자 답변한 메시지라면 채팅방 상태 변경
					ChatRoomVO chatRoomVO = null;
					if(chatMessageVO.getAnswerer().equals("admin")) { 
						chatRoomVO = new ChatRoomVO(chat_room_id, "y");							
					} else {
						chatRoomVO = new ChatRoomVO(chat_room_id, "n");		
					}
					chatService.updateChatStatus(chatRoomVO);
					
					//같은 방 클라이언트에게 JSON형식으로 메세지를 보냄
					s.sendMessage(new TextMessage( new Gson().toJson(chatMessageVO )));
				}
			}
        
		}
	}
	
	// 사용자 접속 해제했을 때 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 웹소켓 연결이 종료되는 경우, sessions안에 저장되어있던 클라이언트의 session정보를 삭제
		sessions.remove(session);
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(this, "/chat").withSockJS();
	}

	
}