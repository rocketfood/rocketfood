<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="${contextPath }/resources/jquery/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>

<script type="text/javascript">
	var wsocket;
	function connect() {
		wsocket = new SockJS("http://localhost:8080/rocketfood/chat.do");
		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}
	function disconnect() {
		wsocket.close();
	}
	function onOpen(evt) {
		//appendMessage("연결되었습니다.");
	}
	function onMessage(evt) {
		const chatMessage = JSON.parse(evt.data);
		appendMessage(chatMessage.message.replace(/\\n/gm, "<br>"));
	}
	function onClose(evt) {
		//appendMessage("연결을 끊었습니다.");
	}
	
	function send() {
		var msg = $('#message').val();
		const chatMessage = {
				"chat_room_id" : "${chat_room_id}",
	            "questioner": "${questioner}",
	            "answerer" : "${answerer}",
	            "message": msg,
	    };
		wsocket.send(JSON.stringify(chatMessage));
		$("#message").val("");
	}

	function appendMessage(msg) {
		var msgBox = "<div class='msg_wrap d-flex ask'><div class='msg_item d-flex'><div class='profile'><img src='${contextPath}/resources/image/img_chat.png' width='24'></div><div class='msg'><p>"
		$("#chatMessageArea").append(msgBox + msg + "<p></div></div></div>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}

	$(document).ready(function() {
		connect();
		
		$('#message').on('keydown', function(event) {
			if (event.keyCode == 13)
	            if (!event.shiftKey){
	                event.preventDefault();
	                send();
	        }
			event.stopPropagation();
		});
		$('#exitBtn').click(function() {
			disconnect();
		});
	});
</script>
</head>


<body>
	<div class="chat_top">
		<div class="d-flex" style="justify-content: space-between;">
			<a href="${contextPath}/chat/chatRoomList.do" class="d-block">
				<svg width="20" height="20" viewBox="0 0 13 20" fill="none" xmlns="http://www.w3.org/2000/svg" foundation="[object Object]" class="InnerIconstyled__Icon-ch-front__sc-197h5bb-0 bClnDQ" defaultopacity="1" hoveredopacity="1" margintop="0" marginright="0" marginbottom="0" marginleft="0" withtheme="true">
					<path fill="#7749eb" fill-rule="evenodd" clip-rule="evenodd" d="M9.17255 16.4226C8.84711 16.748 8.31947 16.748 7.99404 16.4226L2.1607 10.5893C1.83527 10.2638 1.83527 9.73619 2.1607 9.41075L7.99404 3.57742C8.31947 3.25198 8.84711 3.25198 9.17255 3.57742C9.49799 3.90285 9.49799 4.43049 9.17255 4.75593L3.92847 10L9.17255 15.2441C9.49799 15.5695 9.49799 16.0972 9.17255 16.4226Z"></path>
				</svg>
			</a>
			<div><span>로켓톡</span></div>
			<div></div>
		</div>
	</div>

	<div id="chatArea">
		<div id="chatMessageArea">
			<div id="chat_intro">
				<div class="text-center">
					<div class="chat_intro_img"><img src="${contextPath}/resources/image/img_chat.png" width="90"></div>
					<p class="chat_intro_msg">로켓푸드에 문의하기</p>
					<span class="text-muted">무엇이 궁금하신가요?<br>최대한 빠르게 답변해드리겠습니다.</span>
				</div>
			</div>
			
			<c:if test="${not empty chatMessageList}">			
			<c:forEach var="item" items="${chatMessageList}" varStatus="status">
				<c:if test="${status.first}"><div class="text-center date"><fmt:formatDate value="${item.created_date }" pattern="yyyy-MM-dd" /></div></c:if>
				<c:if test="${item.created_date} != ${chatMessageList[status.index+1].created_date}">
					<div class="text-center">${chatMessageList[status.index+1].created_date}</div>
				</c:if>
				<div class="msg_wrap d-flex <c:if test='${empty item.questioner}'>ask</c:if>">
					<div class="msg_item d-flex">
						<div class="msg"><p>${item.message}</p></div>
						<div class="time"><span><fmt:formatDate value="${item.created_date }" pattern="HH:mm" /></span></div>
					</div>
				</div>
			</c:forEach>
			</c:if>
		</div>
	</div>

	
	<div id="chat_input">
		<div class="input_wrap d-flex">
			<textarea  id="message" placeholder="메시지를 입력해주세요." class="chat_textarea"></textarea>
		</div>
	</div>
</body>
</html>
