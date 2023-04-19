<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<c:set var="myOrderInfoList" value="${myOrderMap.myOrderInfoList}"/>
<c:set var="myOrderGoodsList" value="${myOrderMap.myOrderGoodsList}"/>
<c:set var="orderer" value="${myOrderMap.orderer}"/>
<c:set var="address" value="${myOrderMap.Address}"/>
<c:set var="usableCouponList" value="${myOrderMap.usableCouponList}"/>

<!-- 총주문 금액 -->
<c:set var="total_order_price" value="0" />
<!-- 총 상품수 -->
<c:set var="total_order_goods_qty" value="0" />
<!-- 총할인금액 -->
<c:set var="total_discount_price" value="0" />
<!-- 총 배송비 -->
<c:set var="total_delivery_price" value="0" />



<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
//포인트, 쿠폰 사용
const default_final = Number("${myOrderMap.final_goods_price}");  //할인 적용 전 최종 결제가격 (상품 금액 + 배송비)
var point_usage = $('input[name="point"]').val();
var coupon_usage = $('input[name="coupon"]').val();
var today = new Date();   
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours + "" +  minutes+ ""  + seconds +""  + milliseconds;


//약관 보기
function view_terms(viewName) {
	$.ajax({
		type : 'GET',
		url : "${contextPath}/member/viewModal.do",
		data : {
			viewName : viewName
		},
		success : function(data) {
			modal(data);
		}
	});
}

function modal(modalContent) {
	$('#modal').remove();
	
	var html = '';
	html += '<div id="modal_backdrop">';
	html += '<div class="modal" id="modal">';
	html += '<div class="modal_body">';
	html += modalContent;
	html += '</div>';
	html += '<div class="modal_footer">';
	html += '<button class="btn btn_primary" id="close_modal">확인</button>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	
	$('#outer_wrap').append(html);
	$('#close_modal').click(function(){
		$('#modal_backdrop').remove();
	});
}

//카카오페이 실행
function kakopay() {
	const IMP = window.IMP; // 생략 가능
	IMP.init("imp07611688"); // 예: imp00000000a
	var final_price = $('#final_goods_price').val();
    
    
    if(agree1.checked) {
    	IMP.request_pay({
    		pg: "kakaopay",
    	    pay_method: "card",
    	    merchant_uid: makeMerchantUid,   // 주문번호
    	    name: "결제",
    	    amount:final_price,   // 숫자 타입
    	    buyer_email: "${orderer.member_email}",
    	    buyer_name: "${orderer.member_name}",
    	    buyer_tel: "${orderer.member_phone}",
    	    buyer_addr: "${address.roadAddress} ${address.detalied_address}",
    	    buyer_postcode: "11111"
    	}, function (rsp) { // callback
    	  if (rsp.success) {
    	      //결제 성공 시 해당 정보를 컨트롤러로 보내서 DB에 저장해주고 결과 페이지로 보내야함
    	     	order_submit('${myOrderGoodsList.size()}');
    	    } else {
    	      // 결제 실패 시 로직
    	    	console.log(rsp);
    	    }
    	  });
    	
 	} else {
 		Swal.fire({
			html: '약관에 동의하셔야 주문이 가능합니다.'
		});
		agree1.focus();
		return false;
 	}
  
	
}

//주문하기
function order_submit(order_qty) {	
	 if(agree1.checked) {
		 var order_qty_input = document.createElement("input");
		 order_qty_input.name ="order_qty";
		 order_qty_input.value = order_qty;
	     
	     var order_id_input = document.createElement("input");
	     order_id_input.name = "order_id"; 
	     order_id_input.value = makeMerchantUid;
	     order_form.appendChild(order_id_input);
	     order_form.appendChild(order_qty_input);
	     order_form.submit();
		 
	 } else {
		 Swal.fire({
				html: '약관에 동의하셔야 주문이 가능합니다.'
			});
		agree1.focus();
		return false;
	 }	 
}

