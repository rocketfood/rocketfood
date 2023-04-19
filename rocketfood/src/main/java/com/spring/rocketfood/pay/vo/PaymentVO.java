package com.spring.rocketfood.pay.vo;

import org.springframework.stereotype.Component;

@Component("paymentVO")
public class PaymentVO {
	String mId;
	String version;
	String paymentKey;
	String orderId;
	String orderName;
	String currency;
	String method;
	String totalAmount;
	String balanceAmount;
	String suppliedAmount;
	String vat;
	String status;
	String requestedAt;
	String approvedAt;
	String useEscrow;
	String cultureExpense;
	PaymentVO card;
	PaymentVO cancels;
	String type;

	public PaymentVO() {
		
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getSuppliedAmount() {
		return suppliedAmount;
	}

	public void setSuppliedAmount(String suppliedAmount) {
		this.suppliedAmount = suppliedAmount;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(String requestedAt) {
		this.requestedAt = requestedAt;
	}

	public String getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(String approvedAt) {
		this.approvedAt = approvedAt;
	}

	public String getUseEscrow() {
		return useEscrow;
	}

	public void setUseEscrow(String useEscrow) {
		this.useEscrow = useEscrow;
	}

	public String getCultureExpense() {
		return cultureExpense;
	}

	public void setCultureExpense(String cultureExpense) {
		this.cultureExpense = cultureExpense;
	}

	public PaymentVO getCard() {
		return card;
	}

	public void setCard(PaymentVO card) {
		this.card = card;
	}

	public PaymentVO getCancels() {
		return cancels;
	}

	public void setCancels(PaymentVO cancels) {
		this.cancels = cancels;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}