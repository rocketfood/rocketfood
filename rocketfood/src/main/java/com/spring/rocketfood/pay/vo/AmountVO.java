package com.spring.rocketfood.pay.vo;

import org.springframework.stereotype.Component;

//결제내역 vo -> 결제내역을 가져올 때 사용.
@Component("amountVO")
public class AmountVO {
	private Integer total; //총 결제액
	private Integer tax_free; //비과세
	private Integer vat; //부가세
	private Integer point; // 포인트
	private Integer discount; // 할인

	
	public AmountVO () {
		
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public Integer getTax_free() {
		return tax_free;
	}


	public void setTax_free(Integer tax_free) {
		this.tax_free = tax_free;
	}


	public Integer getVat() {
		return vat;
	}


	public void setVat(Integer vat) {
		this.vat = vat;
	}


	public Integer getPoint() {
		return point;
	}


	public void setPoint(Integer point) {
		this.point = point;
	}


	public Integer getDiscount() {
		return discount;
	}


	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
}