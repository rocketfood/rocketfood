package com.spring.rocketfood.mypage.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.order.vo.OrderVO;

public interface MyPageMemberService{
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
	public MemberVO myDetailInfo(String member_id) throws Exception;
	public boolean deleteMember(MemberVO memberVO) throws Exception;


}
