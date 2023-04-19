package com.spring.rocketfood.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.order.vo.OrderVO;

public interface OrderController {
	public ModelAndView orderCartGoods(@RequestParam("cart_goods_qty") String[] cart_goods_qty, @RequestParam("final_goods_price") int final_goods_price, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> orderMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
