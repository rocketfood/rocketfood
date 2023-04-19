<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<div class="container_pop">
	<div class="address_top">
		<div class="d-flex">
			<h2>배송지</h2>
			<span>배송지에 따라 상품정보 및 배송유형이 달라질 수 있습니다.</span>
		</div>
	</div>
	
	<div class="address_body">	
		<div class="d-flex address_head" >
			<span class="check text-center">선택</span> <span class="addr text-center">배송 정보</span> <span class="edit text-center">수정</span>
		</div>	
		
		<div class="address_cont">	
		<c:choose>
		<c:when test="${empty addressList}">	
			<div class="text-center" style="height:100px; line-height: 100px; font-size: 14px">등록된 주소가 없습니다.</div>
		</c:when>
		
		<c:otherwise>
		<c:forEach var="item" items="${addressList}" varStatus="status">
			<div class="d-flex address_item">	
				<div class="check text-center"><input type="checkbox" onClick="location.href='${contextPath}/member/address/updateBasicAddress.do?address_id=${item.address_id}'" class="form_chk" name="check_addr" id="addr${status.index}" <c:if test="${item.is_basic_address == 'y' }">checked</c:if>><label class="form_chk_label" for="addr${status.index}"></label></div>
				<div class="addr">
					<c:if test="${item.is_basic_address == 'y' }"><span class="basic">기본배송지</span></c:if>
					<p class="address">${item.roadAddress} ${item.detalied_address} </p>
					<p class="receiver">${item.receiver_name} <span class="bar">|</span> ${item.receiver_phone }</p>
				</div>
				<div class="edit text-center"><a href="${contextPath}/member/address/editAddress.do?address_id=${item.address_id}"><img src="${contextPath}/resources/image/icon_edit.svg"></a></div>
			</div>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>
<div class="address_footer">
		<a href="${contextPath}/member/address/addAddressForm.do" class="btn btn_bottom">+ 새 배송지 추가</a>
	</div>