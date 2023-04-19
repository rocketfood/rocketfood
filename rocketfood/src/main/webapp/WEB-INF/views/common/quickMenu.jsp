<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<script>
	var array_index=0;
	var SERVER_URL="${contextPath}/thumbnails.do";
	function fn_show_next_goods(){
		var img_sticky=document.getElementById("img_sticky");
		var cur_goods_num=document.getElementById("cur_goods_num");
		var _h_goods_id=document.frm_sticky.h_goods_id;
		var _h_goods_fileName=document.frm_sticky.h_goods_fileName;
		if(array_index <_h_goods_id.length-1)
			array_index++;
		 	
		var goods_id=_h_goods_id[array_index].value;
		var fileName=_h_goods_fileName[array_index].value;
		img_sticky.src=SERVER_URL+"?goods_id="+goods_id+"&fileName="+fileName;
		cur_goods_num.innerHTML=array_index+1;
	}


 function fn_show_previous_goods(){
	var img_sticky=document.getElementById("img_sticky");
	var cur_goods_num=document.getElementById("cur_goods_num");
	var _h_goods_id=document.frm_sticky.h_goods_id;
	var _h_goods_fileName=document.frm_sticky.h_goods_fileName;
	
	if(array_index >0)
		array_index--;
	
	var goods_id=_h_goods_id[array_index].value;
	var fileName=_h_goods_fileName[array_index].value;
	img_sticky.src=SERVER_URL+"?goods_id="+goods_id+"&fileName="+fileName;
	cur_goods_num.innerHTML=array_index+1;
}

function goodsDetail(){
	var cur_goods_num=document.getElementById("cur_goods_num");
	arrIdx=cur_goods_num.innerHTML-1;
	
	var img_sticky=document.getElementById("img_sticky");
	var h_goods_id=document.frm_sticky.h_goods_id;
	var len=h_goods_id.length;
	
	if(len>1){
		goods_id=h_goods_id[arrIdx].value;
	}else{
		goods_id=h_goods_id.value;
	}
	
	
	var formObj=document.createElement("form");
	var i_goods_id = document.createElement("input"); 
    
	i_goods_id.name="goods_id";
	i_goods_id.value=goods_id;
	
    formObj.appendChild(i_goods_id);
    document.body.appendChild(formObj); 
    formObj.method="get";
    formObj.action="${contextPath}/goods/goodsDetail.do?goods_id="+goods_id;
    formObj.submit();
	
	
}
</script>  
 
   
<div id="sticky" >
	<div class="recent">
		<h3>최근 본 상품</h3>
		<c:choose>
		<c:when test="${ not empty quickGoodsList }">
		<a href='javascript:fn_show_previous_goods();'>
        	<svg width="20" height="20" viewBox="0 0 18 18" fill="none" stroke="#ddd" xmlns="http://www.w3.org/2000/svg"><path d="M5 11L9 7L13 11" stroke="#ddd" stroke-width="1.3"></path></svg>
        </a> 
        </c:when>
        </c:choose>
		<div>
		<!--   상품이 없습니다. -->
		 <c:choose>
			<c:when test="${ empty quickGoodsList }">
				<strong>상품이 없습니다.</strong>
			</c:when>
			<c:otherwise>
	       <div class="recent_item">	        
		      <c:forEach var="item" items="${quickGoodsList }" varStatus="itemNum">
		         <c:choose>
		         	<c:when test="${itemNum.count==1 }">
		         	<form name="frm_sticky">
			      	<a href="javascript:goodsDetail();">
			  	    	<img width="60" height="80" id="img_sticky" src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
			      	</a>
			        <input type="hidden"  name="h_goods_id" value="${item.goods_id}" />
			        <input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />
			      <br>
			      	</form>
			      </c:when>
			      <c:otherwise>
			      	<form name="frm_sticky">
			        <input type="hidden"  name="h_goods_id" value="${item.goods_id}" />
			        <input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}" />
			        </form>
			      </c:otherwise>
			      </c:choose>
		     </c:forEach>
		     </div>
		   </c:otherwise>
	      </c:choose>
		 </div>
	 </div>
	 
	 <div>
	 <c:choose>
	    <c:when test="${ empty quickGoodsList }">
		    <h5> 0/0 </h5>
	    </c:when>
	    <c:otherwise>
	    	<h5><span id="cur_goods_num">1</span>/${quickGoodsListNum}</h5>
	    </c:otherwise>
     </c:choose>   
     <c:choose>
		<c:when test="${ not empty quickGoodsList }">
		
		<h5><a href='javascript:fn_show_next_goods();'>
			<svg width="20" height="20" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M13 7L9 11L5 7" stroke="#ddd" stroke-width="1.3"></path></svg>
		</a></h5>
		</c:when>
       </c:choose>
    </div>

