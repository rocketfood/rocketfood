package com.spring.rocketfood.mypage.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.mypage.member.service.MyPageMemberService;

@Controller("myPageMemberController")
@RequestMapping(value="/mypage/member")
public class MyPageMemberControllerImpl extends BaseController  implements MyPageMemberController{
	@Autowired
	private MyPageMemberService myPageMemberService;
	
	@Autowired
	private MemberVO memberVO;
	
	
	
	//개인정보 수정 페이지
	@Override
	@RequestMapping(value="/myDetailInfo.do" ,method = RequestMethod.GET)
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}	
	
	
	//개인 정보 수정하기
	@Override
	@RequestMapping(value="/modifyMyInfo.do" ,method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> memberMap = new HashMap<String,String>();
		String val[] = null;
		//세션에서 로그인한 사용자 정보 가져오기
		HttpSession session = request.getSession();
		memberVO = (MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		
		//입력한 attribute 가져오기
		if(attribute.equals("member_birth")){
			val=value.split(",");
			memberMap.put("member_birth_y",val[0]);
			memberMap.put("member_birth_m",val[1]);
			memberMap.put("member_birth_d",val[2]);
			memberMap.put("member_birth_gn",val[3]);
		}else if(attribute.equals("tel")){
			val=value.split(",");
			memberMap.put("tel1",val[0]);
			memberMap.put("tel2",val[1]);
			memberMap.put("tel3",val[2]);
		}else if(attribute.equals("hp")){
			val=value.split(",");
			memberMap.put("hp1",val[0]);
			memberMap.put("hp2",val[1]);
			memberMap.put("hp3",val[2]);
			memberMap.put("smssts_yn", val[3]);
		}else if(attribute.equals("email")){
			val=value.split(",");
			memberMap.put("email1",val[0]);
			memberMap.put("email2",val[1]);
			memberMap.put("emailsts_yn", val[2]);
		}else if(attribute.equals("address")){
			val=value.split(",");
			memberMap.put("zipcode",val[0]);
			memberMap.put("roadAddress",val[1]);
			memberMap.put("jibunAddress", val[2]);
			memberMap.put("namujiAddress", val[3]);
		}else {
			memberMap.put(attribute,value);	
		}
		
		memberMap.put("member_id", member_id);
		
		//占쏙옙占쏙옙占쏙옙 회占쏙옙 占쏙옙占쏙옙占쏙옙 占쌕쏙옙 占쏙옙占실울옙 占쏙옙占쏙옙占싼댐옙.
		memberVO = (MemberVO) myPageMemberService.modifyMyInfo(memberMap);
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}	
	
	//회원 탈퇴하기
	@Override
	@RequestMapping(value="/deleteMember.do", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public @ResponseBody String deleteMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   HttpSession session = request.getSession();
	   boolean deleteMember_result = myPageMemberService.deleteMember(memberVO);
	     
	   String message = "";	      
	   if(deleteMember_result != true){
		   message = "<script>"
				   + "alert('�떎�떆 �떆�룄�빐二쇱꽭�슂.');"
				   + "location.href='" + request.getContextPath() + "/mypage/myPageMain.do';"
				   + "</script>";
	   } else {
		   session.setAttribute("isLogOn", false);
		   session.removeAttribute("memberInfo");
		   session.setAttribute("deleteMember", true);
		   
		   message =  "<script>"
				   + "alert('�깉�눜媛� �셿猷뚮릺�뿀�뒿�땲�떎.');"
				   + "location.href='" + request.getContextPath() + "/main/main.do';"
				    + "</script>";
	   }
			System.out.println(message);
			return message;
	}
	
	//배송지 불러오기
	public ModelAndView myAddressList(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		return mav;
	}

	
}
