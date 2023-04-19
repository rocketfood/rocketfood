package com.spring.rocketfood.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.chat.service.ChatService;
import com.spring.rocketfood.chat.vo.ChatMessageVO;
import com.spring.rocketfood.chat.vo.ChatRoomVO;
import com.spring.rocketfood.member.vo.MemberVO;

@Controller("chatController")
@SessionAttributes("chat_room_id")
@RequestMapping(value="/chat")
public class ChatControllerImpl implements ChatController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRoomVO chatRoomVO;
	@Autowired
	private MemberVO memberVO;
	
	
	//채팅방 목록 조회
	@RequestMapping(value = "/chatRoomList.do", method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView selectChatRoomList(@RequestParam Map<String, String> dataMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		String section = dataMap.get("section");
		String pageNum = dataMap.get("pageNum");
		
		Map<String, String> chatConditionMap = new HashMap<String, String>();
		if(section== null) {
			section = "1";
		}
		chatConditionMap.put("section", section);
		if(pageNum== null) {
			pageNum = "1";
		}
		chatConditionMap.put("pageNum", pageNum);
		
		Map <String, Object> chatMap = chatService.findAllAnsweredChatRoom(chatConditionMap);
		Map <String, Object> waintingChatMap = chatService.findAllWatingChatRoom(chatConditionMap);
		mav.addObject("chatMap", chatMap);
		mav.addObject("waintingChatMap", waintingChatMap);
		mav.addObject("section", section);
		mav.addObject("pageNum", pageNum);
		return mav;
	}
    
	//채팅방 만들기
	@RequestMapping(value = "/openChatRoom.do", method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView openChatRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);		
		
		
		//로그인한 사용자인지 여부 확인 후 DB에 채팅방 저장
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		int chat_room_id = 0;
		
		if(memberVO != null && memberVO.getMember_id() != "admin") { //로그인한 사용자라면 아이디를 가져온다 관리자는 채팅불가

			String member_id = memberVO.getMember_id();
			chatRoomVO.setQuestioner(member_id);
		
			
			//회원 아이디로 된 채팅방 중복 검사
			int overlappedChatRoomResult = chatService.findOverlappedChatRoom(member_id);
			if(overlappedChatRoomResult < 1) { //기존의 채팅방이 없을 때
				chatService.addChatRoom(chatRoomVO);
				
			} else { //채팅방이 기존에 존재할 때
				//기존의 채팅 목록을 불러온다.
				chatRoomVO = chatService.findChatRoom(member_id);
				chat_room_id = chatRoomVO.getChat_room_id();
				
				List<ChatMessageVO> chatMessageList = chatService.findChatMessage(chat_room_id);
				mav.addObject("chatMessageList", chatMessageList);
			}
			session.setAttribute("questioner", member_id);
			
		} else { 
			//세션에 저장된 비회원의 게스트 아이디로 된 채팅방 중복 검사
			String guest_id = (String) session.getAttribute("questioner");
			if(guest_id != null && !guest_id.equals("")) { // 세션에 저장된 게스트 아이디가 있을 때
				//게스트 아이디로 기존의 채팅 목록을 불러온다.
				chatRoomVO = chatService.findChatRoom(guest_id);
				chat_room_id = chatRoomVO.getChat_room_id();
				
				List<ChatMessageVO> chatMessageList = chatService.findChatMessage(chat_room_id);
				mav.addObject("chatMessageList", chatMessageList);
								
			} else { //해당 비회원의 게스트 아이디가 존재하지 않을 때
				//로그인 하지 않은 사용자라면 guest 아이디를 부여하고 채팅방을 생성 후 세션에 게스트 아이디 저장(세션이 만료되면 비회원은 해당 채팅방에 재접속 불가)
				guest_id = "guest" + System.currentTimeMillis();
				chatRoomVO.setQuestioner(guest_id);
				chatService.addChatRoom(chatRoomVO);
				session.setAttribute("questioner", guest_id);
			}
			
			
		}		
		String questioner = (String) session.getAttribute("questioner");
		chatRoomVO = chatService.findChatRoom(questioner);	
		chat_room_id = chatRoomVO.getChat_room_id();
		System.out.println("chat_room_id : " + chat_room_id);
		
		// 채팅방 입장
		mav.addObject("questioner", questioner);
		mav.addObject("chat_room_id", chat_room_id);
		return mav;
	}	
	
	//관리자로 채팅방 입장
	@RequestMapping(value = "/replychat.do", method = RequestMethod.GET)
	public ModelAndView replychat (@RequestParam("chat_room_id") int chat_room_id, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);	
		
		//로그인한 사용자인지 여부 확인
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		//관리지인지 여부 확인
		if(memberVO != null && memberVO.getMember_id().equals("admin")) {
			List<ChatMessageVO> chatMessageList = chatService.findChatMessage(chat_room_id);
			mav.addObject("chatMessageList", chatMessageList);
		}
		
		String answerer = "admin";
		
		// 채팅방 입장
		mav.addObject("answerer", answerer);
		mav.addObject("chat_room_id", chat_room_id);
		return mav;
	}
}
