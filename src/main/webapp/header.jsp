<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- page title -->
<title>Hoogle</title>

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
<!-- Fontawesome -->
<script src="https://kit.fontawesome.com/0cf2b86d93.js" crossorigin="anonymous"></script>
<!-- ajax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<body>
<!-- ================ Preloader ================ -->
<div id="preloader">
  <div class="spinner-grow" role="status"> <span class="sr-only">Loading...</span> </div>
</div>
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
            <!-- mobile Toggle -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTheme" aria-controls="navbarTheme" aria-expanded="false" aria-label="Toggle navigation"> <span></span> <span></span> <span></span> </button>
            <!-- mobile toggle end --> 
            <!-- top Menu -->
            <div class="collapse navbar-collapse" id="navbarTheme">
              <ul class="navbar-nav align-items-start align-items-lg-center">
                <li class="active"><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/logo_small_removebg.png" style="width: 50px;" alt="" class="img-fluid"></a></li> 
                <li><a class="nav-link" href="<%=request.getContextPath() %>/compare/compare.jsp">比較</a></li>
                <li><a class="nav-link" href="gallery.html">美食</a></li>
                <!--原pages-->
                <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">關於我們</a>
                  <div class="dropdown-menu" aria-labelledby="dropdown05"> <a class="dropdown-item" href="faq.html">關於我們</a> <a class="dropdown-item" href="QA.html">常見問題QA</a> <a class="dropdown-item" href="Terms.html">服務條款</a> <a class="dropdown-item" href="customer-service.html">客服支援</a> </div>                
                </li>
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

            </ul>
          </div>
          <!-- header right link end --> 
        </div>
      </div>
    </div>
  </div>
  <!-- header upper end --> 
</header>
<!-- ================ Header end ================ --> 