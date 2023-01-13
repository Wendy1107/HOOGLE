<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tw.com.hoogle.commend.model.*"%>
<%
CommendVO commendVO = (CommendVO) request.getAttribute("commendAuto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>旅客資料 - listOneCommend.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h2>評價資料 - listOneCommend.jsp</h2>
				<h3>
					<a href="select_page.jsp">回首頁</a>
				</h3>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>評價編號</th>
			<th>訂單編號</th>
			<th>評價等級</th>
			<th>評價內容</th>
			<th>評價日期</th>
		</tr>
		<tr>
			<td>${commendVO.commendAuto}</td>
			<td>${commendVO.ordId}</td>
			<td>${commendVO.commendGrade}</td>
			<td>${commendVO.commendContent}</td>
			<td>${commendVO.commendDate}</td>
		</tr>
	</table>
</body>
</html>