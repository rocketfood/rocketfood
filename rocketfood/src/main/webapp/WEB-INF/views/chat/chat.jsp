<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="d-flex" style="justify-content: center;">
			<div><span>로켓톡</span></div>
		</div>
	</div>

	<div id="chatArea">
		<div id="chatMessageArea">
			<div id="chat_intro">
				<div class="text-center">
					<div class="chat_intro_img"><img src="${contextPath}/resources/image/img_chat.png" width="90"></div>
					<p class="chat_intro_msg">로켓푸드에 문의하기</p>
					<span class="text-muted">무엇이 궁금하신가요?<br>최대한 빠르게 답변해드리겠습니다. <br>비회원으로 문의하시는 경우, 인터넷 브라우저를 닫으시면 답변을 받으실 수 없습니다.</span>
				</div>
			</div>
			
			<c:if test="${not empty chatMessageList}">
			<c:forEach var="item" items="${chatMessageList}">
				<div class="msg_wrap d-flex <c:if test='${empty item.answerer}'>ask</c:if>">
					<div class="msg_item d-flex">
						<div class="profile"><img src="${contextPath}/resources/image/img_chat.png" width="24"></div>
						<div class="msg"><p>${item.message}</p></div>
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
