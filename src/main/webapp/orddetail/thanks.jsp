<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
<%-- <meta http-equiv="refresh" content="5; url=${pageContext.request.contextPath}/index.jsp"/> --%>
<title>Thank you for your order</title>

<%@ include file="/header.jsp" %>
</head>
<body>

	<div align="center">
	<br><br>
		<h2>感謝您的下訂</h2><br>
		<h2>~~ 提醒您 ~~ </h2><br>
		<h2><span>入住飯店 : </span><span style="color:red">${ordVO.hotelName}</span></h2><br>
		<h2><span>入住日期 : </span><span style="color:red">${checkinInput}</span></h2><br>
		<h2><span>退房日期 : </span><span style="color:red">${checkoutInput}</span></h2><br>
<!-- 		<h3><span>即將跳轉回首頁在</span><span class="second">5</span><span>秒</span></h3> -->
		<h2><span>將於</span><span id="jumpTo" style="color:red">10</span>秒後返回首頁</h2>
		<h2><a href="${pageContext.request.contextPath}/index.jsp">直接回首頁</a></h2>
		<script type="text/javascript">
		    JumpToIndex(10,'${pageContext.request.contextPath}/index.jsp');
		    
		    function JumpToIndex(secs,surl){
		        var jumpTo=document.getElementById('jumpTo');
		        jumpTo.innerHTML=secs;
		        if(--secs >= 0){
		            setTimeout(function(){ JumpToIndex(secs,surl)},1000);
		            // setTimeout("funJump("+secs+",'"+surl+"')",1000);   
		
		        }else{
		            location.href=surl;
		        }
		
		    }
		</script>  
	
	<br><br><br><br><br><br><br><br>
	</div>
	
 <%@ include file="/footer.jsp" %>
 
</body>
</html>