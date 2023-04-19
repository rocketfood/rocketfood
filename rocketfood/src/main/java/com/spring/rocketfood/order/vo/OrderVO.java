package com.spring.rocketfood.order.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("orderVO")
public class OrderVO {
	private int order_id;
	private String order_status;
	private int cart_id;
	private String member_id;
	private String address_id;

	private String orderer_name;
	private String orderer_phone;
	private String receiver_name;
	private String receiver_phone;
	
	private String pay_method;
	private String card_com_name;
	private int card_pay_month;
	private String pay_orderer_hp;
	private Date pay_order_time;
	
	private int final_total_price;
	private int order_qty;
	private String thumbnail_url;
	
	private Date created_date;
	private Date modified_date;
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getOrderer_name() {
		return orderer_name;
	}
	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}
	public String getOrderer_phone() {
		return orderer_phone;
	}
	public void setOrderer_phone(String orderer_phone) {
		this.orderer_phone = orderer_phone;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getCard_com_name() {
		return card_com_name;
	}
	public void setCard_com_name(String card_com_name) {
		this.card_com_name = card_com_name;
	}
	public int getCard_pay_month() {
		return card_pay_month;
	}
	public void setCard_pay_month(int card_pay_month) {
		this.card_pay_month = card_pay_month;
	}
	public String getPay_orderer_hp() {
		return pay_orderer_hp;
	}
	public void setPay_orderer_hp(String pay_orderer_hp) {
		this.pay_orderer_hp = pay_orderer_hp;
	}
	public Date getPay_order_time() {
		return pay_order_time;
	}
	public void setPay_order_time(Date pay_order_time) {
		this.pay_order_time = pay_order_time;
	}
	public int getFinal_total_price() {
		return final_total_price;
	}
	public void setFinal_total_price(int final_total_price) {
		this.final_total_price = final_total_price;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
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
