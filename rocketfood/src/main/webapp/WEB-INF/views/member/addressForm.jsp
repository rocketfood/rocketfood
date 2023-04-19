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
</script>
<style>
	.address_body {margin-bottom:30px; margin-top:20px}
	.address_from_item {margin-bottom:30px}
	.address_from_item .full_input {width: 100%}
	.address_from_item:last-child {margin-bottom:0}
	.address_from_item label {margin-bottom:10px; font-weight: 400; display:block; }
	.address_footer {padding: 0 30px; bottom: 30px}
	.address_footer a {display: block; width: 100%;}
</style>


<div class="container_pop">
	<c:choose>
	<c:when test="${form_status == 'edit'}"> 	<!-- 배송지 수정 폼 -->
	<div class="address_top">
		<div class="d-flex">
			<h2>배송지 수정</h2>
		</div>
	</div>	
	<div class="address_body">	
		<form name="form_address_edit" method="post" action="${contextPath}/member/address/updateAddress.do">	
		<input type="hidden" name="address_id" value="${addressVO.address_id }">	
		<div class="address_from_item">
			<c:if test="${addressVO.is_basic_address == 'y' }">
			<span class="basic">기본배송지</span>
			</c:if>
			<p><b>${addressVO.roadAddress}</b></p>
			<input type="hidden" name="roadAddress" value="${addressVO.roadAddress}" class="input full_input">
			<input type="text" name="detalied_address" placeholder="나머지 주소를 입력해 주세요" class="input full_input">
		</div>		
		
		<div class="address_from_item">
			<label>받으실 분</label>
			<input type="text" name="receiver_name" value="${addressVO.receiver_name }" class="input full_input">
		</div>
		<div class="address_from_item">
			<label>휴대폰</label>
			<input type="text" name="receiver_phone" value="${addressVO.receiver_phone }" class="input full_input">
		</div>
		<c:if test="${addressVO.is_basic_address != 'y' }">
		<div class="address_from_item">
			<input type="checkbox" name="is_basic_address" id="is_basic_address" class="form_chk" value="y" >
			<label class="form_chk_label" for="is_basic_address">기본 배송지로 저장</label>			
		</div>
		</c:if>
		</form>
	</div>
	<div class="address_footer">
		<a href="javascript:form_address_edit.submit()" class="btn btn_primary">저장</a>
		<a href="${contextPath}/member/address/removeAddress.do?address_id=${addressVO.address_id}" class="btn btn_second">삭제</a>
	</div>
	</c:when>
	
	
	
	
	
	<c:otherwise> <!-- 배송지 입력 폼 -->
		<div class="address_top">
		<div class="d-flex">
			<h2>배송지 입력</h2>
		</div>
	</div>	
	<div class="address_body">	
		<form name="form_address_add" method="post" action="${contextPath}/member/address/addNewAddress.do">	
		<div class="address_from_item">
			<input type="hidden" name="jibunAddress" id="jibunAddress" >
			<input type="hidden" name="zipcode" id="zipcode" class="input full_input">
			<input type="button" onClick="execDaumPostcode()" class="btn btn_findAddr" value="주소검색">			
			<input type="text" name="roadAddress" id="roadAddress" placeholder="주소를 입력해주세요." class="input full_input">
			<input type="text" name="detalied_address" id="detalied_address" placeholder="나머지 주소를 입력해 주세요" class="input full_input">
		</div>		
		
		<div class="address_from_item">
			<label>받으실 분</label>
			<input type="text" name="receiver_name" class="input full_input">
		</div>
		<div class="address_from_item">
			<label>휴대폰</label>
			<input type="text" name="receiver_phone" class="input full_input">
		</div>
		<div class="address_from_item">
			<input type="checkbox" name="is_basic_address" id="is_basic_address" class="form_chk" value="y" >
			<label class="form_chk_label" for="is_basic_address">기본 배송지로 저장</label>			
		</div>
		</form>
	</div>
	<div class="address_footer">
		<a href="javascript:form_address_add.submit()" class="btn btn_primary">저장</a>
	</div>
	</c:otherwise>
	</c:choose>
</div>
