package com.spring.rocketfood.order.vo;

import java.sql.Date;

public class OrderGoodsVO {
	private int order_goods_id;
	private int order_id;
	private int goods_id;
	private int count;
	private Date created_date;
	private Date modified_date;
	
	private OrderGoodsVO() {
		
	}
	
	
	public int getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(int order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
