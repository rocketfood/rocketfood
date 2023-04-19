package com.spring.rocketfood.mypage.wish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.mypage.wish.service.WishService;
import com.spring.rocketfood.mypage.wish.vo.WishGoodsVO;
import com.spring.rocketfood.mypage.wish.vo.WishVO;

@Controller("wishController")
@RequestMapping(value = "/wish")
public class WishControllerImpl extends BaseController implements WishController {
	@Autowired
	private WishService wishService;
	@Autowired
	private WishVO wishVO;
	@Autowired
	private WishGoodsVO wishGoodsVO;
	@Autowired
	private MemberVO memberVO;
	
	//찜한 목록 추가하기
	@Override
	@RequestMapping(value = "/addGoodsInWish.do")
	public @ResponseBody String addGoodsInWish(@RequestParam("goods_id") String _goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		//회원 아이디로 위시리스트가 있는지 조회
		boolean isAlreadyExistedMemberId = wishService.findWish(member_id);
		
		
		if (isAlreadyExistedMemberId == false) {
			//회원에게 배정된 위시리스트가 없다면 만들어주기
			wishService.addMemberIdInWish(member_id);
		}
		
		int wish_id = wishService.findWishId(member_id); //member_id로 wish에서 wish_id 가져옴
		int goods_id = Integer.parseInt(_goods_id);
		wishGoodsVO.setWish_id(wish_id); //위시 아이디
		wishGoodsVO.setGoods_id(goods_id); //상품 아이디
		
		//회원의 위시리스트에 해당 상품이 존재하는지 확인하기
		boolean isAlreadyExistedWishGoods = wishService.findWishGoods(wishGoodsVO);
		
		String message = "";
		if (isAlreadyExistedWishGoods == true) { //찜 목록에 이미 존재하는 상품이면
			message = "wish_add_failed"; 
			
		} else { //찜 목록에 존재하지 않는 상품이면
			wishService.addGoodsInWishGoods(wishGoodsVO);
			message = "wish_add_success";
		}
		return message;
	}
	
	//찜한 목록 보기 추가 필요
	
	
	//찜한 목록에서 삭제
	
	//찜한 목록에서 장바구니 담기
	
}
