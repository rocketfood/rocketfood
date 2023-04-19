package com.spring.rocketfood.goods.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("goodsQnaVO")
public class GoodsQnaVO {
	private int article_id;
	private String title;
	private String content;
	private Date created_date;
	private Date modified_date;
	private String member_id;
	private int goods_id;
	private String secret;
	private String reply;
	private String reply_content;
	private Date replyed_date;
	
	public GoodsQnaVO() {
		
	}
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModeified_date() {
		return created_date;
	}
	public void setModeified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReplyed_date() {
		return replyed_date;
	}
	public void setReplyed_date(Date reply_date) {
		this.replyed_date = reply_date;
	}

	
}
