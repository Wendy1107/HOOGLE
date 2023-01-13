<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.hotel.model.*"%>
<%
HotelVO hotelVO = (HotelVO) request.getAttribute("hotelVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>loginForHotel</title>

<%@ include file="/header.jsp" %>

<!-- loginForhotel > forgotPassword css -->
<link href="<%=request.getContextPath()%>/css/hotel/hotel.css" rel="stylesheet">
</head>
<body>
	<!-- ================ Inner banner ================ -->
	<div class="inner-banner inner-banner-bg pt-70 pb-40">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-8 col-md-8 mb-30">
					<!-- page-title -->
					<div class="page-title">
						<h1>登入</h1>
					</div>
					<!-- page-title end -->
				</div>
				<div class="col-lg-4 col-md-4 mb-30">
					<!-- breadcrumb -->
					<ol class="breadcrumb mb-0">
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
						<li class="breadcrumb-item active">Sign In</li>
					</ol>
					<!-- breadcrumb end -->
				</div>
			</div>
		</div>
	</div>
	<!-- ================ Login page ================ -->
	<div class="login-register-page pt-70 pb-70">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<!-- login box -->
					<div class="login-box">
						<form class="form-style-1 shadow p-30" METHOD="post" ACTION="<%=request.getContextPath()%>/hotel/HotelServlet" enctype="multipart/form-data">
							<h3 class="text-center">飯店登入系統</h3>
							<br>

							<c:if test="${not empty errorMsgs}">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<center><li style="color: red">${message}</li></center>
									</c:forEach>
								</ul>
							</c:if>

							<br>
							<div class="form-group">
								<input type="email" class="form-control" placeholder="Email" name="hotelEmail" autocomplete="off">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" placeholder="密碼" name="hotelPassword">
							</div>		
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6">
									<div class="form-group">
										<div class="forgot-password">
											<a href=".hotelPasswordChange" class="text-danger ForgotPasswordForHotel">忘記密碼</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<button type="submit" class="btn-style-1 w-100">Sign In</button>
								<input type="hidden" name="hotelservlet" value="loginForHotel"> 
							</div>
							<p class="mb-0">
								還不是會員？ <a href="<%=request.getContextPath()%>/hotel/registerForHotel.jsp">註冊專區</a>
							</p>
						</form>
					</div>
					<!-- login box end -->
				</div>
			</div>
		</div>
	</div>
	<!-- ================ Login page end ================ -->
	
	<!-- ForgotPasswordForHotel page -->
<form class="forget" action="<%=request.getContextPath()%>/hotel/HotelServlet" method="POST" enctype="multipart/form-data">
		<div class="hotelPasswordChange" Style="display: none">
			<article>
				<label for="hotellogin" class="input-label">
				 	<input type="text" name="hotelEmail" placeholder="請填註冊時信箱" autocomplete="off">		 	
				 	<input type="text" name="hotelTaxid" placeholder="請填註冊時統一編號" autocomplete="off">
					
					<button type="submit" class="input-button hotelbtn">送出</button>
					<input type="hidden" name="hotelservlet" value="forgotPasswordForHotel">
					<button type="button" class="btn_modal_close btnNew hotelbtn">關閉</button>
				</label>
			</article>
		</div>               
</form>
<!-- ForgotPasswordForHotel page end-->
	
<%@ include file="/footer.jsp" %>
<!-- hotelForgotPassword js -->
<script src="<%=request.getContextPath()%>/js/hotel/hotelForgotPassword.js"></script>
</body>
</html>