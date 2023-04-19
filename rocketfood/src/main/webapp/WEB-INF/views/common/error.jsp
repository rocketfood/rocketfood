<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<script>
	window.onload = function() {
		${message}
	}
	
	function swal(message) {
		Swal.fire({icon: 'error',
			title: '페이지 에러',
			text: message,	}).then(function(){
			location.href='${contextPath}/main/main.do';
			})
	}
</script>
