package com.spring.rocketfood.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface PayContoller {
	public String kakaopay(HttpServletRequest request, HttpServletResponse response) ;
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model);
	public String success(@RequestParam("orderId") String orderId, @RequestParam("paymentKey") String paymentKey, @RequestParam("amount") int amount, Model model);
	
}