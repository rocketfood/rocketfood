<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<script>
	function changePwForm_check() {
		var pw = document.getElementById('member_pw');
		var pw_val = pw.value;
		var pw_confirm_val = document.getElementById('member_pw_confirm').value;
		
		//비밀번호 입력 여부 확인
		if (pw_val == null || pw_val == '') {
			Swal.fire({
				html: '새 비밀번호를 입력해 주세요.'
			});
			return false;
		}
		
		//비밀번호 확인 입력 여부 확인
		if (pw_confirm_val == null || pw_confirm_val == '') {
			Swal.fire({
				html: '새 비밀번호 확인란을 입력해 주세요.'
			});
			return false;
		}
		
		//비밀번호 글자 수 검사
		if (pw_val.length < 6) {
			Swal.fire({
				html: '입력한 글자가 6글자 이상이어야 합니다.'
			});
			return false;
		}
		
		//비밀번호 일치 여부 확인
		if (pw_val != pw_confirm_val) {
			Swal.fire({
				html: '비밀번호가 일치하지 않습니다. 다시 입력해 주세요.'
			});
			return false;
		}
				
		//비밀번호 정규식 검사
		var pwCheck =  /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{6,}$/;
		if (pwCheck.test(pw_val) == false) {
			Swal.fire({
				html: '비밀번호는 영문, 숫자, 특수문자 중 2가지 이상 조합하여 6자리 이상으로 사용해야 합니다.'
			});
			pw.focus();
			return false;
		}
		
		//유효성 검사 후 폼 제출
		document.change_pw_form.submit();
	}
</script>

<div id="container">
	<div class="page_title">
		<h3>새 비밀번호로 변경</h3>
	</div>
	
	<div class="tab_container">
		<div class="tab_content" id="findPw">
			<form name="change_pw_form" action="${contextPath}/member/findPwResult.do" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="member_id" value="${member_id}">
				<input type="password" id="member_pw" class="input full_input" name="member_changePw" placeholder="새 비밀번호를 입력해 주세요." required />
				<input type="password" id="member_pw_confirm" class="input full_input" name="member_changePw_confirm" placeholder="새 비밀번호를 다시 입력해 주세요." required />
				<input type="button" class="btn btn_primary" onclick="changePwForm_check()" value="확인" >
			</form>
		</div>
	</div>
</div>

