package com.spring.rocketfood.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.goods.vo.GoodsVO;
import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.GradeVO;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.member.vo.PointVO;
import com.spring.rocketfood.mypage.coupon.service.CouponService;
import com.spring.rocketfood.mypage.coupon.vo.CouponVO;
import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;
import com.spring.rocketfood.order.service.OrderService;
import com.spring.rocketfood.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl extends BaseController implements OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private OrderVO orderVO;
	@Autowired
	private AddressVO addressVO;
	@Autowired
	private GradeVO gradeVO;
	@Autowired
	private PointVO pointVO;
	@Autowired
	private CouponVO couponVO;
	@Autowired
	private MyCouponVO myCouponVO;
	
	
	//장바구니 상품 주문
	@RequestMapping(value="/orderCartGoods.do" ,method = RequestMethod.POST)
	public ModelAndView orderCartGoods(@RequestParam("checked_goods")  String[] cart_goods_qty, @RequestParam("final_goods_price") int final_goods_price, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
				
		//세션에서 장바구니 정보와 회원 정보 가져오기
		HttpSession session=request.getSession();
		Map cartMap = (Map) session.getAttribute("cartMap");
		List<GoodsVO> myGoodsList = (List<GoodsVO>) cartMap.get("myGoodsList");
		int myCart_id = (Integer)cartMap.get("myCart_id");
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		Map<String, Object> myOrderMap = new HashMap<String, Object>();
		List<OrderVO> myOrderInfoList = new ArrayList<OrderVO>();
		List<GoodsVO> myOrderGoodsList = new ArrayList<GoodsVO>();
		
		for(int i=0; i<cart_goods_qty.length; i++){
			//request에서 받아온 파라미터를 원하는 정보로 쪼개서 배열로 담기
			int order_goods_id = Integer.parseInt(cart_goods_qty[i].substring(0, cart_goods_qty[i].indexOf(":"))); // 세미콜론 기준으로 앞부분
			int order_goods_qty = Integer.parseInt(cart_goods_qty[i].substring(cart_goods_qty[i].indexOf(":")+1));  // 세미콜론 기준으로 뒷부분
			
			for(int j = 0; j< myGoodsList.size(); j++) {
				//세선에서 가져온 장바구니 상품 리스트를 가져오기
				GoodsVO goodsVO = myGoodsList.get(j);
				int goods_id = goodsVO.getGoods_id();
				
				//장바구니 상품 리스트의 goods_id와 폼에서 보낸 goods_id가 일치한다면
				if(goods_id == order_goods_id) {
					goodsVO.setGoods_id(goods_id);
					goodsVO.setGoods_name(goodsVO.getGoods_name());
					goodsVO.setThumbnail_url(goodsVO.getThumbnail_url());
					
					//order_id 세팅 해줘야함
					orderVO.setCart_id(myCart_id);
					orderVO.setMember_id(memberVO.getMember_id());
					orderVO.setOrder_status("주문 중");
					orderVO.setFinal_total_price(final_goods_price);
					orderVO.setThumbnail_url(goodsVO.getThumbnail_url());
					orderVO.setOrder_qty(order_goods_qty);
					
					myOrderInfoList.add(orderVO);
					myOrderGoodsList.add(goodsVO);
					break;
				}
			}
		}
		
		//주문상품 정보, 주문자 정보, 배송정보 셋팅
		myOrderMap.put("myOrderInfoList", myOrderInfoList);
		myOrderMap.put("myOrderGoodsList", myOrderGoodsList);
		myOrderMap.put("final_goods_price", final_goods_price);
		myOrderMap.put("myCart_id", myCart_id);
		
		memberVO = memberService.findMemberById(memberVO.getMember_id());
		addressVO = memberService.findBasicAddress(memberVO.getMember_id());
		
		myOrderMap.put("myOrderMap", myOrderMap);
		myOrderMap.put("orderer", memberVO);
		myOrderMap.put("Address", addressVO);
		
		//쿠폰, 포인트 정보 셋팅
		int usablePoint = memberService.findMemberPoint(memberVO.getMember_id());
		List<CouponVO> usableCouponList = couponService.listUsableCoupon(memberVO.getMember_id());
		myOrderMap.put("usablePoint", usablePoint);
		myOrderMap.put("usableCouponList", usableCouponList);
		
		//주문서로 넘겨주기
		mav.addObject("myOrderMap", myOrderMap);	
		session.setAttribute("myOrderGoodsList", myOrderGoodsList);
		return mav;
	}	
	
	//주문하기
	@RequestMapping(value="/payToOrderGoods.do" ,method = RequestMethod.POST)
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//세션에서 회원정보 받아오기
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		List<OrderVO> myOrderList = new ArrayList<OrderVO>();
		
		//주문 정보 셋팅
		orderVO.setOrder_id(Integer.parseInt(receiverMap.get("order_id")));
		orderVO.setMember_id(member_id);
		orderVO.setCart_id(Integer.parseInt(receiverMap.get("cart_id")));
		orderVO.setOrderer_name(receiverMap.get("orderer_name"));
		orderVO.setReceiver_name(receiverMap.get("receiver_name"));
		orderVO.setAddress_id(receiverMap.get("address_id"));
		orderVO.setOrder_qty(Integer.parseInt(receiverMap.get("order_qty")));
		myOrderList.add(orderVO); 
		
		try {
			//새 주문 정보 저장
			orderService.addNewOrder(myOrderList);
		    
		    //쿠폰을 사용했다면
			String coupon_use = receiverMap.get("coupon_use");
			if(coupon_use != null && coupon_use.equals("y")) {
				myCouponVO.setCoupon_id(Integer.parseInt(receiverMap.get("coupon_id")));
				myCouponVO.setMember_id(member_id);
				myCouponVO.setCoupon_use("y");
				couponService.updateMyCoupon(myCouponVO);
			}
			
			// 결제가 완료되면 자동으로 포인트 적립율을 계산하여 해당 회원에게 포인트를 추r가
		    // 회원 아이디로 해당 회원의 등급을 조회하여 적립율을 가져온다. 최종 결제 금액 * 적립율 계산하여 포인트 테이블에 저장하고 결과 페이지에 추가된 적립금을 표시해준다.		    
		    gradeVO = memberService.findMemberGrade(member_id); //회원 등급 조회
		    double accrual_rate = gradeVO.getAccrual_rate(); // 등급에 따른 적립율 가져오기
		    int new_point = (int) Math.round(Integer.parseInt(receiverMap.get("final_goods_price")) * accrual_rate); // 적립 포인트 계산
		    
		    //적립 포인트 저장하기
		    pointVO.setCurrent_point(new_point);
		    pointVO.setMember_id(memberVO.getMember_id());
		    pointVO.setPoint_detail(createPointDetail(pointVO, "save", orderVO.getOrder_id(), (int)accrual_rate*100));
		    memberService.updateMemberPoint(pointVO);
		    
		    //사용 포인트 저장하기
		    if(receiverMap.get("point_usage") != null && !receiverMap.get("point_usage").equals("")) {
		    	pointVO.setCurrent_point(Integer.parseInt(receiverMap.get("point_usage")));
		    	pointVO.setPoint_detail(createPointDetail(pointVO, "use", orderVO.getOrder_id(), (int)accrual_rate*100));
		    	pointVO.setPoint_expiration(null);
		    	memberService.updateMemberPoint(pointVO);
		    }
		    
		    mav.addObject("final_goods_price", receiverMap.get("final_goods_price"));
			mav.addObject("new_point", new_point);
			
			//주문이 완료됐다면 해당 상품을 장바구니에서 삭제
			List<GoodsVO> myOrderGoodsList = (List<GoodsVO>) session.getAttribute("myOrderGoodsList");
			List<String> orderCartGoods = new ArrayList<String>();
			for(int i=0; i<myOrderGoodsList.size(); i++) {
				String orderGoodsInfo = receiverMap.get("cart_id") +":"+ myOrderGoodsList.get(i).getGoods_id();
				orderCartGoods.add(orderGoodsInfo);
			} 
			orderService.deleteGoodsFromCart(orderCartGoods);
		} catch(Exception e) {
			e.printStackTrace();
		}
	    
		return mav;
	}
	
	
	// 포인트 적립 내용 생성
	private String createPointDetail(PointVO pointVO, String type, int order_id, int accrual_rate ) {
		String point_detail = "";
		
		//포인트 적립인지, 사용인지에 따라서 적립 내용 다르게 생성
		if(type.equals("use")) { //포인트 사용이라면,			
			point_detail = "[사용] 주문("+order_id+") 결제 시 사용";
		} else { //포인트 적립이라면
			point_detail = "[적립] 주문("+order_id+") " + accrual_rate + "% 적립";
		}
		return point_detail;
	}
}
