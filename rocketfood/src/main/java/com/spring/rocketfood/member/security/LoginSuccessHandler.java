package com.spring.rocketfood.member.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.vo.MemberVO;

@Service("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	MemberService memberService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {	
		
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		String prevPage = (String) request.getSession().getAttribute("prevPage");
		if(prevPage!=null) request.getSession().removeAttribute("prevPage");
		
		String uri = request.getContextPath();
		String member_id = authentication.getName();
		if(savedRequest!=null) {
			uri = savedRequest.getRedirectUrl();

			requestCache.removeRequest(request, response);
			System.out.println(uri);
			
			//session attribute 확인용
			Enumeration<String> list = request.getSession().getAttributeNames();
			while (list.hasMoreElements()) {
				System.out.println(list.nextElement());
			}
			
		} else if (prevPage != null && !prevPage.equals("")) {
			uri = prevPage;
			System.out.println("uri : "+uri);
			if(uri.equals("http://localhost:8080/rocketfood/member/memberForm3.do")) {
				uri = "http://localhost:8080/rocketfood/main/main.do";
			}
			if(uri.equals("http://localhost:8080/rocketfood/member/findIdResult.do")) {
				uri = "http://localhost:8080/rocketfood/main/main.do";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("isLogOn", true);
			MemberVO memberVO = new MemberVO();
			try {
				memberVO = memberService.findMemberById(member_id);
				
			} catch(Exception e) {
				memberVO.setMember_id(member_id);
			}
			
			session.setAttribute("memberInfo", memberVO);
			
		}
		response.sendRedirect(uri);
	}
}