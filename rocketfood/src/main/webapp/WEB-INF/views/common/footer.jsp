<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"    
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div id="container">
	<div class="f_left">
		<h2>고객행복센터</h2>
		<strong class="cs_tel">1544-0000<span class="cs_text">월~토요일 오전 7시 - 오후 6시</span></strong>
		<ul class="f_button">
			<li>
				<button>1:1 문의</button>
				<div>
					<p>문의 주시면 순차적으로 응대해드립니다.</p>
				</div>
			</li>
			<li>
				<button>대량주문 문의</button>
				<div>
					<p>대량구매에 관한 부분을 문의주시면 답변해 드립니다.</p>
				</div>
			</li>
		</ul>
	</div>
	<div class="f_right">
		<ul class="f_menu">
			<li><a href="#">회사소개</a></li>
			<li><a href="#">이용약관</a></li>
			<li><a href="#">개인정보처리방침</a></li>
			<li><a href="#">이용안내</a></li>
		</ul>
		<div class="f_info">
			상호명: 로켓푸드 | 사업자번호: 123-45-678900 | <br>
			통신판매업 : 제 2023-대전서구-01234호 | <br>
			주소: 대전광역시 서구 대덕대로 182 오라클 빌딩 1005 <br>
			TEL : 1544-0000 | FAX : 070-123-4567 <br>
			E-mail : <a href="mailto:rocketfood@rocketfood.com">rocketfood@rocketfood.com</a>
		</div>
		<ul class="f_link">
			<li><a href="#" target="_blank"><img src="${contextPath}/resources/image/icon_insta.png"></a></li>
			<li><a href="#" target="_blank"><img src="${contextPath}/resources/image/icon_fb.png"></a></li>
			<li><a href="#" target="_blank"><img src="${contextPath}/resources/image/icon_blog.png"></a></li>
			<li><a href="#" target="_blank"><img src="${contextPath}/resources/image/icon_naverpost.png"></a></li>
			<li><a href="#" target="_blank"><img src="${contextPath}/resources/image/icon_youtube.png"></a></li>
		</ul>
	</div>
</div> 
<div class="f_copy">ⒸRocketFood. ALL RIGHTS RESERVED.</div>

<div id="chat">
	<div>
		<c:choose>
		<c:when test="${memberInfo.member_id =='admin' }">
			<a href="javascript:openWindowPop('${contextPath}/chat/chatRoomList.do', 'popup');"><img src="${contextPath}/resources/image/icon_chat.png"><span>${waitingChat}</span></a>
		</c:when>
		<c:otherwise>
			<a href="javascript:openWindowPop('${contextPath}/chat/openChatRoom.do', 'popup');"><img src="${contextPath}/resources/image/icon_chat.png"></a>
		</c:otherwise>
		</c:choose>
	</div>
</div>
<div id="btn_top">
	<img src="${contextPath}/resources/image/icon_top.png">
</div>
