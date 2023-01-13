<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.compare.model.*"%>
<%@ page import="java.util.*"%>
<%
CompareVO compareVO = (CompareVO) request.getAttribute("compareVO");
CompareService compareSvcList = new CompareService();
List<CompareVO> listService = compareSvcList.getAll();
pageContext.setAttribute("listService", listService);
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>比較您的選擇</title>

<%@ include file="/header.jsp"%>

<style>
/* p#detail { */
/* 	height: 500px; */
/* 	width: 80%; */
/* 	background: lightgray; */
/* 	margin-inline: auto; */
/* 	overflow-x: hidden; */
/* 	overflow-y: scroll; */
/* 	font-size: 20px; */
/* 	white-space: pre-wrap; */
/* 	font-weight: bolder; */
/* } */
textarea.type {
	display: inline-block;
	margin: 10px 20px 20px 20px;
	width: calc(( 100% - 150px)/3);
	font-size: 20px;
	background-color: lightblue;
	text-rendering: auto;
}

h1 {
	text-align: center;
	margin-top: 5px;
	margin-bottom: 5px;
}

b {
	font-size: 20px;
}
</style>
</head>
<body>


	<!-- 搜尋日期房型 開始 -->

	<!-- 	<div class="center-search"> -->
	<!-- 		<h1 class="text-white">Enjoy Your Holiday</h1> -->
	<!-- 		<p class="text-white">Search and Book Hotel</p> -->
	<!-- 		<form class="form-style-1"> -->

	<!-- 			<div class="row"> -->
	<!-- 				<div class="col-lg-6 col-md-6 col-sm-6 col-6 checkin"> -->
	<!-- 					<div class="form-group"> -->
	<!-- 						<input type="text" class="form-control" id="check-in" -->
	<!-- 							placeholder="Check In"> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 				<div class="col-lg-6 col-md-6 col-sm-6 col-6 checkout"> -->
	<!-- 					<div class="form-group"> -->
	<!-- 						<input type="text" class="form-control" id="check-out" -->
	<!-- 							placeholder="Check Out"> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->

	<!-- 			<button type="submit" class="btn-style-1 w-100">Search</button> -->
	<!-- 		</form> -->
	<!-- 	</div> -->
	<!-- 搜尋日期房型 結束 -->

	<!-- 比較數量 開始 -->
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/compare/CompareServlet"
		enctype="multipart/form-data">
		<h1>比較飯店</h1>
		<div class="blog-area pt-70 pb-40 position-relative"
			style="padding-top: 0px">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-6 mb-30">
						<!-- blog box -->
						<div class="blog-box shadow1">
							<div class="blog_img mb-20">
								<!-- 								<img -->
								<%-- 									src="<%=request.getContextPath()%>/images/compare/blog/blog-1.jpg" --%>
								<!-- 									alt="" /> -->

							</div>
							<div class="blog-des">
								<div class="form-group">
									<ul>
										<jsp:useBean id="compareSvc" scope="page"
											class="tw.com.hoogle.compare.model.CompareService"></jsp:useBean>
										<li>
											<%-- 										<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/compare/CompareServlet"> --%>
											<b>飯店1：</b> <select size="1" name="hotelName1"
											style="font-size: 20px">
												<c:forEach var="compareVO" items="${compareSvc.all}">
													<option value="${compareVO.hotelName}">${compareVO.hotelName}
												</c:forEach>
										</select> <!-- 												<input type="hidden" name="action" value="getOne_For_Display">  -->
											<!-- 	<input type="submit" value="查看詳情" class="btn-style-1"> -->
											<!-- 										</FORM> -->
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- blog box end -->
					</div>
					<div class="col-lg-4 col-md-6 mb-30">
						<!-- blog box -->
						<div class="blog-box shadow1">
							<div class="blog_img mb-20">
								<!-- 								<img -->
								<%-- 									src="<%=request.getContextPath()%>/images/compare/blog/blog-1.jpg" --%>
								<!-- 									alt="" /> -->
							</div>
							<div class="blog-des">
								<div class="form-group">
									<ul>
										<li>
											<%-- 										<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/compare/CompareServlet"> --%>
											<b>飯店2：</b> <select size="1" name="hotelName2"
											style="font-size: 20px">
												<c:forEach var="compareVO" items="${compareSvc.all}">
													<option value="${compareVO.hotelName}">${compareVO.hotelName}
												</c:forEach>
										</select> <!-- 												<input type="hidden" name="action" value="getOne_For_Display">  -->
											<!-- 	<input type="submit" value="查看詳情" class="btn-style-1"> -->
											<!-- 										</FORM> -->
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- blog box end -->
						<!-- 送出比較按鈕 -->
						<br>
						<div style="text-align: center">
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="hidden" name="action"
								value="getOneService_For_Display"> <input type="submit"
								name="action" value="送出比較" class="btn-style-1">
						</div>
						<!-- 送出比較按鈕 結束 -->

					</div>
					<div class="col-lg-4 col-md-6 mb-30">
						<!-- blog box -->
						<div class="blog-box shadow1">
							<div class="blog_img mb-20">
								<!-- 								<img -->
								<%-- 									src="<%=request.getContextPath()%>/images/compare/blog/blog-1.jpg" --%>
								<!-- 									alt="" /> -->

							</div>
							<div class="blog-des">
								<div class="form-group">
									<ul>
										<li><b>飯店3：</b> <select size="1" name="hotelName3"
											style="font-size: 20px">
												<c:forEach var="compareVO" items="${compareSvc.all}">
													<option value="${compareVO.hotelName}">${compareVO.hotelName}
												</c:forEach>
										</select> <!-- 												<input type="hidden" name="action" value="getOne_For_Display">  -->
											<!-- 	<input type="submit" value="查看詳情" class="btn-style-1"> -->
											<!-- 										</FORM> --></li>

									</ul>
								</div>
							</div>
						</div>

						<!-- blog box end -->
					</div>
					<!-- blog box end -->
				</div>
			</div>
		</div>
	</FORM>
	<!-- 比較數量 結束 -->

	<!-- 介紹內文 開始 -->
	<!-- 	<div class="detail"> -->
	<!-- 		<p id="detail" style="word-wrap: break-word;"> -->
	<%-- 			<c:if test="${not empty list}"> --%>
	<%-- 				飯店:${list[0].hotelName}  --%>
	<%-- 				地址:${list[0].hotelAddress} --%>
	<%-- 				<c:forEach var="compareVO" items="${list}"> --%>
	<%-- 				房型:${compareVO.roomName}	價格:${compareVO.roomPrice} --%>
	<%-- 				</c:forEach> --%>
	<%-- 			</c:if> --%>
	<!-- 		</p> -->
	<!-- 	</div> -->
	<!-- <textarea class="detila" name="mytext"  placeholder="飯店介紹" readonly></textarea> -->
	<!-- 介紹內文 結束 -->

	<!-- 自助黏貼區 開始 -->
	<!-- blog box -->
	<div class="images">
		<span class="images"> <!-- 		<img --> <%-- 			src="<%=request.getContextPath()%>/images/compare/blog/blog-1.jpg" --%>
			<!-- 			alt="" style="margin-left:4px;margin-right:20px;border-radius: 10px;"/> -->
			<c:if test="${not empty list1}">
				<img
					style="margin-left: 20px; margin-right: 20px; border-radius: 10px; width: calc(( 100% - 150px)/3);"
					src="${pageContext.request.contextPath}/compare/HotelpicReader?hotelName=${list1[0].hotelName}">
			</c:if> <!-- 		<img --> <%-- 			src="<%=request.getContextPath()%>/images/compare/blog/blog-1.jpg" --%>
			<!-- 			alt="" style="margin-left:20px;margin-right:20px;border-radius: 10px;"/>  -->
			<c:if test="${not empty list2}">
				<img
					style="margin-left: 20px; margin-right: 20px; border-radius: 10px; width: calc(( 100% - 150px)/3);"
					src="${pageContext.request.contextPath}/compare/HotelpicReader?hotelName=${list2[0].hotelName}">
			</c:if> <!-- 		<img --> <%-- 			src="<%=request.getContextPath()%>/images/compare/blog/blog-1.jpg" --%>
			<!-- 			alt="" style="margin-left:20px;margin-right:21px;border-radius: 10px;"/> -->
			<c:if test="${not empty list3}">
				<img
					style="margin-left: 20px; margin-right: 20px; border-radius: 10px; width: calc(( 100% - 150px)/3);"
					src="${pageContext.request.contextPath}/compare/HotelpicReader?hotelName=${list3[0].hotelName}">
			</c:if>
		</span>
	</div>
	<div class="self-typying">
		<textarea class="type" name="mytext" rows="15" cols="20"
			placeholder="" style="border-radius: 10px;">
