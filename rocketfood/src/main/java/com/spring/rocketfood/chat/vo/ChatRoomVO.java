package com.spring.rocketfood.chat.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("chatRoomVO")
public class ChatRoomVO {
	private int chat_room_id;
	private String questioner;
	private Date created_date;
	private Date modified_date;
	private String chat_status;
	
	public ChatRoomVO() {
		
	}
	public ChatRoomVO(int chat_room_id, String chat_status) {
		this.chat_room_id = chat_room_id;
		this.chat_status = chat_status;
	}
	
	public int getChat_room_id() {
		return chat_room_id;
	}
	public void setChat_room_id(int chat_room_id) {
		this.chat_room_id = chat_room_id;
	}
	public String getQuestioner() {
		return questioner;
	}
	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	public String getChat_status() {
		return chat_status;
	}
	
	public Date getModified_date() {
		return modified_date;
	}
	
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	
	public void setChat_status(String chat_status) {
		this.chat_status = chat_status;
	}
}
