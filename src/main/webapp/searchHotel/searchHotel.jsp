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

	<h3>Hotel Table</h3>

	<form action="<c:url value="/searchHotel/searchHotel.controller" />" method="get">
		<table>
			<tr>
				<td>hotelCounty :</td>
				<td><input type="text" name="hotelCounty" value="${param.hotelCounty}"></td>
				<td><span class="error">${errors.hotelCounty}</span></td>
			</tr>
			<tr>
				<td><input type="submit" name="searchHotel" value="Select"> <input
					type="button" value="Clear" onclick="clearForm()"></td>
			</tr>
		</table>

	</form>

	<h3>
		<span class="error">${errors.action}</span>
	</h3>

</body>
</html>