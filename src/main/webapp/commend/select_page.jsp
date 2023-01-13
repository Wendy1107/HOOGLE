<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Commend搜尋頁面</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h1 {
	color: red;
}

h4 {
	color: blue;
	display: inline;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>Commend搜尋頁面 select_page.jsp</h3> <img src="img/logo.jpg"
				width="100" height="100" border="0">
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<h1>資料查詢：</h1>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>

		<li><a href="listAllCommend.jsp">評價資料列表</a> all <br> <br></li>
		<li>
			<FORM METHOD="post" ACTION="commend.do">
				<b>輸入評價編號：</b> <input type="text" name="commendAuto"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM> <br>
		</li>

		<jsp:useBean id="commendSvc" scope="page"
			class="tw.com.hoogle.commend.model.CommendService"></jsp:useBean>

				<li>
					<FORM METHOD="post" ACTION="commend.do">
						<b>輸入訂單編號：</b> <select size="1" name="commendAuto">
							<c:forEach var="commendVO" items="${commendSvc.all}">
								<option value="${commendVO.commendAuto}">${commendVO.ordId}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM><br>
				</li>
		
	</ul>

	<h1>
		新增資料Test：
		</h1>

		<ul>
			<li><a href="addCommend.jsp">新增評價</a></li>
		</ul>
</body>
</html>