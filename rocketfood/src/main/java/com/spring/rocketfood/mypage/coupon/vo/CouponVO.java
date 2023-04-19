package com.spring.rocketfood.mypage.coupon.vo;

import org.springframework.stereotype.Component;

@Component("couponVO")
public class CouponVO {
	private int coupon_id;
	private String coupon_name;
	private String coupon_code;
	private Double coupon_discount;
	private int coupon_period;
	private int coupon_stock;
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}
	public Double getCoupon_discount() {
		return coupon_discount;
	}
	public void setCoupon_discount(Double coupon_discount) {
		this.coupon_discount = coupon_discount;
	}
	public int getCoupon_period() {
		return coupon_period;
	}
	public void setCoupon_period(int coupon_period) {
		this.coupon_period = coupon_period;
	}
	public int getCoupon_stock() {
		return coupon_stock;
	}
	public void setCoupon_stock(int coupon_stock) {
		this.coupon_stock = coupon_stock;
	}
	
	
}
