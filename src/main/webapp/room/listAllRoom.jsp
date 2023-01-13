<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="tw.com.hoogle.room.model.*"%>



<%
RoomService roomSvc = new RoomService();
    List<RoomVO> list = roomSvc.getAll();
    pageContext.setAttribute("list",list);
%>



<html>
<head>
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
		 <h4>房間管理 </h4>
				 <a href='<%=request.getContextPath()%>/room/newAddRoomFile.jsp' class="btn btn-outline-warning">新增房型</a>
				 <a href='<%=request.getContextPath()%>/hotel/hotelMemberCenter.jsp' class="btn btn-outline-primary">返回</a>
	</td></tr>
</table>


	                 <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>房型流水號</th>                                        
                                        <th>飯店編號</th>
                                        <th>房間總數</th>
                                        <th>房型名稱</th>
                                        <th>房型狀態</th>
                                        <th>房型單價</th>
                                        <th>房型照片</th>
                                        <th>修改</th>
<!--                                         <th>刪除</th> -->
                                    </tr>
                                </thead>
                                
	<%@ include file="page1.file" %> 
	<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		
		<tr>
			<td>${roomVO.roomAuto}</td>
			<td>${roomVO.hotelId}</td>
			<td>${roomVO.roomTotal}</td>
			<td>${roomVO.roomName}</td>
			<td><ion-icon
						name=${roomVO.roomStatus==true?"checkbox-outline":"square-outline"}></ion-icon></td>
			<td>${roomVO.roomPrice}</td> 
			<td><img src="${pageContext.request.contextPath}/roompic/RoompicReader?roompicId=${roomVO.roomAuto}" height="50" width="50"></td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/RoomServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="roomAuto"  value="${roomVO.roomAuto}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			     
			     
			     
			</td>
			
		</tr>
	</c:forEach>
	</table>

                </div>
               </div>
        

<%@ include file="page2.file" %>

</body>
<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
 <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
</html>