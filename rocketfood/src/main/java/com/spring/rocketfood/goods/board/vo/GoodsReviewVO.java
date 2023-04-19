package com.spring.rocketfood.goods.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("goodsReviewVO")
public class GoodsReviewVO {
	private int article_id;
	private String title;
	private int help_count;
	private String description;
	private String star;
	private Date created_date;
	private Date modified_date;
	private int goods_id;
	private String member_id;
	
	public int getArticle_id() {
		return article_id;
	}
	
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getHelp_count() {
		return help_count;
	}
	
	public void setHelp_count(int help_count) {
		this.help_count = help_count;
	}
		
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStar() {
		return star;
	}
	
	public void setStar(String star) {
		this.star = star;
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
	
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	

	
	
}
