package com.spring.rocketfood.mypage.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.member.vo.MemberVO;

public interface MyPageOrderController {
	public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message,HttpServletRequest request, HttpServletResponse response)  throws Exception ;
	public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
