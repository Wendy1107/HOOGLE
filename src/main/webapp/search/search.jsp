<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="/css/main.css" />

<title>Search</title>
<script type="text/javascript">
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
	}
</script>
</head>
<body>

	<h3>Welcome</h3>

	<h3>Product Table</h3>

	<form action="<c:url value="/search/search.controller" />" method="get">
		<table>
			<tr>
				<td>userId :</td>
				<td><input type="text" name="userId" value="${param.userId}"></td>
				<td><span class="error">${errors.userId}</span></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>userEmail :</td> -->
<!-- 				<td><input type="text" name="userEmail" -->
<%-- 					value="${param.userEmail}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>userPassword :</td> -->
<!-- 				<td><input type="text" name="userPassword" -->
<%-- 					value="${param.userPassword}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>userName :</td> -->
<!-- 				<td><input type="text" name="userName" -->
<%-- 					value="${param.userName}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>userPhone :</td> -->
<!-- 				<td><input type="text" name="userPhone" -->
<%-- 					value="${param.userPhone}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>userIdentity :</td> -->
<!-- 				<td><input type="text" name="userIdentity" -->
<%-- 					value="${param.userIdentity}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>userBirthday :</td> -->
<!-- 				<td><input type="text" name="userBirthday" -->
<%-- 					value="${param.userBirthday}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>userRegistration :</td> -->
<!-- 				<td><input type="text" name="userRegistration" -->
<%-- 					value="${param.userRegistration}"></td> --%>
<!-- 				<td></td> -->
<!-- 			</tr> -->
			<tr>
				<td><input type="submit" name="search" value="Select"> <input
					type="button" value="Clear" onclick="clearForm()"></td>
			</tr>
		</table>

	</form>

	<h3>
		<span class="error">${errors.action}</span>
	</h3>

</body>
</html>