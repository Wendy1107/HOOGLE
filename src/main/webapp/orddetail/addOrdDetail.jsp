<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="tw.com.hoogle.orddetail.model.*"%>

<%
  // OrdDetailVO orddetailVO = (OrdDetailVO) request.getAttribute("orddetailVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改訂單明細 - update_orddetail_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #CCCCFF;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單明細新增 - addOrdDetail.jsp</h3></td><td>
		 <h4><a href="select_orddetail_page.jsp"><img src="images/logo.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>訂單新增:</h3>


<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="orddetail.do" name="form1">
<table>
	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="ordId" size="45" 
			 value="${param.ordId}"/></td><td>${errorMsgs.ordId}</td>
	</tr>
	<tr>
		<td>房間編號:</td>
		<td><input type="TEXT" name="roomAuto" size="45" 
			 value="${param.roomAuto}"/></td><td>${errorMsgs.roomAuto}</td>
	</tr>
	<tr>
		<td>訂房房數:</td>
		<td><input type="TEXT" name="roomNumber" size="45" 
			 value="${param.roomNumber}"/></td><td>${errorMsgs.roomNumber}</td>
	</tr>
</table>	

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/js/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
        
</html>