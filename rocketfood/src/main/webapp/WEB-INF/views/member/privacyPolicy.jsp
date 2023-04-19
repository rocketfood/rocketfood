<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  

<div id="container">
	<div class="page_title text-left">
		<h3>인정보 수집·이용 동의 (필수)</h3>
	</div>
	<div class="box_tbl">
		<table>
		<colgroup>
			<col width="50%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<thead>
			<tr>
				<th class="tit1" scope="row">수집 목적</th>
				<th class="tit2" scope="row">수집 항목</th>
				<th class="tit3" scope="row">보유 기간</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>회원 가입의사 확인, 이용자 식별 및 본인여부, 회원자격 유지·관리, 계약 이행 및 약관변경 등의 고지를 위한 연락, 본인의사 확인 및 민원 등의 고객 고충 처리, 부정이용 방지, 비인가 사용방지 및 서비스 제공 및 계약의 이행, 서비스 이용 및 상담, 문의, 후기 등 원활한 의사소통 경로 확보, 맞춤형 회원 서비스 제공, 거점 기반 서비스 제공</td>
				<td>이름, 아이디, 비밀번호, 휴대폰번호, 이메일, 주소</td>
				<td class="emph">회원 탈퇴 <br>즉시 파기 <br><br>부정이용 방지를 위하여 30일 동안 보관 (아이디, 휴대폰 번호) 후 파기 </td>
			</tr>
			<tr>
				<td>서비스방문 및 이용기록 분석, 부정이용 방지 등을 위한 기록 관리</td>
				<td>서비스 이용기록, IP주소, 쿠키, MAC주소, 모바일 기기정보(앱 버전, OS 버전), ADID/IDFA(광고식별자)</td>
				<td class="emph">회원 탈퇴 즉시 또는 이용 목적 달성 즉시 파기</td>
			</tr>
		</tbody>
		</table>
		<p class="txt_service">
		※ 단, 회원 탈퇴와 별개로 분쟁 조정, 고객문의 대응 및 법령 준수 이력 증빙을 위하여 이메일, 문자, 알림톡 발송이력은 발송일로부터 6개월 보관(이름, 아이디, 휴대폰 번호, 이메일) 후 파기합니다.
		<br>
		※ 서비스 제공을 위해서 필요한 최소한의 개인정보입니다. 동의를 해 주셔야 서비스를 이용하실 수 있으며, 동의하지 않으실 경우 서비스에 제한이 있을 수 있습니다.
		</p>
	</div>
</div>