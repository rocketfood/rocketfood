package com.spring.rocketfood.pay.service;

import com.spring.rocketfood.pay.vo.PaymentVO;

public interface PayService {
	public PaymentVO paymentInfo(String orderId, String paymentKey, int amount);	
}
