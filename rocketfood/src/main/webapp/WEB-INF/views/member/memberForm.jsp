<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div id="container">
	<div class="page_title">
		<h3>회원가입</h3>
	</div>
	<div class="progress-wrap">
		<ul>
			<li class="here">01 회원선택</li>
			<li>02 약관동의 및 정보입력</li>
			<li class="last">03 가입완료</li>
		</ul>
	</div>
	<div class="square-wrap">
		<form action="${contextPath}/member/addMember.do" method="post">
			<ul>
	 			<li class="square-item puple-square text-center">
	 				<a href="${contextPath}/member/memberForm1.do">
	 					<img src="${contextPath}/resources/image/icon_man.png">	 					
	 					일반 회원
	 				</a>
	 			</li>
				<li class="square-item deep-purple-square text-center">
					<a href="${contextPath}/member/memberForm2.do">
						<img src="${contextPath}/resources/image/icon_business.png">
						사업자 회원
					</a>
				</li>
			</ul>  
		</form>
	</div>
</div>