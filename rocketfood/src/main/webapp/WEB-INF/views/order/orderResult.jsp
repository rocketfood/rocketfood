<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<style>
	.order_info_wrap {width: 300px; background:#f5f5f5; margin:80px auto; padding:50px 30px}
	.order_info_wrap .btn_wrap {margin-bottom:0; margin-top: 50px}
	.order_info_wrap .btn_wrap .btn {width: 100%}
</style>
<div id="container" class="container-sm">

	<div class="order_info_wrap">
		<div class="text-center">
			<h2 style="margin-bottom: 10px; color: #191919">${memberInfo.member_id }님의<br>주문이 완료 되었습니다!</h2>
		</div>
		
		<div class="text-center" style="margin-top:60px">
			<div class="d-flex" style="justify-content: space-between; margin-bottom:10px">
				<span>결제금액</span>
				<span><fmt:formatNumber value="${final_goods_price}" pattern="#,###" />원</span>
			</div>
			<div class="d-flex" style="justify-content: space-between;">
				<span>적립금</span>
				<span><fmt:formatNumber value="${new_point}" pattern="#,###" />원</span>
			</div>
		</div>

		<div class="btn_wrap text-center">
			<a href="#" class="btn btn_primary d-inline-block">주문 상세보기</a> <a
				href="${contextPath}/main/main.do"
				class="btn btn_second d-inline-block">쇼핑 계속하기</a>
		</div>
	</div>
</div>



