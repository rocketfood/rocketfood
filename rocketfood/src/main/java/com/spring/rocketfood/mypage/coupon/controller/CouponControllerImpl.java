package com.spring.rocketfood.mypage.coupon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring.rocketfood.mypage.coupon.service.CouponService;
import com.spring.rocketfood.mypage.coupon.vo.CouponVO;
import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;

@Controller("couponController")
public class CouponControllerImpl implements CouponController {
	@Autowired
	CouponService couponService;
	
	@Autowired
	CouponVO couponVO;
	
	@Autowired
	MyCouponVO myCouponVO;
	
	//사용 가능한 쿠폰 불러오기
	public ResponseEntity<String> findMyusableCoupons(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		
		return resEntity;
	}
	
	//쿠폰 사용하기
	public ResponseEntity<String> UseCoupon(@ModelAttribute("myCouponVO") MyCouponVO myCouponVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity<String> resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		
		return resEntity;
		
	}
}
