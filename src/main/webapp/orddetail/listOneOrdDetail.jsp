<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.orddetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  OrdDetailVO orddetailVO = (OrdDetailVO) request.getAttribute("OrddetailVO"); //OrdServlet.java(Concroller), 存入req的ordVO物件
%>

<html>
<head>
<title>訂單資料 - listOneOrdDetail.jsp</title>

<style>
  table#table-1 {
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

</head>
<body bgcolor='white'>

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>訂單資料 - ListOneOrdDetail.jsp</h3>
		 <h4><a href="select_orddetail_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單明細編號</th>
		<th>訂單編號</th>
		<th>房間編號</th>
		<th>訂房房數</th>
	</tr>

	<tr>
		<td>${orddetailVO.orddetailId}</td>
		<td>${orddetailVO.ordId}</td>
		<td>${orddetailVO.roomAuto}</td>
		<td>${orddetailVO.roomNumber}</td>
	</tr>
</table>

</body>
</html>