//모달 팝업
function modal(modalContent) {
	$('#modal').remove();
	
	var html = '';
	html += '<div id="modal_backdrop">';
	html += '<div class="modal" id="modal">';
	html += '<div class="modal_body">';
	html += modalContent;
	html += '</div>';
	html += '<div class="modal_footer">';
	html += '<button class="btn btn_primary" id="close_modal">확인</button>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	
	$('#outer_wrap').append(html);
	$('#close_modal').click(function(){
		$('#modal_backdrop').remove();
	});
}

//사용 가능한 모든 포인트 사용
function useAllPoint(point) {
	if(point == 0) {
		point = 0
	} else {
		$('input[name="point"]').val(point);
		$('.point_discount b span').text(point * -1);
		$('input[name="point"]').css('color', '#000');
		calcPrice();
	}
}
//일부 포인트 사용
function usePoint() {
	var point = $('input[name="point"]').val();
	if(point <= ${myOrderMap.usablePoint}) {
		$('.point_discount b span').text(point * -1);
		$('input[name="point"]').css('color', '#000');
		calcPrice();	
	} else {
		Swal.fire({
			html: '사용 가능한 포인트 금액 이상을 사용할 수 없습니다.'
		});
	}
	
}
$(document).ready(function(){
	$('.select_down').hide();
	$('.btn_coupon').click(function(){
		$('.select_down').show();
	});
});
//쿠폰 사용
function useCoupon(coupon_discount,coupon_name) {
	var coupon_discount_amount = 0;
	var total_goods_price = $('.total_goods_price span');
	var coupon = $('input[name="coupon"]').val();

	//할인 금액 계산해서 coupon_usage에 넣기
	$('input[name="coupon"]').val(Number(total_goods_price.text().replace(/[^\d]+/g, "")) * coupon_discount * -1) ;
	$('.select_down').hide();
	$('.btn_coupon > span').text(coupon_name);
	calcPrice();
}

//가격 계산
function calcPrice() {
	
	var total_goods_price = $('.total_goods_price span'); //상품가격
	var total_discount_amount =$('#total_discount_amount span'); //할인가격 ()
	var point = Number($('input[name="point"]').val());
	var coupon = Number($('input[name="coupon"]').val()); 
	
	$('.final_goods_price span').text((default_final - (point + coupon)));
	
	$('#final_goods_price').val(default_final - (point + coupon));
	total_discount_amount.text(point + coupon);
}

</script>

