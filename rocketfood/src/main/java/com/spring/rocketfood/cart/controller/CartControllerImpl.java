package com.spring.rocketfood.cart.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.cart.service.CartService;
import com.spring.rocketfood.cart.vo.CartGoodsVO;
import com.spring.rocketfood.cart.vo.CartVO;
import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.vo.MemberVO;

@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl extends BaseController implements CartController{
	@Autowired
	private CartService cartService;
	@Autowired
	private CartVO cartVO;
	@Autowired
	private CartGoodsVO cartGoodsVO;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	//장바구니 불러오기
	@RequestMapping(value="/myCartList.do" ,method = RequestMethod.GET)
	public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//세션에서 로그인한 사용자 아이디를 받아와서 해당 아이디로 카트 불러오기
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");		
		String member_id = memberVO.getMember_id();
		
		if(member_id != null && member_id != "") {
			CartVO cartVO = new CartVO();
			cartVO.setMember_id(member_id);
			
			//카트 리스트를 맵으로 받아와서 세션에 다시 저장(해당 페이지 뿐만 아니라 헤더에서 장바구니 상품 카운트 표시 가능)
			Map<String, Object> cartMap = cartService.myCartList(cartVO);
			session.setAttribute("cartMap", cartMap);
			//상품 delete 및 update용 cart_id도 같이 보내줌 
			mav.addObject("myCart_id", (Integer)cartMap.get("myCart_id"));
		}
		
		//기본 배송지 가져와서 셋팅
		mav.addObject("basicAddress", memberService.findBasicAddress(member_id));
		
		return mav;
	}
	
	//장바구니에 상품 추가하기
	@RequestMapping(value="/addGoodsInCart.do", method=RequestMethod.POST, produces="text/html; charset=utf-8")
	public  @ResponseBody String addGoodsInCart(@RequestParam("goods_id") int goods_id, @RequestParam("cart_goods_qty") int cart_goods_qty,
			                    HttpServletRequest request, HttpServletResponse response)  throws Exception{
		//세션에서 로그인한 사용자 아이디를 받아와서 해당 아이디로 카트 불러오기
		HttpSession session= request.getSession();
		memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();			
		
		//해당 아이디로 카트 정보 세팅
		cartVO.setCart_id(cartService.findCartId(member_id));
		cartVO.setMember_id(member_id);
		cartGoodsVO.setGoods_id(goods_id);
		cartGoodsVO.setCount(cart_goods_qty);
		cartGoodsVO.setCart_id(cartService.findCartId(member_id));
		Map<String, Object> cartGoodsMap = new HashMap<String, Object>();
		cartGoodsMap.put("cart_id", cartVO.getCart_id());
		cartGoodsMap.put("goods_id", cartGoodsVO.getGoods_id());
		cartGoodsMap.put("member_id", cartVO.getMember_id());
		
		//카트에 해당 상품이 있는지 중복 검사
		boolean isAlreadyExisted = cartService.findCartGoods(cartGoodsMap);
		System.out.println("isAreadyExisted : " + isAlreadyExisted);
		
		String message = "";
		//카트에 이미 존재하는 상품이라면
		if(isAlreadyExisted == true){
			 message = "cart_add_failed";
		//카트에 존재하지 않는 상품이라면
		} else{
			cartService.addGoodsInCart(cartGoodsVO);
			message = "cart_add_success";

		}
		return message;
	}
	
	//장바구니 수량 변경
	@RequestMapping(value="/modifyCartQty.do" , method=RequestMethod.POST)
	public @ResponseBody String modifyCartQty(@RequestParam("cart_id") int cart_id, @RequestParam("goods_id") int goods_id, @RequestParam("cart_goods_qty") int cart_goods_qty,
			                                    HttpServletRequest request, HttpServletResponse response)  throws Exception {
		//세션에서 로그인한 사용자 아이디를 받아와서 해당 아이디로 카트 불러오기
		HttpSession session = request.getSession();
		memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		
		//해당 아이디로 조회한 cart_id와 매개변수로 받은 cart_id가 같다면 수정할 카트 정보 세팅
		if(cart_id == cartService.findCartId(member_id)) {
			cartGoodsVO.setCart_id(cart_id); //카트 아이디
			cartGoodsVO.setGoods_id(goods_id); // 상품 아이디
			cartGoodsVO.setCount(cart_goods_qty); // 상품 수량			
		} else {
			return "modify_failed";
		}
		
		//카트 상품 테이블에서 상품 변경하는 것 추가하기
		try {
			boolean result = cartService.modifyCartQty(cartGoodsVO);
			if(result == true) return "modify_success";
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "modify_failed";
	}
	
	//장바구니 상품 삭제
	@RequestMapping(value="/removeCartGoods.do" ,method = RequestMethod.POST)
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id, @RequestParam("goods_id") int goods_id, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		ModelAndView mav = new ModelAndView();
		
		//전달 받은 cart_id를 가진 member와 로그인한 member_id의 정보가 일치하는 지 확인
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		
		cartVO = cartService.findCartInfo(cart_id);
		
		//조회한 결과가 존재하고 로그인한 사용자의 아이디와 서로 일치한다면
		try {			
			if(cartVO != null) {
				if(cartVO.getMember_id().equals(member_id)) {
					//장바구니에서 삭제 후 장바구니 페이지를 새로고침
					Map<String, Object> cartGoodsMap = new HashMap<String, Object>();
					cartGoodsMap.put("cart_id", cart_id);
					cartGoodsMap.put("goods_id", goods_id);
					cartService.removeCartGoods(cartGoodsMap);
				}
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
						
		mav.setViewName("redirect:/cart/myCartList.do");
		return mav;
	}
}
