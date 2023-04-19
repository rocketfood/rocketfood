package com.spring.rocketfood.goods.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("goodsVO")
public class GoodsVO {
	private int goods_id;
	private String goods_name;
	private String goods_status;
	private int stock_quantity;
	private String thumbnail_url;
	private String short_description;
	private String goods_description;
	private Date modified_date;
	private Date created_date;
	private int category_id;
	private int goods_price;
	private int sales_price;
	private int sales_count;
	private int wish_count;
	private String goods_fileName;
	private String business_name;
	private int count;
	
	public String getGoods_fileName() {
		return goods_fileName;
	}
	public void setGoods_fileName(String goods_fileName) {
		this.goods_fileName = goods_fileName;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public int getSales_count() {
		return sales_count;
	}
	public void setSales_count(int sales_count) {
		this.sales_count = sales_count;
	}
	public int getWish_count() {
		return wish_count;
	}
	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}
	public int getSales_price() {
		return sales_price;
	}
	public void setSales_price(int sales_price) {
		this.sales_price = sales_price;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getShort_description() {
		return short_description;
	}
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}

	
}
