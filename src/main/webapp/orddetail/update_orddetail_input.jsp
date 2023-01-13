<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.orddetail.model.*" %>

<%
  // OrdDetailVO orddetailVO = (OrdDetailVO) request.getAttribute("orddetailVO"); //OrdServlet.java (Concroller) 存入req的orddetailVO物件 (包括幫忙取出的orddetailVO, 也包括輸入資料錯誤時的orddetailVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單明細修改 - update_orddetail_input.jsp</title>

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
		 <h3>訂單明細修改 - update_orddetail_input.jsp</h3>
		 <h4><a href="select_orddetail_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>訂單明細編號:<font color=red><b>*</b></font></td>
		<td>${param.orddetailId}</td>
	</tr>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td>${param.ordId}</td>
	</tr>
	<tr>
	
		<td>房間編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="roomAuto" size="45"
			 value="${param.roomAuto}"/></td><td>${errorMsgs.roomAuto}</td>
<%-- 		<td>${param.roomAuto}</td> --%>
	</tr>
	<tr>
		<td>訂房房數:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="roomNumber" size="45"
			 value="${param.roomNumber}"/></td><td>${errorMsgs.roomNumber}</td>
<%-- 		<td>${param.roomNumber}</td> --%>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="orddetailId" value="${param.orddetailId}">
<input type="hidden" name="ordId" value="${param.ordId}">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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