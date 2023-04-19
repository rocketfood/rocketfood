<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<c:if test='${not empty LoginFailMessage }'>

<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script>
window.onload=function()
{
  result();
}

function result(){
	Swal.fire({
		html: "${LoginFailMessage}"
	});

}
</script>

</c:if>

<div id="container">
	<div class="page_title">
		<h3>로그인</h3>
	</div>
	<div id="login_wrap">
		<div id="login_form">
			<form action="${contextPath}/member/login.do" method="post">
				<input type="text" class="input full_input" name="member_id" placeholder="아이디를 입력해주세요">
				<input type="password" class="input full_input" name="member_pw" placeholder="비밀번호를 입력해주세요">
				<div>
					<div class="text-right"><a href="${contextPath}/member/findMemberInfo.do">아이디 | 비밀번호 찾기</a></div>
				</div>
				<input type="submit" class="btn btn_primary" value="로그인">
				<a href="${contextPath}//member/memberForm1.do" class="btn btn_second">회원가입</a>
			</form>
			<div class="social_login_box">
				<h5 class="text-center">간편 로그인</h5>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=f1f06dd941ee84dd6d4f2c776f201fe6&redirect_uri=http://localhost:8080/rocketfood/kakaoLogin.do&response_type=code" class="btn btn_kakao"><img src="${contextPath}/resources/image/kakaotalk.png" width="20">카카오 아이디로 로그인</a>
				<a href="${naverAuthUrl}" class="btn btn_naver"><img src="${contextPath}/resources/image/naver.png" width="20">네이버 아이디로 로그인</a>
			</div>
		</div>
	</div>
</div>