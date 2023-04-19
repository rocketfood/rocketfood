package com.spring.rocketfood.chat.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.chat.vo.ChatMessageVO;
import com.spring.rocketfood.chat.vo.ChatRoomVO;

@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO {
	@Autowired
	SqlSession sqlSession;
	@Autowired
	ChatRoomVO chatRoomVO;

	
	//채팅방 만들기
	@Override
	public int createChatRoom(ChatRoomVO chatRoomVO) throws DataAccessException {
		return sqlSession.insert("mapper.chat.insertChatRoom", chatRoomVO);
	}
	
	// 답변 완료한 모든 채팅방 불러오기
	public Map <String, Object> selectAllAnsweredChatRoom(Map<String, String> chatConditionMap) throws DataAccessException {
		chatConditionMap.put("chat_status", "y");
		Map <String, Object> chatMap = new HashMap<String, Object>();
		List<ChatRoomVO> chatRoomList = sqlSession.selectList("mapper.chat.selectAllChatRoomByStatus", chatConditionMap);
		List<String> chatMsgList = sqlSession.selectList("mapper.chat.selectLastestChatMessageList", chatConditionMap.get("chat_status"));
		chatMap.put("chatRoomList", chatRoomList);
		chatMap.put("chatMsgList", chatMsgList);
		return chatMap;
	}
	
	// 답변 대기인 모든 채팅방 불러오기
	public Map <String, Object> selectAllWaitingChatRoom(Map<String, String> chatConditionMap) throws DataAccessException {
		chatConditionMap.put("chat_status", "n");
		Map <String, Object> waitingChatMap = new HashMap<String, Object>();
		List<ChatRoomVO> chatRoomList = sqlSession.selectList("mapper.chat.selectAllChatRoomByStatus", chatConditionMap);
		List<String> chatMsgList = sqlSession.selectList("mapper.chat.selectLastestChatMessageList", chatConditionMap.get("chat_status"));
		List<Integer> waitingCountList = new ArrayList<Integer>();
		for(int i=0; i< chatRoomList.size(); i++ ) {
			int waitingCount = sqlSession.selectOne("mapper.chat.selectWaitngChatMsgCount", chatRoomList.get(i).getChat_room_id());
			waitingCountList.add(waitingCount);
		}
		waitingChatMap.put("chatRoomList", chatRoomList);
		waitingChatMap.put("chatMsgList", chatMsgList);
		waitingChatMap.put("waitingCountList", waitingCountList);
		return waitingChatMap;
	}
	
	//채팅방 불러오기
	@Override
	public ChatRoomVO selectChatRoom(String member_id) throws DataAccessException {
		chatRoomVO = sqlSession.selectOne("mapper.chat.selectChatRoomByMemberId", member_id);
		return chatRoomVO;
	}
	
	//중복 채팅방 조회
	@Override
	public int overlappedChatRoom(String member_id) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.chat.countChatRoomById", member_id);
		return count;		
	}
	
	//답변 대기 상태의 채팅방 수 불러오기
	public int selectWaitingChatCount() throws DataAccessException {
		int count = sqlSession.selectOne("mapper.chat.selectWaitngChatCount");
		return count;		
	}
	
	//메시지 삽입하기
	@Override
	public int insertMessage(ChatMessageVO chatMessageVO) throws DataAccessException {
		return sqlSession.insert("mapper.chat.insertChatMessage", chatMessageVO);
	}
	
	//채팅방의 모든 메시지 불러오기
	@Override
	public List<ChatMessageVO> selectChatMessage(int chat_room_id) throws DataAccessException {
		List<ChatMessageVO> ChatMessageList = sqlSession.selectList("mapper.chat.selectAllChatMessage", chat_room_id);
		return ChatMessageList;
	}
	
	//채팅방 상태 업데이트
	@Override
	public int updateChatRoomStatus(ChatRoomVO chatRoomVO) throws DataAccessException {
		return sqlSession.update("mapper.chat.updateChatRoomStatus", chatRoomVO);
	}
}
