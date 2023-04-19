package com.spring.rocketfood.mypage.order.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.order.vo.OrderVO;

@Repository("myPageOrderDAO")
public class MyPageOrderDAOImpl implements MyPageOrderDAO{
	@Autowired
	private SqlSession sqlSession;
	
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException{
		List<OrderVO> orderGoodsList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
		return orderGoodsList;
	}
	
	public List selectMyOrderInfo(String order_id) throws DataAccessException {
		List myOrderList = (List) sqlSession.selectList("mapper.mypage.selectMyOrderInfo",order_id);
		return myOrderList;
	}	

	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException{
		List<OrderVO> myOrderHistList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
		return myOrderHistList;
	}
		
	public void updateMyOrderCancel(String order_id) throws DataAccessException{
		sqlSession.update("mapper.mypage.updateMyOrderCancel",order_id);
	}
	
}
