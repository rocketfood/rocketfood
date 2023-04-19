package com.spring.rocketfood.pay.vo;


import java.util.Date;
import org.springframework.stereotype.Component;


//결제요청 vo -> 결제요청할때 사용
@Component("kakaoPayReadyVO")
public class KakaoPayReadyVO {
	private String tid;
	private String next_redirect_pc_url;
	private String partner_order_id;
	private Date created_at;
	
	
	public KakaoPayReadyVO() {
		
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getNext_redirect_pc_url() {
		return next_redirect_pc_url;
	}

	public void setNext_redirect_pc_url(String next_redirect_pc_url) {
		this.next_redirect_pc_url = next_redirect_pc_url;
	}
	
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	
	public String getPartner_order_id() {
		return partner_order_id;
	}
	
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	}