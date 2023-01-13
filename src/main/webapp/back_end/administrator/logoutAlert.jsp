<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.administrator.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HOOGLE</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/loginForAdmin.css">
</head>

<body>
<!-- 	<div class="img"> -->
<%-- 		<img src="<%=request.getContextPath()%>/images/logo_removebg.png" alt="#" width="120" height="120"> --%>
<!-- 	</div> -->
	<div class="system_name">

	</div>

	<div class="login_page">
		<div id="container1">

			<div class="login">
			
			 	<br>
			 	<img alt="#" src="<%=request.getContextPath()%>/images/logo_small_removebg.png" width="50px" height="50px">
				<br>
				<h2 style="margin: 20px;">您已登出！</h2>
				
				<form method="post"
					action="<%=request.getContextPath()%>/back_end/login/login.jsp">
					<input style="height: 30px;width: 70px;" type="submit" value="確定" class="submit">
				</form>
		
				<!-- <h5 onclick="show_hide()">註冊帳號</h5> -->

			</div>
			<!-- login end-->
		</div>
		<!-- container1 end-->
	</div>
	<!-- login_page end-->


</body>
</html>