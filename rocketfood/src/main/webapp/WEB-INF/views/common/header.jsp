<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	var loopSearch=true;
	function keywordSearch(){
		if(loopSearch==false) return;
	 	var value=document.frmSearch.searchWord.value;
		$.ajax({
			type : "get",
			async : true, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/goods/keywordSearch.do",
			data : {keyword:value},
			success : function(data, textStatus) {
			    var jsonInfo = JSON.parse(data);
				displayResult(jsonInfo);
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
				
			}
		}); //end ajax	
	}
	
	function displayResult(jsonInfo){
		var count = jsonInfo.keyword.length;
		if(count > 0) {
		    var html = '';
		    for(var i in jsonInfo.keyword){
			   html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
		    }
		    var listView = document.getElementById("suggestList");
		    listView.innerHTML = html;
		    show('suggest');
		}else{
		    hide('suggest');
		} 
	}
	
	function select(selectedKeyword) {
		 document.frmSearch.searchWord.value=selectedKeyword;
		 loopSearch = false;
		 hide('suggest');
	}
		
	function show(elementId) {
		 var element = document.getElementById(elementId);
		 if(element) {
		  element.style.display = 'block';
		 }
		}
	
	function hide(elementId){
	   var element = document.getElementById(elementId);
	   if(element){
		  element.style.display = 'none';
	   }
	}
	
	function openWindowPop(url, name){
	    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
	    window.open(url, name, options);
	}
</script>
<body>
	<div id="container">
		<div id="top_menu">
			<div id="head_link">
				<ul>
		   		<c:choose>
		     		<c:when test="${isLogOn==true and not empty memberInfo }">
		     		<li>
		     			<a href="#"><b>${memberInfo.member_name}</b>님 <span class="down">▼</span></a>
		     			<ul class="dropdown">
		     				<c:if test="${memberInfo.member_id =='admin' }">  
	   	   					<li class="no_line"><a href="${contextPath}/admin/order/adminOrderMain.do">주문관리</a></li>
	   	   					<li class="no_line"><a href="${contextPath}/admin/goods/adminGoodsMain.do">상품관리</a></li>
	   	   					<li class="no_line"><a href="${contextPath}/admin/member/adminMemberMain.do">회원관리</a></li>
	    					</c:if>
	    					<c:if test="${memberInfo.member_id !='admin' }">
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/myPageMain.do">주문내역</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/wish/myWishList.do">찜한상품</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/myAddressList.do">배송지관리</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/board/myReviewList.do">상품후기</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/board/myGoodsQnaList.do">상품문의</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/point/myPoint.do">적립금</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/coupon/myCouponList.do">쿠폰</a></li>
		     				<li class="dropdown_item"><a href="${contextPath}/mypage/member/myDetailInfo.do">개인정보수정</a></li>
		     				</c:if>	
			   				<li class="dropdown_item"><a href="${contextPath}/member/logout.do">로그아웃</a></li>
		     			</ul>
		     		</li>			   		
			 		</c:when>
			 		<c:otherwise>
			   		<li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
			   		<li><a href="${contextPath}/member/memberForm.do">회원가입</a></li> 
			 		</c:otherwise>
				</c:choose>
				
			   		<li>
			   			<a href="#">고객센터 <span class="down">▼</span></a>
			   			<ul class="dropdown">
			   				<li class="dropdown_item"><a href="/board/noticeList.do">공지사항</a></li>
		     				<li class="dropdown_item"><a href="/board/faqList.do">자주하는 질문</a></li>
		     				<li class="dropdown_item"><a href="#">대량주문 문의</a></li>
			   			</ul>
			   		</li>
				</ul>
			</div>
		</div>
		<div id="header_cont" class="clear">
			<div id="logo">
				<a href="${contextPath}/main/main.do">
					<img width="260" alt="rocketfood" src="${contextPath}/resources/image/logo_color_txt.png">
				</a>
			</div>
			<div id="search" >
				<form name="frmSearch" action="${contextPath}/goods/searchGoods.do" >
					<input name="searchWord" class="main_input" type="text"  onKeyUp="keywordSearch()" placeholder="검색어를 입력하세요"> 
					<span><input type="submit" name="search" id="btn_search"></span>
				</form>
			</div>
			<ul class="head_icon">
				<li><a href="javascript:openWindowPop('${contextPath}/member/address/myAddressList.do', 'popup');"><img src="${contextPath}/resources/image/icon_addr.svg"></a></li>
				<li><a href="#"><img src="${contextPath}/resources/image/icon_heart.svg"></a></li>
				<li><a href="${contextPath}/cart/myCartList.do"><img src="${contextPath}/resources/image/icon_cart.svg"></a></li>
			</ul>
		</div>
		<div id="gnb">
			<ul>
				<li id="allCate">
					<a href="#" class="gnb_item"><img src="${contextPath}/resources/image/icon_menu.svg"><span>카테고리</span></a>
					<ul class="menu_depth1">
						<c:choose>
						<c:when test="${not empty categoryList }">
							<c:forEach var="item" items="${categoryList}" end="12">
							<li class="menu_depth1_item">
								<a href="${contextPath}/goods/goodsList.do?category_id=${item.category_id}">${item.category_name}</a>
								<c:set var="cateNum" value="${status.count}"/>
								<ul class="menu_depth2 ${cateNum}">					
									<c:set var="key" value="${item.category_name}"/>				
									<c:forEach var="entry" items="${categoryMap[key]}">									
									<li class=""><a href="${contextPath}/goods/goodsList.do?category_id=${entry.category_id}">${entry.category_name}</a></li>
									</c:forEach>
								</ul>
							</li> 
							</c:forEach>
						</c:when>	
						</c:choose>					
					</ul>
				</li>
				<li><a href="${contextPath}" class="gnb_item">신상품</a></li>
				<li><a href="${contextPath}" class="gnb_item">베스트</a></li>
				<li><a href="${contextPath}" class="gnb_item">알뜰쇼핑</a></li>
				<li><a href="${contextPath}" class="gnb_item">특가/혜택</a></li>
		</ul>
	</div>
	</div>
</body>
</html>