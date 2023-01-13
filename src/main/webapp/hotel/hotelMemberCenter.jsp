<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.hotel.model.*"%>
<%@ page import="tw.com.hoogle.room.model.*"%>
<%@ page import="java.util.*"%>
<%
if (session.getAttribute("userVO") == null && session.getAttribute("hotelVO") == null) {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/HOOGLE/index.jsp");
	dispatcher.forward(request, response);
	return;
} else if (session.getAttribute("userVO") != null) {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/hotel/hotelMemberCenter.jsp");
	dispatcher.forward(request, response);
	return;
}

HotelVO hotelVO = (HotelVO) request.getSession().getAttribute("hotelVO"); //UserServlet.java(Controller)
Integer hotelId = hotelVO.getHotelId();
%>

<%
RoomService roomSvc = new RoomService();
List<RoomVO> list = roomSvc.getByHotelId(hotelId);
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>hotelMemberCenter</title>

<%@ include file="/header.jsp"%>


<link href="<%=request.getContextPath()%>/css/hotel/hotelIntroduce.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/hotel/roomlistall.css"
	rel="stylesheet">

</head>
<body>

	<!-- ================ Inner banner ================ -->
	<div class="inner-banner inner-banner-bg pt-70 pb-40">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-8 col-md-8 mb-30">
					<!-- page-title -->
					<div class="page-title">
						<h1>飯店會員中心</h1>
					</div>
					<!-- page-title end -->
				</div>
				<div class="col-lg-4 col-md-4 mb-30">
					<!-- breadcrumb -->
					<ol class="breadcrumb mb-0">
						<li class="breadcrumb-item"><a href="/HOOGLE/index.jsp">Home</a></li>
						<li class="breadcrumb-item active">Register</li>
					</ol>
					<!-- breadcrumb end -->
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>

	<!-- ================ memberCenter page ================ -->
	<!-- My Account Start -->
	<div class="my-account">
		<div class="container">
			<div class="row">
				<div class="col-lg-3" id="">
					<aside class="faqsidelink">
						<div>
							<ul class="nav flex-column nav-pills" role="tablist"
								aria-orientation="vertical">
								<li class="nav-item"><a class="nav-link active"
									id="account-nav" data-toggle="pill" href="#account-tab"
									role="tab"><i class="fa fa-user"></i> 資料修改</a></li>
								<li class="nav-item"><a class="nav-link" id="introduce-nav"
									data-toggle="pill" href="#introduce-tab" role="tab"><i
										class="fa fa-shopping-bag"></i> 飯店介紹</a></li>
								<li class="nav-item"><a class="nav-link" id="room-nav"
									data-toggle="pill" href="#room-tab" role="tab"><i
										class="fa fa-credit-card"></i> 房間管理</a></li>
								<li class="nav-item"><a class="nav-link" id="orders-nav"
									data-toggle="pill" href="#orders-tab" role="tab"><i
										class="fa fa-map-marker-alt"></i> 我的報表</a></li>
								<li class="nav-item" id="Signout"><a class="nav-link"><i
										class="fa fa-sign-out-alt"></i> 登出</a></li>
							</ul>
						</div>
					</aside>
				</div>
				<div class="col-md-9">
					<div class="tab-content">
						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">

							<!-- tabs -->
							<div class="detail-tabs mb-30">
								<ul class="nav nav-tabs nav-pills nav-justified" id="myTab"
									role="tablist">
									<li class="nav-item"><a class="nav-link active"
										id="tab1-tab" data-toggle="tab" href="#tab1" role="tab"
										aria-controls="tab1" aria-selected="true">資料修改</a></li>
								</ul>
								<!-- 飯店資料修改開始 -->
								<form action="HotelServlet" method="POST"
									enctype="multipart/form-data">
									<div class="tab-content" id="myTabContent">
										<div class="tab-pane fade show active p-15" id="tab1"
											role="tabpanel" aria-labelledby="tab1-tab">
											<h4 class="mb-6">飯店基本資料</h4>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
												class="dashed-border"></span>

											<div class="form-group">
												<h6>信箱</h6>
												<br> <input type="email" class="form-control" disabled
													name="hotelEmail"
													value="<%=(hotelVO == null) ? "" : hotelVO.getHotelEmail()%>">
											</div>


											<div class="form-group">
												<h6>名稱</h6>
												<br> <input type="text" class="form-control"
													name="hotelName"
													value="<%=(hotelVO == null) ? "" : hotelVO.getHotelName()%>">
												<span class="error" style="color: red">${errors.hotelName}</span>
											</div>


											<div class="form-group">
												<h6>電話</h6>
												<br> <input type="text" class="form-control"
													name="hotelPhone"
													value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPhone()%>">
												<span class="error" style="color: red">${errors.hotelPhone}</span>
											</div>

											<div class="form-group">
												<h6>負責人</h6>
												<br> <input type="text" class="form-control"
													name="hotelPrincipal"
													value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPrincipal()%>">
												<span class="error" style="color: red">${errors.hotelPrincipal}</span>
											</div>

											<div class="form-group">
												<h6>統一編號</h6>
												<br> <input type="text" class="form-control" disabled
													name="hotelTaxid"
													value="<%=(hotelVO == null) ? "" : hotelVO.getHotelTaxid()%>">
											</div>

											<div class="form-group">
												<h6>密碼修改</h6>
												<br> <input type="password" class="form-control"
													placeholder="請輸入密碼" name="hotelPassword"> <span
													class="error" style="color: red">${errors.hotelPassword}</span>
												<%-- 														value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPassword()%>" --%>
											</div>
											<div class="form-group">
												<h6>再次確認密碼</h6>
												<br> <input type="password" class="form-control"
													placeholder="再次確認密碼" name="comfirmPassword"> <span
													class="error" style="color: red">${errors.hotelPassword}</span>
											</div>
											<div class="form-group">
												<h6>飯店狀態</h6>
												<br> <input type="text" class="form-control" disabled
													name="hotelState"
													value="<%=(hotelVO == null) ? "" : hotelVO.getHotelState()%>">
											</div>
											<br>
											<button type="submit" class="btn-style-1 w-100">確認修改</button>
											<input type="hidden" name="hotelservlet" value="hotelupdate">
										</div>
								</form>
								<!-- 飯店資料修改結束 -->
							</div>
						</div>
						<!-- tabs end -->
					</div>
					<div class="col-lg-4 col-md-4"></div>



					<div class="tab-pane fade show" id="introduce-tab" role="tabpanel"
						aria-labelledby="dashboard-nav">


						<div class="card border-warning w-20">
							<div class="card-header  bg-warning sm-3">
								<h3 class="text-white">飯店介紹</h3>
							</div>
							<form
								action="<%=request.getContextPath()%>/otherhotel/OtherHotelServlet"
								method="post" enctype="multipart/form-data">
								<div class="card-body bg-transparents">

									<div class="form-row">

										<div class="form-group col-md-6">
											<select id="inputState" class="form-control" required
												name="hotelType">
												<option selected hidden value="${hotelVO.hotelType}">${hotelVO.hotelType}</option>
												<option value="飯店">飯店</option>
												<option value="民宿">民宿</option>
												<option value="商旅">商旅</option>
											</select>
										</div>
									</div>
									<div class="form-row">

										<div class="form-group col-md-12">
											<label for="exampleFormControlTextarea1" class="form-label">飯店介紹</label>
											<textarea class="form-control" name="hotelIntroduction"
												id="exampleFormControlTextarea1" rows="2">${hotelVO.hotelIntroduction}</textarea>
										</div>

										<div class="form-group col-md-12">
											<label for="exampleFormControlTextarea1" class="form-label">飯店須知</label>
											<textarea class="form-control" name="hotelNotice"
												id="exampleFormControlTextarea1" rows="2">${hotelVO.hotelNotice}</textarea>
										</div>
										<div class="form-group col-md-12">
											<label for="exampleFormControlTextarea1" class="form-label">常見問答</label>
											<textarea class="form-control" name="hotelQa"
												id="exampleFormControlTextarea1" rows="2">${hotelVO.hotelQa}</textarea>
										</div>

										<div class="form-check">
											<input class="form-check-input" name="serviceName"
												type="checkbox" value="9001" id="flexCheckDefault">
											<label class="form-check-label" for="flexCheckDefault">
												停車場 </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" name="serviceName"
												type="checkbox" value="9002" id="flexCheckChecked" checked>
											<label class="form-check-label" for="flexCheckChecked">
												可攜帶寵物 </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" name="serviceName"
												type="checkbox" value="9003" id="flexCheckDefault">
											<label class="form-check-label" for="flexCheckDefault">
												24小時接待櫃台 </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" name="serviceName"
												type="checkbox" value="9004" id="flexCheckChecked" checked>
											<label class="form-check-label" for="flexCheckChecked">
												健身中心 </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" name="serviceName"
												type="checkbox" value="9005" id="flexCheckDefault">
											<label class="form-check-label" for="flexCheckDefault">
												無障礙設施 </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" name="serviceName"
												type="checkbox" value="9006" id="flexCheckChecked" checked>
											<label class="form-check-label" for="flexCheckChecked">
												游泳池 </label>
										</div>




										<select id="city" name="hotelCounty">
											<option value="${hotelVO.hotelCounty}">請選擇</option>
										</select> <select id="area" name="area" style="display: none;">
											<option value="">請選擇</option>
										</select>



										<div class="form-group col-md-12">
											<input type="text" class="form-control" id="inputAddress2"
												name="hotelAddress" placeholder="飯店地址"
												value="${hotelVO.hotelAddress}">
										</div>
										<span class="error" style="color: red">${errors.hotelAddress}</span>

										<div class="form-group col-md-9">
											<label for="formFile" class="form-label">圖片</label>
											<!-- <input class="form-control" type="file" id="formFile"> -->
										</div>


										<input type="file" id="the_file" name="the_file" multiple
											accept="image/*">
										<ul class="picture_list">
										</ul>


										<div class="d-grid gap-2 col-6  mx-auto">
											<!-- <input class="btn btn-primary" type="submit" value="Submit">
                                    <input class="btn btn-primary" type="reset" value="Reset"> -->
											<button type="submit"
												class="btn btn-primary btn-lg btn-block mt-3">送出</button>
											<input type="hidden" name="otherhotelservlet"
												value="updateHotel">
											<button type="reset"
												class="btn btn-dark btn-lg btn-block mt-3">重置</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>

					<!-- ====================================房間管理開始=============================== -->
					<div class="tab-pane fade" id="room-tab" role="tabpanel"
						aria-labelledby="payment-nav">
						<table id="table-1">
							<tr>
								<td>
									<h4>房間管理</h4> <a
									href='<%=request.getContextPath()%>/room/newAddRoomFile.jsp'
									class="badge bg-warning text-dark">新增房型</a>
								</td>
							</tr>
						</table>
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead class="thead-dark">
										<tr>
											<th>房型流水號</th>
											<th>飯店編號</th>
											<th>房間總數</th>
											<th>房型名稱</th>
											<th>房型狀態</th>
											<th>房型單價</th>
											<th>房型照片</th>
											<th>修改</th>
										</tr>
									</thead>

									<%@ include file="../room/page1.file"%>
									<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">


										<tr>
											<td>${roomVO.roomAuto}</td>
											<td>${roomVO.hotelId}</td>
											<td>${roomVO.roomTotal}</td>
											<td>${roomVO.roomName}</td>
											<td><ion-icon
													name=${roomVO.roomStatus==true?"checkbox-outline":"square-outline"}></ion-icon></td>
											<td>${roomVO.roomPrice}</td>
											<td><img
												src="${pageContext.request.contextPath}/roompic/RoompicReader?roompicId=${roomVO.roomAuto}"
												height="50" width="50"></td>


											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/room/RoomServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改"> <input
														type="hidden" name="roomAuto" value="${roomVO.roomAuto}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>



											</td>

										</tr>
									</c:forEach>
								</table>

							</div>
						</div>


						<%@ include file="../room/page2.file"%>
					</div>
					<!-- ====================================房間管理結束=============================== -->

					<div class="tab-pane fade" id="orders-tab" role="tabpanel"
						aria-labelledby="orders-nav">

						<form action="<%=request.getContextPath()%>/room/ord"
							style="margin-bottom: 50px;" method="POST">
							<b>請輸入日期:</b> <input type="date" name="ordDateStart"
								value="2022-01-01"> <input type="date" name="ordDateEnd"
								value="2022-12-31"> <br> <b>房型名稱:</b> <select
								size="1" name="roomName" required>
								<option value="單人房">單人房
								<option value="雙人房">雙人房
								<option value="四人房">四人房
							</select> <input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</form>					
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- My Account End -->
	<!-- ================ MemberCenter page end ================ -->
	<!-- ================ Top scroll ================ -->
	<a href="#" class="scroll-top">Top</a>
	<!-- ================ Top scroll end ================ -->

	<!-- js files -->

	<%@ include file="/footer.jsp"%>

	<!-- hotelIntroduce js by Joy -->
	<script src="<%=request.getContextPath()%>/js/hotel/hotelIntroduce.js"></script>
	<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
</body>
</html>