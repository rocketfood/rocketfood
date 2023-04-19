package com.spring.rocketfood.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service("kakaoService")
public class KakaoService {

	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
 
        try {
            // URL 객체 생성
        	URL url = new URL(reqURL);
            
            // 'http://' 프로토콜 형태로 URL을 통해 서버와 통신하는 객체 생성하여 카카오 서버와 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
 
            // 카카오 서버로 API 사용자 정보 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f1f06dd941ee84dd6d4f2c776f201fe6");
            sb.append("&redirect_uri=http://localhost:8080/rocketfood/kakaoLogin.do"); 
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
 
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode + " 결과");
 
           // 카카오 서버에서 토큰 받아옴
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
 
            while ((line = br.readLine()) != null) {
            	result += line;
                
            }
            
            System.out.println("response body : " + result+" 결과");
 
            // String 형태로 저장된 JSON을 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
 
            //JSON을 String으로 저장
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
 
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
 
            br.close();
            bw.close();
        } catch (IOException e) {
 
            e.printStackTrace();
        }
 
        return access_Token;
    }
	
	public Map<String, Object> getUserInfo (String access_Token) {
	    Map<String, Object> userInfo = new HashMap<String, Object>();
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");	        
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        //카카오 서버에서 회원 정보 받아옴
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	        
	        String line = "";
	        String result = "";	        
	        while ((line = br.readLine()) != null) {	        	
	        	System.out.println("사용자 정보 :" + line);
	            result += line;
	        }
	        
	        System.out.println("response body : " + result);
	        
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	        
	        // JSON 객체에서 회원 정보 분리하여 String으로 저장
	        String member_name = properties.getAsJsonObject().get("nickname").getAsString();
	        String member_email = kakao_account.getAsJsonObject().get("email").getAsString();
	        // 카카오 회원 아이디는 이메일 형태이기 때문에 이메일 앞부분을 따로 member_id로 만들어줌
	        String member_id = "";	       
	        int end = member_email.indexOf("@");
	        if (end != -1) {
	        	member_id = member_email.substring(0, end);
	        	System.out.println("member_email : " + member_email + " / member_id : " + member_id );
	        	userInfo.put("member_id", member_id);
	        	
	        }
	        userInfo.put("member_name", member_name);
	        userInfo.put("member_email", member_email);
	        userInfo.put("socialMember", "kakao");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return userInfo;
	}
	
	public void kakaoLogout(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	    } catch (IOException e) {	        
	        e.printStackTrace();
	    }
	}

}
