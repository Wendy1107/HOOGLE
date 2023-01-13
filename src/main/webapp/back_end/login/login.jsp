<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.administrator.model.*"%>

<jsp:useBean id="administratorSvc" scope="page" class="tw.com.hoogle.administrator.model.AdministratorService"/>
<%AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("administratorVO");%>

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
	<div class="img">
		<img src="<%=request.getContextPath()%>/images/logo_removebg.png" alt="#" width="140" height="140">
		<h2>管理者專區</h2>
	</div>

	<div class="login_page">
		<div id="container1">

			<div class="login">

				<h3>管理者登入</h3>
				<form method="post"
					action="<%=request.getContextPath()%>/AdministratorServlet">
					<input type="text" id="username" name="administratorAccount" 
<%-- 						value="<%=(administratorVO == null) ? "" : administratorVO.getAdministratorAccount()%>" --%>
						placeholder="帳號" required oninvalid="setCustomValidity('帳號不能為空')">
					<div class="tab"></div>
						<div class="tab"></div>

					<input type="password" id="password" name="administratorPassword"
<%-- 						value="<%=(administratorVO == null) ? "" : administratorVO.getAdministratorPassword()%>"  --%>
						placeholder="密碼" required oninvalid="setCustomValidity('密碼不能為空')">

						<!-- 錯誤提示 -->
						<div class="errormsg">
						  <c:if test="${not empty errorMsgs}">
							<c:forEach var="message" items="${errorMsgs}">
								${message}
							</c:forEach>
						  </c:if>
						</div>
						<div class="tab"></div>

					<!-- <input type="submit" value="login" class="submit"> -->

					<input type="hidden" name="action" value="login"> <input
						type="submit" value="登入" class="submit" onclick="setMyText()">
				</form>
		
				<!-- <h5 onclick="show_hide()">註冊帳號</h5> -->

			</div>
			<!-- login end-->
		</div>
		<!-- container1 end-->
	</div>
	<!-- login_page end-->


	<script>
		function show_hide() {
			var login = document.getElementById("container1");
			// var signup = document.getElementById("container2");
			// var copyright = document.getElementById("copyright");

			if (login.style.display === "none") {
				login.style.display = "block"; //lonin出現
				document.getElementById("username").value = "";
				document.getElementById("password").value = "";
				// signup.style.display = "none";  //signup消失
				// copyright.style.margin = "200px 0px 0px 0px";
			} else {
				login.style.display = "none"; //login消失
				// signup.style.display = "block"; //signup出現
				// signup.style.visibility = "visible";
				// copyright.style.margin = "200px 0px 0px 0px";

				// document.getElementById("fullname").value = "";
				// document.getElementById("username2").value = "";
				// document.getElementById("password2").value = "";
				// document.getElementById("comfirm_password").value = "";
			}
		}

		function setMyText() { // 因設置oninvalid="setCustomValidity('請輸入帳號')" 導致
			// validity.customError 就會變成true，則checkValidity()總是會返回false
			// 無論有沒有輸入值都會提示，因此設計此function，未輸入或輸入空白皆擋下
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			if (username.value.trim()=="" || username.validity.patternMismatch === true) {
				username.setCustomValidity("有值的");
			} else {
				username.setCustomValidity('');
			}

			if (password.value.trim()=="" || password.validity.patternMismatch === true) {
				password.setCustomValidity("有值的");
			} else {
				password.setCustomValidity('');
			}
		}
		// 		document.querySelector('.submit').addEventListener('click',	function() {
		// 							if ((document.getElementById("username").value).trim() == "") {
		// 								alert("請輸入帳號！");
		// 							} else if ((document.getElementById("password").value).trim() == "") {
		// 								alert("請輸入密碼！");
		// 							}
		// 						})
	</script>
</body>

</html>