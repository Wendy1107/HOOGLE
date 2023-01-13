<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.user.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="tw.com.hoogle.ord.model.*" %>

<%
	if (session.getAttribute("userVO") == null && session.getAttribute("hotelVO") == null ) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HOOGLE/index.jsp");
		dispatcher.forward(request, response);
		return;
	}else if(session.getAttribute("copVO") != null){
		RequestDispatcher dispatcher = request.getRequestDispatcher("/hotel/hotelMemberCenter.jsp");
		dispatcher.forward(request, response);
		return;
	}
	UserVO userVO = (UserVO) request.getSession().getAttribute("userVO"); //UserServlet.java(Controller)
	
	OrdVO ordVO = (OrdVO) request.getSession().getAttribute("ordVO");
	
	OrdService ordSvc = new OrdService();
    List<OrdVO> list = ordSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>userMemberCenter</title>
<!-- favicon -->
  <link rel="icon" href="favicon.ico" />
  <!-- bootstrap core CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <!-- font awesome -->
  <link href="<%=request.getContextPath()%>/css/all.min.css" rel="stylesheet">
  <!-- owl carousel -->
  <link href="<%=request.getContextPath()%>/css/owl.carousel.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/owl.theme.default.min.css" rel="stylesheet">
  <!-- venobox css -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/venobox.css">
  <!-- datepicker css -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.min.css">
  <!-- custom styles for this template -->
  <link href="<%=request.getContextPath()%>/css/custom.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/responsive.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/helper.css" rel="stylesheet">
</head>
<body>
<!-- ================ Preloader ================ -->
  <!-- load畫面 -->
<!--   <div id="preloader"> -->
<!--     <div class="spinner-grow" role="status"> <span class="sr-only">Loading...</span> </div> -->
<!--   </div> -->
  <!-- ================ Preloader end ================ -->

  <!-- ================ Header ================ -->
  <header class="header">
    <!-- header upper -->
    <div class="header-upper-bar">
      <div class="container">
        <div class="row d-flex align-items-center">
          <div class="col-lg-8 col-md-6 col-sm-4 col-2">
            <!-- header navigation -->
            <nav class="navbar header-navigation navbar-expand-lg p-0">
              <!-- mobile Toggle 手機切換 -->
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTheme"
                aria-controls="navbarTheme" aria-expanded="false" aria-label="Toggle navigation"> <span></span>
                <span></span> <span></span> </button>
              <!-- mobile toggle end -->
              <!-- top Menu -->
              <div class="collapse navbar-collapse" id="navbarTheme">
                <ul class="navbar-nav align-items-start align-items-lg-center">
                  <li class="active"><a class="nav-link" href="/HOOGLE/index.jsp">首頁</a></li>
                  <li><a class="nav-link" href="/HOOGLE/index.jsp">比價</a></li>
                  <li><a class="nav-link" href="/HOOGLE/index.jsp">美食</a></li>
                  <!-- <li><a class="nav-link" href="gallery.html">畫廊</a></li> -->
                  <!-- <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Destinations</a>
                  <div class="dropdown-menu" aria-labelledby="dropdown02"> <a class="dropdown-item" href="destinations.html">Destinations</a> <a class="dropdown-item" href="destination-detail.html">Destination Detail</a> </div>
                </li> -->
                  <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#" id="dropdown04"
                      data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">關於我們</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                      <a class="dropdown-item" href="about.html">關於我們</a>
                      <a class="dropdown-item" href="blog-single.html">常見問題</a>
                      <a class="dropdown-item" href="blog-single.html">聯絡我們</a>
                      <a class="dropdown-item" href="blog-single.html">服務條款</a>
                    </div>
                  </li>
                  <!-- <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pages</a>
                  <div class="dropdown-menu" aria-labelledby="dropdown05"> <a class="dropdown-item" href="listing.html">Hotel Listing</a> <a class="dropdown-item" href="hotel-detail.html">Hotel Detail</a> <a class="dropdown-item" href="signin.html">Login</a> <a class="dropdown-item" href="register.html">Register</a> <a class="dropdown-item" href="team.html">Team</a> <a class="dropdown-item" href="testimonial.html">Testimonial</a> <a class="dropdown-item" href="traveler-information.html">Traveler Information</a> <a class="dropdown-item" href="payment-information.html">Payment Information</a> <a class="dropdown-item" href="faq.html">Faq</a> </div>
                </li> -->
                  <!-- <li><a class="nav-link" href="contact-us.html">Contact Us</a></li> -->
                </ul>
              </div>
              <!-- top menu end -->
            </nav>
            <!-- header navigation end -->
          </div>
          <div class="col-lg-4 col-md-6 col-sm-8 col-10 text-right">
            <!-- header right link -->
            <div class="header-right-link" id="header">
              <ul>
