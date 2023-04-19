package com.spring.rocketfood.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	
	//field
	private String member_id;	
	private String member_pw;	
	private String member_name;
	private String member_email;
	private String member_phone;
	private String member_birth;
	private int point_id;
	private int grade_id;
	private String business_name;
	private String business_regNum;
	private String business_category;
	private String manager_name;
	private String manager_phone;
	private String manager_email;
	private Date created_date;
	private Date modified_date;
	private String socialMember;
	private Boolean enabled;
	
	//constructor
	public MemberVO() {
		
	}
	public MemberVO(String member_id, String member_name, String member_pw, String member_email, String member_phone, String member_birth) {
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_phone = member_phone;
		this.member_birth = member_birth;
	}	
	public MemberVO(String member_id, String member_name, String member_pw, String member_email, String member_phone) {
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_phone = member_phone;
	}
	public MemberVO(String member_id, String member_name, String member_pw, String member_email, String member_phone, String member_birth, 
				String business_name, String business_regNum, String business_category, String manager_name, String manager_phone, String manager_email) {
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_phone = member_phone;
		this.member_birth = member_birth;
		this.business_name = business_name;
		this.business_regNum = business_regNum;
		this.business_category = business_category;
		this.manager_name = manager_name;
		this.manager_phone = manager_phone;
		this.manager_email = manager_email;
	}
	public MemberVO(String member_id, String member_name, String member_pw, String member_email, String member_phone, 
			String business_name, String business_regNum, String business_category, String manager_name, String manager_phone, String manager_email) {
	this.member_id = member_id;
	this.member_name = member_name;
	this.member_pw = member_pw;
	this.member_email = member_email;
	this.member_phone = member_phone;
	this.business_name = business_name;
	this.business_regNum = business_regNum;
	this.business_category = business_category;
	this.manager_name = manager_name;
	this.manager_phone = manager_phone;
	this.manager_email = manager_email;
}
	
	//getter & setter
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}

	public int getPoint_id() {
		return point_id;
	}

	public void setPoint_id(int point_id) {
		this.point_id = point_id;
	}

	public int getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getBusiness_regNum() {
		return business_regNum;
	}

	public void setBusiness_regNum(String business_regNum) {
		this.business_regNum = business_regNum;
	}

	public String getBusiness_category() {
		return business_category;
	}

	public void setBusiness_category(String business_category) {
		this.business_category = business_category;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public String getManager_phone() {
		return manager_phone;
	}

	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}

	public String getManager_email() {
		return manager_email;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
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

	public String getSocialMember() {
		return socialMember;
	}

	public void setSocialMember(String socialMember) {
		this.socialMember = socialMember;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
