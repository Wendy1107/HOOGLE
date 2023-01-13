<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.food.model.*"%>

<%
  // FoodVO foodVO = (FoodVO) request.getAttribute("foodVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
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
	<tr><td>
		 <h3>美食資料新增 - addFood.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/bf31260422585113.jpg" width="100" height="50" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<!-- 錯誤表列 -->
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <FORM METHOD="post" ACTION="/food/FoodServlet" name="form1"> -->

<FORM action="<%=request.getContextPath() %>/food/FoodServlet" method=post enctype="multipart/form-data">
<table>
<tr>
		<td>美食照片編號:</td>
		<td><input type="TEXT" name="foodPicid" size="45" 
			 value="${param.foodPicid}"/></td><td>${errorMsgs.foodPicid}</td>
	</tr>

	<tr>
		<td>餐廳編號:</td>
		<td><input type="TEXT" name="restaurantId" size="45" 
			 value="${param.restaurantId}"/></td><td>${errorMsgs.restaurantId}</td>
	</tr>


	<tr>
		<td>美食照片:</td>
		<td><input type="file" name="foodPic" size="45"
		value="${param.foodPic}"/></td><td>${errorMsgs.foodPic}
		</td>
	</tr>
	
	
	<tr>
		<td>美食名稱:</td>
		<td><input type="text" name="foodName" size="45"
		value="${param.foodName}"/></td><td>${errorMsgs.foodName}</td>
	</tr>
	
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(param.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>


</html>

