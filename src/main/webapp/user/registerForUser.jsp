<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.user.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>RegisterForUser</title>

<%@ include file="/header.jsp" %>
</head>
<body>
	<!-- ================ Inner banner ================ -->
	<div class="inner-banner inner-banner-bg pt-70 pb-40">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-8 col-md-8 mb-30">
					<!-- page-title -->
					<div class="page-title">
						<h1>旅客註冊</h1>
					</div>
					<!-- page-title end -->
				</div>
				<div class="col-lg-4 col-md-4 mb-30">
					<!-- breadcrumb -->
					<ol class="breadcrumb mb-0">
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
						<li class="breadcrumb-item active">Register</li>
					</ol>
					<!-- breadcrumb end -->
				</div>
			</div>
		</div>
	</div>
	<!-- ================ Register page ================ -->
	<div class="login-register-page pt-70 pb-70">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<!-- register box -->
					<div class="login-box">
						<form class="form-style-1 shadow p-30" METHOD="post" ACTION="UserServlet" name="form1"> 
							<p>還沒有帳號嗎？立即註冊吧！</p>

							
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<div class="form-group">
								<label for="email" class="form-label">Email address：</label> <input
									id="email" type="email" class="form-control" name="userEmail"
									placeholder="信箱(此為登入帳號)"
									value="<%=(userVO == null) ? "" : userVO.getUserEmail()%>
									">
									
							</div>
							<div class="form-group">
								<label for="password" class="form-label">Password：</label> <input
									id="password" type="password" class="form-control"
									name="userPassword" placeholder="密碼"
									value="<%=(userVO == null) ? "" : userVO.getUserPassword()%>">
									
							</div>
							<div class="form-group">
								<label for="comfirmpassword" class="form-label">ComfirmPassword：</label>
								<input id="comfirmpassword" type="password" class="form-control"
									name="comfirmpassword" placeholder="確認密碼"
									value="<%=(userVO == null) ? "" : userVO.getUserPassword()%>">
									
							</div>
							<div class="form-group">
								<label for="name" class="form-label">Name：</label> <input
									id="name" type="text" class="form-control" placeholder="姓名"
									name="userName"
									value="<%=(userVO == null) ? "" : userVO.getUserName()%>">
									
							</div>
							<div class="form-group">
								<label for="cellphone" class="form-label">CellPhone：</label> <input
									id="cellphone" type="text" class="form-control"
									name="userPhone" placeholder="手機號碼"
									value="<%=(userVO == null) ? "" : userVO.getUserPhone()%>">
									
							</div>
							<div class="form-group">
								<label for="identity" class="form-label">Identity：</label> <input
									id="identity" type="text" class="form-control"
									name="userIdentity" placeholder="身分證"
									value="<%=(userVO == null) ? "" : userVO.getUserIdentity()%>">
									
							</div>
							<div class="form-group">
								<label for="birthday" class="form-label">Birthday：</label> <input
									id="birthday" type="date" class="form-control" placeholder="生日"
									name="userBirthday"
									value="<%=(userVO == null) ? "" : userVO.getUserBirthday()%>">
									
							</div>
							<button type="submit" class="register btn-style-1 w-100" >Create
								an Account</button>
							<input type="hidden" name="action" value="insert"> 
<!-- 							<input -->
<!-- 	 							type="hidden" name="userRegistration" -->
<%-- 								value="<%=userVO.getUserRegistration()%>"> --%>
						</form>
					</div>
					<!-- register box end -->
				</div>
			</div>
		</div>
	</div>
	<!-- ================ Register page end ================ -->
	
<%@ include file="/footer.jsp" %>

</body>
</html>