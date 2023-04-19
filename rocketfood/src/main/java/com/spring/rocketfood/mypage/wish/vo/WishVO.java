package com.spring.rocketfood.mypage.wish.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("wishVO")
public class WishVO {
	private int wish_id;
	private Date created_date;
	private String member_id;
	
	public int getWish_id() {
		return wish_id;
	}

	public Date getCreated_date() {
		return created_date;
	}
	
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
	
}
