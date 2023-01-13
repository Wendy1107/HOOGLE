<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.hoogle.administrator.model.*"%>
<%@ page import="tw.com.hoogle.news.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="administratorSvc" scope="page" class="tw.com.hoogle.administrator.model.AdministratorService"/>
<jsp:useBean id="newsSvc" scope="page" class="tw.com.hoogle.news.model.NewsService"/>
<%
List<AdministratorVO> list = administratorSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
String dateFrom = ((Date)(request.getAttribute("dateFrom"))).toString();
String dateEnd = ((Date)(request.getAttribute("dateEnd"))).toString();
List<NewsVO> searchNews = newsSvc.findNewsByDate(java.sql.Date.valueOf(dateFrom), 
		                                         java.sql.Date.valueOf(dateEnd));
System.out.println("日期轉型成功");
pageContext.setAttribute("dateFrom", dateFrom);
pageContext.setAttribute("dateEnd", dateEnd);
pageContext.setAttribute("searchNews", searchNews);
%>
<%
String account = (String)session.getAttribute("account");
AdministratorVO permissionsVO = administratorSvc.getPermissionsByAccount(account);
pageContext.setAttribute("permissionsVO", permissionsVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HOOGLE</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/admin.css">


</head>
<body id="body-pd">

	<div class="l-navbar style-3" id="navbar">
		<nav class="nav">
			<div>
				<div class="nav_link">
					<a href="<%=request.getContextPath()%>/back_end/administrator/adminIndex.jsp" class="nav_logo"> <img
						src="<%=request.getContextPath()%>/images/logo_small_removebg.png" class="pic"> <span
						class="nav_name aaa">HOOGLE</span>
					</a>
				</div>
				
				<div class="nav_brand">
					<!-- <ion-icon name="menu-outline" class="nav_toggle" id="nav_toggle"></ion-icon> -->
					<span> <ion-icon name="menu-outline" class="nav_toggle"
							id="nav-toggle"></ion-icon> <span class="nav_ch">管理者專區</span>
					</span>
				</div>
				
				<div class="nav_list">
					<!-- <div class="nav_h2">管理者專區</div>  要調整字的顏色、字在縮起來時消失-->
					
					<a href="<%=request.getContextPath()%>/back_end/finStm/stmList.jsp" class="nav_link">
						<ion-icon name="stats-chart-outline" class="nav_icon"></ion-icon>
						<span class="nav_name">報表查詢</span>
					</a>
					
					<div class="nav_link collapse" 
						style="display:${permissionsVO.hotelDominate==false && permissionsVO.userDominate==false?"none":""}">
						<ion-icon name="search-outline" class="nav_icon"></ion-icon>
						<span class="nav_name">飯店及旅客資訊</span>
						<ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>
						<ul class="collapse_menu">
							<div style="display:${permissionsVO.hotelDominate==true?"":"none"}">
								<a href="<%=request.getContextPath()%>/back_end/hotelAndUser/hotelList.jsp" class="collapse__sublink">
									<pre>飯店</pre>
								</a>
							</div>
							<div style="display:${permissionsVO.userDominate==true?"":"none"}">
								<a href="<%=request.getContextPath()%>/back_end/hotelAndUser/userList.jsp" class="collapse__sublink">
									<pre>旅客</pre>
								</a>
							</div>	
						</ul>
					</div>
					
<%-- 					<div class="nav_link collapse" style="display:${permissionsVO.hotelDominate==true?"":"none"}"> --%>
<!-- 						<ion-icon name="checkmark-done-outline" class="nav_icon"></ion-icon> -->
<!-- 						<span class="nav_name">審核飯店</span> -->
<!-- 						<ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon> -->
<!-- 						<ul class="collapse_menu"> -->
<%-- 							<a href="<%=request.getContextPath()%>/back_end/approval/approveRegisterHotel.jsp" class="collapse__sublink"> --%>
<!-- 								<pre>飯店註冊</pre> -->
<!-- 							</a> -->
<!-- 							<a href="#" class="collapse__sublink"> -->
<!-- 								<pre>房間上架</pre> -->
<!-- 							</a> -->
<!-- 						</ul> -->
<!-- 					</div> -->
					<div style="display:${permissionsVO.hotelDominate==true?"":"none"}">
						<a href="<%=request.getContextPath()%>/back_end/approval/approveRegisterHotel.jsp" 
							class="nav_link">
							<ion-icon name="checkmark-done-outline" class="nav_icon"></ion-icon>
							<span class="nav_name">審核飯店</span>
						</a>
					</div>
					
					<div style="display:${permissionsVO.administratorDominate==true?"":"none"}">
						<a href="<%=request.getContextPath()%>/back_end/administrator/admin_page.jsp" 
							class="nav_link">
							<ion-icon name="people-outline" class="nav_icon"></ion-icon>
							<span class="nav_name">管理者資料</span>
						</a>
					</div>
					
<!-- 					<div class="nav_link collapse"  -->
<%-- 						style="display:${permissionsVO.hotelDominate==false && permissionsVO.userDominate==false?"none":""}"> --%>
<!-- 						<ion-icon name="mail-outline" class="nav_icon"></ion-icon> -->
<!-- 						<span class="nav_name">系統訊息管理</span> -->
<!-- 						<ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon> -->
<!-- 						<ul class="collapse_menu"> -->
<%-- 							<div style="display:${permissionsVO.hotelDominate==true?"":"none"}"> --%>
<!-- 								<a href="#" class="collapse__sublink"><pre>飯店訊息</pre></a> -->
<!-- 							</div> -->
<%-- 							<div style="display:${permissionsVO.userDominate==true?"":"none"}"> --%>
<!-- 								<a href="#" class="collapse__sublink"><pre>旅客訊息</pre></a> -->
<!-- 							</div> -->
<!-- 						</ul> -->
<!-- 					</div> -->
					
					<div style="display:${permissionsVO.newsDominate==true?"":"none"}">
						<a href="<%=request.getContextPath()%>/back_end/news/newsList.jsp" class="nav_link">
							<ion-icon name="newspaper-outline" class="nav_icon"></ion-icon>
							<span class="nav_name">最新消息管理</span>
						</a>
					</div>
										
				</div>
			</div>
		</nav>
	</div>
	
	<div class="func_list">

		<a href="<%=request.getContextPath()%>/AdministratorServlet?action=logout" class="logout_link">
 			<ion-icon name="log-out-outline" class="logout_icon"></ion-icon>
 			<label class="logout_text">登出</label>
 		</a>
<%-- 		<form href="<%=request.getContextPath()%>/AdministratorServlet" class="logout_link"> --%>
<!-- 			<input id="logout_icon" type="hidden" name="action" value="logout"> -->
<!-- 			<ion-icon type="submit" for="logout_icon" name="log-out-outline" class="logout_icon"> -->
<!-- 			</ion-icon> -->
<!-- 		</form> -->
		
<!-- 		<a href="#" class="logout_link"> -->
<!-- 			<ion-icon name="notifications-outline" class="notification_icon"></ion-icon> -->
<!-- 		</a> -->
		

		<div class="test_radius">最新消息搜尋結果</div>
		<div class="login_mark">
			<%=account%> 登入中...
		</div>
	</div>


	<div class="features">
		<div class="features_search">
			<label class="count_register_hotel"><%=dateFrom%>~<%=dateEnd%>：共有 ${searchNews.size()} 筆</label>
			<a class="link_news_list" href="<%=request.getContextPath()%>/back_end/news/newsList.jsp">最新消息列表</a>
		</div>
		
	</div>
	<div class="div_table">
	<table>
		<tr class="td_head">
			<th width="45">編號</th>
			<th width="40">管理者<br>編號</th>
			<th width="80">最新消息主旨</th>
			<th width="140">最新消息內容</th>
			<th width="60">上架日期</th>
			<th width="80">照片</th>
			<th width="40">狀態</th>
			<th width="40">下架</th>
		</tr>
		<%-- 	<%@ include file="page1.file" %>  --%>
		<%-- 	<c:forEach var="administratorVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		<c:forEach var="newsVO" items="${searchNews}">
			<tr class="td_body" style=${newsVO.newsState==0?"color:gray;":""}>
						
				<td>${newsVO.newsId}</td>
				<td>${newsVO.administratorId}</td>
				<td>${newsVO.newsSubject}</td>
				<td>${newsVO.newsContent}</td>
				<td>${newsVO.newsDate}</td>
				<td>
					<img src="${pageContext.request.contextPath}/NewsPicReader?newsId=${newsVO.newsId}" height="100" width="100">
				</td>
				<td>${newsVO.newsState==0?"已下架":"上架中"}</td>
				<td>
					<form>
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
	</div>
	<%-- <%@ include file="page2.file" %> --%>

	<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
	<script src="<%=request.getContextPath()%>/js/admin/admin.js"></script>
</body>
</html>