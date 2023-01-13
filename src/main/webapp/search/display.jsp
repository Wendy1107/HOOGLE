<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="tw.com.hoogle.search.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/table.css" />
<style>
table {
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	margin: 45px;
	width: 480px;
	text-align: left;
	border-collapse: collapse;
	position: relative;
	left: 50px; 
}

th {
	font-size: 13px;
	font-weight: normal;
	padding: 8px;
	background: #b9c9fe;
	border-top: 4px solid #aabcfe;
	border-bottom: 1px solid #fff;
	color: #039;
}

td {
	padding: 8px;
	background: #e8edff; 
	border-bottom: 1px solid #fff;
	color: #669;
	border-top: 1px solid transparent;
}

tr:hover td {
	background: #d0dafd;
	color: #339;
}

</style>

<title>Display ${select.userId}</title>
</head>
<body>
<h3>Display userId = ${select.userId}</h3>
<c:if test="${not empty select}">
<table>
	<thead>
	<tr>
		<th>userId</th>
		<th>userEmail</th>
		<th>userPassword</th>
		<th>userName</th>
		<th>userPhone</th>
		<th>userIdentity</th>
		<th>userBirthday</th>
		<th>userRegistration</th>
	</tr>
	</thead>
	<tbody>
	
	<tr>
		<td>${select.userId}</td>
		<td>${select.userEmail}</td>
		<td>${select.userPassword}</td>
		<td>${select.userName}</td>
		<td>${select.userPhone}</td>
		<td>${select.userIdentity}</td>
		<td>${select.userBirthday}</td>
		<td>${select.userRegistration}</td>
	</tr>
	
	</tbody>
</table>
</c:if>
	
<h3><a href="<c:url value="/search/search.jsp" />">Search Table(重新查詢)</a></h3>

</body>
</html>