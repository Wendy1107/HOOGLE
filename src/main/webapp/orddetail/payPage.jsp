<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- page title -->
    <title>
      Pay For Order
    </title>
    <%@ include file="/header.jsp" %>
    <!-- favicon -->
    <link rel="icon" href="favicon.ico" />
    <!-- bootstrap core CSS -->
    <link rel="stylesheet" href="/HOOGLE/css/bootstrap.min.css" />
    <!-- font awesome -->
    <link href="/HOOGLE/css/all.min.css" rel="stylesheet" />
    <!-- owl carousel -->
    <link href="/HOOGLE/css/owl.carousel.min.css" rel="stylesheet" />
    <link href="/HOOGLE/css/owl.theme.default.min.css" rel="stylesheet" />
    <!-- venobox css -->
    <link rel="stylesheet" href="/HOOGLE/css/venobox.css" />
    <!-- datepicker css -->
    <link rel="stylesheet" href="/HOOGLE/css/datepicker.min.css" />
    <!-- custom styles for this template -->
    <link href="/HOOGLE/css/custom.css" rel="stylesheet" />
    <link href="/HOOGLE/css/responsive.css" rel="stylesheet" />
    <link href="/HOOGLE/css/helper.css" rel="stylesheet" />
  </head>

  <body>
    <!-- ================ Preloader ================ -->
<!--     <div id="preloader"> -->
<!--       <div class="spinner-grow" role="status"> -->
<!--         <span class="sr-only">Loading...</span> -->
<!--       </div> -->
<!--     </div> -->
    <!-- ================ Preloader end ================ -->

    <!-- ================ Header ================ -->
    <!-- ================ Header end ================ -->

    <!-- ================ Inner banner ================ -->
    <div class="inner-banner inner-banner-bg pt-70 pb-40">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-8 col-md-8 mb-30">
            <!-- page-title -->
            <div class="page-title">
              <h1>Payment Information</h1>
            </div>
            <!-- page-title end -->
          </div>
          <div class="col-lg-4 col-md-4 mb-30">
            <!-- breadcrumb -->
            <ol class="breadcrumb mb-0">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Payment Information</li>
            </ol>
            <!-- breadcrumb end -->
          </div>
        </div>
      </div>
    </div>
    <!-- ================ Inner banner end ================ -->

    <!-- ================ Payment Information page ================ -->
    <div class="detail-page pt-70 pb-40">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-8 mb-30">
              <h4 class="mb-15">信用卡資訊</h4>
              <div class="card-type">
                <div class="row">
                  <div class="col-lg-6 mb-20">
                    <!-- <div
                      class="card-type-img border rounded p-20 text-center position-relative"
                    >
                      <input
                        class="form-check-input"
                        type="radio"
                        name="option2"
                        value="option2"
                      />
                      <img src="images/card-type-img-1.jpg" alt="" />
                    </div> -->
                  </div>
                  <div class="col-lg-6 mb-20">
                    <!-- <div
                      class="card-type-img border rounded p-20 text-center position-relative"
                    >
                      <input
                        class="form-check-input"
                        type="radio"
                        name="option2"
                        value="option2"
                      />
                      <img src="images/card-type-img-2.jpg" alt="" />
                    </div> -->
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-6">
                  <div class="form-group">
                    <label
                      >Card Holder Name<span class="text-danger">*</span></label
                    >
                    <input type="text" class="form-control" />
                  </div>
                </div>
                <div class="col-lg-6">
                  <div class="form-group">
                    <label>Card Number<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-4">
                  <div class="form-group">
                    <label
                      >Expiry Mounth<span class="text-danger">*</span></label
                    >
                    <input type="text" class="form-control" />
                  </div>
                </div>
                <div class="col-lg-4">
                  <div class="form-group">
                    <label>Expiry Year<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" />
                  </div>
                </div>
                <div class="col-lg-4">
                  <div class="form-group">
                    <label>CVC<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" />
                  </div>
                </div>
              </div>
              <div class="form-group form-check">
