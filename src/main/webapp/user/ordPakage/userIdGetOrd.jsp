<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.ord.model.*" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //OrdServlet.java(Concroller), 存入req的ordVO物件

//OrdDetailVO orddetailVO = (OrdDetailVO) request.getSession().getAttribute("OrddetailVO"); //OrdServlet.java(Concroller), 存入req的orddetailVO物件
//OrdDetailService orddetailSvc = new OrdDetailService();
//List<OrdDetailVO> list = orddetailSvc.getOneOrd(Integer.valueOf(getInitParameter("OrddetailVO")));
//List<OrdDetailVO> list = orddetailSvc.getOneOrd(5002);
//pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>訂單資料 - listOneOrd.jsp</title>

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
		 <h3>訂單資料 - ListOneOrd.jsp</h3>
		<h4><a href="${pageContext.request.contextPath}/user/ordSearch.jsp"><img src="${pageContext.request.contextPath}/images/logo_removebg.png" width="100"  border="0"></a></h4>
		 <h4><a href="${pageContext.request.contextPath}/user/ordSearch.jsp">回會員中心</a></h4>	</td></tr>
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