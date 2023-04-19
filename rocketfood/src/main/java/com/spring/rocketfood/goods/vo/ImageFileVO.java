package com.spring.rocketfood.goods.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("imageFileVO")
public class ImageFileVO {
	private int goods_id;
	private int image_id;
	private String fileName;
	private String fileType;
	private String imageFileType;
	private Date created_date;

	public Date getCreated_date() {
		return created_date;
	}


	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}


	public ImageFileVO() {
		super();
	}


	public int getGoods_id() {
		return goods_id;
	}




	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}




	public int getImage_id() {
		return image_id;
	}




	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}




	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getImageFileType() {
		return imageFileType;
	}


	public void setImageFileType(String imageFileType) {
		this.imageFileType = imageFileType;
	}




	

}
