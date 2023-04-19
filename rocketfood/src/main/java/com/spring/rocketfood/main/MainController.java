package com.spring.rocketfood.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.admin.category.service.CategoryService;
import com.spring.rocketfood.admin.category.vo.CategoryVO;
import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.goods.service.GoodsService;
import com.spring.rocketfood.goods.vo.GoodsVO;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	

	@RequestMapping(value= {"/main/main.do"} ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session = request.getSession();
		session.setAttribute("side_menu", "user");
		
		if(session.getAttribute("memberInfo") == null) {
			session.setAttribute("isLogOn", false);
		}
		
		Map<String,List<GoodsVO>> goodsMap = goodsService.listGoodsByStatus();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}
	
	@RequestMapping(value= {"/admin/main.do"} ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView adminMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		return mav;
	}
	
	@RequestMapping(value= {"/error.do"} , method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView error (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		String LoginFailMessage = "접근 권한이 없습니다.";
		String message = "";

		message += " swal('" + LoginFailMessage + "');";

		mav.addObject("message",message);
		return mav;
	}
}
