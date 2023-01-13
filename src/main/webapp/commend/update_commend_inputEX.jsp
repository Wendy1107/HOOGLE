<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.commend.model.*"%>

<%
  CommendVO commendVO = (CommendVO) request.getAttribute("commendVO"); //CommendServlet.java (Concroller) 存入req的commendVO物件 (包括幫忙取出的commendVO, 也包括輸入資料錯誤時的commendVO物件)
%>
<%-- --<%= commendVO==null %>--${commendVo.commendAuto}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>評價資料修改 - update_commend_input.jsp</title>

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
		 <h3>評價資料修改  - update_commend_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="img/logo.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="commend.do" name="form1">
<table>
	<tr>
		<td>評價編號:</td>
		<td>${commendVO.commendAuto}</td>
	</tr>
	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="ordId" size="45" value="${commendVO.ordId}"/></td>
	</tr>
	<tr>
		<td>評價等級:</td>
		<td><input type="TEXT" name="commendGrade" size="45" value="${commendVO.commendGrade}" /></td>
	</tr>
	<tr>
		<td>評價內容:</td>
		<td><input type="TEXT" name="commendContent" size="100"	value="${commendVO.commendContent}" /></td>
	</tr>
	<tr>
		<td>評價日期：</td>
		<td><input type="Date" name="commendDate" size="20" value="${commendVO.commendDate}"></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="commendAuto" value="${commendVO.commendAuto}">
<input type="submit" value="送出修改"></FORM>


</body>

</html>