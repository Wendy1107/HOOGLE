<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.roompic.model.*"%>
<%
RoompicService roompicSvc = new RoompicService();
List<RoompicVO> list = roompicSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房間照片列表</title>
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
	width: 100%;
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
<body>

	<table id="table-1">
		<tr>
			<td>
				<h2>所有房間照片資料 - listAllRoompic.jsp</h2>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>房型照片編號</th>
			<th>房型流水號</th>
			<th>房型種類</th>
			<th>房型照片</th>
		</tr>
		<c:forEach var="roompicVO" items="${list}">
			<tr>
				<td>${roompicVO.roompicId}</td>
				<td>${roompicVO.roomAuto}</td>
				<td>${roompicVO.roomType}</td>
				<td><img src="${pageContext.request.contextPath}/roompic/RoompicReader?roompicId=${roompicVO.roompicId}" height="100" width="100"></td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>