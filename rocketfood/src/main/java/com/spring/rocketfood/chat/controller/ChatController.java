package com.spring.rocketfood.chat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface ChatController {
	public ModelAndView selectChatRoomList(@RequestParam Map<String, String> dataMap, HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView openChatRoom(HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView replychat (@RequestParam("chat_room_id") int chat_room_id, HttpServletRequest request,HttpServletResponse response) throws Exception;
}
