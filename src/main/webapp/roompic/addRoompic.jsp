<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.roompic.model.*"%>
<%
RoompicVO roompicVO = (RoompicVO) request.getAttribute("roompicVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增房間照片</title>

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
	width: 450px;
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
<body>

	<table id="table-1">
		<tr>
			<td>
				<h2>房間照片新增 - addRoompic.jsp</h2>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="RoompicServlet" name="form1"
		enctype="multipart/form-data">

		<table>
			<tr>
				<td>房型流水號：</td>
				<td><input type="text" name="roomAuto" size="10"
					value="<%=(roompicVO == null) ? "" : roompicVO.getRoomAuto()%>"></td>
			</tr>
			<tr>
				<td>房型種類：</td>
				<td><input type="text" name="roomType" size="10"
					value="<%=(roompicVO == null) ? "" : roompicVO.getRoomType()%>"></td>
			</tr>
			<tr>
				<td><b>房型圖片：</b></td>
				<td><input type="file" name="roompicPic" accept="image/*"
					 value=""></td>
<%-- 					 <%=(roompicVO == null) ? "" : roompicVO.getRoompicPic()%> --%>

			</tr>
		</table>

		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">

	</FORM>
</body>
</html>