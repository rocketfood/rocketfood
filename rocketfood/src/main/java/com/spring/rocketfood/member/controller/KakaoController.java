package com.spring.rocketfood.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.member.security.CustomAuthenticationProvider;
import com.spring.rocketfood.member.service.KakaoService;
import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.vo.MemberVO;

@Controller
public class KakaoController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberVO memberVO;
	
	@Autowired
	private KakaoService kakaoService;

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	//카카오 로그인 실행
	@RequestMapping(value = "/kakaoLogin.do", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String access_Token = kakaoService.getAccessToken(code);
	    Map<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);
	    String memeber_id = String.valueOf(userInfo.get("member_id"));
	    String member_name = String.valueOf(userInfo.get("member_name"));
	    String member_email = String.valueOf(userInfo.get("member_email"));
	    
	    HttpSession session= request.getSession();
	    ModelAndView mav = new ModelAndView();
	    
	    //카카오 서버에서 전달 받은 member_id가 존재한다면
	    if (userInfo.get("member_id") != null) {	    	
	    	session.setAttribute("isLogOn", true);
	    	session.setAttribute("kakao", "y");
	        session.setAttribute("access_Token", access_Token);
	        
	        try {
	        	//memberVO = memberService.findMemberById(memeber_id);
	        	


		        	MemberVO memberVO = new MemberVO();
		        	memberVO.setMember_id(memeber_id);
	        		memberVO.setMember_name(member_name);
	        		memberVO.setMember_email(member_email);
	        		memberVO.setSocialMember("kakao");
	        		
	        		System.out.println("카카오 아이디로 회원가입");
	        		//memberService.addMember(memberVO);
	        		
	        	//회원 정보를 세션에 저장하여 로그인 유지
	        	session.setAttribute("memberInfo", memberVO);
	       	        	
	        	mav.setViewName("redirect:/main/main.do");	
	    	    System.out.println("카카오 아이디로 로그인");
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
	    //카카오 서버에서 전달 받은 member_id가 없다면    
	    } else {
	    	session.setAttribute("isLogOn", false);
	    	mav.setViewName("redirect:/member/loginForm.do");	
	    }    
	    
	    return mav;
	}
}
