<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
      pageContext.setAttribute("br", "<br/>"); //br 태그
%> 
<script>
/*상품 필터링 관련 자바스크립트 구현*/
function layout(items) {
	items.forEach( item => {
		var template = "<li class='goods_item'>";
		template += "<div class='goods_info'>";
		template += "<div class='goods_image'>";
		template += "<img width='249' src='${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}'></div>";
		template += "<div class='goods_description'>";
		template += "<span>로켓배송</span><h4 class='goods_name'>[${item.business_name}] ${item.goods_name}</h4><p class='desc'>${item.short_description}</p>";
		template += "<div class='goods_price'><span><fmt:formatNumber value='${item.goods_price}' type='number' var='goods_price' />${goods_price}원</span></div>";
		template += "<div class='goods_review'><span>후기</span></div></div></li>"
		$('#items').append(template);
	});
}

</script>
<style>
 	#category_section {width: 1200px; padding:30px; margin-top: 28px; border: 1px solid rgb(226, 226, 226);line-height: 20px; display: flex; flex-wrap: wrap; justify-content: space-around	}
 	.category_wrap {width: 900px; margin: 0 auto;}
 	.category_box {display: flex; flex-wrap: wrap; justify-content: flex-start; line-height: 2.5;}
 	.cate_item {width: 25%;}
 	.cate_item.here a {font-weight:bold; color:#7749eb}
 	#goods_area {width: 1200px;display: flex;margin-top: 50px;}
 	.filter_container {width: 20%; display:none}
 	.filterLabel {display: flex;justify-content: space-between;padding: 16px 0;}
 	.filterLabel:first-child {padding-bottom: 20px;padding-top: 0;}	
 	.filterList li {margin: 20px 0;} 	
 	
 	.goodsList_container {width: 100%;padding-left: 40px;} 
 	.goodsSort_wrapper {width: 100%;display: flex;justify-content: space-between;}
 	.sortNameList {display: flex;}	
 	.verticalVar {border-left: 1px solid rgb(226, 226, 226);padding: 0 8px; }
 	.sortNameList li:first-child .verticalVar {border:0}

  	.icon_interval {margin-right: 6px;font-size: 18px;}
 	.range_border {border-bottom: 1px solid rgb(238, 238, 238);}
 	.sideColor {color: rgb(226, 226, 226);}
 	
 	#goods_list_wrap {margin-bottom: 80px}
 	.goods_item {margin: 31px 18px}
 	.goods_info {cursor: pointer}
 	.goods_item .goods_description {padding: 14px 10px 0 0}
 	.goods_description span {font-size: 14px;color: rgb(153, 153, 153); line-height: 19px; letter-spacing: -0.5px;}
 	.goods_name {color:#000; max-height: 58px; font-size: 16px; line-height: 24px; letter-spacing: normal; display: -webkit-box; overflow: hidden; word-break: break-all; white-space: normal; -webkit-line-clamp: 2;-webkit-box-orient: vertical}
 	.goods_description .desc{padding-top: 4px; font-size: 12px; color: rgb(153, 153, 153); line-height: 18px;letter-spacing: normal; display: -webkit-box; overflow: hidden; word-break: break-all; white-space: normal; -webkit-line-clamp: 2; -webkit-box-orient: vertical;}
 	.goods_price {display: flex; flex-direction: column; padding-top: 8px;}
 	.goods_price span {font-weight: 800; font-size: 16px; line-height: 24px; white-space: nowrap; letter-spacing: -0.5px; color:#000}
 	.goods_review {display: flex; padding-top: 8px; font-weight: 500; font-size: 12px; color: rgb(153, 153, 153); line-height: 17px;}
 </style>


<div id="container">
	<div class="page_title">
		<h3>${categoryInfo.category_name}</h3>
	</div>
	<div id="items"></div>
	<c:if test="${not empty other_categoryMap}">
	<section id="category_section">
		<div class="category_wrap">
			<ul class="category_box d-flex">				
				<c:set var="key" value="${other_categoryMapKey}"></c:set>
				<li class="cate_item <c:if test='${categoryInfo.category_id == key}'>here</c:if>"><a href="${contextPath}/goods/goodsList.do?category_id=${key}">전체보기</a></li>
				<c:forEach var="item" items="${other_categoryMap[key]}">				
				<li class="cate_item <c:if test='${item.category_name == categoryInfo.category_name}'>here</c:if>"><a href="${contextPath}/goods/goodsList.do?category_id=${item.category_id}">${item.category_name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</section>
	</c:if>
	
	<section id="goods_area">
		<div class="filter_container">
			<div class="filterLabel range_border">
				<span>필터</span>
				<a href="javascript:filter_reset();"><i class="fa-solid fa-rotate-right sideColor"></i><span class="sideColor">초기화</span></a>
			</div>
			<div class="filterLabel"><span>브랜드</span><i class="fa-solid fa-angle-down sideColor"></i></div>
			<div>
				<ul class="filterList range_border">
					<li><label class="form_chk_label" for="브랜드명"><input type="checkbox" class="form_chk" id="브랜드명">브랜드명</label></li>

				</ul>
			</div>
			<div class="filterLabel"><span>가격</span><i class="fa-solid fa-angle-down sideColor"></i></div>
			<div>
				<ul class="filterList range_border">
					<li><a href="#"><i class="fa-regular fa-circle-check sideColor icon_interval"></i><span class="">2,690원 미만</span><span class="sideColor">33</span></a></li>
				</ul>
			</div>
			<div class="filterLabel"><span>혜택</span><i class="fa-solid fa-angle-down sideColor"></i></div>
			<div>
				<ul class="filterList range_border">
					<li><a href="#"><i class="fa-regular fa-circle-check sideColor icon_interval"></i><span class="">할인상품</span><span class="sideColor">6</span></a></li>
					<li><a href="#"><i class="fa-regular fa-circle-check sideColor icon_interval"></i><span>한정수량</span><span class="sideColor">3</span></a></li>
				</ul>
			</div>
			<div class="filterLabel"><span>유형</span><i class="fa-solid fa-angle-down sideColor"></i></div>
			<div>
				<ul class="filterList range_border">
					<li><a href="#"><i class="fa-regular fa-circle-check sideColor icon_interval"></i><span class="">Rocket Only</span><span class="sideColor">2</span></a></li>
				</ul>
			</div>
		</div>
		<div class="goodsList_container">
			<div class="goodsSort_wrapper">
				<div class="total_goodsNum">

				</div>
				<ul class="sortNameList">
					<li><a href="${contextPath}/goods/goodsList.do?category_id=${categoryInfo.category_id}&goods_sort=new"><span class="verticalVar">신상품순</span></a></li>
					<li><a href="${contextPath}/goods/goodsList.do?category_id=${categoryInfo.category_id}&goods_sort=wish_count"><span class="verticalVar">찜한숫자순</span></a></li>
					<li><a href="${contextPath}/goods/goodsList.do?category_id=${categoryInfo.category_id}&goods_sort=sales_count"><span class="verticalVar">판매량순</span></a></li>
					<li><a href="${contextPath}/goods/goodsList.do?category_id=${categoryInfo.category_id}&goods_sort=price_asc"><span class="verticalVar">낮은가격순</span></a></li>
					<li><a href="${contextPath}/goods/goodsList.do?category_id=${categoryInfo.category_id}&goods_sort=price_desc"><span class="verticalVar">높은가격순</span></a></li>
				</ul>
			</div>
			
			<div id="goods_list_wrap">
				<ul class="d-flex goods_list_box">
					<c:forEach var="item" items="${goodsList}"> 
						<li class="goods_item">
							<div onClick="location.href='${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}'" class="goods_info">
								<div class="goods_image">
									<img width="249" src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
								</div>
								<div class="goods_description">
									<span>로켓배송</span>
									<h4 class="goods_name">[${item.business_name}] ${item.goods_name}</h4>	
									<p class="desc">${item.short_description}</p>						
								</div>
								<div class="goods_price">
									<span><fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />${goods_price}원</span>
								</div>
							<div class="goods_review"><span>후기</span></div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>			
		</div>
	</section>						
</div>