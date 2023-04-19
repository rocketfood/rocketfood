<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 	isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="goods"  value="${goodsMap.goodsVO}"  />
<c:set var="imageList"  value="${goodsMap.imageList }"  />
<c:set var="isLogOn" value="${isLogOn}" />
 <%
      //치환 변수 선언합니다.
      //pageContext.setAttribute("crcn", "\r\n"); //개행문자
      pageContext.setAttribute("crcn" , "\n"); //Ajax로 변경 시 개행 문자 
      pageContext.setAttribute("br", "<br/>"); //br 태그
%>  

<script type="text/javascript">
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
	
//장바구니에 담기
function add_cart(goods_id) {
var isLogOn = ${isLogOn}
		
	if (!isLogOn){
		Swal.fire({
			html: "로그인 후 장바구니 담기가 가능합니다."
		});
		return;
	} 

	var cart_goods_qty = document.getElementById("cart_goods_qty").innerText;
    	
   	$.ajax({
   		type : "post",
   		async : false, //false인 경우 동기식으로 처리한다.
   		url : "${contextPath}/cart/addGoodsInCart.do",
   		data : {
   			goods_id:goods_id,
   			cart_goods_qty : cart_goods_qty
   		},
    		
   		success : function(data, textStatus) {
   			if(data.trim()=='cart_add_success'){
   				Swal.fire({
   					html: "장바구니에 상품을 추가했습니다.",    					
   					confirmButtonColor: '#7749eb', // confrim 버튼 색깔 지정
   					confirmButtonText: '장바구니 확인', // confirm 버튼 텍스트 지정
   				}).then(result => {
   				    if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
   				    	location.href="${contextPath}/cart/myCartList.do"
   				    }
   				});
   				//수량 변경 후, 가격 계산 함수 실행
   			}else{
   				Swal.fire({
   					html: "해당 상품이 장바구니에 이미 존재합니다."
   				});
   			}    			
    	},
    	error : function(data, textStatus) {
    		alert("에러가 발생했습니다."+data);
    	}
    }); //end ajax	
}
	
//찜 목록에 담기
function add_wish(goods_id) {
	var isLogOn = ${isLogOn}
		
	if (!isLogOn){
		Swal.fire({
			html: "로그인 후 찜 목록 담기가 가능합니다."
		});
		return;
	} 
		    	
   	$.ajax({
   		type : "post",
   		async : false, //false인 경우 동기식으로 처리한다.
   		url : "${contextPath}/wish/addGoodsInWish.do",
   		data : {
   			goods_id:goods_id
   		},
    		
   		success : function(data, textStatus) {
   			if(data.trim()=='wish_add_success'){
   				Swal.fire({
   					html: "찜한 목록에 상품을 추가했습니다.",    					
  					confirmButtonColor: '#7749eb', // confrim 버튼 색깔 지정
   					confirmButtonText: '찜한 목록 보기', // confirm 버튼 텍스트 지정
   				}).then(result => {
   				    if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
   				    	location.href="${contextPath}/wish/myWishList.do"
   				    }
   				});
   				//수량 변경 후, 가격 계산 함수 실행
   			}else{
   				Swal.fire({
   					html: "해당 상품이 찜한 목록에 이미 존재합니다."
   				});
   			}    			
    	},
    	error : function(data, textStatus) {
    		alert("에러가 발생했습니다."+data);
    	}
    }); //end ajax	
}
	
//상품 수량 계산
function calcCount(operator) {
	var cartGoodsQtyEl = document.getElementById("cart_goods_qty");
	var cartGoodsQty = cartGoodsQtyEl.innerText;
	var minus = document.querySelector(".minus");
		
	if (operator === "plus") {
		cartGoodsQty = parseInt(cartGoodsQty) + 1;
	} else if (operator === "minus") {
		cartGoodsQty = parseInt(cartGoodsQty) - 1;
	}
	cartGoodsQtyEl.innerText = cartGoodsQty;
	
	if (cartGoodsQty <= 1) {
		minus.setAttribute("disabled", true);
	} else {
		minus.removeAttribute("disabled");
	}
		
	var initialGoodsTotalPriceEl = document.getElementById("initial_goods_total_price");
	initialGoodsTotalPriceEl.className = "remove";
	displayTotalPrice(cartGoodsQty); //상품 수량 인자로 넘겨줌

}
	
