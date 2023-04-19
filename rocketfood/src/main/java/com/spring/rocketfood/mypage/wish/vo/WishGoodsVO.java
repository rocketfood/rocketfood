package com.spring.rocketfood.mypage.wish.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("WishGoodsVO")
public class WishGoodsVO {
	private int wish_goods_id;
	private Date created_date;
	private int wish_id;
	private int goods_id;
	
	public int getWish_goods_id() {
		return wish_goods_id;
	}
	
	public Date getCreated_date() {
		return created_date;
	}
	
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	public int getWish_id() {
		return wish_id;
	}
	
	public void setWish_id(int wish_id) {
		this.wish_id = wish_id;
	}
	
	public int getGoods_id() {
		return goods_id;
	}
	
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
}
