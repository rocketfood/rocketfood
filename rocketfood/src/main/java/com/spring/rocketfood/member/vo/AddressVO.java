package com.spring.rocketfood.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("addressVO")
public class AddressVO {
	private int address_id;
	private int zip_code;
	private String roadAddress;
	private String jibunAddress;
	private String detalied_address;
	private String is_basic_address;
	private String receiver_name;
	private String receiver_phone;
	private String member_id;
	private Date created_date;
	private Date modified_date;
	
	
	public AddressVO() {
		
	}
	public AddressVO(int zip_code, String roadAddress, String jibunAddress, String detalied_address, String member_id, String is_basic_address) {
		this.zip_code = zip_code;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.detalied_address = detalied_address;
		this.member_id = member_id;
		this.is_basic_address = is_basic_address;
	}
	
	public int getAddress_id() {
		return address_id;
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
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getZip_code() {
		return zip_code;
	}
	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getJibunAddress() {
		return jibunAddress;
	}
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	public String getDetalied_address() {
		return detalied_address;
	}
	public void setDetalied_address(String detalied_address) {
		this.detalied_address = detalied_address;
	}
	public String getIs_basic_address() {
		return is_basic_address;
	}
	public void setIs_basic_address(String is_basic_address) {
		this.is_basic_address = is_basic_address;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
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