//총 상품 금액 계산
function calcTotalPrice(goodsTotalPrice, cartGoodsQty) {
	var goodsTotalPrice = goodsTotalPrice * cartGoodsQty;
	return goodsTotalPrice;
}
	
//총 상품 금액 보여주기
function displayTotalPrice(cartGoodsQty) {
	var goodsPrice = ${goods.goods_price}; //6490
	var goodsSalesPrice = ${goods.sales_price}; //6035
	var goodsTotalPrice;
		
	var goodsTotalPriceEl = document.getElementById("goods_total_price");
	if (goodsSalesPrice === 0) {
		goodsTotalPrice = calcTotalPrice(goodsPrice, cartGoodsQty);
	} else {
		goodsTotalPrice = calcTotalPrice(goodsSalesPrice, cartGoodsQty);
	}
		
	goodsTotalPrice = goodsTotalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	goodsTotalPriceEl.innerText = goodsTotalPrice;
}

//상품 할인율 계산
function calcDiscountRate(goods_price, sales_price) {
	var discountRate = ((goods_price - sales_price) / goods_price) * 100;
    discountRate = Math.floor(discountRate);
    return discountRate;
}

//상품 할인율 보여주기
function displayDiscountRate() {
	var goodsPrice = ${goods.goods_price};
    var goodsSalesPrice = ${goods.sales_price};
    var discountRate = calcDiscountRate(goodsPrice, goodsSalesPrice);
    var discountRateEl = document.getElementById("discountRate");
    discountRateEl.innerText = discountRate;
}
	
document.addEventListener('DOMContentLoaded', function() {
	var goodsSalesPrice = ${goods.sales_price};
    if (goodsSalesPrice !== 0) {
    	displayDiscountRate();	
    }
});
    
function writeQna(goods_id) {
	$('#modal').remove();
	var isLogOn = ${isLogOn}

	if (!isLogOn) {
		Swal.fire({
			html: "회원만 상품 문의가 가능합니다. 회원이라면 로그인 해주세요."
		});
    	return
    } else{
    		
    	var html = '';
    	html = '<div id="modal_backdrop">';
    	html +=	'<div class="modal modal-lg" id="modal">';
    	html +=	'<div class="modal_body">';
    	html +=	'<form id="form_wirteQna" name="form_wirteQna" action="${contextPath}/goods/board/addNewqQna.do" method="POST">';
    	html +=	'<div class="text-left modal-title">문의 작성</div>';
    	html +=	'<ul>';
    	html +=	'<li class="form_row">';
    	html +=	'<div class="label"><label class="required">제목</label></div>';
    	html +=	'<input type="text" id="qna_title" name="title" class="btn_input input">';
    	html +=	'</li>';
    	html +=	'<li class="form_row">';
    	html +=	'<div class="label"><label class="required">내용</label></div>';
    	html +=	'<div class="d-flex" style="flex-wrap:wrap;flex:1"><textarea id="qna_content" name="content" class="full_input input"/>';
    	html +=	'<div class="secret_box"><input type="checkbox" class="form_chk" id="secret" name="secret" onClick="secretQna()"><label class="form_chk_label" for="secret">비밀글로 문의하기</label></div></div></li>';
    	html +=	'</ul>';
    	html +=	'<div class="btn_wrap text-center">';
    	html +=	'<input type="hidden" name="goods_id" value="'+goods_id+'">';
    	html +=	'<input type="button" onClick="javascript:qnaSubmit()" class="btn btn_primary" value="등록">';
    	html +=	'<input type="button" id="close_modal" class="btn btn_second" value="취소">';
    	html +=	'</div></form></div></div></div>';
    		
    	$('#outer_wrap').append(html);
    	$('#close_modal').click(function() {
    		$('#modal_backdrop').remove();
    	});
    }
}
    
