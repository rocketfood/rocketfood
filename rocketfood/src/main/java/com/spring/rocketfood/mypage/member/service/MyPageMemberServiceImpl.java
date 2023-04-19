package com.spring.rocketfood.mypage.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.mypage.member.dao.MyPageMemberDAO;
import com.spring.rocketfood.order.vo.OrderVO;

@Service("myPageMemberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MyPageMemberServiceImpl  implements MyPageMemberService{
	@Autowired
	private MyPageMemberDAO myPageDAO;

	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception{
		return myPageDAO.selectMyOrderGoodsList(member_id);
	}
	
	public List findMyOrderInfo(String order_id) throws Exception{
		return myPageDAO.selectMyOrderInfo(order_id);
	}
	
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception{
		return myPageDAO.selectMyOrderHistoryList(dateMap);
	}
	
	public MemberVO modifyMyInfo(Map memberMap) throws Exception{
		 String member_id=(String)memberMap.get("member_id");
		 myPageDAO.updateMyInfo(memberMap);
		 return myPageDAO.selectMyDetailInfo(member_id);
	}
	
	public MemberVO myDetailInfo(String member_id) throws Exception{
		return myPageDAO.selectMyDetailInfo(member_id);
	}
	
	@Override
	   public boolean deleteMember(MemberVO memberVO) throws Exception {
		int result = myPageDAO.deleteMemberById(memberVO);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}

}
