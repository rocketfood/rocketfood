package com.spring.rocketfood.member.vo;

import org.springframework.stereotype.Component;

@Component("authVO")
public class AuthVO {
	private String member_id;
	private String authority;
	
	public AuthVO() {
		
	}
	public AuthVO(String member_id) {
		this.member_id = member_id;
	}
	public AuthVO(String member_id, String authority) {
		this.member_id = member_id;
		this.authority = authority;
	}
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
