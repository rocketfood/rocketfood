<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


<c:if test='${modified_personal_info==true }'>
<script>
window.onload=function()
{
  test();
}

function test(){
	init();
	alert("회원 정보가 수정되었습니다.");
}
function init(){
	var frm_mod_member=document.frm_mod_member;
	var h_tel1=frm_mod_member.h_tel1;
	var h_hp1=frm_mod_member.h_hp1;
	var tel1=h_tel1.value;
	var hp1=h_hp1.value;
	
	var select_tel1=frm_mod_member.tel1;
	var select_hp1=frm_mod_member.hp1;
	select_tel1.value=tel1;
	select_hp1.value=hp1;
}

</script>
</c:if>

<script>
function execDaumPostcode() {
	new daum.Postcode({
    	oncomplete: function(data) {
        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('roadAddress').value = fullRoadAddr;
            document.getElementById('jibunAddress').value = data.jibunAddress;

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}
</script>
<script>
function fn_modify_member_info(member_id , mod_type){
	var frm_mod_member = document.frm_mod_member; 
	var value; //값을 넣을 변수	
	
	if(mod_type =='member_pw'){
		value=frm_mod_member.member_pw.value;
		
			
	} else if(mod_type =='member_birth'){
		var member_birth_y=frm_mod_member.member_birth_y.value;
		var member_birth_m=frm_mod_member.member_birth_m.value;
		var member_birth_d=frm_mod_member.member_birth_d.value;
		
		//생년월일 유효성 검사 추가
		value = value_y+"-"+value_m+"-"+value_d;
	
	} else if(mod_type=='member_phone'){
		var value_tel1 = frm_mod_member.tel1.value;
		var value_tel2 = frm_mod_member.tel2.value;
		var value_tel3 = frm_mod_member.tel3.value;
			
		value = value_tel1+"-"+value_tel2+"-"+value_tel3;
			
	} else if(mod_type=='member_email'){
		var email = frm_mod_member.member_email.value;
		
		//이메일 유효성 검사 추가
		value = email;

	} else if(mod_type=='address'){
		var zipcode=frm_mod_member.zipcode;
		var roadAddress=frm_mod_member.roadAddress;
		var jibunAddress=frm_mod_member.jibunAddress;
		var namujiAddress=frm_mod_member.namujiAddress;
			
		value_zipcode=zipcode.value;
		value_roadAddress=roadAddress.value;
		value_jibunAddress=jibunAddress.value;
		value_namujiAddress=namujiAddress.value;
			
		value=value_zipcode+","+value_roadAddress+","+value_jibunAddress+","+value_namujiAddress;
	}
	 
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/admin/member/modifyMemberInfo.do",
		data : {
			member_id:member_id,
			mod_type:mod_type,
			value:value
		},
		success : function(data, textStatus) {
			if(data.trim()=='mod_success'){
				Swal.fire({
					html: "회원 정보를 수정했습니다."
				});
			} else if(data.trim()=='failed'){
				Swal.fire({
					html: "다시 시도해 주세요."
				});
			}		
		},
		error : function(data, textStatus) {
			alert("에러가 발생했습니다."+data);
			Swal.fire({
				html: "에러가 발생했습니다. " + data
			});
		}		
	}); //end ajax
}

function fn_delete_member(member_id ,del_yn){
	var frm_mod_member=document.frm_mod_member;
	var i_member_id = document.createElement("input");
	var i_del_yn = document.createElement("input");
    
	
    i_member_id.name="member_id";
    i_del_yn.name="del_yn";
    i_member_id.value=member_id;
    i_del_yn.value=del_yn;
    
    frm_mod_member.appendChild(i_member_id);
    frm_mod_member.appendChild(i_del_yn);
    frm_mod_member.method="post";
    frm_mod_member.action="/bookshop01/admin/member/deleteMember.do";
    frm_mod_member.submit();
}
</script>
</head>

<body>
	<h3>내 상세 정보</h3>
<form name="frm_mod_member">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
						<input name="member_id" type="text" size="20" value="${member_info.member_id }"  disabled/>
					</td>
					 <td>
					  <input type="button" value="수정하기" disabled onClick="fn_modify_member_info('${member_info.member_id }','member_name')" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td>
					  <input name="member_pw" type="password" size="20" value="${member_info.member_pw }" />
					</td>
					<td>
					  <input type="button" value="수정하기" onClick="fn_modify_member_info('${member_info.member_id }','member_pw')" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이름</td>
					<td>
					  <input name="member_name" type="text" size="20" value="${member_info.member_name }"  disabled />
					 </td>
					 <td>
					  <input type="button" value="수정하기" disabled onClick="fn_modify_member_info('${member_info.member_id }','member_name')" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이메일</td>
					<td>
					  <input name="member_email" type="text" size="20" value="${member_info.member_email }"  disabled />
					 </td>
					 <td>
					  <input type="button" value="수정하기" disabled onClick="fn_modify_member_info('${member_info.member_id }','member_email')" />
					</td>
				</tr>				
				<!-- 생년월일 추가 -->
				
				<tr class="dot_line">
					<td class="fixed_join">휴대폰번호</td>
					<td>
						<input name="member_phone" type="text" size="20" value="${member_info.member_phone }"  disabled />
				    </td>
					<td>
						<input type="button" value="수정하기" onClick="fn_modify_member_info('${member_info.member_id }','member_phone')" />
					</td>	
				</tr>
				
				<!-- 주소 추가 -->
				
				
			</tbody>
		</table>
		</div>
		<div class="clear">
		<br><br>
		<table align=center>
		<tr>
			<td >
				<input type="hidden" name="command"  value="modify_my_info" /> 
				<c:choose>
				  <c:when test="${member_info.del_yn=='Y' }">
				    <input  type="button"  value="회원복원" onClick="fn_delete_member('${member_info.member_id }','N')">   
				  </c:when>
				  <c:when  test="${member_info.del_yn=='N' }">
				    <input  type="button"  value="회원탈퇴" onClick="fn_delete_member('${member_info.member_id }','Y')">
				  </c:when>
				  
				</c:choose>
				
			</td>
		</tr>
	</table>
	</div>
</form>	
</body>
</html>
