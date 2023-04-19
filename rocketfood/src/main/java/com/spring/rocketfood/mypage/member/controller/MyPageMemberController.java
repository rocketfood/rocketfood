package com.spring.rocketfood.mypage.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.member.vo.MemberVO;

public interface MyPageMemberController {
	//개인정보 관련
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value")  String value, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String deleteMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//배송지 관련
	public ModelAndView myAddressList(HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
