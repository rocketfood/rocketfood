package com.spring.rocketfood.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.service.NaverLoginService;
import com.spring.rocketfood.member.vo.MemberVO;



@Controller("naverLoginContoller")
public class NaverLoginController {
	@Autowired
	private MemberService memberService;
	
	
	/* NaverLoginBO */
	@Autowired
    private NaverLoginService naverLoginService;
    private String apiResult = null;
    
	
	   
	// 네이버 로그인 & 회원정보(이름) 가져오기
    @RequestMapping(value = "/naverLogin.do", method = RequestMethod.GET)
	public ModelAndView naverLogin(@RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		ModelAndView mav = new ModelAndView();
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginService.getAccessToken(session, code, state);
	
		// 로그인한 사용자의 모든 정보가 JSON타입으로 저장되어 있음
		apiResult = naverLoginService.getUserProfile(oauthToken);
	 
		// String 형태로 저장된 JSON을 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = null;
            

        element = parser.parse(apiResult);
        JsonObject respone = element.getAsJsonObject().getAsJsonObject("response");
		
        //JSON을 String으로 저장
        String nname = respone.get("name").getAsString();
        String nemail = respone.get("email").getAsString();
        String nid = respone.get("id").getAsString();   
        String nphone = respone.get("mobile").getAsString();
           
		//네이버 서버에서 전달 받은 member_id가 존재한다면
		if (nname != null) {	    	
		session.setAttribute("isLogOn", true);
		session.setAttribute("naver", "y");
		session.setAttribute("access_Token", oauthToken);
		        
			try {
				String result = memberService.findMemberById(nid).getMember_id();
				MemberVO memberVO = new MemberVO();
				memberVO.setMember_id(nid);
				memberVO.setMember_name(nname);
				memberVO.setMember_email(nemail);
				memberVO.setManager_phone(nphone);
				memberVO.setSocialMember("naver");
				
				//회원 이름과 이메일로 DB 상의 회원 정보가 있는지 조회 후 없다면 새로 추가
				if(result == null || !result.equals(nid)) {	
					System.out.println("네이버로 회원가입");
					memberService.addMember(memberVO);		        	
				}
		        	
				//회원 정보를 세션에 저장하여 로그인 유지
				session.setAttribute("memberInfo", memberVO);
		        	
				mav.setViewName("redirect:/main/main.do");	
				System.out.println("네이버 아이디로 로그인");
			} catch(Exception e) {
				e.printStackTrace();
			}
			//카카오 서버에서 전달 받은 member_id가 없다면    
		} else {
			session.setAttribute("isLogOn", false);
			mav.setViewName("redirect:/member/loginForm.do");	
		}    
			
		return mav;
	}// end naverLogin()	
}
