package com.spring.rocketfood.mypage.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.order.vo.OrderVO;

public interface MyPageOrderDAO {
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;
	public List selectMyOrderInfo(String order_id) throws DataAccessException;
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
	public void updateMyOrderCancel(String order_id) throws DataAccessException;
}
