<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="myCartGoodsList"  value="${cartMap.myCartGoodsList}"  />
<c:set var="myGoodsList"  value="${cartMap.myGoodsList}"  />


<c:set  var="totalGoodsPrice" value="0" />  <!--주문 개수 -->
<c:set  var="totalDiscountedAmount" value="0" /> <!-- 총 할인금액 -->

<script type="text/javascript">
window.onpageshow = function(event) {
    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
   // Back Forward Cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우
    }

}

//체크박스를 클릭했을 때 발생하는 금액 계산 함수 -> 클릭할 시 전체 폼을 기준으로 계산하도록 수정
function calcGoodsPrice(){
	var checked_goods = document.querySelectorAll('input[name="checked_goods"]:checked'); //체크된 체크박스를 모두 선택
	var goods_qty = checked_goods.length; //선택된 체크박스의 숫자
	
	var totalNum = document.getElementById("totalGoodsNum"); //총 상품 수
	var totalGoodsPrice_text = document.getElementById("totalGoodsPrice"); // 상품 금액
	var totalDiscountedAmount_text = document.getElementById("totalDiscountedAmount"); //상품 할인 금액
	var totalDeliveryPrice_text = document.getElementById("totalDeliveryPrice"); //배송비
	var final_totalPrice_text = document.getElementById("finalTotalPrice"); //결제예정금액 (상품금액 - 상품 할인 금액 + 배송비)	
	var deliverNotice = document.querySelector('.deliveryFee_notice');
	var totalGoodsPrice = 0;
	var totalDiscountedAmount = 0;
	var totalDeliveryPrice = 0;
	var final_total_price = 0;
	
		totalNum = goods_qty; //총 상품 수는 체크된 상품의 수
		if(totalDeliveryPrice < 0){ // 만약 배송비가 0보다 작다면 배송비는 0으로 세팅
			totalDeliveryPrice  = 0;
		}
		
		if(totalNum > 0) { //선택된 상품 수가 1개 이상이라면
			var sales_price= [];
			var goods_price= [];
			var goods_qty = document.querySelectorAll('[name="cart_goods_qty"]');

			for(var i=0; i<totalNum; i++){
				if(checked_goods[i].checked == true){ //각 체크박스를 확인해서 체크가 되어있다면
					
					var goods_qty_value = goods_qty[i].value;
				
					//세일가격이 있다면 상품 가격은 세일 가격으로 계산하고 상품 가격에서 세일 가격을 뺀 금액을 totalDiscountedAmount에 더한다.								
					if(document.getElementById("sales_price"+[i]) != null){
						goods_price[i] = parseInt(document.getElementById("sales_price"+[i]).innerText.replace(/,/g , '')) * Number(goods_qty_value);
						totalDiscountedAmount = totalDiscountedAmount + ( parseInt(document.getElementById("goods_price"+[i]).innerText.replace(/,/g , '')) - parseInt(document.getElementById("sales_price"+[i]).innerText.replace(/,/g , '')) );
					} else {
						goods_price[i] = parseInt(document.getElementById("goods_price"+[i]).innerText.replace(/,/g , '')) * Number(goods_qty_value);
					}					
					
					totalGoodsPrice += goods_price[i];
					
					if(totalGoodsPrice < 40000){
						totalDeliveryPrice = Number(3000);
						document.querySelector('.delivery_notice').style.display = 'block'; 	
						var price = 40000 - totalGoodsPrice
						deliverNotice.innerText = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

					} else {
						totalDeliveryPrice = 0;
						document.querySelector('.delivery_notice').style.display = 'none'; 	
					}
					final_total_price = totalGoodsPrice + totalDeliveryPrice - totalDiscountedAmount;
					
				}
			}
		} else {
			totalGoodsPrice = 0;
			totalDiscountedAmount = 0;
			totalDeliveryPrice = 0;
			final_total_price = 0;
			
		}
			
		//계산된 값을 입력
		totalGoodsPrice_text.innerHTML = totalGoodsPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); 
		totalDiscountedAmount_text.innerHTML = totalDiscountedAmount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); 
		totalDeliveryPrice_text.innerHTML = totalDeliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); 
		final_totalPrice_text.innerHTML = final_total_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); 
		document.getElementById("finalTotalPrice_i").value = final_total_price;
}

