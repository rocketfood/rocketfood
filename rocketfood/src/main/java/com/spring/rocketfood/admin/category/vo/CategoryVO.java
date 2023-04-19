package com.spring.rocketfood.admin.category.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("CategoryVO")
public class CategoryVO {
	//필드
	private int category_id;
	private String category_name;
	private Date created_date;
	private Date modified_date;
	private int parent_id;
	
	//생성자
	public CategoryVO() {
		
	}	
	public CategoryVO(String category_name) {
		this.category_name = category_name;
	}
	public CategoryVO(String category_name, int parent_id) {
		this.category_name = category_name;
		this.parent_id = parent_id;
	}

	//getter & setter
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	
}
