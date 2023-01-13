<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="tw.com.hoogle.searchHotel.model.*" %>

<!--      SearchHotelService searchHotelSvc = new SearchHotelService(); -->
<!--      List<SearchHotelBean> list = searchHotelSvc.getAll(); -->
<!--      pageContext.setAttribute("list",list); -->

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

<%-- <title>Display ${select.hotelCounty}</title> --%>
</head>
<body>
<%-- <h3>Display hotelCounty = ${select.hotelCounty}</h3> --%>
<c:if test="${not empty select}">
<table>
	<thead>
	<tr>
		<th>hotelId</th>
		<th>hotelEmail</th>
		<th>hotelPassword</th>
		<th>hotelName</th>
		<th>hotelPhone</th>
		<th>hotelPrincipal</th>
		<th>hotelTaxid</th>
		<th>hotelCounty</th>
		<th>hotelAddress</th>
		<th>hotelType</th>
		<th>hotelNotice</th>
		<th>hotelQa</th>
		<th>hotelIntroduction</th>
		<th>hotelState</th>
	</tr>
	</thead>
	<tbody>
		
		<c:forEach var="row" items="${select}">
		<c:url value="/searchHotel/displayHotel.jsp" var="path">
			<c:param name="hotelId" value="${row.hotelId}" />
			<c:param name="hotelEmail" value="${row.hotelEmail}" />
			<c:param name="hotelPassword" value="${row.hotelPassword}" />
			<c:param name="hotelName" value="${row.hotelName}" />
			<c:param name="hotelPhone" value="${row.hotelPhone}" />
			<c:param name="hotelPrincipal" value="${row.hotelPrincipal}" />
			<c:param name="hotelTaxid" value="${row.hotelTaxid}" />
			<c:param name="hotelCounty" value="${row.hotelCounty}" />
			<c:param name="hotelAddress" value="${row.hotelAddress}" />
			<c:param name="hotelType" value="${row.hotelType}" />
			<c:param name="hotelNotice" value="${row.hotelNotice}" />
			<c:param name="hotelQa" value="${row.hotelQa}" />
			<c:param name="hotelIntroduction" value="${row.hotelIntroduction}" />
			<c:param name="hotelState" value="${row.hotelState}" />
		</c:url>
		
	<tr>
		<td><a href="${path}">${row.hotelId}</a></td>
		<td>${row.hotelEmail}</td>
		<td>${row.hotelPassword}</td>
		<td>${row.hotelName}</td>
		<td>${row.hotelPhone}</td>
		<td>${row.hotelPrincipal}</td>
		<td>${row.hotelTaxid}</td>
		<td>${row.hotelCounty}</td>
		<td>${row.hotelAddress}</td>
		<td>${row.hotelType}</td>
		<td>${row.hotelNotice}</td>
		<td>${row.hotelQa}</td>
		<td>${row.hotelIntroduction}</td>
		<td>${row.hotelState}</td>
	</tr>
		</c:forEach>
	
	</tbody>
</table>
</c:if>
<form  action="${pageContext.request.contextPath}/searchHotel/searchHotel.controller" method="get">
<%-- <form action="<%=request.getContextPath()%>/searchHotel/searchHotel.controller" method="get"> --%>
<input type="submit" name="searchHotel" value="Search Table(重新查詢)">
<input type="hidden" name="searchHotel" value="ListAll">
</form>
</body>
</html>