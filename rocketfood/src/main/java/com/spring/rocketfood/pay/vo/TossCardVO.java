package com.spring.rocketfood.pay.vo;

import org.springframework.stereotype.Component;

@Component("tossCardVO")
public class TossCardVO {
	String company;
	String number;
	String installmentPlanMonths;
	String isInterestFree;
	String approveNo;
	String useCardPoint;
	String cardType;
	String ownerType;
	String acqureStatus;
	String receiptUrl;
	
	public TossCardVO() {
		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getInstallmentPlanMonths() {
		return installmentPlanMonths;
	}

	public void setInstallmentPlanMonths(String installmentPlanMonths) {
		this.installmentPlanMonths = installmentPlanMonths;
	}

	public String getIsInterestFree() {
		return isInterestFree;
	}

	public void setIsInterestFree(String isInterestFree) {
		this.isInterestFree = isInterestFree;
	}

	public String getApproveNo() {
		return approveNo;
	}

	public void setApproveNo(String approveNo) {
		this.approveNo = approveNo;
	}

	public String getUseCardPoint() {
		return useCardPoint;
	}

	public void setUseCardPoint(String useCardPoint) {
		this.useCardPoint = useCardPoint;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getAcqureStatus() {
		return acqureStatus;
	}

	public void setAcqureStatus(String acqureStatus) {
		this.acqureStatus = acqureStatus;
	}

	public String getReceiptUrl() {
		return receiptUrl;
	}

	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}

}