//상품 수량 플러스
function qty_plus(goods_id, cart_qty_input_id, cart_id){
	var goods_qty_input = document.getElementById(cart_qty_input_id);
	var goods_qty_view = document.getElementById(cart_qty_input_id+"Val");
	var cart_goods_qty = goods_qty_input.value;
	cart_goods_qty = Number(cart_goods_qty) +1 ;
	goods_qty_input.value = cart_goods_qty;
	goods_qty_view.innerHTML = cart_goods_qty;
	
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/cart/modifyCartQty.do",
		data : {
			goods_id:goods_id,
			cart_goods_qty:cart_goods_qty,
			cart_id: cart_id
		},
		
		success : function(data, textStatus) {
			if(data.trim()=='modify_success'){
				Swal.fire({
					html: "수량을 변경했습니다."
				});
				//수량 변경 후, 가격 계산 함수 실행
				calcGoodsPrice();
			} else{
				Swal.fire({
					html: "다시 시도해 주세요."
				});
			}
			
		},
		error : function(data, textStatus) {
			alert("에러가 발생했습니다."+data);
		}
	}); //end ajax	
}
//전체선택
function check_all(check_all)  {
  const checkboxes = document.getElementsByName('checked_goods');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = check_all.checked;
  })
}

//상품 수량 마이너스
function qty_minus(goods_id, cart_qty_input_id, cart_id){
	var goods_qty_input = document.getElementById(cart_qty_input_id);
	var goods_qty_view = document.getElementById(cart_qty_input_id+"Val");
	var cart_goods_qty = goods_qty_input.value;
	cart_goods_qty = Number(cart_goods_qty) - 1 ;
	goods_qty_input.value = cart_goods_qty;
	if(Number(cart_goods_qty) <= 0){
		cart_goods_qty = 1;
		Swal.fire({
			html: "수량은 1보다 작을 수 없습니다."
		});
		return false;
	}
	goods_qty_input.value = cart_goods_qty;
	goods_qty_view.innerHTML = cart_goods_qty;
	
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/cart/modifyCartQty.do",
		data : {
			goods_id:goods_id,
			cart_goods_qty:cart_goods_qty,
			cart_id: cart_id
		},
		
		success : function(data, textStatus) {
			if(data.trim()=='modify_success'){
				Swal.fire({
					html: "수량을 변경했습니다."
				});
				//수량 변경 후, 가격 계산 함수 실행
				calcGoodsPrice();
			}else{
				Swal.fire({
					html: "다시 시도해 주세요."
				});
			}
			
		},
		error : function(data, textStatus) {
			alert("에러가 발생했습니다."+data);
		}
	}); //end ajax	
}

//삭제 버튼으로 상품 삭제
function delete_cart_goods(cart_id, goods_id){
	var cart_id = Number(cart_id);
	var goods_id = Number(goods_id);
	
	//전송용 form과 input 생성
	var formObj = document.createElement("form");
	var i_cart_id = document.createElement("input");
	var i_goods_id = document.createElement("input");
	i_cart_id.name="cart_id";
	i_cart_id.value = cart_id; // <input name='cart_id' value='cart_id값'>
	i_goods_id.name="goods_id";
	i_goods_id.value = goods_id; // <input name='goods_id' value='goods_id값'>
	
	//form에 input을 붙이고 페이지에 붙여서 전송하기
	formObj.appendChild(i_cart_id);
	formObj.appendChild(i_goods_id);
    document.body.appendChild(formObj); 
    formObj.method="post";
    formObj.action="${contextPath}/cart/removeCartGoods.do";
    formObj.submit();
}

