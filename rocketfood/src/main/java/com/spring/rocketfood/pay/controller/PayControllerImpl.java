package com.spring.rocketfood.pay.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.rocketfood.order.service.OrderService;
import com.spring.rocketfood.order.vo.OrderVO;
import com.spring.rocketfood.pay.service.KakaoPayService;
import com.spring.rocketfood.pay.service.PayService;
import com.spring.rocketfood.pay.vo.KakaoPayApprovalVO;
import com.spring.rocketfood.pay.vo.PaymentVO;

@Controller("payController")
@RequestMapping("/pay")
public class PayControllerImpl implements PayContoller {
	@Autowired
	private KakaoPayService kakaopay;
	@Autowired
	private KakaoPayApprovalVO kakaoPayApprovalVO;
	
	@Autowired 
	private PaymentVO paymentVO;
	@Autowired 
	private PayService payService;
	
	@Autowired
	OrderService orderService;
	
	//카카오페이 결제하기
	@RequestMapping(value= "/kakaoPay.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public String kakaopay(HttpServletRequest request, HttpServletResponse response) {
		
		String item_name = request.getParameter("item_name");
		String quantity = request.getParameter("quantity");
		String total_amount = request.getParameter("total_amount");
		String tax_free_amount = "0"; // 현 쇼핑몰 내에서 비과세 항목 없을 예정이라 0으로 설정
		
		return "redirect:"+kakaopay.kakaoPayReady(item_name, quantity, total_amount, tax_free_amount);
		 
	}
	
	//카카오페이 결제 성공 후 주문 완료 페이지로 이동
	@RequestMapping(value="/kakaoPaySuccess.do", method= {RequestMethod.GET})	
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		
		//카카오페이에 결제 승인 요청하기
		KakaoPayApprovalVO kakaoApprove = kakaopay.kakaoPayInfo(pg_token);
		
		//orders 테이블에 정보 저장
		//orderService.addNewOrder(null);
		
		//모델로 결과 값을 함께 페이지로 쏴주기
		model.addAttribute("info", kakaoApprove);		
		return "redirect:/order/orderResult.do";
	}
	
	//카카오페이 결제 취소시 실행 url
    @RequestMapping("/kakaoPayCancel.do")
 	public String payCancel() {
 		return "redirect:/myCartList.do";
 	}
    
    //카카오 페 결제 실패시 실행 url    	
    @RequestMapping("/kakaoPayFail.do")
 	public String payFail() {
 		return "redirect:/carts";
 	}
	
	@RequestMapping(value="/success.do", method= {RequestMethod.GET})
	public String success(@RequestParam("orderId") String orderId, 
			@RequestParam("paymentKey") String paymentKey, @RequestParam("amount") int amount, Model model) {
		PaymentVO paymentApprove = payService.paymentInfo(orderId, paymentKey, amount);
		
		model.addAttribute("info", paymentApprove);
		return "paymentSuccess";
	}


//	public  ResponseEntity<PaymentVO> success() {
//	RestTemplate restTemplate = new RestTemplate();
//
//	HttpHeaders headers = new HttpHeaders();
//	headers.set("Authorization", "Basic dGVzdF9za19XZDQ2cW9wT0I4OXo1T3ZscTFkM1ptTTc1eTB2Og==");
//	headers.set("Content-Type", "application/json");
//
//	String requestBody = "{\"paymentKey\":\"Ol1oDEOhzGUIAOauovcMQ\",\"amount\":15000,\"orderId\":\"-vIL3ggc30Q8AkJ8TUAwf\"}";
//	HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
//
//	ResponseEntity<PaymentVO> response = restTemplate.postForEntity("https://api.tosspayments.com/v1/payments/confirm", entity, PaymentVO.class);
//	System.out.println("helllllllllllllllllllllllllllllllllllllllllllllllllll");
//	return response;
//	
//}

	
//	@RequestMapping("/success.do")
//	@ResponseBody
//	public HttpRequest success() {
//	HttpRequest request = HttpRequest.newBuilder()
//		    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
//		    .header("Authorization", "Basic dGVzdF9za19XZDQ2cW9wT0I4OXo1T3ZscTFkM1ptTTc1eTB2Og==")
//		    .header("Content-Type", "application/json")
//		    .method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"hTKewwfuV7mUsf0KBVksa\",\"amount\":15000,\"orderId\":\"FO2JVL4RYv388Rvtw50d0\"}"))
//		    .build();
//	try {
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return request;
//	
//	
//	}
	
}