package com.spring.rocketfood.goods.board.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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

import com.spring.rocketfood.goods.board.service.GoodsBoardService;
import com.spring.rocketfood.goods.board.vo.GoodsQnaVO;
import com.spring.rocketfood.goods.board.vo.ReviewHelpVO;
import com.spring.rocketfood.member.vo.MemberVO;

@Controller("goodsBoardController")
@RequestMapping(value="/goods/board")
public class GoodsBoardControllerImpl implements GoodsBoardController {
	@Autowired 
	private GoodsBoardService goodsBoardService;
	@Autowired
	private MemberVO memberVO;
	
	//상품 문의 리스트 보여주기
	@Override
	@RequestMapping(value= "/listQnaArticle.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listQnaArticle(@RequestParam("goods_id") int goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//goods_id로 해당 상품에 관련된 문의글만 불러오기
		List<GoodsQnaVO> bodsList = goodsBoardService.listGoodsQna(goods_id);
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("bodsList", bodsList);
		return mav;
	}
		
	//상품 문의하기
	@Override
	@RequestMapping(value="/addNewqQna.do" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewQnaArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Map<String,Object> bodMap = new HashMap<String, Object>();
		
		//상품 문의 폼에서 받은 값들을 맵에 저장
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()){
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			bodMap.put(name,value);
		}
		
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		
		//세션에서 로그인한 회원의 정보를 가져와 맵에 저장
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		bodMap.put("member_id", member_id);
		
		
		// ResponseEntity로 실행 결과를 리턴해서 해당 페이지에서 스크립트 띄우기
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String message;
	
		try {
			goodsBoardService.addNewGoodsQna(bodMap);
			message = "<script>";
			message += " location.href='"+request.getContextPath()+"/goods/goodsDetail.do?goods_id="+goods_id+"#goods_qna';";
			message +=" qna_result('등록이 완료됐습니다. 빠르게 답변 드리겠습니다.');";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    
		} catch(Exception e) {			
			message = " <script>";
			message += " location.reload();";
			message +=" qna_result('오류가 발생했습니다. 다시 확인해 주세요.')";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	//상품 문의 수정하기
	@ResponseBody
	@Override
	public ResponseEntity editQnaArticle(@RequestParam("article_id") int article_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String,Object> bodMap = new HashMap<String, Object>();
		
		//세션에서 로그인한 회원의 정보를 가져와 맵에 저장
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		String id = memberVO.getMember_id();
		bodMap.put("member_id", id);
		bodMap.put("board_name", "goods_qna");
				
		// ResponseEntity로 실행 결과를 리턴해서 해당 페이지에서 스크립트 띄우기
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
		 	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return resEnt;
	}
	
	
	//상품 문의에 답변달기 
	@RequestMapping(value="/addReaplyToQna.do" ,method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	@Override
	public ResponseEntity addReplyToQna(@RequestParam("article_id") int article_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String,Object> bodMap = new HashMap<String, Object>();
		
		//상품 문의 폼에서 받은 값들을 맵에 저장
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()){
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			bodMap.put(name,value);
		}
		
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		
		// ResponseEntity로 실행 결과를 리턴해서 해당 페이지에서 스크립트 띄우기
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String message;
	
		try {
			//답변 넣어서 저장
			goodsBoardService.addReply(bodMap);
			message = "<script>";
			message += " location.href='"+request.getContextPath()+"/goods/goodsDetail.do?goods_id="+goods_id+"#goods_qna';";
			message +=" qna_result('등록이 완료됐습니다. 빠르게 답변 드리겠습니다.');";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    
		} catch(Exception e) {			
			message = " <script>";
			message += " location.reload();";
			message +=" qna_result('오류가 발생했습니다. 다시 확인해 주세요.')";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	//상품 후기 등록
	@Override
	@RequestMapping(value = "/addNewReview.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewReviewArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> reviewMap = new HashMap<String, Object>();
			
		//상품 후기 폼에서 받은 값들을 맵에 저장
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			reviewMap.put(name, value);
		}
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
			
		//세션에서 로그인한 회원의 정보를 가져와 맵에 저장
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		String id = memberVO.getMember_id();
		reviewMap.put("member_id", id);
		reviewMap.put("board_name", "goods_review");
		
			
		// ResponseEntity로 실행 결과를 리턴해서 해당 페이지에서 스크립트 띄우기
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String message;
		
		try {
			goodsBoardService.addNewGoodsReview(reviewMap);
			message = "<script>";
			message += "location.href='"+request.getContextPath()+"/goods/goodsDetail.do?goods_id="+goods_id+"#goods_review';";
			message +="reviewResult('등록이 완료됐습니다. 빠르게 답변 드리겠습니다.');";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch(Exception e) {			
			message = " <script>";
			message += " location.reload();";
			message +=" reviewResult('오류가 발생했습니다. 다시 확인해 주세요.')";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;	
	}
	
	// 도움돼요 수정하기
	@RequestMapping(value="/updateHelpCount.do" , method=RequestMethod.POST)
	public @ResponseBody String modifyCartQty(@ModelAttribute("reviewHelpVO") ReviewHelpVO reviewHelpVO, HttpServletRequest request, HttpServletResponse response)  throws Exception {

		System.out.println("help_y" +reviewHelpVO.getHelp_y());
		
		try {
			Boolean result = goodsBoardService.updateMyHelp(reviewHelpVO);
			if(result == true) return "modify_success";
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "modify_failed";
	}
}