<c:if test="${not empty list1}">
飯店:${list1[0].hotelName} 
地址:${list1[0].hotelAddress}
服務設施:<c:forEach var="compareVO" items="${listservice1}">${compareVO.serviceName} </c:forEach>
============================
<c:forEach var="compareVO" items="${list1}">${compareVO.roomName}	價格:${compareVO.roomPrice}
</c:forEach>
</c:if>
</textarea>
		<textarea class="type" name="mytext" rows="15" cols="20"
			placeholder="" style="border-radius: 10px;">
<c:if test="${not empty list2}">
飯店:${list2[0].hotelName} 
地址:${list2[0].hotelAddress}
服務設施:<c:forEach var="compareVO" items="${listservice2}">${compareVO.serviceName} </c:forEach>
============================
<c:forEach var="compareVO" items="${list2}">${compareVO.roomName}	價格:${compareVO.roomPrice}
</c:forEach>

</c:if>
</textarea>
		<textarea class="type" name="mytext" rows="15" cols="20"
			placeholder="" style="border-radius: 10px;">
<c:if test="${not empty list3}">
飯店:${list3[0].hotelName} 
地址:${list3[0].hotelAddress}
服務設施:<c:forEach var="compareVO" items="${listservice3}">${compareVO.serviceName} </c:forEach>
============================
<c:forEach var="compareVO" items="${list3}">${compareVO.roomName}	價格:${compareVO.roomPrice}
</c:forEach>
</c:if>
</textarea>
	</div>
	<!-- blog box end -->
	<!-- 自助黏貼區 結束 -->


	<!-- js files -->

	<%@ include file="/footer.jsp"%>
</body>
</html>
