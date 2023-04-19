package com.spring.rocketfood.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("pointVO")
public class PointVO {
	private int point_id;
	private String point_detail;
	private Date point_expiration;
	private String member_id;
	private int current_point;
	private Date created_date;
	
	public PointVO() {
		
	}
	
	public PointVO(String point_detail, String member_id, int current_point) {
		this.point_detail = point_detail;
		this.member_id = member_id;
		this.current_point = current_point;
	}

	public int getPoint_id() {
		return point_id;
	}

	public void setPoint_id(int point_id) {
		this.point_id = point_id;
	}

	public String getPoint_detail() {
		return point_detail;
	}

	public void setPoint_detail(String point_detail) {
		this.point_detail = point_detail;
	}

	public Date getPoint_expiration() {
		return point_expiration;
	}

	public void setPoint_expiration(Date point_expiration) {
		this.point_expiration = point_expiration;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getCurrent_point() {
		return current_point;
	}

	public void setCurrent_point(int current_point) {
		this.current_point = current_point;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
}