//폼 제출 전 유효성 검사 (아직 수정 중)
function qnaSubmit() {
	var form = document.getElementById("form_wirteQna");
	var title = document.getElementById("qna_title").value;
	var content = document.getElementById("qna_content").value;
		
	if( title == "" ) {
		Swal.fire({
			html: "제목을 입력 해주세요."
		});
	} else if( content.replace(/\s|　/gi).length == 0 ) {
		Swal.fire({
			html: "내용을 입력 해주세요."
		});
	} else {
		form.submit();
	}
		
}
	
function qna_result(resultMsg) {
	Swal.fire({
		html: resultMsg
	});
}
    
function secretQna() {
   	if(event.target.checked)  {
   		document.getElementById('secret').value = "y";
   	} else {
   		result = '';
   	}
}

function writeReview(goods_id) {
   	$('#modal').remove();
   	var isLogOn = ${isLogOn}

   	if (!isLogOn) {
   		Swal.fire({
			html: "회원만 상품 후기 작성이 가능합니다. 회원이라면 로그인 해주세요."
		});
   		return
   	} else {
   		var html = '';
    	html = '<div id="modal_backdrop">';
    	html +=	'<div class="modal modal-lg" id="modal">';
    	html +=	'<div class="modal_body">';
    	html +=	'<form id="form_wirteReview" name="form_wirteReview" action="${contextPath}/goods/board/addNewReview.do" method="POST">';
    	html +=	'<div class="text-left modal-title">문의 작성</div>';
    	html +=	'<ul>';
    	html +=	'<li class="form_row">';
    	html +=	'<div class="label"><label class="required">별점</label></div>';
    	html += '<a class="star-item" href="javascript:checkStar(1)"><i class="fa-regular fa-star"></i></a>';
    	html += '<a class="star-item" href="javascript:checkStar(2)"><i class="fa-regular fa-star"></i></a>';
    	html += '<a class="star-item" href="javascript:checkStar(3)"><i class="fa-regular fa-star"></i></a>';
    	html += '<a class="star-item" href="javascript:checkStar(4)"><i class="fa-regular fa-star"></i></a>';
    	html += '<a class="star-item" href="javascript:checkStar(5)"><i class="fa-regular fa-star"></i></a>';
    	html +=	'<input type="hidden" id="star" name="star"';
    	html +=	'</li>';
    	html +=	'<li class="form_row">';
    	html +=	'<div class="label"><label class="required">제목</label></div>';
    	html +=	'<input type="text" id="review_title" name="title" class="btn_input input">';
    	html +=	'</li>';
    	html +=	'<li class="form_row">';
    	html +=	'<div class="label"><label class="required">내용</label></div>';
    	html +=	'<textarea id="review_content" name="description" class="full_input input"/>';
    	html +=	'</li>';
    	html +=	'</ul>';
    	html +=	'<div class="btn_wrap text-center">';
    	html +=	'<input type="hidden" name="goods_id" value="'+goods_id+'">';
    	html +=	'<input type="button" onClick="javascript:reviewSubmit()" class="btn btn_primary" value="등록">';
    	html +=	'<input type="button" id="close_modal" class="btn btn_second" value="취소">';
    	html +=	'</div></form></div></div></div>';
   		
    	$('#outer_wrap').append(html);
    	$('#close_modal').click(function() {
    		$('#modal_backdrop').remove();
    	});
    }
}
    
function reviewSubmit(formName) {
	var form = document.getElementById("form_wirteReview");
	var title = $("#review_title").val().trim();
	var description = $("#review_content").val().trim();
	
	if(title == "") {
		Swal.fire({
			html: "제목을 입력 해주세요."
		});
		return false;
	} else if(description == "") {
		Swal.fire({
			html: "내용을 입력 해주세요."
		});
		return false;
	} else {
		form.submit();
	}
}

