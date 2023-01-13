<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="tw.com.hoogle.room.model.*"%>


<%
  RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); //FoodServlet.java(Concroller), 存入req的foodVO物件
%>

<html>
<head>
<title>listOneFood.jsp</title>

<title>所有資料 </title>

<style>
.room-page {
    position: relative;
    padding: 30px 0;
}

.room-page .room-page-inner {
    padding: 30px;
    background: #ffffff;

}

.room-page .table {
    width: 100%;
    text-align: center;
    margin-bottom: 0;
}

.room-page .table .thead-dark th {
    font-family: 'Source Code Pro', monospace;
    font-size: 18px;
    font-weight: 700;
    color: #353535;
    text-align: center;
    background: transparent;
    border-color: #dddddd;
    border-bottom: none;
    vertical-align: middle;
}

.room-page .table td {
    font-size: 16px;
    vertical-align: middle;
    }
   
    
}
    
.room-page .table .img img {
    max-width: 60px;
    max-height: 60px;
    margin-right: 15px;
}
</style>
 <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/all.min.css" rel="stylesheet">
</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>listOneRoom.jsp</h3>
		 <a href="select_room_page.jsp"></a>	</td></tr>
</table>

<table>
	<tr>
		<th>房型流水號</th>
		<th>飯店編號</th>
		<th>房型名稱</th>
		<th>房型編號</th>
		<th>房型單價</th>
	</tr>
	<tr>
		    <td>${roomVO.roomAuto}</td>
			<td>${roomVO.hotelId}</td>
			<td>${roomVO.roomName}</td>
<%-- 			<td>${roomVO.roomId}</td> --%>
			<td>${roomVO.roomPrice}</td>
			
			
<%-- 		<td>${foodVO.foodPic}</td> --%>
	</tr>
</table>

</body>
</html>