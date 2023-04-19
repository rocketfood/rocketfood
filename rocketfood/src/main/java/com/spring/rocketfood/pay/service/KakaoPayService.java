package com.spring.rocketfood.pay.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.spring.rocketfood.pay.vo.KakaoPayApprovalVO;
import com.spring.rocketfood.pay.vo.KakaoPayReadyVO;

@Service("kakaoPayService")
public class KakaoPayService {
	 
    private static final String HOST = "https://kapi.kakao.com";
    
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    
    //카카오 결제 요청
    public String kakaoPayReady(String item_name, String quantity, String total_amount, String tax_free_amount ) {
    	        
        // 카카오가 요구한 결제요청 request값을 담는다.
    	
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME"); 
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany"); //가맹점 회원 아이디
        params.add("item_name", item_name); // 상품명
        params.add("quantity", quantity); //상품 수량
        params.add("total_amount", total_amount); //총 결제액 - 임시로 넣어놓은 값
        params.add("tax_free_amount", tax_free_amount ); //비과세액
        params.add("approval_url", "http://localhost:8080/rocketfood/pay/kakaoPaySuccess.do"); // 결제승인시 넘어갈 url
        params.add("cancel_url", "http://localhost:8080/pay/kakaoPayCancel.do"); // 결제취소시 넘어갈 url
        params.add("fail_url", "http://localhost:8080/pay/kakaoPayFail.do"); // 결제 실패시 넘어갈 url
        
        
        HttpEntity body = new HttpEntity(params, this.getHeaders());
        
        // 외부url요청 통로 열기
     	RestTemplate restTemplate = new RestTemplate();
     	
     	// template으로 값을 보내고 받아온 ReadyResponse값 readyResponse에 저장       
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);     
            System.out.println("kakaoPayReady 실행");
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return "/pay";
        
    }
    
    //카카오 결제 승인 요청
    public KakaoPayApprovalVO kakaoPayInfo(@RequestParam("pg_token") String pg_token) { 
        RestTemplate restTemplate = new RestTemplate();
 	
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK 093c7dd61a40f77a0e80b56066ff1727");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", "2100");
        
        
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);            
            System.out.println("kakaoPayInfo 실행");
            return kakaoPayApprovalVO;
        
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    // header() 셋팅
 	private HttpHeaders getHeaders() {
 		HttpHeaders headers = new HttpHeaders();
 		headers.add("Authorization", "KakaoAK 093c7dd61a40f77a0e80b56066ff1727");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 		
 		return headers;
 	}
}