function reviewResult(resultMsg) {
	Swal.fire({
		html: resultMsg
	});
}
//도움돼요 
function updateHelpCount(article_id, member_id) {
	var review_help_wrap = $("#review"+article_id+ " .review_help_wrap");
	var help_y = "y";
	
	if(member_id == null || member_id == "") {
		Swal.fire({
			html: "로그인한 사용자만 이용 가능합니다."
		});
	} else {
		if(review_help_wrap.hasClass('help_on')){
			help_y = "n"		
		}

		$.ajax({
			type : "post",
			async : false, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/goods/board/updateHelpCount.do",
			data : {
				article_id:article_id,
				member_id:member_id,
				help_y: help_y
			},
		
			success : function(data, textStatus) {
				if(data.trim()=='modify_success'){
					location.reload();
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
}

function checkStar(count) { 
	$('#star').val(count);
	$('.star-item').find('i').removeClass('fa-solid');
	$('.star-item').find('i').addClass('fa-regular');
	$('.star-item').each(function(index, e){
		$(this).find('i').removeClass('fa-regular');
		$(this).find('i').addClass('fa-solid');
		if((index+1) == count) return false;
	})
	

	
}
</script>
<script>
	$(document).ready(function(){
		$('.bod_content').hide();
		$('.td-title a').click(function(e){
			e.preventDefault();
			if($(this).hasClass('active')){
				$(this).removeClass("active");
				$(".bod_content").hide();
			} else {
				//비밀글 여부 확인 후 본인이라면 글을 보여준다
				var member_id = "${memberInfo.member_id}";
				var writer = document.getElementById('qna_writer').innerHTML;

				if($(this).hasClass('secret') && ((member_id == writer) ||(member_id == 'admin'))) {
					$(this).addClass("active");
					$(".bod_content").hide();
					$(this).parent().siblings('.bod_content').show();	
				} else {
					Swal.fire({
						html: "비밀글로 작성된 글은 작성자만 확인 가능합니다."
					});
				}
					
			}
			
		});
		
	});
</script>
<script src="https://kit.fontawesome.com/bd24c0821d.js"></script>

<div id="container" class="container-sm">
	
	<div id="goods_mainInfo_container">
		<section id="goods_image_area">
			<figure>
				<img alt="상품 이미지" src="${contextPath}/download.do?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}">
			</figure>
		</section>
		
		<section id="goods_mainInfo_area">
			<div class="goods_mainInfo">
				<div class="brand">${goods.business_name }</div>
				<h1>${goods.goods_name}</h1>
				<h2>${goods.short_description}</h2>
			    <c:choose>
			        <c:when test="${not empty goods.sales_price and goods.sales_price ne 0}"> <!-- sales_price가 0이나 null이 아닐 경우 -->
			        	<div>
			        		<span id="discountRate" class="discountRate_text"></span>
							<span class="discountRate_text">%</span>
			        		<span class="goods_price">
						    	<fmt:formatNumber value="${goods.sales_price}" type="number" var="sales_price" />
				            		${sales_price}원
			        		</span>
			        	</div>
			            <div class="text_line">
       						<fmt:formatNumber value="${goods.goods_price}" type="number" var="goods_price" />
			            		${goods_price}원
			            </div>
			        </c:when>
			        <c:otherwise>
			        	<span class="goods_price">
							<fmt:formatNumber value="${goods.goods_price}" type="number" var="goods_price" />
			            		${goods_price}원			        	
			        	</span>
			        </c:otherwise>
			    </c:choose>
			</div>
			
			<div id="detail_table">
				<table>
					<tbody>
						<tr class="solid_line">
							<td class="fixed">배송</td>
							<td class="fixed">
								<span>샛별배송</span>
								<p>23시 전 주문 시 내일 아침 7시 전 도착</p>
								<p>(대구·부산·울산 샛별배송 운영시간 별도 확인)</p>
							</td>
						</tr>
						<tr class="solid_line">
							<td class="fixed">판매자</td>
							<td class="fixed">
								<span>로켓푸드</span>
							</td>
						</tr>
						<tr class="solid_line">
							<td class="fixed">포장타입</td>
							<td class="fixed">냉장 (종이포장)<br/>택배배송은 에코 포장이 스티로폼으로 대체됩니다.</td>
						</tr>
						<tr class="solid_line">
							<td class="fixed">판매단위</td>
							<td class="fixed">1개</td>
						</tr>
						<tr class="solid_line">
							<td class="fixed">원산지</td>
							<td class="fixed">상품설명/상세정보 참조</td>
						</tr>
						<tr class="solid_line">
							<td class="fixed">상품선택</td>
							<td class="fixed">
								<div class="goods_selection_box">
									<div>
										<span>${goods.goods_name}</span>
									</div>
									<div class="d-flex" style="align-items: center; justify-content: space-between;">
										<div class="count_button_wrap">
											<button onclick="calcCount('minus')" class="minus" type="button" aria-label="수량내리기" disabled>-</button>
											<div id="cart_goods_qty">1</div>
											<button onclick="calcCount('plus')" class="plus" type="button" aria-label="수량올리기">+</button>										
										</div>
										<div>
											 <c:choose>
										        <c:when test="${not empty goods.sales_price and goods.sales_price ne 0}"> <!-- sales_price가 0이나 null이 아닐 경우 -->
										        	<div>
											            <span class="text_line">
								       						<fmt:formatNumber value="${goods.goods_price}" type="number" var="goods_price" />
											            		${goods_price}원
											            </span>
											            <span class="goods_price">
													    	<fmt:formatNumber value="${goods.sales_price}" type="number" var="sales_price" />
											            		${sales_price}원
										        		</span>
    											        </div>
										        </c:when>
										        <c:otherwise>
										        	<span class="goods_price">
														<fmt:formatNumber value="${goods.goods_price}" type="number" var="goods_price" />
										            		${goods_price}원			        	
										        	</span>
										        </c:otherwise>
										    </c:choose>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>	
			</div>
			
			<div class="goods_total_box">
				<div class="total_price_wrap">
					<div class="text-right">
						<span>총 상품금액: </span>
						<span id="goods_total_price"></span>
						<span id="initial_goods_total_price">
							<c:choose>
						        <c:when test="${not empty goods.sales_price and goods.sales_price ne 0}"> <!-- sales_price가 0이나 null이 아닐 경우 -->
						            <fmt:formatNumber value="${goods.sales_price}" type="number" var="sales_price" />
						            	${sales_price}
						        </c:when>
						        <c:otherwise>
						            <fmt:formatNumber value="${goods.goods_price}" type="number" var="goods_price" />
						            	${goods_price}
						        </c:otherwise>
					    	</c:choose>					
						</span>
						<span>원</span>
					</div>
				</div>
				
				<div class="total_buttons d-flex">
					<a class="wish" href="javascript:add_wish('${goods.goods_id}')"><i class="fa-regular fa-heart fa-2x"></i> <!-- 찜 --></a>
					<a href="#"><i class="fa-regular fa-bell fa-2x"></i> <!-- 재입고 --></a>
					<a class="cart btn btn_primary" href="javascript:add_cart('${goods.goods_id}')">장바구니 담기</a> <!-- 장바구니 -->
				</div>
			</div>
		</section>
	</div>
	
	<!-- 내용이 들어 가는 곳 -->
	<div id="goods_detailInfo_container">
		<ul class="goods_nav d-flex text-center">
			<li><a href="#goods_info"><span>상품설명</span></a></li>
			<li><a href="#goods_detail"><span>상세정보</span></a></li>
			<li><a href="#goods_review"><span>후기</span></a></li>
			<li><a href="#goods_qna"><span>문의</span></a></li>
		</ul>
		
		<div class="goods_section">
			<section id="goods_info">
				<div class="d-inline-block">
					<h4 class="text-left">상품 설명</h4>
				</div>
				<div class="text-center">
					<c:forEach var="item" items="${goodsMap.imageList }">
					<img src="${contextPath}/download.do?goods_id=${goods.goods_id}&fileName=${item.fileName}">
					</c:forEach>
				</div>
			</section>
			<section id="goods_detail">
				<div class="d-inline-block">
					<h4 class="text-left">상세 설명</h4>
					<div>${goods.goods_description }</div>
				</div>
			</section>
			
			<!-- 상품 후기 -->
			<section id="goods_review">				
				<div class="board_top d-flex">
					<div class="d-inline-block">
						<h4 class="text-left">상품 후기</h4>
					</div>
					
					<div class="d-inline-block"><a href="javascript:writeReview('${goods.goods_id}')" class="btn btn_check">후기 작성</a></div>
				</div>
				
				<div class="boardInfo_wrap">
					<div>
						<fmt:formatNumber value="${reviewCount}" type="number" var="reviewCount"/>
							총 ${reviewList.size()}개
					</div>
					<div class="board_filter">
						<button>추천순</button>
						<button>|</button>
						<button>최근등록순</button>
					</div>
				</div>
	
				<div class="board_body">
					<div class="board_body_review">
						<c:choose>
							<c:when test="${reviewList == null || reviewList.size() == 0}">
								<p class="text-center no_review">등록된 후기가 없습니다.</p>
							</c:when>
							<c:otherwise>
								<c:forEach var="reviewMap" items="${reviewList}" varStatus="status">
									<div class="review_wrap" id="review${reviewMap.article_id}">
										<div class="memberInfo">
											<span class="gradeName">${reviewMap.grade_name}</span>
											<span class="memberName">${reviewMap.member_name}</span>
										</div>
										<article>
											<div class="goodsName_wrap">
												<h3>[${goods.business_name}] ${goods.goods_name}</h3>
											</div>
											<p>
												<c:forEach begin="1" end="${reviewMap.star }">												
													<span class="star"><i class="fa-star fa-solid"></i></span>
												</c:forEach>
											</p>
											<p>${reviewMap.description}</p>
											<div class="review_sideInfo">
												<span class="createdDate">${reviewMap.created_date}</span>
												<div class="review_help_wrap 
												<c:forEach var="helpReview" items="${helpReviewList}">
													<c:if test="${memberInfo.member_id == helpReview.member_id && helpReview.article_id == reviewMap.article_id }">help_on</c:if>
												</c:forEach>
												" 
												onClick="javascript:updateHelpCount('${reviewMap.article_id}', '${memberInfo.member_id}')">
													<i class="fa-regular fa-thumbs-up"></i>
													<span>도움돼요 ${reviewMap.help_count}</span>
												</div>
											</div>
		 								</article>
	 								</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>	
			</section>
			<!--// 상품 후기 -->
			
			<!-- 상품 문의 -->
			<section id="goods_qna">				
				<div class="board_top d-flex">
					<div class="d-inline-block">
						<h4 class="text-left">상품 문의</h4>
						<ul>
							<li>상품에 대한 문의를 남기는 공간입니다. 해당 게시판의 성격과 다른 글은 사전 동의 없이 담당 게시판으로 이동될 수 있습니다.</li>
							<li>배송관련, 주문(취소/교환/환불) 관련 문의 및 요청 사항은 <b>마이페이지의 1:1 문의</b>에 남겨주세요</li>
						</ul>
					</div>
					<div class="d-inline-block"><a href="javascript:writeQna('${goods.goods_id}')" class="btn btn_check">문의하기</a></div>
				</div>
						

                <div class="board_body">    
					<ul class="table">
						<li class="d-flex table_head">
							<div class="th td-title">제목</div>
							<div class="th td-name">작성자</div>
							<div class="th td-date">작성일</div>
							<div class="th td-status">답변상태</div>
						</li>
						<c:choose>
							<c:when test="${empty bodsList && bodsList.size() == 0 }">
								<li class="d-flex table_item"><p class="text-center empty">등록된 문의가 없습니다.</p></li>
							</c:when>						
							<c:otherwise>
								<c:forEach var="bod" items="${bodsList}" varStatus="bodNum" >
									<li class="d-flex table_item">
										<div class="td td-title">
											<a href="#" class="<c:if test="${bod.secret == 'y'}">secret</c:if>">${bod.title}<c:if test="${bod.secret == 'y'}"><img src="${contextPath}/resources/image/icon_lock.svg"></c:if></a>											
										</div>
										<div class="td td-name" id="qna_writer">${bod.member_id }</div>
										<div class="td td-date">${bod.created_date}</div>
										<div class="td td-status <c:if test="${bod.reply == '답변완료' }">replyed</c:if>">${bod.reply}</div>
										<div class="bod_content">
												<div class="qna_content">
													<span class="q"></span>
													<p>${bod.content}</p>
												</div>
												<c:if test="${not empty bod.reply_content}">
												<div class="reply_content">
													<span class="a"></span>
													<p>${bod.reply_content}</p>
													<p class="reply_date text-left">${bod.replyed_date}</p>
												</div>
												</c:if>
										</div>
									</li>								
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>		
			</section>
			<!-- // 상품 문의 -->
		</div>	
	</div>
</div>