<!--                 <input -->
<!--                   type="checkbox" -->
<!--                   class="form-check-input" -->
<!--                   id="exampleCheck1" -->
<!--                 /> -->
				<input type="checkbox" id="terms" name="terms">
                <label class="form-check-label font-size-14" for="exampleCheck1"
                  >我已閱讀並同意 <a href="">本網站付款條款</a></label
                >
              </div>            
            <form class="form-style-1" METHOD="post" ACTION="${pageContext.request.contextPath}/OrddetailServlet" >
              <button type="submit" class="btn-style-1" onclick="checkTerms()">確認付款</button>
              		<input type="hidden" name="ordId" value="${newOrdId.ordId}"><font color=red>${errorMsgs.ordId}</font>
					<input type="hidden" name="roomAuto1" value="${4001}"><font color=red>${errorMsgs.roomAuto}</font>
					<input type="hidden" name="roomNumber1" value="${roomNumber1}"><font color=red>${errorMsgs.roomAuto}</font>
					<input type="hidden" name="roomAuto2" value="${4002}"><font color=red>${errorMsgs.roomAuto}</font>
              		<input type="hidden" name="roomNumber2" value="${roomNumber2}"><font color=red>${errorMsgs.roomAuto}</font>
					<input type="hidden" name="roomAuto3" value="${4003}"><font color=red>${errorMsgs.roomAuto}</font>
              		<input type="hidden" name="roomNumber3" value="${roomNumber3}"><font color=red>${errorMsgs.roomAuto}</font>
              		<input type="hidden" name="ordNights" value="${ordNights}"><font color=red>${errorMsgs.roomAuto}</font>
					<input type="hidden" name="nonreserved4001" value="${nonreserved4001}"><font color=red>${errorMsgs.roomAuto}</font>
					<input type="hidden" name="nonreserved4002" value="${nonreserved4002}"><font color=red>${errorMsgs.roomAuto}</font>
					<input type="hidden" name="nonreserved4003" value="${nonreserved4003}"><font color=red>${errorMsgs.roomAuto}</font>
					
              <input type="hidden" name="action" value="insert">
            </form>
            
          </div>
          <div class="col-lg-4 col-md-4">
            <aside>
              <!-- filter widget -->
              <div class="filter-widget mb-20">
                <div
                  class="accordion filter-accordion"
                  id="filter-widget-accordion4-d"
                >
                  <div class="card">
                    <div class="card-header" id="headingOne4-d">
                      <a
                        class="btn btn-link w-100 text-left"
                        href=""
                        data-toggle="collapse"
                        data-target="#collapseOne4-m"
                        aria-expanded="true"
                        aria-controls="collapseOne4-m"
                      >
                        <!-- title widget -->
                        <div class="filter-title-widget">
                          <h3>
                            <!-- Hotel Details -->
                            訂單明細
                            <i class="fas fa-plus-square float-right"></i>
                            <i class="fas fa-minus-square float-right"></i>
                          </h3>
                        </div>
                        <!-- title widget end -->
                      </a>
                    </div>
                    <div
                      id="collapseOne4-m"
                      class="collapse show mt-10"
                      aria-labelledby="headingOne4-d"
                      data-parent="#filter-widget-accordion4-d">
                      <div class="card-body">
                        <ul class="list-inline select-all mb-10">
                          <li class="list-inline-item">
                            <!-- Hilton Miami Downtown -->
                          </li>
                        </ul>
                        <div class="table-responsive">
                          <table class="table table-bordered bg-gray w-100 border-0">
                           <tr>
                            <td>旅客姓名</td>
                            <td>${userVO.userName}</td>
                          </tr>
<!--                           <tr> -->
<!--                             <td>飯店編號</td> -->
<%--                             <td>${ordVO.hotelId}</td> --%>
<!--                           </tr> -->
                          <tr>
                            <td>飯店名稱</td>
                            <td>${ordVO.hotelName}</td>
                          </tr>
                          <tr>
                            <td>訂單編號</td>
                            <td>${ordId}</td>
                          </tr>
                          <tr>
                            <td>訂單日期</td>
                            <td>${ordDate}</td>
                          </tr>
                          <tr>
                            <td>入住日期</td>
                            <td>${checkinInput}</td>
                          </tr>
                          <tr>
                            <td>退房日期</td>
                            <td>${checkoutInput}</td>
                          </tr>
                          <tr>
                            <td>天數</td>
                            <td>共計<sapn>${ordNights}</sapn>晚</td>
                          </tr>
                          <tr>
                            <td>房數</td>
                            <td>
                            單人房每晚<sapn>${roomNumber1}</sapn>間<br>
                            雙人房每晚<sapn>${roomNumber2}</sapn>間<br>
                            四人房每晚<sapn>${roomNumber3}</sapn>間
                            </td>
                          </tr>
                          <tr>
                            <td>金額</td>
                            <td>共計<sapn>${money}</sapn>元</td>
                          </tr>
                          </table>
                        </div>
                        <div class="table-responsive">
                          <table
                            class="table table-bordered bg-gray mb-0 w-100 border-0">
<!--                             <tr> -->
<!--                               <th>房型種類</th> -->
<!--                               <th>雙人房</th> -->
<!--                             </tr> -->
<!--                             <tr> -->
<!--                               <th>訂房數</th> -->
<!--                               <th>1間</th> -->
<!--                             </tr> -->
<!--                             <tr> -->
<!--                               <th>Pay Amount</th> -->
<!--                               <th>金額</th> -->
<!--                               <th>$900</th> -->
<!--                             </tr> -->
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- filter widget end -->
              <!-- help us -->
              <!-- <div class="help-us mb-30">
                <h3>How can we help you?</h3>
                <p>
                  Lorem ipsum dolor sit ametdf consectetur adipiscing elitdgsh
                  ametdf consectetur piscing.
                </p>
                <a class="view-detail-btn" href=""
                  ><i class="fas fa-phone-alt"></i> Contact Us</a
                >
              </div> -->
              <!-- help us end -->
            </aside>
          </div>
        </div>
      </div>
    </div>
    <!-- ================ Payment Information page end ================ -->


    <!-- ================ Top scroll ================ -->
     <%@ include file="/footer.jsp" %> 
    <a href="#" class="scroll-top">Top</a>
    <!-- ================ Top scroll end ================ -->

<!--     js files -->
    <script src="/HOOGLE/js/jquery-3.5.1.min.js"></script>
    <script src="/HOOGLE/js/bootstrap.bundle.min.js"></script>
<!--     counter js -->
    <script src="/HOOGLE/js/jquery-1.10.2.min.js"></script>
    <script src="/HOOGLE/js/waypoints.min.js"></script>
    <script src="/HOOGLE/js/jquery.counterup.min.js"></script>
<!--     venobox js -->
    <script src="/HOOGLE/js/venobox.min.js"></script>
<!--     owl carousel -->
    <script src="/HOOGLE/js/owl.carousel.min.js"></script>
<!--     portfolio js -->
    <script src="/HOOGLE/js/jquery.mixitup.min.js"></script>
<!--     datepicker js -->
    <script src="/HOOGLE/js/datepicker.min.js"></script>
<!--     script js -->
    <script src="/HOOGLE/js/custom.js"></script>
    
    <script>
  function checkTerms() {
    if (document.getElementById('terms').checked) {
      // Do something, submit form, etc.
    } else {
      alert('請閱讀並同意本網站付款條款');
      event.preventDefault();
    }
  }
</script>
  </body>
</html>
