package com.spring.rocketfood.chat.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("chatMessageVO")
public class ChatMessageVO {
	private int msg_id;
	private int chat_room_id;
	private String questioner;
	private String answerer;
	private String message;
	private Timestamp created_date;
	
	public ChatMessageVO() {
		
	}
	
	public ChatMessageVO(String message) {
		this.message = message;
	}
	
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
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
	public String getAnswerer() {
		return answerer;
	}
	public void setAnswerer(String answerer) {
		this.answerer = answerer;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	
	
	
	
}