<div id="container" class="container-sm">
	<div class="page_title">
		<h3>주문/결제</h3>
	</div>

	<form name="order_form" action="${contextPath}/order/payToOrderGoods.do" method="post">
	<input type="hidden" name="cart_id" value="${myOrderMap.myCart_id}">
	<section id="order_product">
		<h3 class="section-title">주문 상품</h3>
		<div class="border-top">
			<ul>
				<c:forEach var="item" items="${myOrderGoodsList}" varStatus="status">
					<li class="d-flex order-item">
						<span class="goods_image">
							<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
								<img width="75" alt="" src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
								<input type="hidden" id="h_goods_id${status.index}" name="goods_id${status.index}" value="${item.goods_id }" /> 
								<input type="hidden" id="h_goods_fileName${status.index}" name="goods_fileName${status.index}" value="${item.goods_fileName }" />
							</a>
						</span>
						<span class="goods_name" style="flex:5">
							<a href="${pageContext.request.contextPath}/goods/goods.do?command=goods_detail&goods_id=${item.goods_id }"><b>${item.goods_name }</b></a>
							<input type="hidden" id="h_goods_name${status.index}" name="goods_name${status.index}" value="${item.goods_name }" />
						</span>
						<span class="goods_qty">			
							${myOrderInfoList[status.index].order_qty}개
							<input type="hidden" id="h_goods_qty" name="goods_qty${status.index}" value="${myOrderInfoList.get(status.index).order_qty}" />
						</span>
						<span class="goods_price">
							<c:choose>
								<c:when test="${item.sales_price != null && item.sales_price != 0}">
									<strong><span><fmt:formatNumber value="${item.sales_price}" pattern="#,###" />원</span></strong>
									<span class="text_muted"><fmt:formatNumber value="${item.goods_price}" pattern="#,###" />원</span>
									<input type="hidden" id="h_goods_price${status.index}" name="goods_price${status.index}" value="${item.sales_price}" /> 
								</c:when>
								<c:otherwise>
									<strong><span><fmt:formatNumber value="${item.goods_price}" pattern="#,###" />원</span></strong>
									<input type="hidden" id="h_goods_price${status.index}" name="goods_price${status.index}" value="${item.goods_price}" /> 
								</c:otherwise>
							</c:choose>					
						</span>
					</li>
					<c:set var="total_goods_price" value="${total_goods_price + item.goods_price *item.count}"/>
					<c:set var="total_sales_price" value="${total_sales_price + item.sales_price }"/>
					<c:set var="total_discount_amount" value="${total_sales_price - total_goods_price }"/>
					<c:if  test="${total_sales_price <= 0 }"><c:set var="total_discount_amount" value="0"/></c:if>					
				</c:forEach>
			</ul>
		</div>
	</section>
	
	<section id="orderer_info">
		<h3 class="section-title">주문자 정보</h3>
		<div class="border-top">
			<table>
				<tr class="dot_line">
					<td>보내는 분</td>					
					<td>
						${orderer.member_name}
						<input type="hidden" id="orderer_name" name="orderer_name" value="${orderer.member_name}" />
					</td>
				</tr>
				<tr class="dot_line">
					<td>휴대폰</td>
					<td>
						${orderer.member_phone}
						<input type="hidden" id="orderer_phone" name="orderer_phone" value="${orderer.member_phone}" />
					</td>
				</tr>
				<tr class="dot_line">
					<td>이메일</td>
					<td>
						${orderer.member_email}
						<input type="hidden" id="orderer_email" name="orderer_email" value="${orderer.member_email}" />
						<p style="color:#888; font-size: 12px;margin-top: 10px">이메일을 통해 주문처리과정을 보내드립니다. <br>정보변경은 마이페이지에서 가능합니다.</p>
					</td>
				</tr>

			</table>
		</div>
	</section>
	<section id="order_delivery">
		<h3 class="section-title">배송 정보</h3>
		<div class="border-top">
			<table>
				<tr class="dot_line">
					<td>배송지</td>
					<td>
						${address.roadAddress} ${address.detalied_address}
						<input type="hidden" id="address_id" name="address_id" value="${address.address_id}" />
					</td>
				</tr>
				<tr class="dot_line">
					<td>상세정보</td>
					<td>
						${orderer.member_name}, ${orderer.member_phone}
						<a href="javascript:change_addr()" class="btn btn_edit">수정</a>
						<input type="hidden" id="receiver_name" name="receiver_name" value="${orderer.member_name}" />
						<input type="hidden" id="receiver_phone" name="receiver_phone" value="${orderer.member_phone}" />
					</td>
				</tr>
				
			</table>
		</div>
	</section>
	<div class="order_wrap d-flex" style="justify-content: space-between">
		<div class="order_leftbox">
			<section id="order_discount">
				<h3 class="section-title">쿠폰/적립금</h3>
				<div class="border-top">
					<table>
						<tr class="dot_line">
							<td>쿠폰 적용</td>
							<td>
								<div class="btn_coupon <c:if test="${empty usableCouponList}">empty</c:if>">
									<span>사용가능 쿠폰 : ${usableCouponList.size() }장</span>
									<ul class="select_down">
										<c:forEach var="coupon" items="${usableCouponList}">
										<li><a href="javascript:useCoupon(${coupon.coupon_discount}, '${coupon.coupon_name }')">${coupon.coupon_name}</a></li>
										</c:forEach>
									</ul>						
								</div>
								<input type="hidden" name="coupon" value="0">
							</td>
						</tr>
						<tr class="dot_line">
							<td>적립금 적용</td>
							<td>
								<input name="point" value="0" class="input input_point"> 
								<a class="btn btn_point" href="javascript:usePoint()">사용</a>
								<a class="btn btn_point" href="javascript:useAllPoint(${myOrderMap.usablePoint})">모두사용</a>								
								<p style="margin-top:10px; font-size: 12px">사용가능 적립금 : <b>${myOrderMap.usablePoint}</b>원</p>
							</td>
						</tr>

					</table>
				</div>
			</section>

			<section id="order_pay">
				<h3 class="section-title">결제 수단</h3>
				<div class="border-top">
					<table>
						<tr class="dot_line">
							<td>결제수단 선택</td>
							<td>
								<a href="javascript:kakopay()" class="btn btn_pay">카카오페이</a>
								<a href="#" class="btn btn_pay">신용카드</a>
								<a href="javascript:order_submit(${myOrderGoodsList.size()})" class="btn btn_pay">무통장 입금</a>
							</td>
						</tr>
					</table>
				</div>
			</section>

			<section id="order_agree">
				<h3 class="section-title">개인정보 수집/제공</h3>
				<div class="border-top" style="overflow:hidden;height:auth">
					<div style="margin-bottom:10px">개인정보 수집·이용 및 처리 동의<a href="javascript:view_terms('terms')" class="text-underline" style="float:right">약관보기</a></div>
					<div>전자지급 결제대행 서비스 이용약관 동의<a href="javascript:view_terms('privacyPolicy')" class="text-underline" style="float:right">약관보기</a></div>
				</div>
				<h2>
					<input type="checkbox" id="agree1" class="form_chk"><label for="agree1" class="form_chk_label">위 내용을 확인 하였으며 결제에 동의 합니다.</label>
				</h2>
			</section>
		</div>
		<div class="order_rightbox">
			<div class="sticky">
			<h1>결제 금액</h1>
			<ul>
				<li>
					<span><b>주문금액</b></span>
					<span>
						<b class="final_goods_price"><span><fmt:formatNumber value="${myOrderMap.final_goods_price}" pattern="#,###" /></span>원</b>
						<input type="hidden" id="final_goods_price" name="final_goods_price" value="${myOrderMap.final_goods_price}" />
					</span>
				</li>
				<li style="padding-left: 20px; margin:5px 0; color:#888;font-size:14px">
					<span>상품금액</span>
					<span class="total_goods_price"><span><fmt:formatNumber value="${total_goods_price}" pattern="#,###" /></span>원</span>
				</li>
				<li style="padding-left: 20px; margin:5px 0; color:#888;font-size:14px;">
					<span>상품할인금액</span>
					<span id="total_discount_amount"><span><fmt:formatNumber value="${total_discount_amount }" pattern="#,###" /></span>원</span>
				</li>
				<li>
					<span><b>배송비</b></span>
					<span>
						<b>
							<c:choose>
							<c:when test="${myOrderMap.final_goods_price < 40000}"><span><fmt:formatNumber value="3000" pattern="#,###" /></span>원</c:when>
							<c:otherwise>0원</c:otherwise>
							</c:choose>
						</b>
					</span>
				</li>
				<li>
					<span><b>쿠폰할인</b></span>
					<span><b><span><fmt:formatNumber value="0" pattern="#,###" /></span>원</b></span>
				</li>
				<li>
					<span><b>적립금 사용</b></span>
					<span class="point_discount"><b><span><fmt:formatNumber value="0" pattern="#,###" /></span>원</b></span>
				</li>
			</ul>
			</div>
		</div>
	</div>
	</form>
</div>

