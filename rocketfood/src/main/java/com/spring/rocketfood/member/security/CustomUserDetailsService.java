package com.spring.rocketfood.member.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.spring.rocketfood.member.dao.MemberDAO;

@Service("userLoginService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsDTO userDetailsDTO = memberDAO.selectMember(username);
		ArrayList<String> authList = memberDAO.getAuthList(username);
		
		if (userDetailsDTO == null) {
			System.out.println("loadUserByUsername 실패");
			throw new UsernameNotFoundException(username);
		}
		else {
			System.out.println("loadUserByUsername 성공");
			userDetailsDTO.setAuthority(authList);
		}
		
		System.out.println("username : "+userDetailsDTO.getUsername()+" password : " + userDetailsDTO.getPassword());
		return userDetailsDTO; 
	}
}
