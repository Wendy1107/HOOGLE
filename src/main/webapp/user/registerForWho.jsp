<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>旅客或飯店註冊選擇</title>

<%@ include file="/header.jsp" %>

</head>



<body>
	<!-- ================ Inner banner ================ -->
<div class="inner-banner inner-banner-bg pt-70 pb-40">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-lg-8 col-md-8 mb-30"> 
        <!-- page-title -->
        <div class="page-title">
          <h1>註冊</h1>
        </div>
        <!-- page-title end --> 
      </div>
      <div class="col-lg-4 col-md-4 mb-30"> 
        <!-- breadcrumb -->
        <ol class="breadcrumb mb-0">
          <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
          <li class="breadcrumb-item active">Register</li>
        </ol>
        <!-- breadcrumb end --> 
      </div>
    </div>
  </div>
</div>
<!-- ================ Register page ================ -->
<div class="blog-area pt-40 pb-50 position-relative">
    <div class="bg-style-1"></div>
    <div class="container"> 
      <!-- section title -->
      <div class="section-title text-center">
        <h1>請選擇註冊模式</h1>
        <span class="dashed-border"></span> </div>
      <!-- section title -->
      <div class="row">
        <div class="col-lg-6 col-md-6 mb-30"> 
          <!-- blog box -->
          <div class="blog-box shadow">
            <div class="blog_img mb-20"><a href="registerForUser.jsp"><img src="/HOOGLE/images/user/registerForUser.png" alt=""></a></div>
            <div class="blog-des">
              <h2 class="mt-10 mb-6 text-center"><a href="registerForUser.jsp" class="text-dark">旅客註冊</a></h2>
            </div>
          </div>
          <!-- blog box end --> 
        </div>
        <div class="col-lg-6 col-md-6 mb-30"> 
          <!-- blog box -->
          <div class="blog-box shadow">
            <div class="blog_img mb-20"><a href="/HOOGLE/hotel/registerForHotel.jsp"><img src="/HOOGLE/images/hotel/registerForHotel.jpg" alt=""></a></div>
            <div class="blog-des">
              <h2 class="mt-10 mb-6 text-center"><a href="/HOOGLE/hotel/registerForHotel.jsp" class="text-dark">飯店註冊</a></h2>
            </div>
          </div>
          <!-- blog box end --> 
        
      </div>
    </div>
  </div>
<!-- ================ Register page end ================ --> 

<%@ include file="/footer.jsp" %>

</body>
</html>