package com.spring.rocketfood.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.common.mail.MailSendService;
import com.spring.rocketfood.member.service.KakaoService;
import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.service.NaverLoginService;
import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.MemberVO;

@Controller("memberController")
@EnableAsync
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private AddressVO addressVO;
	@Autowired
	private MailSendService mailService;
	@Autowired
	private KakaoService kakaoService;
	@Autowired
	private NaverLoginService naverLoginService;
	
	// 로그인 페이지
	@RequestMapping("/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, Model model, Authentication authentication, HttpSession session) throws Exception {
		String uri = request.getHeader("Referer"); 
		String naverAuthUrl = naverLoginService.getAuthorizationUrl(session);

		// 권한이 없다면
		if (authentication != null)
			return null;

		if (uri != null && !uri.contains("/loginForm.do")) {
			request.getSession().setAttribute("prevPage", uri);
		}

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("naverAuthUrl", naverAuthUrl);
		return mav;
	}

	// 시큐리티 달기 이전의 로그인 - 23-03-22 시큐리티 사용으로 주석화
	/*@Override
	 @RequestMapping(value = "/login.do", method = RequestMethod.POST) 
	 public ModelAndView login(@RequestParam Map<String, Object> loginMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
		 ModelAndView mav = new ModelAndView(); 
		 Map<String, Object> loginInfoMap = memberService.login(loginMap);
		 memberVO = (MemberVO) loginInfoMap.get("memberVO");
	 
		 if (memberVO != null && memberVO.getMember_id() != null) { 
			 HttpSession session = request.getSession(); 
			 session.setAttribute("isLogOn", true);
			 session.setAttribute("memberInfo", memberVO);
	  
			 String action = (String) session.getAttribute("action");
	 
			 if (action != null){ 
				 mav.setViewName("forward:" + action); 
			 } else {
				 mav.setViewName("redirect:/main/main.do"); 
			 } 
		 } else { 
			 String message = "아이디나  비밀번호가 틀립니다. 다시 로그인해주세요"; 
			 mav.addObject("message", message);
			 mav.setViewName("/member/loginForm"); 
		 } 
		 return mav; 
	 }*/
	
	 

	
	  //로그아웃
	  @Override
	  @RequestMapping(value="/logout.do" ,method = RequestMethod.GET) 
	  public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		ModelAndView mav = new ModelAndView(); 
		HttpSession	session = request.getSession();
		
	  	
	  	//권한 정보 확인 후 시큐리티 라이브러리 이용하여 로그아웃
	  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  	if (auth != null) {
	  	// 카카오 로그인인지 여부 확인
    	  	String kakao = (String) session.getAttribute("kakao"); 
    	  	String access_Token = (String) session.getAttribute("access_Token");
    	  	if(kakao == "y") {
    	  		kakaoService.kakaoLogout(access_Token);
    	  		session.removeAttribute("kakao"); 
    	  		System.out.println("카카오 로그아웃 완료"); 
    	  	}
    	  	
            new SecurityContextLogoutHandler().logout(request, response, auth);
         
        }
	  	
	  	mav.setViewName("redirect:/main/main.do"); 
	  	System.out.println("로그아웃 완료"); 
	  	return mav; 
	  }
	 

	//회원가입
	@Override
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ResponseEntity<String> addMember(@ModelAttribute("memberVO") MemberVO memberVO, @ModelAttribute("addressVO") AddressVO addressVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		//이름, 이메일로 기존 회원 여부 검사 후 중복이면 스크립트 리턴 해줘야 함
		
		//이메일 중복 검사 필요함
		
		//addressVO셋팅 - 회원가입 시 입력한 주소는 기본 배송주소로 설정된다
		addressVO.setIs_basic_address("y");
		addressVO.setReceiver_name(memberVO.getMember_name());
		addressVO.setReceiver_phone(memberVO.getMember_phone());
		
		try {
			memberService.addMember(memberVO);
			memberService.addAddress(addressVO);
			
			message = "<script>";
			message += " location.href='" + request.getContextPath() + "/member/memberForm3.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm1.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity<String>(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	//아이디 중복검사
	@Override
	@RequestMapping(value = "/overlapped.do", method = RequestMethod.POST)
	public ResponseEntity<String> overlapped(@RequestParam("id") String member_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseEntity<String> resEntity = null;
		String result = memberService.overlapped(member_id);
		resEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return resEntity;
	}

	// 모달창 약관 보기
	@RequestMapping(value = "/viewModal.do", method = RequestMethod.GET)
	public String modalPop(String viewName) {
		String returnView = "/member/" + viewName;
		System.out.println(returnView);
		return returnView;
	}

	//아이디찾기 이메일 보내기
	@Override
	@RequestMapping(value = "/findId.do", method = RequestMethod.GET)
	@ResponseBody
	public String memberIdSearch(@RequestParam("find_member_name") String member_name,
			@RequestParam("find_member_email") String member_email) throws Exception {
		System.out.println("이메일 인증 요청이 들어옴");

		String code = "false";
		String result = memberService.findMemberInfo(member_name, member_email);

		if (result != null || result != "") {
			code = mailService.findIdEmail(member_email);
			System.out.println("MemberController Code return : " + code);
		}
		return code;
	}

	// 아이디 찾기 결과
	@RequestMapping(value = "/findIdResult.do", method = RequestMethod.POST)
	public ModelAndView memberIdSearchResult(@RequestParam("find_member_name") String member_name,
			@RequestParam("find_member_email") String member_email, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		String member_id = memberService.findMemberInfo(member_name, member_email);

		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("member_id", member_id);

		return mav;
	}


	// 비밀번호 찾기 이메일 보내기
	@RequestMapping(value = "/findPw.do", method = RequestMethod.GET)
	@ResponseBody
	public String memberPwSearch(@RequestParam("find_member_id") String member_id,
			@RequestParam("find_member_email") String member_email, HttpServletRequest request) throws Exception {

		String code = "false";
		String member_id_forPw = memberService.findMemberInfoForPw(member_id, member_email);
		System.out.println("MemberController 비밀번호 찾기 : " + member_email);

		if (member_id_forPw != null || member_id_forPw != "") {

			code = mailService.findPwEmail(member_email);
			System.out.println("MemberController Code return : " + code);
		}
		return code;
	}

	// 새 비밀번호 설정 페이지로 보내기
	@Override
	@RequestMapping(value = "/changePwForm.do", method = RequestMethod.POST)
	public ModelAndView changePwForm(@RequestParam("userId") String member_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		mav.addObject("member_id", member_id);
		return mav;
	}

	//새 비밀번호를 DB에 저장하기
	@RequestMapping(value = "/findPwResult.do", method = RequestMethod.POST)
	public ModelAndView changePw(@RequestParam("member_id") String member_id,
			@RequestParam("member_changePw") String member_pw, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		memberVO.setMember_id(member_id);
		memberVO.setMember_pw(member_pw);
		int result = memberService.updateChangePw(memberVO);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	
	
	//배송지 관련
	
	//배송지 목록 불러오기
	@Override
	@RequestMapping(value= "/address/myAddressList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listAllAddress(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//세션에서 로그인 한 사용자 정보 가져오기
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();	

		
		try {
			//회원 아이디로 회원의 주소 목록 검색하기
			List<AddressVO> addressList = memberService.findAllAddress(member_id);
			mav.addObject("addressList", addressList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	//배송지 수정 양식
	@Override
	@RequestMapping(value= {"/address/editAddress.do", "/address/addAddressForm.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AddressForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		String addressId = request.getParameter("address_id") ;
		try {
			//주소 아이디로 주소 정보 불러오기
			if(addressId != "" && addressId != null) {
				int address_id = Integer.parseInt(addressId);	
				addressVO = memberService.findAddressInfo(address_id);
				
				if(addressVO != null) {
					mav.addObject("addressVO", addressVO);
					mav.addObject("form_status", "edit");
				} else {
					mav.addObject("form_status", "add");
				}
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	
	//배송지 수정하기
	@Override
	@RequestMapping(value = "/address/updateAddress.do", method = RequestMethod.POST)
	public ResponseEntity<String> updateAddress(@ModelAttribute("addressVO") AddressVO addressVO, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		addressVO.setReceiver_name(request.getParameter("receiver_name"));
		addressVO.setReceiver_phone(request.getParameter("receiver_phone"));
		
		if(addressVO.getIs_basic_address() == null) {
			addressVO.setIs_basic_address("n");
		}
		String message = "";
			
		try {
			memberService.updateAddress(addressVO);
			if(addressVO.getIs_basic_address().equals("y")) {
				message += "<script>opener.parent.location.reload();window.close();</script>";
			} else {
				message += "<script>location.href='" + request.getContextPath() + "/member/address/myAddressList.do';</script>";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		resEntity = new ResponseEntity<String>(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	//배송지 추가하기
	@Override
	@RequestMapping(value = "/address/addNewAddress.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> addNewAddress(@ModelAttribute("addressVO") AddressVO addressVO, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		addressVO.setMember_id(memberVO.getMember_id());
		String message = "";
				
		try {
			memberService.addAddress(addressVO);
			message += "<script>location.href='" + request.getContextPath() + "/member/address/myAddressList.do';</script>";
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		resEntity = new ResponseEntity<String>(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	//배송지 삭제하기
	@Override
	@RequestMapping(value = "/address/removeAddress.do", method = RequestMethod.GET)
	public ResponseEntity<String> removeAddress(@RequestParam("address_id") int address_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
				
		String message = "";
				
		try {
			memberService.deleteAddress(address_id);
			message += "<script>location.href='" + request.getContextPath() + "/member/address/myAddressList.do';</script>";
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		resEntity = new ResponseEntity<String>(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	// 기본배송지 변경하기
	@Override
	@RequestMapping(value = "/address/updateBasicAddress.do", method = RequestMethod.GET)
	public ResponseEntity<String> updateBaiscAddress(@ModelAttribute("address_id") int address_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
				
		String message = "";
		try {
			memberService.updateBasicAddress(address_id);
			message += "<script>opener.parent.location.href='" + request.getContextPath() + "/cart/myCartList.do';window.close();</script>";
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		resEntity = new ResponseEntity<String>(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
}