<%--                 <li><a href="" class="header-request"><i class=""></i> <%=(userVO == null) ? "" : userVO.getUserEmail()%></a></li>  <%=userVO.getUserName()%> --%>
<!--                 <li><a href=""><i class="fa-solid fas fa-user-gear"></i> 會員中心</a></li> -->
<!--                 <li><a href="signin.html"><i class="fa-solid fa-right-from-bracket"></i> 登出</a></li> -->

                <!-- <li><a href="contact-us.html" class="header-request">Request a Quote</a></li> -->
              </ul>
            </div>
            <!-- header right link end -->
          </div>
        </div>
      </div>
    </div>
    <!-- header upper end -->
 <!-- ================ Inner banner ================ -->
  <div class="inner-banner inner-banner-bg pt-70 pb-40">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-8 col-md-8 mb-30">
          <!-- page-title -->
          <div class="page-title">
            <h1>會員中心</h1>
          </div>
          <!-- page-title end -->
        </div>
        <div class="col-lg-4 col-md-4 mb-30">
          <!-- breadcrumb -->
          <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item"><a href="/HOOGLE/index.jsp">Home</a></li>
            <li class="breadcrumb-item active">Register</li>
          </ol>
          <!-- breadcrumb end -->
        </div>
      </div>
    </div>
  </div>
  <!-- ================ Inner banner end================ -->    
    
<!-- ================ memberCenter page ================ -->
  <div class="memberCenter-page pt-70 pb-40">
    <div class="container">
      <div class="row">
        <div class="col-lg-3 col-md-4">
          <aside>
            <!-- membercenter side link -->
            <div class="faqsidelink mb-20">
              <ul class="nav flex-column nav-tabs nav-pills" id="myTab" role="tablist">
                <li class="nav-item"> <a class="nav-link active" id="tab5-tab" data-toggle="tab" href="#tab5" role="tab"
                    aria-controls="tab5" aria-selected="true"><i class="fa-regular fa-circle-user"></i> 旅客基本資料</a> </li>
                <li class="nav-item"> <a class="nav-link" id="order-paid-nav" data-toggle="tab" href="#order-paid" role="tab"
                    aria-controls="tab6" aria-selected="false"><i class="fa-solid fa-clipboard"></i> 住宿訂單管理</a> </li>
                <li class="nav-item" id="Signout" style="cursor:pointer"><a class="nav-link"><i class="fa-solid fa-right-from-bracket"></i> 登出</a> </li>                  
              </ul>
            </div>
			<!--   錯誤處理訊息開始      -->
			
            	<c:if test="${not empty errorMsgs}">
					<div>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if> 
            
            <!--   錯誤處理訊息結束      -->
          </aside>
        </div>
        
		
        
        
        
        <div class="tab-content col-lg-9 col-md-8">
          <div class="tab-pane fade show active p-15" id="tab5" role="tabpanel" aria-labelledby="tab5-tab">

            <h2 class="mb-20">旅客基本資料</h2>
            <!-- tabs -->
            <div class="detail-tabs mb-30">
              <ul class="nav nav-tabs nav-pills nav-justified" id="myTab" role="tablist">
                <li class="nav-item"> <a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab1" role="tab"
                    aria-controls="tab1" aria-selected="true">資料修改</a> </li>
                <li class="nav-item"> <a class="nav-link" id="tab2-tab" data-toggle="tab" href="#tab2" role="tab"
                    aria-controls="tab2" aria-selected="false">密碼修改</a> </li>
              </ul>
			
<!-- 			<form action="UserServlet" method="POST" id="comfirmChange"> -->
<!-- 			<input type="hidden" name="action" value="update"> -->
              <div class="tab-content" id="myTabContent">
              	
                <div class="tab-pane fade show active p-15" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
                  <!-- 資料修改內文 -->
                  <form action="UserServlet" method="POST">
                  <div class="form-group">
                    <h6>信箱</h6><br>
                    <input type="email" class="form-control" disabled placeholder="註冊時email" name="userEmail"
                    value="<%=(userVO == null) ? "" : userVO.getUserEmail()%>">
                  </div>
                  <div class="form-group">
                    <h6>姓名</h6><br>
                    <input type="text" class="form-control" placeholder="註冊時姓名" name="userName"
                    value="<%=(userVO == null) ? "" : userVO.getUserName()%>">
                    <span class="error" style="color: red">${errors.userName}</span>
                  </div>
                  <div class="form-group">
                    <h6>生日</h6><br>
                    <input type="date" class="form-control" placeholder="註冊時生日" name="userBirthday"
                    value="<%=(userVO == null) ? "" : userVO.getUserBirthday()%>">
                    <span class="error" style="color: red">${errors.userBirthday}</span>
                  </div>
                  <div class="form-group">
                    <h6>電話</h6><br>
                    <input type="text" class="form-control" placeholder="註冊時電話" name="userPhone"
                    value="<%=(userVO == null) ? "" : userVO.getUserPhone()%>">
                    <span class="error" style="color: red">${errors.userPhone}</span>
                  </div>
                  <div class="form-group">
                    <h6>身分證</h6><br>
                    <input type="text" class="form-control" placeholder="註冊時身分證" name="userIdentity"
                    value="<%=(userVO == null) ? "" : userVO.getUserIdentity()%>">
                    <span class="error" style="color: red">${errors.userIdentity}</span>
                  </div>
                  <br>
                  <button type="submit" class="btn book-now-btn w-50">確認修改</button>
                  <input type="hidden" name="action" value="update">
                   </form>
                </div>
               
				
				
                <div class="tab-pane fade p-15" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
                  <!-- 密碼修改內文 -->
                  <form action="UserServlet" method="POST" id="comfirmChange">

                  <div class="form-group">
                    <h6>舊密碼</h6><br>
                    <input type="text" class="form-control" placeholder="舊密碼" name="oldUserPassword">
                    <span class="error" style="color: red">${errors.oldUserPassword}</span>
                  </div>
                  <div class="form-group">
                    <h6>新密碼</h6><br>
                    <input type="password" class="form-control" placeholder="請輸入新密碼" name="newUserPassword" id="newUserPassword"
                    >
