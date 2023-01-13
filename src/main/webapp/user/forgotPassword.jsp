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
<title>forgotPassword</title>

<!-- favicon -->
<!-- <link rel="icon" href="favicon.ico" /> -->
<!-- bootstrap core CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<!-- font awesome -->
<link
	href="<%=request.getContextPath()%>/css/all.min.css"
	rel="stylesheet">
<!-- owl carousel -->
<link
	href="<%=request.getContextPath()%>/css/owl.carousel.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/css/owl.theme.default.min.css"
	rel="stylesheet">
<!-- venobox css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/venobox.css">
<!-- datepicker css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/datepicker.min.css">
<!-- custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/custom.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/css/responsive.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/helper.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>

	<!-- ================ Inner banner ================ -->
	<div class="inner-banner inner-banner-bg pt-70 pb-40">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-8 col-md-8 mb-30">
					<!-- page-title -->
					<div class="page-title">
						<h1>忘記密碼</h1>
					</div>
					<!-- page-title end -->
				</div>
				<div class="col-lg-4 col-md-4 mb-30">
					<!-- breadcrumb -->
					<ol class="breadcrumb mb-0">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">Sign In</li>
					</ol>
					<!-- breadcrumb end -->
				</div>
			</div>
		</div>
	</div>
	<!-- ================ forgotPassword page ================ -->
	<div class="login-register-page pt-70 pb-70">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<!-- login box -->
					<div class="login-box">
						<form class="form-style-1 shadow p-30" action="UserServlet"
							method="POST">
							<h3 class="text-center">忘記密碼</h3>
							<br>

							<div class="form-group">
								<div class="input-group mb-3">
									<input type="email" class="form-control" placeholder="Email"
										name="userEmail"
										value="<%=(userVO == null) ? "" : userVO.getUserEmail()%>">

									<div class="input-group-append">
										<div class="input-group-text">
											<span class="fas fa-envelope"></span>
										</div>
									</div>
								</div>
								<c:if test="${not empty errorMsgs}">
									<!-- 								<font style="color: red">請修正以下錯誤:</font> -->
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>

								<div class="form-group">
									<button type="submit" class="btn-style-1 w-100">發送驗證信</button>
									<input type="hidden" name="action" value="forgotPassword">
								</div>
								<p class="mb-0 fas fa-exclamation-triangle">請到Email中提取新的密碼</p>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- ================ forgotPassword page end ================ -->

	<!-- js files -->
	<script
		src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
	<!-- counter js -->
	<script
		src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/waypoints.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/jquery.counterup.min.js"></script>
	<!-- venobox js -->
	<script
		src="<%=request.getContextPath()%>/js/venobox.min.js"></script>
	<!-- owl carousel -->
	<script
		src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
	<!-- portfolio js -->
	<script
		src="<%=request.getContextPath()%>/js/jquery.mixitup.min.js"></script>
	<!-- datepicker js -->
	<script
		src="<%=request.getContextPath()%>/js/datepicker.min.js"></script>
	<!-- script js -->
	<script src="<%=request.getContextPath()%>/js/custom.js"></script>

</body>
</html>