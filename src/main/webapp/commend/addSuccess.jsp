<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.commend.model.*"%>

<%
CommendService comservice = new CommendService();
List<CommendVO> list = comservice.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>新增評價成功</title>
<%@ include file="/header.jsp"%>
<style type="text/css">
.center-in-center {
	position: absolute;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	-moz-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	-o-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
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

table {
	width: 1000px;
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/commend/commend.css" />
</head>
<body>
<%-- 	<a href="<%=request.getContextPath()%>/commend/select_page.jsp">回首頁</a> --%>
	<div class="center-in-center">
		<h1 style="text-align:center">新增資料成功，謝謝您的回饋</h1>
		<table id="table-1">
			<tr>
				<td>
					<h2>所有評價資料</h2>
					<h3>
						<a href="<%=request.getContextPath()%>/user/userMemberCenter.jsp">回首頁</a>
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


			<c:forEach var="commendVO" items="${list}">
				<tr>
					<td>${commendVO.commendAuto}</td>
					<td>${commendVO.ordId}</td>
					<td>${commendVO.commendGrade}</td>
					<td>${commendVO.commendContent}</td>
					<td>${commendVO.commendDate}</td>
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/commend/commend.do" --%>
<!-- 							style="margin-bottom: 0px;"> -->
<!-- 							<input type="hidden" value="修改"> <input type="hidden" -->
<%-- 								name="commendAuto" value="${commendVO.commendAuto}"> <input --%>
<!-- 								type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 						</FORM> -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/commend/commend.do" --%>
<!-- 							style="margin-bottom: 0px;"> -->
<!-- 							<input type="hidden" value="刪除"> <input type="hidden" -->
<%-- 								name="commendAuto" value="${commendVO.commendAuto}"> <input --%>
<!-- 								type="hidden" name="action" value="delete"> -->
<!-- 						</FORM> -->
<!-- 					</td> -->
				</tr>
			</c:forEach>
		</table>
	</div>
<%-- 	<jsp:include page="/footer.jsp"/> --%>
<%-- 	<%@ include file="/footer.jsp"%> --%>
		<!-- js files -->
		<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
		<!-- counter js -->
		<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/waypoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.counterup.min.js"></script>
		<!-- venobox js -->
		<script src="<%=request.getContextPath()%>/js/venobox.min.js"></script>
		<!-- owl carousel -->
		<script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
		<!-- portfolio js -->
		<script src="<%=request.getContextPath()%>/js/jquery.mixitup.min.js"></script>
		<!-- datepicker js -->
		<script src="<%=request.getContextPath()%>/js/datepicker.min.js"></script>
		<!-- script js -->
		<script src="<%=request.getContextPath()%>/js/abu-js/abu.js"></script>
		<!-- header right link js by aga -->
		<script src="<%=request.getContextPath()%>/js/member/memberHeader.js"></script>
</body>
</html>