package com.spring.rocketfood.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("gradeVO")
public class GradeVO {
	private int grade_id;
	private String grade_name;
	private double accrual_rate;
	private Date created_date;
	private Date modified_date;
	
	public GradeVO() {
		
	}

	public int getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public double getAccrual_rate() {
		return accrual_rate;
	}

	public void setAccrual_rate(double accrual_rate) {
		this.accrual_rate = accrual_rate;
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
