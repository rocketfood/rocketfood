package com.spring.rocketfood.chat.service;

import java.util.List;
import java.util.Map;

import com.spring.rocketfood.chat.vo.ChatMessageVO;
import com.spring.rocketfood.chat.vo.ChatRoomVO;

public interface ChatService {
	public int addChatRoom(ChatRoomVO chatRoomVO) throws Exception;
	public Map <String, Object> findAllAnsweredChatRoom(Map<String, String> chatConditionMap) throws Exception;
	public Map <String, Object> findAllWatingChatRoom(Map<String, String> chatConditionMap) throws Exception;
	public ChatRoomVO findChatRoom(String member_id) throws Exception;
	public int findOverlappedChatRoom(String member_id) throws Exception;
	public int findWaitingChatCount() throws Exception;
	public int addMessage(ChatMessageVO chatMessageVO) throws Exception;
	public List<ChatMessageVO> findChatMessage(int chat_room_id) throws Exception;
	public void updateChatStatus(ChatRoomVO chatRoomVO) throws Exception;
}
