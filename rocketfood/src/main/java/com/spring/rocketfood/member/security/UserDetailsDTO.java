package com.spring.rocketfood.member.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("userDetailsDTO")
public class UserDetailsDTO implements UserDetails {

	private String ID;
	private String PASSWORD;
	private boolean ENABLED;
	private ArrayList<GrantedAuthority> authority;
	
	public UserDetailsDTO() {}
	
	public UserDetailsDTO(String ID, String PASSWORD) {
		this.ID = ID;
		this.PASSWORD = PASSWORD;
	}
	
	public UserDetailsDTO(String ID, String PASSWORD, boolean ENABLED) {
		this.ID = ID;
		this.PASSWORD = PASSWORD;
		this.ENABLED = ENABLED;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}
	
	public void setAuthority(ArrayList<String> authList) {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		for(int i=0;i<authList.size();i++) {
			auth.add(new SimpleGrantedAuthority(authList.get(i)));
		}
		this.authority=auth;
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}

	@Override
	public String getUsername() {
		return ID;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return ENABLED;
	}
}