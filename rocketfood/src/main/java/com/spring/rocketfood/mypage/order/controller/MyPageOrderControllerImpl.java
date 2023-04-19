package com.spring.rocketfood.mypage.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.mypage.order.service.MyPageOrderService;
import com.spring.rocketfood.order.vo.OrderVO;

@Controller("myPageOrderController")
@RequestMapping(value="/mypage")
public class MyPageOrderControllerImpl extends BaseController  implements MyPageOrderController{
	@Autowired
	private MyPageOrderService myPageService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/myPageMain.do" ,method = RequestMethod.GET)
	public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message,
			   HttpServletRequest request, HttpServletResponse response)  throws Exception {
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "my_page"); //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱듸옙 占쌨댐옙占쏙옙 占쏙옙占쏙옙占싼댐옙.
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
		
		List<OrderVO> myOrderList=myPageService.listMyOrderGoods(member_id);
		
		mav.addObject("message", message);
		mav.addObject("myOrderList", myOrderList);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/myOrderDetail.do" ,method = RequestMethod.GET)
	public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		MemberVO orderer=(MemberVO)session.getAttribute("memberInfo");
		
		List<OrderVO> myOrderList=myPageService.findMyOrderInfo(order_id);
		mav.addObject("orderer", orderer);
		mav.addObject("myOrderList",myOrderList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/listMyOrderHistory.do" ,method = RequestMethod.GET)
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,
			                               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String beginDate=null,endDate=null;
		
		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);
		dateMap.put("member_id", member_id);
		List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap);
		
		String beginDate1[]=beginDate.split("-"); //占싯삼옙占쏙옙占쌘몌옙 占쏙옙,占쏙옙,占싹뤄옙 占싻몌옙占쌔쇽옙 화占썽에 占쏙옙占쏙옙占쌌니댐옙.
		String endDate1[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate1[0]);
		mav.addObject("endMonth",endDate1[1]);
		mav.addObject("endDay",endDate1[2]);
		mav.addObject("myOrderHistList", myOrderHistList);
		return mav;
	}	
	
	@Override
	@RequestMapping(value="/cancelMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		myPageService.cancelOrder(order_id);
		mav.addObject("message", "cancel_order");
		mav.setViewName("redirect:/mypage/myPageMain.do");
		return mav;
	}
	
}
