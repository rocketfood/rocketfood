package com.spring.rocketfood.chat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.chat.vo.ChatMessageVO;
import com.spring.rocketfood.chat.vo.ChatRoomVO;

public interface ChatDAO {
	public int createChatRoom(ChatRoomVO chatRoomVO) throws DataAccessException;
	public Map <String, Object> selectAllAnsweredChatRoom(Map<String, String> chatConditionMap) throws DataAccessException;
	public Map <String, Object> selectAllWaitingChatRoom(Map<String, String> chatConditionMap) throws DataAccessException;
	public ChatRoomVO selectChatRoom(String member_id) throws DataAccessException;
	public int overlappedChatRoom(String member_id) throws DataAccessException;
	public int selectWaitingChatCount() throws DataAccessException;
	public int insertMessage(ChatMessageVO chatMessageVO) throws DataAccessException;
	public List<ChatMessageVO> selectChatMessage(int chat_room_id) throws DataAccessException;
	public int updateChatRoomStatus(ChatRoomVO chatRoomVO) throws DataAccessException;
}
