<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.room.model.*"%>
<%@ page import="tw.com.hoogle.roompic.model.*"%>

<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>資料修改</title>


<style>
img.preview {
	width: 200px;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul>li {
	display: inline-block;
	vertical-align: top;
}
</style>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/all.min.css"
	rel="stylesheet">
</head>

<body>


	<div class="container">
		<div class="row">
			<!-- 改善建議開始 -->

			<div class="col-md-9">

				<div class="card border-secondary w-20">
					<div class="card-header  bg-Primary sm-3">
						<h3 class="text-white">房間修改</h3>
					</div>




					<FORM action="<%=request.getContextPath()%>/room/RoomServlet"
						method=post enctype="multipart/form-data">

						<table>
							<div class="form-group col-lg-12" style="margin-top: 10px">
							<tr>
								<td>房型流水號:<font color=red><b>*</b></font></td>
								<td>${param.roomAuto}</td>
							</tr>
							</div>
							<div class="form-group col-lg-9">
							<tr>
								<td>飯店編號:<font color=red><b>*</b></font></td>
								<td>${param.hotelId}</td>
							</tr>
							</div>
							<div class="col-md-9">
							<tr>
								<td>房型名稱:</td>
								<td><input type="TEXT" name="roomName" size="45"
									value="${param.roomName}" /></td>
								<td>${errorMsgs.roomName}</td>

							</tr>
							</div>
							<div class="form-group col-md-12">
							<tr>
								<td>房型照片:</td>
								<td><input type="file" name="roompicPic" size="45"
									value="${param.roompicPic}" /></td>
								<td>${errorMsgs.roompicPic}</td>
							<tr>
								</div>

								<div class="col-md-12">
								<td>房型狀態:</td>
								<td><input type="checkbox" name="roomStatus" value="true"
									checked="" id="roomStatus"></td>
							</tr>
							</div>


							<div class="col-md-9">
							<tr>
								<td>房間總數:</td>
								<td><input type="TEXT" name="roomTotal" size="45"
									value="${param.roomTotal}" /></td>
								<td>${errorMsgs.roomTotal}</td>
							</tr>
							</div>
							<div class="col-md-6">
							<tr>
								<td>房型單價(NTD):</td>
								<td><input type="TEXT" name="roomPrice" size="45"
									value="${param.roomPrice}" /></td>
								<td>${errorMsgs.roomPrice}</td>

							</tr>
							</div>
						</table>
						<br> <input type="hidden" name="action" value="update">
						<input type="hidden" name="room" value="${param.room}"> <input
							type="submit" value="送出修改"> <input type="hidden"
							name="roomAuto" value="${param.roomAuto}"> <input
							type="hidden" name="hotelId" value="${param.hotelId}">

					</FORM>

					<script>
						let rootsta = '${param.roomStatus}';
						console.log("console=", rootsta);
						if (rootsta == 'true') {
							document.getElementById("roomStatus").checked = true;
						} else {
							document.getElementById("roomStatus").checked = false;
						}
					</script>
</body>
</html>



