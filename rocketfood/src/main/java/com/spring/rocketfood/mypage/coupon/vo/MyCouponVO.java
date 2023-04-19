package com.spring.rocketfood.mypage.coupon.vo;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("myCouponVO")
public class MyCouponVO {
	private int coupon_id;
	private String member_id;
	private Timestamp coupon_expire;
	private String coupon_use;
	private Date created_date;
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Timestamp getCoupon_expire() {
		return coupon_expire;
	}
	public void setCoupon_expire(Timestamp coupon_expire) {
		this.coupon_expire = coupon_expire;
	}
	public String getCoupon_use() {
		return coupon_use;
	}
	public void setCoupon_use(String coupon_use) {
		this.coupon_use = coupon_use;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
}
