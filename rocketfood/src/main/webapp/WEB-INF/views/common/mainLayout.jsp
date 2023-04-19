<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("utf-8");
%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<link href="${contextPath}/resources/css/reset.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/basic-jquery-slider.css" rel="stylesheet">

<script src="${contextPath}/resources/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${contextPath}/resources/jquery/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/common.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/tabs.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/carousel.js" type="text/javascript"></script>

<!-- slick.js -->
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<!-- sweetalert2 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<title><tiles:insertAttribute name="title" /></title>
	
</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">
			<header>
				   <tiles:insertAttribute name="header" />
			</header>
			<div class="clear"></div>
			<div id="body">
			 	<tiles:insertAttribute name="body" />
			</div>
			<div class="clear"></div>
			<footer>
        		<tiles:insertAttribute name="footer" />
        	</footer>
		</div>
		 <tiles:insertAttribute name="quickMenu" />
    </div>        	
</body>      
        
        