//상품 주문하기
function fn_order_selected_cart_goods(){	
	var order_goods_qty;
	var order_goods_id;
	
	var objForm = document.frm_cart; //카트 폼
	var checked_goods = document.querySelectorAll('input[name="checked_goods"]:checked'); //체크된 체크박스를 모두 선택
	var length = checked_goods.length; //선택된 체크박스의 숫자
	
	if(length > 0){
		for(var i=0; i<length; i++){
			if(checked_goods[i].checked == true){ //각 체크박스를 확인해서 체크가 되어있다면
				order_goods_id = checked_goods[i].value; // 주문하려는 상품 아이디
				order_goods_qty = document.getElementById("cart_goods_qty"+[i]).value; // 주문하려는 상품 수량
				checked_goods[i].value="";
				checked_goods[i].value = order_goods_id+":"+order_goods_qty; //checked_goods[i] = "goods_id:count" <- 이거를 컨트롤러로 보냄
				console.log(checked_goods[i].value);
			}
		}
		
		var final_goods_price_i = document.createElement("input");
		final_goods_price_i.name="final_goods_price";
		final_goods_price_i.value = document.getElementById("finalTotalPrice_i").value;
		objForm.appendChild(final_goods_price_i);
		
		
		objForm.method="post";
	 	objForm.action="${contextPath}/order/orderCartGoods.do";
	 	objForm.submit();
		
	} else {
		Swal.fire({
			html: "장바구니에 담긴 상품이 없습니다!"
		});
	}
 	
}


</script>

