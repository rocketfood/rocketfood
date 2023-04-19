package com.spring.rocketfood.common.mail;


import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailSendService") 
public class MailSendService {
	@Autowired
	private JavaMailSender mailSender;
	private int authNumber;
	
		public void makeRandomNumber() {
			// 인증번호용 6자리 난수 생성 
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			authNumber = checkNum;
		}
		
		public String findIdEmail(String email) {
			makeRandomNumber();
			String setFrom = "plus0706@naver.com"; 
			String toMail = email;
			String title = "로켓푸드 아이디 찾기 인증메일입니다."; 
			String content = 
					"안녕하세요. 로켓푸드입니다." + 
	                "<br><br>" + 
				    "인증번호는 " + authNumber + " 입니다." + 
				    "<br>" + 
				    "로켓푸드 아이디 찾기에서 인증번호를 입력해주세요.";
			mailSend(setFrom, toMail, title, content);
			String code = Integer.toString(authNumber);
			return code;
		}

		public String findPwEmail(String email) {
			makeRandomNumber();
			String setFrom = "plus0706@naver.com";
			String toMail = email;
			String title = "로켓푸드 비밀번호 찾기 인증메일입니다."; 
			String content = 
					"안녕하세요. 로켓푸드입니다." + 
	                "<br><br>" + 
	                "인증번호는 " + authNumber + " 입니다." + 
				    "<br>" + 
				    "로켓푸드 비밀번호 찾기에서 인증번호를 입력해주세요.";
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//메일 보내기
		public void mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);

				helper.setText(content,true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
	
}
