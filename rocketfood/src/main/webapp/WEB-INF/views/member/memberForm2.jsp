<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function execDaumPostcode() {
	new daum.Postcode({
			oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 도로명 조합형 주소 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', '
						+ data.buildingName : data.buildingName);
			}
			// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
			if (fullRoadAddr !== '') {
				fullRoadAddr += extraRoadAddr;
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
			document.getElementById('roadAddress').value = fullRoadAddr;
			document.getElementById('jibunAddress').value = data.jibunAddress;						
		}
	}).open();
}

//아이디 중복 검사
function fn_overlapped() {
	var _id = $("#_member_id").val();
	if (_id == '') {
		Swal.fire({
			html: '아이디를 입력해주세요.'
		});
		return;
	}
	$.ajax({
		type : "post",
		async : false,
		url : "${contextPath}/member/overlapped.do",
		dataType : "text",
		data : {
			id : _id
		},
		success : function(data, textStatus) {
			if (data == 'false') {
				Swal.fire({
					html: '사용할 수 있는 아이디입니다.'
				});
				$('#btnOverlapped').prop("disabled", true);
				$('#_member_id').prop("disabled", true);
				$('#member_id').val(_id);
			} else {
				Swal.fire({
					html: '사용할 수 없는 아이디입니다.'
				});
			}
		}
	}); //end ajax	 
}

//비밀번호 유효성 검사
function test() {
	var p1 = document.getElementById('member_pw').value;
	var p2 = document.getElementById('member_pw1').value;

	if (p1.length < 6) {
		alert('입력한 글자가 6글자 이상이어야 합니다.');
		return false;
	}
	if (p1 != p2) {
		alert("비밀번호불일치");
		return false;
	} else {
		alert("비밀번호가 일치합니다");
		return true;
	}
}
//폼 제출전 전체 유효성 검사
function joinform_check(){
	var id = document.getElementById("_member_id");
	var pw = document.getElementById("_member_pw");
	var pw_confirm = document.getElementById("_member_pw_confirm");
	var name = document.getElementById("_member_name");
	var email = document.getElementById("_member_email");
	var phone = document.getElementById("_member_phone");
	var zipcode =document.getElementById("zipcode");
	var agree1 = document.getElementById("agree1");
	var agree2 = document.getElementById("agree2");
	
	//아이디 입력 여부 확인
	if(id.value == null || id.value == '' ){
		Swal.fire({
			html: '아이디를 입력해주세요.'
		});
		id.focus();
		return false;
	}
	
	var pwCheck = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	//비밀번호 입력 여부 확인
	if(pw.value == null || pw.value == '' ){
		Swal.fire({
			html: '비밀번호를 입력해주세요.'
		});
		pw.focus();
		return false;
	}
	//비밀번호 확인 입력 여부 확인
	if(pw_confirm.value == null || pw_confirm.value == '' ){
		Swal.fire({
			html: '비밀번호 확인을 입력해주세요.'
		});
		pw_confirm.focus();
		return false;
	}
	
	//비밀번호 정규식 검사
	if(pwCheck.test(pw.value) == false ){
		Swal.fire({
			html: '비밀번호는 영문+숫자+특수문자를 조합하여 6~25자리를 사용해야 합니다.'
		});
		pw.focus();
		return false;
	}

	//이름 입력 여부 확인
	if(name.value == null || name.value == '' ){
		Swal.fire({
			html: '이름을 입력해주세요.'
		});
		name.focus();
		return false;
	}

	//이메일 입력 여부 확인
	if(email.value == null || email.value == '' ){
		Swal.fire({
			html: '이메일을 입력해주세요.'
		});
		email.focus();
		return false;
	}

	//휴대폰 입력 여부 확인
	if(phone.value == null || phone.value == '' ){
		Swal.fire({
			html: '휴대폰 번호를 입력해주세요.'
		});
		phone.focus();
		return false;
	}
	
	//주소 입력 여부 확인
	if(zipcode.value == null || zipcode.value == '' ){
		Swal.fire({
			html: '주소를 입력해주세요.'
		});
		phone.focus();
		return false;
	}
	
	//약관 동의 여부 확인
	if(!agree1.checked) {
		Swal.fire({
			html: '약관에 동의하셔야 가입이 가능합니다.'
		});
		agree1.focus();
		return false;
	}
	if(!agree2.checked) {
		Swal.fire({
			html: '약관에 동의하셔야 가입이 가능합니다.'
		});
		agree2.focus();
		return false;
	}
	
	//생년월일 조합하기
	var year = document.getElementById("birthYear").value;
	var month = document.getElementById("birthMonth").value;
	var day = document.getElementById("birthDay").value;
	var member_birth = document.getElementById("member_birth");
	member_birth.value = year+"-"+month+"-"+day;
	if(isNaN(year)==false && isNaN(month)==false && isNaN(day)==false){
		member_birth.value = year+"-"+month+"-"+day;
		console.log(member_birth.value);
	} else {
		Swal.fire({
			html: '생년월일은 숫자로만 구성되어야 합니다.'
		});
		return false;
	}
	//유효성 검사 후 폼 제출
	document.join_form.submit();
}

//약관 읽기 팝업 호출
function popup() {
	var url = "popup.html";
	var name = "popup test";
	var option = "width = 500, height = 500, top = 100, left = 200, location = no"
	window.open(url, name, option);
}

</script>

