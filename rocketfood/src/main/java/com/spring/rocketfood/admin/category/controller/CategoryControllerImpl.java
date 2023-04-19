package com.spring.rocketfood.admin.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.admin.category.service.CategoryService;
import com.spring.rocketfood.admin.category.vo.CategoryVO;
import com.spring.rocketfood.common.base.BaseController;

@Controller("adminCategoryController")
@RequestMapping(value="/admin/category")
public class CategoryControllerImpl implements CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	@RequestMapping(value="/categoryList.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView categoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		List<CategoryVO> categoryList = categoryService.selectAllCategoryList();
		mav.addObject("categoryList", categoryList);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/addNewCategory.do" ,method={RequestMethod.POST})
	public ResponseEntity addNewCategory(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception {
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value="/modifyCategory.do" ,method={RequestMethod.POST})
	public ResponseEntity modifyCategory( @RequestParam("category_id") int category_id,
            @RequestParam("parent_id") int parent_id, @RequestParam("category_name") String category_name, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value="/removeCategory.do" ,method={RequestMethod.POST})
	public void removeCategory(@RequestParam("category_id") int category_id, HttpServletRequest request, HttpServletResponse response)  throws Exception {
		try{
			categoryService.deleteCategory(category_id);
			System.out.println(category_id + "�����Ϸ�");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
