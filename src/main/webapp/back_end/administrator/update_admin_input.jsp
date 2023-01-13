<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.administrator.model.*"%>
<%@ page import="java.text.Format"%>
<%@ page import="java.text.SimpleDateFormat"%>

<jsp:useBean id="administratorSvc" scope="page" class="tw.com.hoogle.administrator.model.AdministratorService"/>
<%
AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("administratorVO");
%>
<%
String account = (String)session.getAttribute("account");
AdministratorVO permissionsVO = administratorSvc.getPermissionsByAccount(account);
pageContext.setAttribute("permissionsVO", permissionsVO);
%>
<%
Format sfm = new SimpleDateFormat("yyyy/MM/dd");
pageContext.setAttribute("sfm", sfm);
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
		

		<div class="test_radius">更新管理者資料</div>
		<div class="login_mark">
			<%=account%> 登入中...
		</div>
	</div>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="AdministratorServlet" name="form1">
	<div class="div_table">
		<table>
			<tr class="td_head">
				<td width="40">管理者<br>編號</td>
				<td width="70">管理者<br>姓名</td>
				<td width="100">管理者帳號</td>
				<td width="100">管理者密碼</td>
				<td width="40">管理者<br>相關</td>
				<td width="55">上下架<br>最新消息</td>
				<td width="40">飯店<br>相關</td>
				<td width="40">旅客<br>相關</td>
				<td width="70">雇用日期</td>
			</tr>
			<tr class="td_body">
				<td><%=administratorVO.getAdministratorId()%></td>
				<td><input type="TEXT" name="administratorName" size="8"
					value="<%=administratorVO.getAdministratorName()%>" /></td>
				<td><%=administratorVO.getAdministratorAccount()%></td>
				<td><input type="TEXT" name="administratorPassword" size="15"
					value="<%=administratorVO.getAdministratorPassword()%>" /></td>
				<!-- 		設定value="true"的話，有勾選會回傳boolean，否則會回傳null -->
				<!-- 		利用三元運算，到資料庫取值，若資料庫資料是true則checkbox設定為checked -->
				<td><input type="checkbox" name="administratorDominate"
					value="true"
					<%=(administratorVO.getAdministratorDominate()) == false ? "" : "checked"%> /></td>
				<td><input type="checkbox" name="newsDominate" value="true"
					<%=(administratorVO.getNewsDominate()) == false ? "" : "checked"%> /></td>
				<td><input type="checkbox" name="hotelDominate" value="true"
					<%=(administratorVO.getHotelDominate()) == false ? "" : "checked"%> /></td>
				<td><input type="checkbox" name="userDominate" value="true"
					<%=(administratorVO.getUserDominate()) == false ? "" : "checked"%> /></td>
				<td>${sfm.format(administratorVO.administratorHiredate)}</td>
			</tr>

		</table>
	</div>
		<br> <input type="hidden" name="action" value="update">
		<!-- 以下3行是設定不能修改的欄位且要保留原儲存資料 -->
		<input type="hidden" name="administratorId"
			value="<%=administratorVO.getAdministratorId()%>">
		<input type="hidden" name="administratorAccount" value="<%=administratorVO.getAdministratorAccount()%>">
		<input type="hidden" name="administratorHiredate" value="<%=administratorVO.getAdministratorHiredate()%>">
		<div><input type="submit" value="送出修改" class="input_update"></div>
		
	</FORM>

	<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
	<script src="<%=request.getContextPath()%>/js/admin/admin.js"></script>

</body>
</html>