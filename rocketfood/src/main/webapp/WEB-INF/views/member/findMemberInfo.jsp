<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<script>
	var code = null;
	
	function send_id_certiMail() {
		const find_member_email = $('input[name="find_member_email"]').val();
		const find_member_name = $('input[name="find_member_name"]').val();
		
		console.log('완성된 이메일 : ' + find_member_email); // 이메일 오는지 확인
		
		$.ajax({
			type : 'get',
			async : false,
			url : "${contextPath}/member/findId.do", // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : {
				find_member_name : find_member_name,
				find_member_email : find_member_email
			},
			success : function (data, textStatus) {				
				if (data != "false") {
					console.log("find_member_email : " +  find_member_email);
					console.log("data : " + data + "textStatus : " + textStatus );
					code = data;					
					Swal.fire({
						html: '회원가입 시 입력하신 메일로 인증번호가 전송되었습니다.'
					});			
					$('#certiNum_id').attr('type', 'text');
					$('#id_mail_check').css('display', 'inline-block');
				
				} else {
					Swal.fire({
						html: '입력하신 정보와 회원 정보가 일치하지 않습니다.'
					});
				}
			}
		}); // end ajax
	} // end send eamil

	// 인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	function check_certi_id() {
		const find_member_email = $('input[name="find_member_email"]');
		const find_member_name = $('input[name="find_member_name"]');
		var inputCode = $(certiNum_id).val();
		
		if(inputCode === code){
			Swal.fire({
				html: '인증번호가 일치합니다.'
			});
			find_id_form.submit();
		} else {
			Swal.fire({
				html: '인증번호가 불일치 합니다. 다시 확인해주세요!'
			});
		}
	}
	
	function send_pw_certiMail() {
		const find_member_email = $('input[name="find_member_email"]').val();
		const find_member_name = $('input[name="find_member_name"]').val();
		
		console.log('완성된 이메일 : ' + find_member_email); // 이메일 오는지 확인
		
		$.ajax({
			type : 'get',
			async : false,
			url : "${contextPath}/member/findPw.do", // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : {
				find_member_name : find_member_name,
				find_member_email : find_member_email
			},
			success : function (data, textStatus) {				
				if (data != "false") {
					console.log("find_member_email : " +  find_member_email);
					console.log("data : " + data + "textStatus : " + textStatus );
					code = data;					
					Swal.fire({
						html: '회원가입 시 입력하신 메일로 인증번호가 전송되었습니다.'
					});			
					$('#certiNum_pw').attr('type', 'text');
					$('#pw_mail_check').css('display', 'inline-block');
				
				} else {
					Swal.fire({
						html: '입력하신 정보와 회원 정보가 일치하지 않습니다.'
					});
				}
			}
		}); // end ajax
	} // end send eamil

	// 인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	function check_certi_pw() {
		const find_member_email = $('input[name="find_member_email"]');
		const find_member_name = $('input[name="find_member_name"]');
		var inputCode = $(certiNum_pw).val();
		
		if(inputCode === code){
			Swal.fire({
				html: '인증번호가 일치합니다.'
			});
			find_id_form.submit();
		} else {
			Swal.fire({
				html: '인증번호가 불일치 합니다. 다시 확인해주세요!'
			});
		}
	}
	
	// 인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	function check_certi_id() {
		const find_member_email = $('input[name="find_member_email"]');
		const find_member_name = $('input[name="find_member_name"]');
		var inputCode = $(certiNum_id).val();
		
		if(inputCode === code){
			find_id_form.submit();
		} else {
			Swal.fire({
				html: '인증번호가 불일치 합니다. 다시 확인해주세요!'
			});
		}
	}
	
	// 여기서부터 !!!
	// 비밀번호 찾기
	function send_pw_certiMail() {
		const find_member_email = $('input[name="userEmail"]').val();
		const find_member_id = $('input[name="userId"]').val();
		
		console.log('완성된 이메일 : ' + find_member_email); // 이메일 오는지 확인
		
		$.ajax({
			type : 'get',
			async : false,
			url : "${contextPath}/member/findPw.do", // GET방식이라 Url 뒤에 email을 붙힐수있다.
			data : {
				find_member_id : find_member_id,
				find_member_email : find_member_email
			},
			success : function (data, textStatus) {				
				if (data != "false") {
					console.log("find_member_email : " +  find_member_email);
					console.log("data : " + data + "textStatus : " + textStatus );
					code = data;					
					Swal.fire({
						html: '회원가입 시 입력하신 메일로 인증번호가 전송되었습니다.'
					});			
					$('#certiNum_pw').attr('type', 'text');
					$('#pw_mail_check').css('display', 'inline-block');
				
				} else {
					Swal.fire({
						html: '입력하신 정보와 회원 정보가 일치하지 않습니다.'
					});
				}
			}
		}); // end ajax
	} // end send eamil

	// 인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	function check_certi_pw() {
		const find_member_email = $('input[name="userId"]');
		const find_member_id = $('input[name="userEmail"]');
		var inputCode = $(certiNum_pw).val();
		
		if(inputCode === code){
			find_pw_form.submit();
		} else {
			Swal.fire({
				html: '인증번호가 불일치 합니다. 다시 확인해주세요!'
			});
		}
	}
</script>

<div id="container">
	<ul class="tabs page_title">
		<li class="active"><a href="#findId">아이디 찾기</a></li>
		<li><a href="#findPw">비밀번호 찾기</a></li>
	</ul>
	
	<div class="tab_container">
		<div class="tab_content" id="findId">
			<form name="find_id_form" action="${contextPath}/member/findIdResult.do" method="post">
				<input type="text" class="input full_input" name="find_member_name" placeholder="이름을 입력해주세요">
				<input type="text" class="input full_input" name="find_member_email" placeholder="이메일을 입력해주세요">
				<input type="hidden" class="input btn_input" name="certiNum_id" id="certiNum_id" placeholder="인증번호를 입력해주세요">
				<a href="javascript:check_certi_id()" class="btn btn_confirm" id="id_mail_check">확인</a>
				<a href="javascript:send_id_certiMail()" class="btn btn_primary">인증메일 받기</a>
			</form>
		</div>
	
		<div class="tab_content" id="findPw">
			<form name="find_pw_form" action="${contextPath}/member/changePwForm.do" method="post">
				<input type="text" class="input full_input" name="userId" placeholder="아이디를 입력해주세요">
				<input type="text" class="input full_input" name="userEmail" placeholder="이메일을 입력해주세요">
				<input type="hidden" class="input btn_input" name="certiNum_pw" id="certiNum_pw" placeholder="인증번호를 입력해주세요">
				<a href="javascript:check_certi_pw()" class="btn btn_confirm" id="pw_mail_check">확인</a>
				<a href="javascript:send_pw_certiMail()" class="btn btn_primary">인증메일 받기</a>
			</form>
		</div>
	</div>
</div>