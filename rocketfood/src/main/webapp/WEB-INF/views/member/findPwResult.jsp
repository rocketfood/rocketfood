<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<style>
	.btn_wrap {margin: 80px auto }
	.text-muted {margin-bottom:50px; margin-top:10px;}
	.border-box {padding: 50px; width: 400px; border:1px solid #efefef; margin: 0 auto}
	.border-box strong {margin-left: 10px; color:#7749eb}
</style>

<div id="container">
	<div class="page_title">
		<h3>비밀번호 찾기 결과</h3>
	</div>
	<div class="text-center">
		<h2 style="color:#191919">고객님의 비밀번호 변경을 완료했습니다.</h2>
		<p class="text-muted">비밀번호 확인 후 로그인해 주세요.</p>
	</div>
	
	<div class="btn_wrap text-center">
		<a href="${contextPath}/main/main.do" class="btn btn_second d-inline-block">메인으로 가기</a>
		<a href="${contextPath}/member/loginForm.do" class="btn btn_primary d-inline-block">로그인 하기</a>
	</div>
	
</div>
