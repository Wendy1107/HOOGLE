<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOOGLE_Administratorr</title>
<style>
  table#table-1 {
	width: 450px;
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
   <tr><td><h3>HOOGLE Ord: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for HOOGLE Ord: Home</p>

<h3>訂單查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllOrd.jsp'>List</a> all Ords.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="ord.do" >
        <b>輸入訂單編號 (5001~5005):</b>
        <input type="text" name="ordId" value="${param.ordId}"><font color=red>${errorMsgs.ordId}</font>
<!--         <input type="text" name="ordId"> -->
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="ordSvc" scope="page" class="tw.com.hoogle.ord.model.OrdService" />
   
  <li>
     <FORM METHOD="post" ACTION="ord.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ordId">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ordId}">${ordVO.ordId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="ord.do" >
       <b>選擇訂房日期:</b>
       <select size="1" name="ordId">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ordId}">${ordVO.ordDate}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="ord.do" >
       <b>選擇入住日期:</b>
       <select size="1" name="ordId">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ordId}">${ordVO.ordCheckin}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
   <li>
     <FORM METHOD="post" ACTION="ord.do" >
       <b>選擇退房日期:</b>
       <select size="1" name="ordId">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ordId}">${ordVO.ordCheckout}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>訂單新增:</h3>

<ul>
  <li><a href='addOrd.jsp'>Add</a> a new Ord.</li>
</ul>
</body>
</html>