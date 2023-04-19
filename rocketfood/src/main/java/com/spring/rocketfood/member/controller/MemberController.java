package com.spring.rocketfood.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView loginForm(HttpServletRequest request, Model model, Authentication authentication, HttpSession session) throws Exception;
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> addMember(@ModelAttribute("memberVO") MemberVO memberVO, @ModelAttribute("addressVO") AddressVO addressVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> overlapped(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String memberIdSearch(@RequestParam("find_member_name") String member_name, @RequestParam("find_member_email") String member_email) throws Exception;
	public ModelAndView changePwForm(@RequestParam("userId") String member_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView changePw(@RequestParam("member_id") String member_id, @RequestParam("member_changePw") String member_pw, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listAllAddress(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView AddressForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> updateAddress(@ModelAttribute("addressVO") AddressVO addressVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> addNewAddress(@ModelAttribute("addressVO") AddressVO addressVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> removeAddress(@RequestParam("address_id") int address_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> updateBaiscAddress(@ModelAttribute("address_id") int address_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
