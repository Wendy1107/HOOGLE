<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.room.model.*"%>

<%
// RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>資料修改 - update_room_input.jsp</title>

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
		<tr>
			<td>
				<h3>room新增 - addRoom.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="listAllRoom.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>room新增:</h3>

	<!-- 錯誤表列 -->
	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
	<!-- 	<ul> -->
	<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 			<li style="color:red">${message.value}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- </c:if> --%>

	<!-- <FORM METHOD="post" ACTION="room.do" name="form1"> -->

	<FORM action="<%=request.getContextPath()%>/room/RoomServlet"
		method=post enctype="multipart/form-data">
		<table>


			<tr>
				<td>飯店編號:</td>
				<td><input type="TEXT" name="hotelId" size="45"
					value="${param.hotelId}" /></td>
				<td>${errorMsgs.hotelId}</td>
			</tr>


			<tr>
				<td>房間總數:</td>
				<td><input type="TEXT" name="roomTotal" size="45"
					value="${param.roomTotal}" /></td>
				<td>${errorMsgs.roomTotal}</td>
			</tr>


			<tr>
				<td>房間名稱:</td>
				<td><input type="text" name="roomName" size="45"
					value="${param.roomName}" /></td>
				<td>${errorMsgs.roomName}</td>
			</tr>
			<tr>

				<td>房型狀態:</td>
				<td><input type="checkbox" name="roomStatus" value="true"
					${(roomVO.roomStatus).equals(true)?"checkbox-outline":"square-outline"}></td>
			</tr>
			<tr>
				<td>房間單價:</td>
				<td><input type="text" name="roomPrice" size="45"
					value="${param.roomPrice}" /></td>
				<td>${errorMsgs.roomPrice}</td>
			</tr>

			<tr>
				<td>房型照片:</td>
				<td><input type="file" name="roompicPic" size="45"
					value="${param.roompicPic}" /></td>
				<td>${errorMsgs.roompicPic}</td>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>


</html>

