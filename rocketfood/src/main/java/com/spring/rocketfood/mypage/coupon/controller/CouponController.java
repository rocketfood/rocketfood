package com.spring.rocketfood.mypage.coupon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;

public interface CouponController {
	public ResponseEntity<String> findMyusableCoupons(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> UseCoupon(@ModelAttribute("myCouponVO") MyCouponVO myCouponVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
