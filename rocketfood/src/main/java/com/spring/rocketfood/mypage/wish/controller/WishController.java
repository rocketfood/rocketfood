package com.spring.rocketfood.mypage.wish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface WishController {
	public @ResponseBody String addGoodsInWish(@RequestParam("goods_id") String _goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