<%--                     value="<%=(userVO == null) ? "" : userVO.getUserPassword()%>" --%>
                  </div>
                  <div class="form-group">
                    <h6>密碼確認</h6><br>
                    <input type="password" class="form-control" placeholder="再次確認新密碼" name="comfirmPassword" id="comfirmPassword"
                    >
<%--                     value="<%=(userVO == null) ? "" : ""%>" --%>
                  </div>
                  <br>
                  <button type="submit" class="btn book-now-btn w-50" >確認修改</button>
				   <input type="hidden" name="action" value="updateUserPassword">
				</form>
                </div>
                
              </div>
<!--               </form>           -->
            </div>
          </div>

          <div class="tab-pane fade p-15 section-title" id="order-paid" role="tabpanel" aria-labelledby="order-paid-nav">

            <h2>住宿訂單管理</h2>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="dashed-border"></span>
            <br><br>
            <h3>訂單查尋</h3>
            
<!-- -----------------------------Yang修改區-----------------------------  -->
            <div class="nav-pills mb-30">
              <br>
              <div class="table-responsive">
                <table class="table table-bordered">
                  <thead class="thead-dark">
                    <tr class="text-center">
                      <th>訂單編號</th>
                      <th>飯店名稱</th>
                      <th>訂單日期</th>
                      <th>入住天數</th>
                      <th>入住日期</th>
                      <th>退房日期</th>
<!--                       <th>查看明細</th> -->
                    </tr>
                  </thead>
                  
            <c:forEach var="ordVO" items="${list}">
            <c:if test="${ userVO.userId == ordVO.userId }">
                  <tbody>
                    <tr class="text-center">
                      <td>${ordVO.ordId}</td>
                      <td>${ordVO.hotelName}</td>
                      <td>${ordVO.ordDate}</td>
                      <td>${ordVO.ordNights}</td>
                      <td>${ordVO.ordCheckin}</td>
                      <td>${ordVO.ordCheckin}</td>
<!--                       <td> -->
<%--                     	<form METHOD="post" action="${pageContext.request.contextPath}/OrdServlet"> --%>
<!-- 						<input type="hidden" name="action" value="getOrddetail_For_Display"> -->
<%-- 						<input type="hidden" name="ordId" value="${ordVO.ordId}"> --%>
<!-- 						<input name="action" type="submit" id="button" value="查看明細"> -->
<!-- 						</form> -->
<!--                     	</td> -->
                    </tr>
                  </tbody>
                  </c:if>
                  </c:forEach>
                </table>
              </div>
            </div>
            <h3>訂單明細查尋</h3>
            <li>
     		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/OrddetailServlet" >
       		<b>選擇訂單編號:</b>
       		<select size="1" name="ordId">
         		<c:forEach var="ordVO" items="${list}"> 
         		      <c:if test="${ userVO.userId == ordVO.userId }">
         		 <option value="${ordVO.ordId}">${ordVO.ordId}
        		      </c:if>
        		 </c:forEach>   
      		 </select>
     		  <input type="hidden" name="action" value="OrdId_getOne_For_Display">
     		 <input type="submit" value="送出">
   		 	</FORM>
  			</li>
<!-- -----------------------------Yang修改區-----------------------------  -->
            
          </div>


        </div>

      </div>
    </div>
  </div>
  </div>
  <!-- ================ MemberCenter page end ================ -->
  
  <!-- js files -->
  <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  <!-- counter js -->
  <script src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/waypoints.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.counterup.min.js"></script>
  <!-- venobox js -->
  <script src="<%=request.getContextPath()%>/js/venobox.min.js"></script>
  <!-- owl carousel -->
  <script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
  <!-- portfolio js -->
  <script src="<%=request.getContextPath()%>/js/jquery.mixitup.min.js"></script>
  <!-- datepicker js -->
  <script src="<%=request.getContextPath()%>/js/datepicker.min.js"></script>
  <!-- script js -->
  <script src="<%=request.getContextPath()%>/js/custom.js"></script>
  <script src="https://kit.fontawesome.com/4ce3b6b697.js" crossorigin="anonymous"></script>
  <script src="<%=request.getContextPath()%>/js/member/memberHeader.js"></script>
  <script src="<%=request.getContextPath()%>/js/member/memberChangePassword.js"></script>
  
</body>
</html>