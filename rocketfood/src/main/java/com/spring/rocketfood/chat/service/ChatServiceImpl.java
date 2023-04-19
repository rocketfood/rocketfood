package com.spring.rocketfood.chat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rocketfood.chat.dao.ChatDAO;
import com.spring.rocketfood.chat.vo.ChatMessageVO;
import com.spring.rocketfood.chat.vo.ChatRoomVO;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	ChatDAO chatDAO;
	@Autowired
	ChatRoomVO chatRoomVO;
	
	
	//채팅방 만들기
	@Override
	public int addChatRoom(ChatRoomVO chatRoomVO) throws Exception {
		return chatDAO.createChatRoom(chatRoomVO);
	}
	
	// 답변 완료한 채팅방 리스트 가져오기
	@Override
	public Map <String, Object> findAllAnsweredChatRoom(Map<String, String> chatConditionMap) throws Exception {
		Map <String, Object> chatMap = chatDAO.selectAllAnsweredChatRoom(chatConditionMap);
		return chatMap;
	}
	
	// 답변 대기중인 채팅방 리스트 가져오기
	@Override
	public Map <String, Object> findAllWatingChatRoom(Map<String, String> chatConditionMap) throws Exception {
		Map <String, Object> waitingChatMap = chatDAO.selectAllWaitingChatRoom(chatConditionMap);
		return waitingChatMap;
	}
	
	//회원 아이디로 채팅방 가져오기
	@Override
	public ChatRoomVO findChatRoom(String member_id) throws Exception {
		chatRoomVO = chatDAO.selectChatRoom(member_id);
		return chatRoomVO;
	}
	
	//채팅방 중복조회
	@Override
	public int findOverlappedChatRoom(String member_id) throws Exception {
		return chatDAO.overlappedChatRoom(member_id);
	}
	
	//답변 대기중인 채팅방 수 불러오기
	@Override
	public int findWaitingChatCount() throws Exception {
		return chatDAO.selectWaitingChatCount();
	}
	
	//채팅 메시지 저장하기
	@Override
	public int addMessage(ChatMessageVO chatMessageVO) throws Exception {
		String answerer = chatMessageVO.getAnswerer();
		if(answerer != null && answerer.equals("admin") ) {
			chatRoomVO.setChat_status("y");
			
		} else {
			chatRoomVO.setChat_status("n");
		}
		
		chatRoomVO.setChat_room_id(chatMessageVO.getChat_room_id());
		chatDAO.updateChatRoomStatus(chatRoomVO);
		
		return chatDAO.insertMessage(chatMessageVO);
	}
	
	//채팅방 메시지 가져오기
	@Override
	public List<ChatMessageVO> findChatMessage(int chat_room_id) throws Exception {
		List<ChatMessageVO> chatMessageList = chatDAO.selectChatMessage(chat_room_id);
		return chatMessageList;
	}
	
	//채팅방 상태변경
	public void updateChatStatus(ChatRoomVO chatRoomVO) throws Exception {
		chatDAO.updateChatRoomStatus(chatRoomVO);
	}
}
