<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
	
<nav>
<ul>

<c:if test="${side_menu=='csCenter' }">
   <li>
		<h3 class="sidebar_title">고객센터</h3>
		<ul class="sidebar_menuBox">
			<li class="sidebar_menu"><a href="${contextPath}/board/boardList.do?board_id=notice">공지사항</a></li>
			<li class="sidebar_menu"><a href="${contextPath}/board/boardList.do?board_id=faq">자주하는 질문</a></li>
			<li class="sidebar_menu"><a href="${contextPath}/board/boardList.do?board_id=qa">1:1 문의</a></li>
			<li class="sidebar_menu"><a href="#">대량구매 문의</a></li>
		</ul>
	</li>
</c:if>
<c:if test="${side_menu=='my_page' }">
	<li>
		<h3 class="sidebar_title">마이페이지</h3>
		<ul class="sidebar_menuBox">
			<li class="sidebar_menu"><a href="${contextPath}/mypage/listMyOrderHistory.do">주문내역/배송 조회</a></li>
			<li class="sidebar_menu"><a href="#">반품/교환 신청 및 조회</a></li>
			<li class="sidebar_menu"><a href="#">취소 주문 내역</a></li>
			<li class="sidebar_menu"><a href="#">세금 계산서</a></li>
		</ul>
	</li>	
</c:if>
	<li>
		<h3 class="sidebar_title">고객센터</h3>
		<ul class="sidebar_menuBox">
			<li class="sidebar_menu"><a href="${contextPath}/board/boardList.do?board_id=notice">공지사항</a></li>
			<li class="sidebar_menu"><a href="${contextPath}/board/boardList.do?board_id=faq">자주하는 질문</a></li>
			<li class="sidebar_menu"><a href="${contextPath}/board/boardList.do?board_id=qa">1:1 문의</a></li>
			<li class="sidebar_menu"><a href="#">대량구매 문의</a></li>
		</ul>
	</li>
</ul>
</nav>
<div class="clear"></div>
