package com.spring.rocketfood.mypage.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.mypage.order.dao.MyPageOrderDAO;
import com.spring.rocketfood.order.vo.OrderVO;

@Service("myPageOrderService")
@Transactional(propagation=Propagation.REQUIRED)
public class MyPageOrderServiceImpl  implements MyPageOrderService{
	@Autowired
	private MyPageOrderDAO myPageOrderDAO;

	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception{
		return myPageOrderDAO.selectMyOrderGoodsList(member_id);
	}
	
	public List findMyOrderInfo(String order_id) throws Exception{
		return myPageOrderDAO.selectMyOrderInfo(order_id);
	}
	
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception{
		return myPageOrderDAO.selectMyOrderHistoryList(dateMap);
	}
	
	public void cancelOrder(String order_id) throws Exception{
		myPageOrderDAO.updateMyOrderCancel(order_id);
	}

}
