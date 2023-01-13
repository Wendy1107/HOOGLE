<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.orddetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
//   OrdDetailVO orddetailVO = (OrdDetailVO) request.getSession().getAttribute("OrddetailVO"); //OrdServlet.java(Concroller), 存入req的orddetailVO物件
//   OrdDetailService orddetailSvc = new OrdDetailService();
//   List<OrdDetailVO> list = orddetailSvc.getOneOrd(Integer.valueOf(getInitParameter("OrddetailVO")));
//   List<OrdDetailVO> list = orddetailSvc.getOneOrd(5002);
//   pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>訂單資料 - listOneOrdDetail.jsp</title>
<style>
   table#table-1 {  
  	background-color: #90EE90; 
      border: 2px solid black; 
      text-align: center;  
    } 
    table#table-1 h4 {  
      color: red;  
      display: block;
      margin-bottom: 1px;  
    }  
    h4 {  
     color: blue;  
     display: inline;  
    }  
     </style>

<style>
   table { 
 	width: 600px; 
 	background-color: white; 
	margin-top: 5px; 
 	margin-bottom: 5px; 
   } 
   table, th, td { 
     border: 1px solid #CCCCFF; 
  } 
  th, td { 
     padding: 5px; 
     text-align: center; 
  } 
 </style> 
<%@ include file="/header.jsp" %>

</head>
<body bgcolor='white'>

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<table  align="center">
	<tr class="text-center">
		<td>
			<div ><h4 class = "text-align: center"><a href="${pageContext.request.contextPath}/user/userMemberCenter.jsp"><img src="${pageContext.request.contextPath}/images/logo_removebg.png" width="100"  border="0"></a></h4></div>
		 	<h4 class = "text-align: center"><a href="${pageContext.request.contextPath}/user/userMemberCenter.jsp">回會員中心</a></h4>	</td></tr>
		</td>
	</tr>
</table>

<table >
<div class="nav-pills mb-30">
              <br>
              <div class="table-responsive">
                <table class="table table-bordered" style="width:  80%" align="center">
                  <thead class="thead-dark">
                    <tr class="text-center">
							<th>訂單明細編號</th>
							<th>訂單編號</th>
							<th>房型種類</th>
							<th>訂房房數</th>
							
                    </tr>
                  </thead>
                
                <h3 style="width: 35%" align="center"><c:if test="${not empty orddetailVO}">訂單明細如下 : </c:if></h3>
                <h3 style="width: 39%" align="center"><c:if test="${empty orddetailVO}">查無任何訂單明細</c:if></h3>

<%--             <c:forEach var="ordVO" items="${list}"> --%>
<%--             <c:if test="${ userVO.userId == ordVO.userId }"> --%>
                  <tbody>
                    <tr class="text-center">
                    <c:forEach var="orddetailVO" items="${orddetailVO}" >	
					<tr>
						<td>${orddetailVO.orddetailId}</td>
						<td>${orddetailVO.ordId}</td>
<%-- 						<td>${orddetailVO.roomAuto}人房</td> --%>
						<td>${orddetailVO.roomName}</td>
						<td>${orddetailVO.roomNumber}</td>
					</tr>
					</c:forEach>
					
<!--                       <td> -->
<%--                     	<form METHOD="post" action="${pageContext.request.contextPath}/OrdServlet"> --%>
<!-- 						<input type="hidden" name="action" value="getOrddetail_For_Display"> -->
<%-- 						<input type="hidden" name="ordId" value="${ordVO.ordId}"> --%>
<!-- 						<input name="action" type="submit" id="button" value="查看明細"> -->
<!-- 						</form> -->
<!--                     	</td> -->
                    </tr>
                  </tbody>
<%--                   </c:if> --%>
<%--                   </c:forEach> --%>
                </table>
              </div>
            </div>



<!-- 	<tr> -->
<!-- 		<th>訂單明細編號</th> -->
<!-- 		<th>訂單編號</th> -->
<!-- 		<th>房型種類</th> -->
<!-- 		<th>訂房房數</th> -->
<!-- 	</tr> -->

								
</table>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
 <%@ include file="/footer.jsp" %> 

</body>
</html>