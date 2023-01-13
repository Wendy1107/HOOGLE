<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="tw.com.hoogle.ord.model.*"%>

<%
  OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //OrdServlet.java (Concroller) 存入req的ordVO物件 (包括幫忙取出的ordVO, 也包括輸入資料錯誤時的ordVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單修改 - update_ord_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單修改 - update_ord_input.jsp</h3>
		 <h4><a href="select_ord_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="ord.do" name="form1">
<table>
    <tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td>${param.ordId}</td>
	</tr>
	<tr>
		<td>旅客編號:<font color=red><b>*</b></font></td>
<!-- 		<td><input type="TEXT" name="userId" size="45"  -->
<%-- 			 value="${param.userId}" disabled /></td><td>${errorMsgs.userId}</td> --%>
		<td>${param.userId}</td>
	</tr>
	<tr>
		<td>飯店編號:<font color=red><b>*</b></font></td>
<!-- 		<td><input type="TEXT" name="hotelId" size="45"  -->
<%-- 			 value="${param.hotelId}"/></td><td>${errorMsgs.hotelId}</td> --%>
		<td>${param.hotelId}</td>
	</tr>
	<tr>
		<td>旅客名稱:<font color=red><b>*</b></font></td>
<!-- 		<td><input type="TEXT" name="userName" size="45" -->
<%-- 			 value="${param.userName}"/></td><td>${errorMsgs.userName}</td> --%>
		<td>${param.userName}</td>
	</tr>
	<tr>
		<td>飯店名稱:<font color=red><b>*</b></font></td>
<!-- 		<td><input type="TEXT" name="hotelName" size="45" -->
<%-- 			 value="${param.hotelName}"/></td><td>${errorMsgs.hotelName}</td> --%>
		<td>${param.hotelName}</td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><input name="ordDate" id="f_date1" value="${param.ordDate}" type="text"/></td><td>${errorMsgs.ordDate}</td>
	</tr>
	<tr>
		<td>入住日期:</td>
		<td><input name="ordCheckin" id="f_date2" value="${param.ordCheckin}" type="text"/></td><td>${errorMsgs.ordCheckin}</td>
	</tr>
	<tr>
		<td>退房日期:</td>
		<td><input name="ordCheckout" id="f_date3" value="${param.ordCheckout}" type="text"/></td><td>${errorMsgs.ordCheckout}</td>
	</tr>
	<tr>
		<td>訂房天數:</td>
		<td><input type="TEXT" name="ordNights" size="45"
			 value="${param.ordNights}"/></td><td>${errorMsgs.ordNights}</td>
	</tr>
	<tr>
		<td>注意事項:</td>
		<td><input type="TEXT" name="ordRemark" size="45"
			 value="${param.ordRemark}"/></td><td>${errorMsgs.ordRemark}</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ordId" value="${param.ordId}">
<input type="hidden" name="userId" value="${param.userId}">
<input type="hidden" name="hotelId" value="${param.hotelId}">
<input type="hidden" name="userName" value="${param.userName}">
<input type="hidden" name="hotelName" value="${param.hotelName}">

<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/js/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${param.ordDate}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date2').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '${param.ordCheckin}', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            //minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        $('#f_date3').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '${param.ordCheckout}', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            //minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>

</html>