<div id="container" class="container-sm">
	<div class="page_title">
		<h3>장바구니</h3>
	</div>
	<div class="d-flex" style="justify-content: space-between">
		<div class="shop_leftTable">
			<div class="cart_top">
				<span><input type="checkbox" class="form_chk" id="check_all" name="check_all" onClick="javascript:check_all(this);" checked><label class="form_chk_label" for="check_all">전체선택</label></span>
				<a href="#">선택삭제</a>
			</div>
			<form name="frm_cart">
			<ul class="list_view cart_table">				
			<c:choose>
				<c:when test="${ empty myCartGoodsList || empty myGoodsList}">
				<li>
					<div class="fixed text-center" style="height:200px; line-height:200px"><p class="text-muted">장바구니에 상품이 없습니다.</p></div>
				</li>
				</c:when>
				
				<c:otherwise>	
					
				<c:forEach var="item" items="${myGoodsList }" varStatus="status">
				<c:set var="totalGoodsPrice" value="${totalGoodsPrice + item.sales_price}"/>
				<li class="d-flex" style="align-items: center; padding: 20px">							
						<div class="goods_chk">
							<input type="checkbox" class="form_chk" name="checked_goods" id="checked_goods_${item.goods_id}" checked value="${item.goods_id }" onClick="calcGoodsPrice()">
							<label class="form_chk_label" for="checked_goods_${item.goods_id}"></label>
						</div>
						<div class="goods_image">
							<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }"><img width="75" alt="" src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" /></a>
						</div>
						<div class="goods_name"><h2><a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }" class="text-left">${item.goods_name }</a></h2></div>									
						<div class="goods_count">
							<div class="d-flex" style="justify-content:center">
								<button type="button" aria-label="수량올리기" class="btn_minus" onClick="qty_minus('${item.goods_id}','cart_goods_qty${status.index}','${myCart_id}');">-</button>
								<span id="cart_goods_qty${status.index}Val">${item.count}</span>
								<input type="hidden" id="cart_goods_qty${status.index}" name="cart_goods_qty" value="${item.count}">								
								<button type="button" aria-label="수량내리기" class="btn_plus" onClick="qty_plus('${item.goods_id}','cart_goods_qty${status.index}','${myCart_id}');">+</button>
							</div>
						</div>
						<div class="goods_price">
							<c:choose>
								<c:when test="${item.sales_price != null && item.sales_price != 0}">
									<strong><span id="sales_price${status.index}"><fmt:formatNumber value="${item.sales_price}" pattern="#,###" /></span>원</strong>
									<span class="text_muted" id="goods_price${status.index}"><fmt:formatNumber value="${item.goods_price}" pattern="#,###" /></span>원
								</c:when>
								<c:otherwise>
									<strong><span id="goods_price${status.index}"><fmt:formatNumber value="${item.goods_price}" pattern="#,###" /></span>원</strong>
								</c:otherwise>
							</c:choose>					
						</div>
						<div class="goods_delete"><a href="javascript:delete_cart_goods('${myCart_id}', '${item.goods_id}');">삭제</a></div>	
											
				</li>
				<c:set var="cart_goods_qty" value="${myCartList[status.count-1].cart_goods_qty}" />
				<c:set var="totalGoodsPrice" value="${totalGoodsPrice + item.goods_price*item.count }" /> <!-- 각 상품 가격 더하기 -->
				<c:if test="${item.sales_price > 0 }"><c:set var="totalDiscountedAmount" value="${totalDiscountedAmount+(item.goods_price- item.sales_price)*item.count}" /> <!-- 각 할인 가격 더하기 -->
				</c:if>
				</c:forEach>
				</c:otherwise>
			</c:choose>					
			</ul>
			</form>
		</div>
		<div class="shop_rightbar">
			<div class="addr_wrap">
				<h3 class="addr_title">배송지</h3>
				<div class="addr_info">
					<p class="addr">${basicAddress.roadAddress} ${basicAddress.detalied_address }</p>
					<a class="btn btn_second" href="javascript:openWindowPop('${contextPath}/member/address/myAddressList.do', 'popup');" style="height:36px; width: 100%; line-height:36px">배송지 변경</a>
				</div>
			</div>
			<ul class="price_wrap">
				<li>
					<span>상품금액</span>
					<span><strong id="totalGoodsPrice"><fmt:formatNumber value="${totalGoodsPrice }" pattern="#,###" /></strong>원</span>
					<input type="hidden" value="${totalGoodsPrice }">
				</li>
				<li>
					<span>상품할인금액</span>
	
					<span><strong id="totalDiscountedAmount"><fmt:formatNumber value="${-totalDiscountedAmount }" pattern="#,###" /></strong>원</span>
					<input type="hidden" value="${totalDiscountedAmount }">

				</li>
				<li>
					<span>배송비</span>
					<span>
						<c:choose>
							<c:when test="${totalGoodsPrice < 40000 and totalGoodsPrice != 0 }">
								<c:set var="totalDeliveryPrice" value="3000"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="totalDeliveryPrice" value="0"></c:set>
							</c:otherwise>							
						</c:choose>						
						<strong id="totalDeliveryPrice"><fmt:formatNumber value="${totalDeliveryPrice }" pattern="#,###" /></strong>원
						<input type="hidden" value="${totalDeliveryPrice }">
					</span>
				</li>
				<c:if test="${40000- (totalGoodsPrice + totalDeliveryPrice - totalDiscountedAmount) > 0 }">
				<li class="delivery_notice">				
					<p><strong class="deliveryFee_notice"><fmt:formatNumber value="${40000-(totalGoodsPrice + totalDeliveryPrice - totalDiscountedAmount)}" pattern="#,###" /></strong>원 추가주문시, 무료배송</p>											
				</li>
				</c:if>
				<li style="border-top:1px solid #efefef; padding-top:12px">
					<span>결제예정금액</span>
					<span><strong id="finalTotalPrice"><fmt:formatNumber value="${totalGoodsPrice + totalDeliveryPrice - totalDiscountedAmount}" pattern="#,###" /></strong>원</span>
					<input type="hidden" value="${totalGoodsPrice + totalDeliveryPrice - totalDiscountedAmount}" name="finalTotalPrice" id="finalTotalPrice_i">
				</li>
			</ul>
			<div class="btn_wrap">
			<c:choose>
				<c:when test="${ empty myCartGoodsList || empty myGoodsList}">
				<a href="javascript:false;" class="btn_disabled btn">상품을 담아주세요</a>
				</c:when>
				<c:otherwise><a href="javascript:fn_order_selected_cart_goods()" class="btn_primary btn">주문하기</a></c:otherwise>
			</c:choose>
			</div>
			<ul class="cart_notice">
				<li>쿠폰/적립금은 주문서에서 사용 가능합니다</li>
				<li>[주문완료] 상태일 경우에만 주문 취소 가능합니다.</li>
				<li>[마이페이지 &gt; 주문내역 상세페이지] 에서 직접 취소하실 수 있습니다.</li>
				<li>쿠폰, 적립금 사용 금액을 제외한 실 결제 금액 기준으로 최종 산정됩니다.</li>
				<li>상품별로 적립금 지급 기준이 다를 수 있습니다. (상품 상세 페이지에서 확인 가능합니다)</li>
			</ul>
		</div>
	</div>
</div>