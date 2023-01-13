<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.ord.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
OrdService ordSvc = new OrdService();
    List<OrdVO> list = ordSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有訂單資料 - listAllEmp.jsp</title>

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
	width: 800px;
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

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>所有訂單資料 - listAllEmp.jsp</h3>
		 <h4><a href="select_ord_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>旅客編號</th>
		<th>飯店編號</th>
		<th>旅客名稱</th>
		<th>飯店名稱</th>
		<th>訂房日期</th>
		<th>入住日期</th>
		<th>退房日期</th>
		<th>訂房天數</th>
		<th>注意事項</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ordVO.ordId}</td>
			<td>${ordVO.userId}</td>
			<td>${ordVO.hotelId}</td>
			<td>${ordVO.userName}</td>
			<td>${ordVO.hotelName}</td>
			<td>${ordVO.ordDate}</td>
			<td>${ordVO.ordCheckin}</td>
			<td>${ordVO.ordCheckout}</td>
			<td>${ordVO.ordNights}</td>
			<td>${ordVO.ordRemark}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/ord.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ordId"  value="${ordVO.ordId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ord/ord.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ordId"  value="${ordVO.ordId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>