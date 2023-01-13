<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Food: Home</title>

<style>
  table#table-1 {
	width:900px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Food</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Food: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <li><a href='listAllFood.jsp'>List</a> all Foods.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="FoodServlet" >
        <b>輸入美食照片編號: (12001):</b>
        <input type="text" name="foodPicid" value="${param.foodPicid}"><font color=red>${errorMsgs.foodPicid}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="foodSvc" scope="page" class="tw.com.hoogle.food.model.FoodService" />
   
  <li>
     <FORM METHOD="post" ACTION="FoodServlet" >
       <b>選擇美食照片編號:</b>
       <select size="1" name="foodPicid">
         <c:forEach var="foodVO" items="${foodSvc.all}" > 
          <option value="${foodVO.foodPicid}">${foodVO.foodPicid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="FoodServlet" >
       <b>美食照片名稱:</b>
       <select size="1" name="foodPicid">
         <c:forEach var="foodVO" items="${foodSvc.all}" > 
          <option value="${foodVO.foodPicid}">${foodVO.foodName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>美食管理</h3>

<ul>
  <li><a href='addFood.jsp'>Add</a> a new Food.</li>
</ul>

</body>
</html>