package com.spring.rocketfood.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.rocketfood.admin.category.service.CategoryService;
import com.spring.rocketfood.admin.category.vo.CategoryVO;
import com.spring.rocketfood.chat.service.ChatService;
import com.spring.rocketfood.member.vo.MemberVO;

public class ViewNameInterceptor extends  HandlerInterceptorAdapter{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ChatService chatService;
	
	   @Override
	   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		   try {
			String viewName = getViewName(request);
			request.setAttribute("viewName", viewName);
			HttpSession session = request.getSession();
			Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
			if(isLogOn == null) {
				session.setAttribute("isLogOn", false);
			}
			//카테고리 메뉴 네비를 모든 페이지에서 나오게 하기 위해 interceptor에서 처리
			List<CategoryVO> categoryList = categoryService.selectAllCategoryList();
			
			// 임의의 숫자 999를 설정하여 모든 카테고리를 불러오도록 함
			Map<String, List<CategoryVO>> categoryMap = categoryService.selectCategoryList(999);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("categoryMap", categoryMap);
			
			
			//관리자로 로그인할 시, 새로 들어온 채팅 수를 띄워준다.
			MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");

			if(memberVO != null && memberVO.getMember_id().equals("admin")) {
				int waitingChat = chatService.findWaitingChatCount();
				request.setAttribute("waitingChat", waitingChat);
			}
			return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		   return true;
	   }

	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response,
	                           Object handler, ModelAndView modelAndView) throws Exception {
	   }

	   @Override
	   public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
	                                    Object handler, Exception ex)    throws  Exception {
	   }
	   
	   private String getViewName(HttpServletRequest request) throws Exception {
			String contextPath = request.getContextPath();
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
			}

			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}

			int end;
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}

			String fileName = uri.substring(begin, end);
			if (fileName.indexOf(".") != -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			if (fileName.lastIndexOf("/") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/",1), fileName.length());
			}
			return fileName;
		}
	}
