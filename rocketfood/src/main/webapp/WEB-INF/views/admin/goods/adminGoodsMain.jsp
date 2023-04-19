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
<script>
function search_goods_list(fixeSearchPeriod){
	var formObj=document.createElement("form");
	var i_fixedSearch_period = document.createElement("input");
	i_fixedSearch_period.name="fixedSearchPeriod";
	i_fixedSearch_period.value=searchPeriod;
    formObj.appendChild(i_fixedSearch_period);
    document.body.appendChild(formObj); 
    formObj.method="get";
    formObj.action="${contextPath}/admin/goods/adminGoodsMain.do";
    formObj.submit();
}


function  calcPeriod(search_period){
	var dt = new Date();
	var beginYear,endYear;
	var beginMonth,endMonth;
	var beginDay,endDay;
	var beginDate,endDate;
	
	endYear = dt.getFullYear();
	endMonth = dt.getMonth()+1;
	endDay = dt.getDate();
	if(search_period=='today'){
		beginYear=endYear;
		beginMonth=endMonth;
		beginDay=endDay;
	}else if(search_period=='one_week'){
		beginYear=dt.getFullYear();
		beginMonth=dt.getMonth()+1;
		dt.setDate(endDay-7);
		beginDay=dt.getDate();
		
	}else if(search_period=='two_week'){
		beginYear = dt.getFullYear();
		beginMonth = dt.getMonth()+1;
		dt.setDate(endDay-14);
		beginDay=dt.getDate();
	}else if(search_period=='one_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-1);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}else if(search_period=='two_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-2);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}else if(search_period=='three_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-3);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}else if(search_period=='four_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-4);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}
	
	if(beginMonth <10){
		beginMonth='0'+beginMonth;
		if(beginDay<10){
			beginDay='0'+beginDay;
		}
	}
	if(endMonth <10){
		endMonth='0'+endMonth;
		if(endDay<10){
			endDay='0'+endDay;
		}
	}
	endDate=endYear+'-'+endMonth +'-'+endDay;
	beginDate=beginYear+'-'+beginMonth +'-'+beginDay;
	//alert(beginDate+","+endDate);
	return beginDate+","+endDate;
}
</script>
</head>
<div id="container">
	<div class="page_title">
		<h3>상품 조회</h3>
	</div>

	<form method="post">
		<table>
			<tr>
				<td><input type="radio" name="r_search" checked /> 등록일로조회
					&nbsp;&nbsp;&nbsp; <input type="radio" name="r_search" />상세조회
					&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td><select name="curYear">
						<c:forEach var="i" begin="0" end="5">
							<c:choose>
								<c:when test="${endYear==endYear-i}">
									<option value="${endYear}" selected>${endYear}</option>
								</c:when>
								<c:otherwise>
									<option value="${endYear-i }">${endYear-i }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>년 <select name="curMonth">
						<c:forEach var="i" begin="1" end="12">
							<c:choose>
								<c:when test="${endMonth==i }">
									<option value="${i }" selected>${i }</option>
								</c:when>
								<c:otherwise>
									<option value="${i }">${i }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>월 <select name="curDay">
						<c:forEach var="i" begin="1" end="31">
							<c:choose>
								<c:when test="${endDay==i}">
									<option value="${i }" selected>${i }</option>
								</c:when>
								<c:otherwise>
									<option value="${i }">${i }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>일 &nbsp;이전&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="javascript:search_goods_list('today')"> <img
						src="${contextPath}/resources/image/btn_search_one_day.jpg">
				</a> <a href="javascript:search_goods_list('one_week')"> <img
						src="${contextPath}/resources/image/btn_search_1_week.jpg">
				</a> <a href="javascript:search_goods_list('two_week')"> <img
						src="${contextPath}/resources/image/btn_search_2_week.jpg">
				</a> <a href="javascript:search_goods_list('one_month')"> <img
						src="${pageContext.request.contextPath}/resources/image/btn_search_1_month.jpg">
				</a> <a href="javascript:search_goods_list('two_month')"> <img
						src="${contextPath}/resources/image/btn_search_2_month.jpg">
				</a> <a href="javascript:search_goods_list('three_month')"> <img
						src="${contextPath}/resources/image/btn_search_3_month.jpg">
				</a> <a href="javascript:search_goods_list('four_month')"> <img
						src="${contextPath}/resources/image/btn_search_4_month.jpg">
				</a> &nbsp;까지 조회</td>
			</tr>
			<tr>
				<td>
					<select name="search_condition" disabled>
						<option value="전체" selected>전체</option>
						<option value="제품번호">상품번호</option>
						<option value="제품이름">상품이름</option>
						<option value="제조사">제조사</option>
					</select> 
					<input type="text" size="30" disabled /> 
					<input type="button" value="조회" disabled />
				</td>
			</tr>
			<tr>
				<td>조회한 기간:<input type="text" size="4" value="${beginYear}" />년
					<input type="text" size="4" value="${beginMonth}" />월 
					<input type="text" size="4" value="${beginDay}" />일 &nbsp; ~ 
					<input type="text" size="4" value="${endYear }" />년 
					<input type="text" size="4" value="${endMonth }" />월 
					<input type="text" size="4"	value="${endDay }" />일
				</td>
			</tr>
		</table>
	</form>
	


<ul class="list_view">
	<li class="d-flex">
				<span>상품번호</span>
				<span>상품이름</span>
				<span>상품가격</span>
				<span>입고일자</span>
			</li>
   <c:choose>
     <c:when test="${empty newGoodsList }">			
			<li class="d-flex">
		       <span class="emtpy text-center">조회된 상품이 없습니다.</span>
		     </li>
	 </c:when>
	 <c:otherwise>
     <c:forEach var="item" items="${newGoodsList }">
			 <li class="d-flex" style="justify-content:space-between">       
				<span><strong>${item.goods_id }</strong></span>
				<span><img width="75" alt="" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}"></span>
				<span><a href="${pageContext.request.contextPath}/admin/goods/modifyGoodsForm.do?goods_id=${item.goods_id}"><strong>${item.goods_name } </strong></a> </span>
				<span><strong>${item.business_name }</strong> </span>
				<span> <strong>${item.sales_price }</strong></span>
				<span><strong>${item.created_date }</strong></span>
			</li>
	</c:forEach>
	</c:otherwise>
  </c:choose>
           <li>
             <span>
                 <c:forEach   var="page" begin="1" end="10" step="1" >
		         <c:if test="${section >1 && page==1 }">
		          <a href="${contextPath}/admin/goods/adminGoodsMain.do?chapter=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp; &nbsp;</a>
		         </c:if>
		          <a href="${contextPath}/admin/goods/adminGoodsMain.do?chapter=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
		         <c:if test="${page ==10 }">
		          <a href="${contextPath}/admin/goods/adminGooodsMain.do?chapter=${section+1}&pageNum=${section*10+1}">&nbsp; next</a>
		         </c:if> 
	      		</c:forEach> 
     		</span>
     	</li>
	</ul>

<h3>상품등록하기</h3>
<div id="search">
	<form action="${contextPath}/admin/goods/addNewGoodsForm.do">
		<input   type="submit" value="상품 등록하기">
	</form>
</div>
</div>