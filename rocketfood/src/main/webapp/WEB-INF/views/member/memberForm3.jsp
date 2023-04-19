<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<style>
	.btn_wrap {margin: 80px auto }
</style>

<div id="container">
	<div class="page_title">
		<h3>회원가입 완료</h3>
	</div>
	<div class="progress-wrap">
		<ul>
			<li>01 회원선택</li>
			<li>02 약관동의 및 정보입력</li>
			<li class="here last">03 가입완료</li>
		</ul>
	</div>
	
	<div class="text-center">
		<img src="${contextPath}/resources/image/img_bi.png" width="200" style="margin-bottom:50px">
		<h2 style="margin-bottom:10px;color:#191919" >로켓푸드 가입을 축하드립니다.</h2>
		<p>신규회원을 위한 쿠폰을 다운 받아 이용해 보세요!</p>
	</div>
	
	
	<div class="btn_wrap text-center">
		<a href="${contextPath}/main/main.do" class="btn btn_second d-inline-block">메인으로 가기</a>
		<a href="${contextPath}/member/loginForm.do" class="btn btn_primary d-inline-block">로그인 하기</a>
	</div>
	
</div>
