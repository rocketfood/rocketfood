package com.spring.rocketfood.cart.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("cargGoodsVO")
public class CartGoodsVO {
	private int cart_goods_id;
	private int count;
	private int cart_id;
	private int goods_id;
	private Date created_date;
	private Date modified_date;
	
	public CartGoodsVO() {}
	public CartGoodsVO(int goods_id, int count) {
		this.goods_id = goods_id;
		this.count = count;
	}
	
	public int getCart_goods_id() {
		return cart_goods_id;
	}
	public void setCart_goods_id(int cart_goods_id) {
		this.cart_goods_id = cart_goods_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	
	
}
