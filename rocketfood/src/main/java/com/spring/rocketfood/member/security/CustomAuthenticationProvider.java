package com.spring.rocketfood.member.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication; 
 
        System.out.println("CustomAuthenticationProvider authenticate 메소드 실행");
        String member_id = authentication.getName(); 
        String member_pw = authentication.getCredentials().toString(); //userPassword
        
        UserDetails userInfo = customUserDetailsService.loadUserByUsername(authToken.getName()); 
    
        
        //회원 아이디 확인
        if (userInfo == null) { 
          throw new UsernameNotFoundException(authToken.getName());
        }

        //회원 비밀번호 확인        
        if(!userInfo.getPassword().equals(member_pw)) {
        	System.out.println("비밀번호 불일치 예외 던지기 : BadCredentialsException");
        	throw new BadCredentialsException("패스워드가 일치하지 않습니다");
        }
        
        
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();      
        
        UsernamePasswordAuthenticationToken MemberInfoToken = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), authToken.getCredentials(), authorities); 
        return MemberInfoToken;
    }
	
	private boolean matchPassword(String password, Object credentials, String inName) {
		//password : 디비값  credentails : 입력값 inName : 아이디
		System.out.println("matchPassword password : " + password + " / credentials : " + credentials + " / inName : " + inName);
        System.out.println("matchPassword 결과 " + password.equals(credentials));
        return password.equals(credentials);
    }
	
	@Override
    public boolean supports(Class<?> authentication) {
         return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

