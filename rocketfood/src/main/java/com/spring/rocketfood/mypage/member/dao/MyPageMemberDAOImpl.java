package com.spring.rocketfood.mypage.member.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.order.vo.OrderVO;

@Repository("myPageMemberDAO")
public class MyPageMemberDAOImpl implements MyPageMemberDAO{
	@Autowired
	private SqlSession sqlSession;
	
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException{
		List<OrderVO> orderGoodsList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
		return orderGoodsList;
	}
	
	public List selectMyOrderInfo(String order_id) throws DataAccessException{
		List myOrderList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderInfo",order_id);
		return myOrderList;
	}	

	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException{
		List<OrderVO> myOrderHistList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
		return myOrderHistList;
	}
	
	public void updateMyInfo(Map memberMap) throws DataAccessException{
		sqlSession.update("mapper.mypage.updateMyInfo",memberMap);
	}
	
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException{
		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
		return memberVO;
		
	}
	
	@Override
	   public int deleteMemberById(MemberVO memberVO) throws DataAccessException {
	      int result = sqlSession.delete("mapper.mypage.deleteMemberById", memberVO);
	      return result;
	   }
}
