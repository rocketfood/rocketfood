package com.spring.rocketfood.admin.category.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface CategoryController {
	public ModelAndView categoryList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewCategory(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyCategory( @RequestParam("category_id") int category_id,
            @RequestParam("parent_id") int parent_id, @RequestParam("category_name") String category_name, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public void removeCategory(@RequestParam("category_id") int category_id, HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
