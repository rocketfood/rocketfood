<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">

</head>
<body>
	<H3>카테고리 조회</H3>

<table class="list_view">
		<thead align=center >
			<tr style="background:#33ff00" >
				<td>카테고리 번호</td>
				<td>카테고리 이름</td>
			</tr>
		</thead>
   <c:choose>
     <c:when test="${empty categoryList }">			
			<tr>
		       <td colspan=8 class="fixed">
				  <strong>조회된 카테고리가 없습니다.</strong>
			   </td>
		     </tr>
	 </c:when>
	 <c:otherwise>
     <c:forEach var="item" items="${categoryList }">
			 <tr>       
				<td>${categoryList.category_id}</td>
				<td>${categoryList.category_name}</td>
			</tr>
	</c:forEach>
	</c:otherwise>
  </c:choose>
	</table>
</body>
</html>