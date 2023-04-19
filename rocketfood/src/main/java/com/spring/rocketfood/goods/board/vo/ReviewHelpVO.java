package com.spring.rocketfood.goods.board.vo;

import java.sql.Date;

public class ReviewHelpVO {
	private int help_id;
	private int article_id;
	private String member_id;
	private String help_y;
	private Date created_date;
	public int getHelp_id() {
		return help_id;
	}
	public void setHelp_id(int help_id) {
		this.help_id = help_id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getHelp_y() {
		return help_y;
	}
	public void setHelp_y(String help_y) {
		this.help_y = help_y;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	
}
