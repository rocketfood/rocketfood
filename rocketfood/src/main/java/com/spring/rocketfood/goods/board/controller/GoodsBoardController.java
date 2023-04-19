package com.spring.rocketfood.goods.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface GoodsBoardController {
	public ModelAndView listQnaArticle(@RequestParam("goods_id") int goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewQnaArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity editQnaArticle(@RequestParam("article_id") int article_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addReplyToQna(@RequestParam("article_id") int article_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewReviewArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
