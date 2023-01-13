<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.food.model.*"%>

<%
FoodVO foodVO = (FoodVO) request.getAttribute("foodVO"); //FoodServlet.java (Concroller) 存入req的foodVO物件 (包括幫忙取出的foodVO, 也包括輸入資料錯誤時的foodVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>美食資料修改 - update_food_input.jsp</title>

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
				<h3>美食資料修改 - update_food_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img
						src="images/istockphoto-1349390515-612x612.jpg" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
	<!-- 	<ul> -->
	<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 			<li style="color:red">${message.value}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- </c:if> --%>
	<FORM action="<%=request.getContextPath()%>/food/FoodServlet" method=post
		enctype="multipart/form-data">

		<table>

			<tr>
				<td>美食照片編號:<font color=red><b>*</b></font></td>
				<td>${param.foodPicid}</td>
			</tr>
		
			<tr>
				<td>餐廳編號:<font color=red><b>*</b></font></td>
				<td>${param.restaurantId}</td>
				
			</tr>
			<tr>
				<td>美食照片:</td>
				<td><input type="file" name="foodPic" size="45"
					value="${param.foodPic}" /></td>
				<td>${errorMsgs.foodPic}</td>
			<tr>

				<td>美食名稱:</td>
				<td><input type="TEXT" name="foodName" size="45"
					value="${param.foodName}" /></td>
				<td>${errorMsgs.foodName}</td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="food" value="${param.food}"> <input
			type="submit" value="送出修改">
	<input type="hidden" name="foodPicid" value="${param.foodPicid}">
	<input type="hidden" name="restaurantId" value="${param.restaurantId}">

	</FORM>
</body>
</html>

<%-- 	<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" /> --%>
<!-- 			<tr> -->
<!-- 				<td>餐廳:<font color=red><b>*</b></font></td> -->
<!-- 				<td><select size="1" name="restaurantId"> -->
<%-- 						<c:forEach var="restaurantVO" items="${restaurantSvc.all}"> --%>
<%-- 							<option value="${restaurantVO.restaurantId}" --%>
<%-- 								${(param.restaurantId==restaurantVO.restaurantId)? 'selected':'' }>${restaurantVO.dname} --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->


