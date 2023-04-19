<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="${contextPath }/resources/jquery/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<style>
	.chatRoom_top {padding: 12px;}
	.chatRoom_top h1 {display: block; margin-left: 10px;font-size: 1.6rem;font-weight: bold; font-size: 20px; color:#000}
	.chat_tab_wrap {padding: 10px 16px}
	.chat_tab_wrap .tab-box {background-color: rgba(239, 239, 240, 0.8); backdrop-filter: blur(60px);padding:4px; border-radius: 10px;}
	.chat_tab_wrap .tab-box li {border:0; flex:1; width: auto;margin: 0 2px; padding: 0; height: 30px; line-height: 30px; cursor:pointer;}
	.chat_tab_wrap .tab-box li.active {border:0; background:#fff; border-radius: 10px}
	.chat_tab_wrap .tab-box li div {font-size: 16px; text-align:center; color:#000}
	.chat_tab_container .tab_content {width:100%; }
	.tab_content .chat_room_item {justify-content: space-between; margin-bottom: 10px; align-items: center;}
	.tab_content .chat_room_item .chat_room_link {width: 100%; cursor:pointer;}
	.tab_content .chat_room_item .profile {border-radius: 50%; overflow:hidden}
	.tab_content .chat_room_item .msg {flex:1; margin: 0 10px}
	.tab_content .chat_room_item .msg .questioner {font-size: 14px; margin-bottom:5px}
	.tab_content .chat_room_item .status {font-size: 14px}
	.tab_content .chat_room_item .status span {display: inline-block; background:#efefef; color:#000; height:25px; width:25px; line-height:27px; border-radius: 50%;}
	.tab_content .chat_room_item .status .waiting {background:#7749eb; color:#fff;}
	.empty_chat {text-align:center; font-size: 14px; height: 200px; line-height: 200px}
	.chat_paging {position:absolute; position: absolute;bottom: 30px;display: flex;justify-content: center;left: 0;right: 0;}
</style>
<div class="chatRoom_top">
	<div class="d-flex" style="justify-content: start;">
		<h1>전체</h1>
	</div>
</div>

<div class="chat_tab_wrap">
	<ul class="d-flex tab-box">
		<li class="active"><div name="#tab1">대기 중</div></li>
		<li><div name="#tab2">답변 완료</div></li>
	</ul>
</div>
<div class="tab_container chat_tab_container">
	<c:choose>
	<c:when test="${not empty waintingChatMap.chatRoomList}">
	<div class="tab_content" id="tab1">
	<c:forEach var="item" items="${waintingChatMap.chatRoomList}" varStatus="status">		
		<div class="chat_room_item ">
			<div onClick="location.href='${contextPath}/chat/replychat.do?chat_room_id=${item.chat_room_id}';" class="d-flex chat_room_link">
				<div class="profile"><img src="${contextPath}/resources/image/img_chat.png" width="50"></div>
				<div class="msg text-left">
					<div class=""><b>${item.questioner}</b></div>
					<p class="bold">${waintingChatMap.chatMsgList[status.index]}</p>
				</div>
			<div class="status"><span class="waiting">${waintingChatMap.waitingCountList[status.index]}</span></div>
			</div>
		</div>		
	</c:forEach>
	<div class="pagination1 chat_paging">
		<c:choose>
			<c:when test="${waintingChatMap.chatRoomList.size()%10} !=0"><c:set var="end" value="${waintingChatMap.chatRoomList.size()/10 +1}"></c:set></c:when>
			<c:otherwise><c:set var="end" value="${waintingChatMap.chatRoomList.size()/10}"></c:set></c:otherwise>
		</c:choose>
		<c:forEach var="page" begin="1" end="${end}" step="1" >
			<c:if test="${section >1 && page==1 }">
	    	<a href="${contextPath}/chat/chatRoomList.do?section=${section-1}&pageNum=${(section-1)*5 +1 }">&nbsp; &nbsp;</a>
	    	</c:if>
	    	<a href="${contextPath}/chat/chatRoomList.do?section=${section}&pageNum=${page}">${(section-1)*5 +page} </a>
		</c:forEach> 
	</div>	
	</div>
	</c:when> 
	<c:otherwise>
	<div class="tab_content" id="tab1">
		<div class="empty_chat">존재하는 채팅방이 없습니다.</div>
	</div>
	</c:otherwise>
	</c:choose>
	
	<c:choose>
	<c:when test="${not empty chatMap.chatRoomList}">
	<div class="tab_content" id="tab2">
	<c:forEach var="item" items="${chatMap.chatRoomList}" varStatus="status">		
		<div class="chat_room_item ">
			<div onClick="location.href='${contextPath}/chat/replychat.do?chat_room_id=${item.chat_room_id}';" class="d-flex chat_room_link">
				<div class="profile"><img src="${contextPath}/resources/image/img_chat.png" width="50"></div>
				<div class="msg text-left">
					<div class=""><b>${item.questioner}</b></div>
					<p class="bold">${chatMap.chatMsgList[status.index]}</p>
				</div>
			</div>
		</div>		
	</c:forEach>
	<div class="pagination1 chat_paging">
		<c:choose>
			<c:when test="${chatMap.chatRoomList.size()%10} !=0"><c:set var="end" value="${chatMap.chatRoomList.size()/10 +1}"></c:set></c:when>
			<c:otherwise><c:set var="end" value="${chatMap.chatRoomList.size()/10}"></c:set></c:otherwise>
		</c:choose>
		<c:forEach var="page" begin="1" end="${end}" step="1" >
			<c:if test="${section >1 && page==1 }">
	    	<a href="${contextPath}/chat/chatRoomList.do?section=${section-1}&pageNum=${(section-1)*5 +1 }">&nbsp; &nbsp;</a>
	    	</c:if>
	    	<a href="${contextPath}/chat/chatRoomList.do?section=${section}&pageNum=${page}">${(section-1)*5 +page} </a>
		</c:forEach> 
	</div>	
	</div>
	</c:when> 
	
	<c:otherwise>
	<div class="tab_content" id="tab2">
		<div class="empty_chat">존재하는 채팅방이 없습니다.</div>
	</div>
	</c:otherwise>
	</c:choose>
</div>
