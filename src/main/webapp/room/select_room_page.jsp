<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.room.model.*"%>

<html>
<head>
<title>房間統計查詢</title>



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
<!--     <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/all.min.css" rel="stylesheet"> -->
</head>
<body bgcolor='white'>

                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>資料查詢</th>
                                        
                                    </tr>
                                </thead>
                            </table>
                        </div>
                   </div>
   <a href='<%=request.getContextPath()%>/hotel/hotelMemberCenter.jsp' class="btn btn-primary">返回</a>
	
    <form action="<%=request.getContextPath()%>/room/ord" style="margin-bottom: 50px;" method="POST">
        <b>請輸入日期:</b>
        <input type="date" name="ordDateStart" value="2022-01-01"  >
        <input type="date" name="ordDateEnd"  value="2022-12-31"  >
        <br>
       <b>房型名稱:</b>
       <select size="1" name="roomName" required>
             <option value="單人房">單人房
             <option value="雙人房">雙人房
             <option value="四人房">四人房
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </form>
	<table border="1">
		<thead>
			<tr>
				<th>roomName</th>
				<th>roomPrice</th>
				<th>roomCount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.roomName}</td>
					<td>${vo.roomPrice}</td>
					<td>${vo.roomCount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>