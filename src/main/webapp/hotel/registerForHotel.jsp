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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>飯店註冊頁面</title>

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
						<h1>飯店註冊</h1>
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
						<form class="form-style-1 shadow p-30" METHOD="post" ACTION="HotelServlet">
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
								<input type="email" class="form-control" name="hotelEmail" placeholder="信箱(此為登入帳號)"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelEmail()%>">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" placeholder="密碼" name="hotelPassword"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPassword()%>"> 
							</div>
							<div class="form-group">
								<input type="password" class="form-control" placeholder="確認密碼" name="hotelcomfirmPassword"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPassword()%>">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="飯店名稱" name="hotelName"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelName()%>">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="電話" name="hotelPhone"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPhone()%>">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="負責人" name="hotelPrincipal"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPrincipal()%>">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="統一編號" name="hotelTaxid"
								value="<%=(hotelVO == null) ? "" : hotelVO.getHotelTaxid()%>">
							</div>
							<button type="submit" class="btn-style-1 w-100">Create
								an Account</button>
							<input type="hidden" name="hotelservlet" value="registerHotel">
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