<div id="container">
	<div class="page_title">
		<h3>회원가입</h3>
	</div>
	<div class="progress-wrap">
		<ul>
			<li>01 회원선택</li>
			<li class="here">02 약관동의 및 정보입력</li>
			<li class="last">03 가입완료</li>
		</ul>
	</div>
	
	
	<form name="join_form" action="${contextPath}/member/addMember.do" method="post">
		<div class="form_wrap">
			<div class="text-right ">
				<span>*</span>필수입력사항
			</div>
			<ul>
				<li class="form_row">
					<div class="label"><label class="required">아이디</label></div>
					<div>
						<input type="text" id="_member_id" name="member_id" class="btn_input input" placeholder="아이디를 입력해주세요">
						<input type="button" onClick="fn_overlapped()" class="btn btn_check" value="중복확인" />
					</div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">비밀번호</label></div>
					<div><input type="password" id="_member_pw" name="member_pw" class="full_input input" placeholder="비밀번호를 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">비밀번호 확인</label></div>
					<div><input type="password" id="_member_pw_confirm" name="member_pw_confirm" class="full_input input" placeholder="비밀번호를 다시 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">이름</label></div>
					<div><input type="text" id="_member_name" name="member_name" class="full_input input" placeholder="이름을 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">이메일</label></div>
					<div><input type="email" id="_member_email" name="member_email" class="full_input input" placeholder="이메일을 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">휴대폰</label></div>
					<div><input type="tel" id="_member_phone" name="member_phone" class="full_input input" placeholder="숫자만 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">주소</label></div>
					<div>
						<input type="button" onClick="execDaumPostcode()" class="btn btn_findAddr" value="주소검색">			
						<div><input type="hidden" id="zipcode" name="zipcode" placeholder="우편번호"></div>			
						<div><input type="text" id="roadAddress" name="roadAddress"  class="full_input input" placeholder="도로명 주소"></div>
						<div><input type="text" id="jibunAddress" name="jibunAddress"  class="full_input input" placeholder="지번 주소"></div>
						<div><input type="text" id="namujiAddress" name="namujiAddress" class="full_input input" placeholder="상세 주소"></div>
					</div>					
				</li>
				<li class="form_row">
					<div class="label"><label>생년월일</label></div>
					<div class="birthInput_wrap">
						<div><input type="hidden" name="member_birth" id="member_birth"></div>
						<div><input type="text" id="birthYear" name="birthYear" class="input birth-input" placeholder="YYYY"></div>
						<div><span>/</span></div>
						<div><input type="text" id="birthMonth" name="birthMonth" class="input birth-input" placeholder="MM"></div>
						<div><span>/</span></div>
						<div><input type="text" id="birthDay" name="birthDay" class="input birth-input" placeholder="DD"></div>
					</div>
				</li>
			</ul>
			<ul>
				<li class="form_row">
					<div class="label"><label class="required">사업자명</label></div>
					<div><input type="text" id="_business_name" name="business_name" class="full_input input" placeholder="사업자명을 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">사업자 번호</label></div>
					<div><input type="text" id="_business_regNum" name="business_regNum" class="full_input input" placeholder="사업자 번호를 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">입점 카테고리</label></div>
					<div>
						<select class="select full_select">
							<option>선택해주세요.</option>
							<option value="채소">채소</option>
							<option value="과일">과일</option>
							<option value="정육">정육</option>
							<option value="곡물">곡물</option>
							<option value="수산">수산</option>
							<option value="간편식">간편식</option>
							<option value="반찬">반찬</option>
							<option value="냉장/냉동">냉장/냉동</option>
							<option value="베이커리/디저트">베이커리/디저트</option>
							<option value="면/양념/오일">면/양념/오일</option>
							<option value="유제품">유제품</option>
							<option value="생수/음표">생수/음료</option>
							<option value="건강식품">건강식품</option>
							<option value="기타">기타</option>
						</select>
					</div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">담당자 이름</label></div>
					<div><input type="text" id="_manager_name" name="manager_name" class="full_input input" placeholder="담당자 이름을 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">담당자 연락처</label></div>
					<div><input type="tel" id="_manager_phone" name="manager_phone" class="full_input input" placeholder="담당자 연락처를 입력해주세요"></div>
				</li>
				<li class="form_row">
					<div class="label"><label class="required">담당자 이메일</label></div>
					<div><input type="tel" id="_manager_email" name="manager_email" class="full_input input" placeholder="담당자 이메일을 입력해주세요"></div>
				</li>
			</ul>	
			<ul>
				<li class="form_row">
					<div class="label"><label class="required">이용약관 동의</label></div>
					<div>						
						<div><input type="checkbox" id="agree1" class="form_chk"><label for="agree1" class="form_chk_label">이용약관 동의 <span class="text_muted">(필수)</span></label> <a href="#" class="text-underline">약관보기</a></div>
						<div><input type="checkbox" id="agree2" class="form_chk"><label for="agree2" class="form_chk_label">개인정보 수집·이용 동의 <span class="text_muted">(필수)</span></label> <a href="#" class="text-underline">약관보기</a></div>
					
					</div>
				</li>
			</ul>
		</div>
		<div class="join_btn_wrap text-center">
			<input type="button" onClick="joinform_check()" class="btn btn_primary" value="가입하기">
		</div>		
	</form>
	

	<div id="Pop" style="position:absolute; left:500px; top:300px; width:500px; height:500px; z-index:9999;display:none;">
   			
   	</div>
</div>