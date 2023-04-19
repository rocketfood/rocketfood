<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<script>
$(document).ready(function(){
	//메인 배너 슬라이드
	$('.main_slider').slick({
		arrows: false,
		dots: false,
		autoplay: true
	});
	
	$('.recommend_goods').slick({
		arrows: true,
		dots: false,
		autoplay: true,
		slidesToShow: 4,
  		slidesToScroll: 4,
  		infinite: true
	});
	
	$('.limited_goods').slick({
		arrows:false,
		dots: false,
		autoplay: true,
		slidesToShow: 3,
  		slidesToScroll: 1,
  		infinite: true,
	});
	$(".custom-prev").click(function () {
		$(".limited_goods").slick("slickPrev");
	});

	$(".custom-next").click(function () {
		$(".limited_goods").slick("slickNext");
	});
});
</script>


<ul class="main_slider">	 	
  	<li><img src="${contextPath}/resources/image/test1.jpg"></li>
	<li><img src="${contextPath}/resources/image/test2.jpg"></li>
	<li><img src="${contextPath}/resources/image/test3.jpg"></li> 
</ul>

<div id="container">
	<div class="sec_title">
		<h5>이런 상품 어때요?</h5>
		<div class="text-right"><a href="#" class="btn_more">more</a></div>
	</div>
	
	<ul class="recommend_goods">
    <c:set  var="goods_count" value="0" />
	<c:forEach var="item" items="${goodsMap.best}">
		<c:set  var="goods_count" value="${goods_count+1 }" />
		<li class="goods_item">
			<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">			 
				<img src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
				<span class="title">${item.goods_name }</span>
				<span class="price"><fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />${goods_price}원</span>
			</a>
		</li>
     </c:forEach>
     </ul>
     
     <div class="sub_banner">
     	<a href="#"><img src="${contextPath}/resources/image/test4.jpg"></a>
     </div>
     
     <div class="limit_wrap">
     	<div class="sec_title text-left">
			<h5>오늘의 특가상품</h5>
			<p>한정수량으로만 판매되는 특가상품<br>망설이면 늦어요!</p>
			<div class="btn_wrap">
				<span><button class="slick-prev custom-prev"></button></span>
				<span><button class="slick-next custom-next"></button></span>
			</div>
		</div>
		<ul class="limited_goods">
    		<c:set  var="goods_count" value="0" />
			<c:forEach var="item" items="${goodsMap.recommend}">
				<c:set  var="goods_count" value="${goods_count+1 }" />
				<li class="goods_item">
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">			 
						<img src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
						<span class="title">${item.goods_name }</span>
						<span class="price"><fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />${goods_price}원</span>
					</a>
				</li>
     		</c:forEach>
     	</ul>
     </div>
     
     <div class="sub_banner">
     	<a href="#"><img src="${contextPath}/resources/image/test5.jpg"></a>
     </div>
     
     <div class="sec_title">
		<h5>지금 가장 핫한 상품!</h5>
		<div class="text-right"><a href="#" class="btn_more">more</a></div>
	</div>
	
	<ul class="hot_goods">
    <c:set  var="goods_count" value="0" />
	<c:forEach var="item" items="${goodsMap.best}" begin="0" end="7" step="1">
		<c:set  var="goods_count" value="${goods_count+1 }"  />
		<li class="goods_item">
			<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">			 
				<img src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
				<span class="title">${item.goods_name }</span>
				<span class="price"><fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />${goods_price}원</span>
			</a>
		</li>
     </c:forEach>
     </ul>
</div>
   