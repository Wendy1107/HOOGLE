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
   <tr><td><h3>HOOGLE OrdDetail: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for HOOGLE OrdDetail: Home</p>

<h3>訂單明細查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllOrdDetail.jsp'>List</a> all OrdDetails.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="orddetail.do" >
        <b>輸入訂單明細編號 (6001~6007):</b>
        <input type="text" name="orddetailId" value="${param.orddetailId}"><font color=red>${errorMsgs.orddetailId}</font>
<!--         <input type="text" name="orddetailId"> -->
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="orddetailSvc" scope="page" class="tw.com.hoogle.orddetail.model.OrdDetailService" />
   
  <li>
     <FORM METHOD="post" ACTION="orddetail.do" >
       <b>選擇訂單明細編號:</b>
       <select size="1" name="orddetailId">
         <c:forEach var="orddetailVO" items="${orddetailSvc.all}" > 
          <option value="${orddetailVO.orddetailId}">${orddetailVO.orddetailId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="orddetail.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ordId">
         <c:forEach var="orddetailVO" items="${orddetailSvc.all}" > 
          <option value="${orddetailVO.ordId}">${orddetailVO.ordId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="OrdId_getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>訂單明細新增</h3>

<ul>
  <li><a href='addOrdDetail.jsp'>Add</a> a new OrdDetail.</li>
</ul>
</body>
</html>