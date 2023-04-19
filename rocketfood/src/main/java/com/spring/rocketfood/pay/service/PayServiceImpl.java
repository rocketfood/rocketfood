package com.spring.rocketfood.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.rocketfood.pay.vo.PaymentVO;

import net.sf.json.JSONObject;

@Service("payService")
public class PayServiceImpl implements PayService{

	@Autowired
	private PaymentVO paymentVO;

	public PaymentVO paymentInfo(String orderId, String paymentKey, int amount) {
		RestTemplate rest= new RestTemplate();
		 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic dGVzdF9za19XZDQ2cW9wT0I4OXo1T3ZscTFkM1ptTTc1eTB2Og==");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", "application/json");
        
        String url = "https://api.tosspayments.com/v1/payments/confirm";
        JSONObject param = new JSONObject();
       
        param.put("orderId", "FO2JVL4RYv388Rvtw50d0");
        param.put("amount", "15000");
        
        
        paymentVO = rest.postForEntity(
        		url+"/"+paymentKey, new HttpEntity(param, headers),
        		PaymentVO.class).getBody();
        
        return paymentVO;

	
}
}