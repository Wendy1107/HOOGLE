<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.hoogle.hotel.model.*"%>
<%@ page import="java.util.*"%>    
<%
HotelVO hotelVO = (HotelVO) request.getSession().getAttribute("hotelVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>飯店內頁</title>

<%@ include file="/header.jsp" %>

  <!-- hotel-detail css by aga-->
  <link href="<%=request.getContextPath()%>/css/hotel-detail/hotel-detail.css" rel="stylesheet">
</head>
<body>
<!-- ================ 飯店內文 page ================ -->
  <div class="detail-page pt-70 pb-40">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 col-md-8">
          <div class="title">
            <h1>HOOGLE飯店</h1>
            <address><i class="fas fa-map-marker-alt"></i> 台北市中正區濟南路一段321號</address>
          </div>
          <!-- 飯店照片輪播圖 -->
          <div class="owl-carousel detail-page-gallery-carousel mb-20">
            <figure class="item mb-0"> <img src="<%=request.getContextPath()%>/images/hotel-detail/img-01.jpg" alt="img description"> </figure>
            <figure class="item mb-0"><img src="<%=request.getContextPath()%>/images/hotel-detail/img-02.jpg" alt="img description"> </figure>
            <figure class="item mb-0"><img src="<%=request.getContextPath()%>/images/hotel-detail/img-03.jpg" alt="img description"> </figure>
          </div>
          <!-- 飯店照片輪播圖 end -->
        </div>

        <div class="col-lg-8 col-md-8">
          <!-- 切換內頁 -->
          <div class="detail-tabs mb-30">
            <ul class="nav nav-tabs nav-pills nav-justified" id="myTab" role="tablist">
              <li class="nav-item"> <a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab1" role="tab"
                  aria-controls="tab1" aria-selected="true">飯店簡介</a> </li>
              <li class="nav-item"> <a class="nav-link" id="tab2-tab" data-toggle="tab" href="#tab2" role="tab"
                  aria-controls="tab2" aria-selected="false">設施服務</a> </li>
              <li class="nav-item"> <a class="nav-link" id="tab3-tab" data-toggle="tab" href="#tab3" role="tab"
                  aria-controls="tab3" aria-selected="false">房型介紹</a> </li>
              <li class="nav-item"> <a class="nav-link" id="tab4-tab" data-toggle="tab" href="#tab4" role="tab"
                  aria-controls="tab4" aria-selected="false">住宿評鑑</a> </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active p-15" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
                <!-- 飯店簡介 開始 -->

                <div class="hotel-results-list">
                  <div class="list-box mb-30">
                    <div class="list-box-content">
                      <div class="list-box-rating">
                        <h2 class="mb-6">HOOGLE飯店</h2>
                        <span class="at-stars"> <i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
                            class="fas fa-star"></i> <i class="fas fa-star"></i> <i class="far fa-star"></i> </span>
                      </div>

                      <div class="list-box-title">
                        <br>
                        <address class="green-line">
                          <i class="fas fa-map-marker-alt"></i> 台北市中正區濟南路一段321號
                        </address>
                      </div>
                      <span>
                        ${hotelVO.hotelIntroduction}
                      </span>
                    </div>
                    <div class="list-box-content">
                      <h2 class="mb-20">常見問答</h2>
                      <!-- Accordion -->
                      <div class="accordion mb-30 faq-box" id="accordionExample">
                        <div class="card">
                          <div class="card-header">
                            <h5 class="mb-0">
                              <button class="btn btn-link py-3 rounded-0 text-left w-100 text-decoration-none"
                                type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false"
                                aria-controls="collapseOne"> <span class="number">01.</span> HOOGLE飯店入住和退房時間是幾點？ <i
                                  class="far fa-eye-slash float-right"></i> <i
                                  class="far fa-eye float-right"></i></button>
                            </h5>
                          </div>
                          <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                            <div class="card-body">
                              <p>辦理入住時間從15:00開始，
                                退房時間至12:00止。視客房供應狀況，您可以在訂房時提出「提前入住」或「延後退房」的需求。若住客在非指定時間辦理入住或退房，可能需支付額外費用。
                                飯店提供入住前及退房後的行李寄放服務。 前台提供全天候服務。</p>
                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed py-3 rounded-0 text-left w-100 text-decoration-none"
                                type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false"
                                aria-controls="collapseTwo"> <span class="number">02.</span> 我該如何前往HOOGLE飯店？ <i
                                  class="far fa-eye-slash float-right"></i> <i
                                  class="far fa-eye float-right"></i></button>
                            </h5>
                          </div>
                          <div id="collapseTwo" class="collapse" data-parent="#accordionExample">
                            <div class="card-body">
                              <p>若您想擁有自己的交通工具，隨心所欲暢遊台灣，飯店可以協助安排計程車服務。</p>
                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed py-3 rounded-0 text-left w-100 text-decoration-none"
                                type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false"
                                aria-controls="collapseThree"> <span class="number">03.</span> HOOGLE飯店有提供什麼餐飲選擇？ <i
                                  class="far fa-eye-slash float-right"></i> <i
                                  class="far fa-eye float-right"></i></button>
                            </h5>
                          </div>
                          <div id="collapseThree" class="collapse" data-parent="#accordionExample">
                            <div class="card-body">
                              <p>HOOGLE飯店內設有餐廳，方便您就近用餐。</p>
                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed py-3 rounded-0 text-left w-100 text-decoration-none"
                                type="button" data-toggle="collapse" data-target="#collapse4" aria-expanded="false"
                                aria-controls="collapseThree"> <span class="number">04.</span> HOOGLE飯店的吸菸相關規定為何？
                                <i class="far fa-eye-slash float-right"></i> <i
                                  class="far fa-eye float-right"></i></button>
                            </h5>
                          </div>
                          <div id="collapse4" class="collapse" data-parent="#accordionExample">
                            <div class="card-body">
                              <p>HOOGLE飯店的客房及室內區域全面禁菸。</p>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- Accordion end -->
                    </div>
                  </div>
                </div>
                <!-- 飯店簡介 結束 -->
              </div>
              <div class="tab-pane fade p-15" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
                <!-- 設施服務 開始 -->
                <h2 class="mb-6">設施服務</h2>
                <div class="row">
                  <div class="col-lg-4">
                    <ul class="ameneties-list">
                      <li><i class="fas fa-wifi pr-6"></i> 公共區域Wi-Fi</li>
                      <li><i class="fas fa-bed pr-6"></i> 提供單人床</li>
                      <li><i class="fas fa-shower pr-6"></i> 淋浴設備</li>
                    </ul>
                  </div>
                  <div class="col-lg-4">
                    <ul class="ameneties-list">
                      <li><i class="fas fa-paw pr-6"></i> 可攜帶寵物</li>
                      <li><i class="fas fa-wheelchair pr-6"></i> 無障礙友善設施</li>
                      <li><i class="fas fa-dumbbell pr-6"></i> 健身房</li>
                    </ul>
                  </div>
                  <div class="col-lg-4">
                    <ul class="ameneties-list">
                      <li><i class="fas fa-hot-tub pr-6"></i> 提供熱水</li>
                      <li><i class="fas fa-swimmer pr-6"></i> 游泳池</li>
                      <li><i class="fas fa-camera-retro pr-6"></i> 24小時保全</li>
                    </ul>
                  </div>
                </div>
                <br>
                <!-- about text -->
                <div class="">
                  <h2 class="mb-6">住宿規定</h2>
                  <form>
                    <p>兒童與加床收費標準</p>
                    <p>【提醒您】加床規定依房型而異，請查看各房型的人數限制瞭解詳細規定。</p>
                    <p>歡迎兒童入住。</p>

                  </form>
                </div>
                <!-- about text end -->
                <!-- 設施服務 結束 -->
              </div>
              <div class="tab-pane fade p-15" id="tab3" role="tabpanel" aria-labelledby="tab3-tab">

                <!-- rooms -->
                <h2 class="mb-6">房型介紹</h2>
                <div class="room-type-wrapper">
                  <!-- list box -->
                  <div class="list-box mb-30">
                    <div class="list-box-img"> <a href="<%=request.getContextPath()%>/images/rooms/img-big-01.jpg" class="venobox" data-gall="gallery1">
                        <img src="<%=request.getContextPath()%>/images/rooms/img-thum-01.jpg" alt=""> </a> <a href="<%=request.getContextPath()%>/images/rooms/img-big-02.jpg"
                        class="venobox" data-gall="gallery1"></a> <a href="<%=request.getContextPath()%>/images/rooms/img-big-03.jpg" class="venobox"
                        data-gall="gallery1"></a>
                      <a href="<%=request.getContextPath()%>/images/rooms/img-big-04.jpg" class="venobox" data-gall="gallery1"></a>
                    </div>
                    <div class="list-box-content">
                      <div class="list-box-title">
                        <h3>標準單人房 <span>$2000 <em>/ 每晚</em></span></h3>
                        <address>
                          Max : 2 Persons
                        </address>
                      </div>
                      <ul class="hotel-featured">
                        <li><span><i class="fas fa-car"></i> 代客叫車服務</span></li>
                        <li><span><i class="fas fa-bath"></i> 浴缸</span></li>
                        <li><span><i class="fas fa-home"></i> 每日客房清潔服務</span></li>
                        <li><span><i class="fas fa-swimming-pool"></i> 游泳池</span></li>
                      </ul>
                      <div class="btn-wrapper mt-20 d-inline-block w-100">
                        <!-- 在 HTML 文件中創建兩個按鈕，分別顯示 + 和 - 符號 -->
                        <button class="plus-button" onclick="showPlus1()">+</button>&nbsp;
                        <!-- 創建一個文本區域，用於顯示計數器的值 -->
                        <span id="counter1">0</span>
                        &nbsp;
                        <button class="minus-button" onclick="showMinus1()">-</button>
                        &nbsp;
                        <button type="button" class="check-order ml-6" onclick="addToCart()">選擇</button>
                      </div>
                    </div>
                  </div>
                  <!-- list box end -->
                  <!-- list box -->
                  <div class="list-box mb-30">
                    <div class="list-box-img"> <a href="<%=request.getContextPath()%>/images/rooms/img-big-01.jpg" class="venobox" data-gall="gallery2">
                        <img src="<%=request.getContextPath()%>/images/rooms/img-thum-01.jpg" alt=""></a> <a href="<%=request.getContextPath()%>/images/rooms/img-big-02.jpg"
                        class="venobox" data-gall="gallery2"></a> <a href="<%=request.getContextPath()%>/images/rooms/img-big-03.jpg" class="venobox"
                        data-gall="gallery2"></a>
                      <a href="<%=request.getContextPath()%>/images/rooms/img-big-04.jpg" class="venobox" data-gall="gallery2"></a>
                    </div>
                    <div class="list-box-content">
                      <div class="list-box-title">
                        <h3>精緻雙人房 <span>$3000 <em>/ night</em></span></h3>
                        <address>
                          Max : 3 Persons
                        </address>
                      </div>
                      <ul class="hotel-featured">
                        <li><span><i class="fas fa-car"></i> 代客叫車服務</span></li>
                        <li><span><i class="fas fa-bath"></i> 浴缸</span></li>
                        <li><span><i class="fas fa-home"></i> 每日客房清潔服務</span></li>
                        <li><span><i class="fas fa-swimming-pool"></i> 游泳池</span></li>
                      </ul>
                      <div class="btn-wrapper mt-20 d-inline-block w-100">
                        <!-- 在 HTML 文件中創建兩個按鈕，分別顯示 + 和 - 符號 -->
                        <button class="plus-button" onclick="showPlus2()">+</button>&nbsp;
                        <!-- 創建一個文本區域，用於顯示計數器的值 -->
                        <span id="counter2">0</span>
                        &nbsp;
                        <button class="minus-button" onclick="showMinus2()">-</button>
                        &nbsp;
                        <button type="button" class="check-order ml-6" onclick="addToCart()">選擇</button>
                      </div>
                    </div>
                  </div>
                  <!-- list box end -->
                  <!-- list box -->
                  <div class="list-box mb-30">
                    <div class="list-box-img"> <a href="<%=request.getContextPath()%>/images/rooms/img-big-01.jpg" class="venobox" data-gall="gallery3">
                        <img src="<%=request.getContextPath()%>/images/rooms/img-thum-01.jpg" alt=""> </a><a href="<%=request.getContextPath()%>/images/rooms/img-big-02.jpg"
                        class="venobox" data-gall="gallery3"></a> <a href="<%=request.getContextPath()%>/images/rooms/img-big-03.jpg" class="venobox"
                        data-gall="gallery3"></a>
                      <a href="<%=request.getContextPath()%>/images/rooms/img-big-04.jpg" class="venobox" data-gall="gallery3"></a>
                    </div>
                    <div class="list-box-content">
                      <div class="list-box-title">
                        <h3>豪華四人房 <span>$5000 <em>/ night</em></span></h3>
                        <address>
                          Max : 4 Persons
                        </address>
                      </div>
                      <ul class="hotel-featured">
                        <li><span><i class="fas fa-car"></i> 代客叫車服務</span></li>
                        <li><span><i class="fas fa-bath"></i> 浴缸</span></li>
                        <li><span><i class="fas fa-home"></i> 每日客房清潔服務</span></li>
                        <li><span><i class="fas fa-swimming-pool"></i> 游泳池</span></li>
                      </ul>
                      <div class="btn-wrapper mt-20 d-inline-block w-100">
                        <!-- 在 HTML 文件中創建兩個按鈕，分別顯示 + 和 - 符號 -->
                        <button class="plus-button" onclick="showPlus3()">+</button>&nbsp;
                        <!-- 創建一個文本區域，用於顯示計數器的值 -->
                        <span id="counter3">0</span>
                        &nbsp;
                        <button class="minus-button" onclick="showMinus3()">-</button>
                        &nbsp;
                        <button type="button" class="check-order ml-6" onclick="addToCart()">選擇</button>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- rooms -->


              </div>
              <div class="tab-pane fade p-15" id="tab4" role="tabpanel" aria-labelledby="tab4-tab">
                <!-- reviews -->
                <h2 class="mb-6">住宿評鑑</h2>
                <hr>
                <div class="reviews-wrapper">
                  <!-- review item -->
                  <div class="media review-item">
                    <div class="media-body">
                      <h5 class="mt-0">評價人姓名 <span>評價日期在這</span></h5>
                      <span>星星顯示在這</span>
                      <br><br>
                      <p class="mb-0">
                        評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這
                      </p>
                    </div>
                  </div>
                  <!-- review item end -->
                  <!-- review item -->
                  <div class="media review-item">
                    <div class="media-body">
                      <h5 class="mt-0">評價人姓名 <span>評價日期在這</span></h5>
                      <span>星星顯示在這</span>
                      <br><br>
                      <p class="mb-0">
                        評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這
                      </p>
                    </div>
                  </div>
                  <!-- review item end -->
                  <!-- review item -->
                  <div class="media review-item">
                    <div class="media-body">
                      <h5 class="mt-0">評價人姓名 <span>評價日期在這</span></h5>
                      <span>星星顯示在這</span>
                      <br><br>
                      <p class="mb-0">
                        評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這評價內容寫在這
                      </p>
                    </div>
                  </div>
                  <!-- review item end -->
                </div>
                <!-- reviews end -->
              </div>
            </div>
          </div>
          <!-- tabs end -->
        </div>
        <div class="col-lg-4 col-md-4">
          <aside>
            <!-- filter widget -->
            <div class="filter-widget mb-20">
              <div class="accordion filter-accordion" id="filter-widget-accordion4-d">
                <div class="card">
                  <div class="card-header" id="headingOne4-d"> <a class="btn btn-link w-100 text-left" href=""
                      data-toggle="collapse" data-target="#collapseOne4-m" aria-expanded="true"
                      aria-controls="collapseOne4-m">
                      <!-- title widget -->
                      <div class="filter-title-widget">
                        <h3>飯店明細 <i class="fas fa-plus-square float-right"></i> <i
                            class="fas fa-minus-square float-right"></i></h3>
                      </div>
                      <!-- title widget end -->
                    </a> </div>
                  <div id="collapseOne4-m" class="collapse show mt-10" aria-labelledby="headingOne4-d"
                    data-parent="#filter-widget-accordion4-d">
                    <div class="card-body">
                      <ul class="list-inline select-all mb-10">
                        <li class="list-inline-item">HOOGLE飯店</li>
                      </ul>
                      <div class="table-responsive">
                        <table class="table table-bordered bg-gray w-100 border-0">
                          <tr>
                            <td>入住日期</td>
                            <td>2022年12月16日 - 週五</td>
                          </tr>
                          <tr>
                            <td>退房日期</td>
                            <td>2022年12月18日 - 週日</td>
                          </tr>
                          <tr>
                            <td>天數</td>
                            <td>共計<sapn>2</sapn>晚</td>
                          </tr>
                        </table>
                      </div>
                      <!-- <a class="btn-style-1" href="">Book Selected Rooms</a> -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- filter widget end -->


            <!-- 計算總額開始 -->

            <div class="nav-pills mb-30">
              <br>
              <div class="table-responsive">
                <table class="table table-bordered">
                  <thead class="thead-dark">
                    <tr class="text-center">
                      <th>房型</th>
                      <th>價格</th>
                      <th>數量</th>
                      <th>天數</th>
                      <th>小計</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="text-center">
                      <td>單人房</td>
                      <td>2000</td>
                      <td>抓到選擇數量</td>
                      <td>抓到選擇天數</td>
                      <td>價格*數量*天數</td>
                    </tr>
                    <tr class="text-center">
                      <td>雙人房</td>
                      <td>3000</td>
                      <td>抓到選擇數量</td>
                      <td>抓到選擇天數</td>
                      <td>價格*數量*天數</td>
                    </tr>
                    <tr class="text-center">
                      <td>四人房</td>
                      <td>5000</td>
                      <td>抓到選擇數量</td>
                      <td>抓到選擇天數</td>
                      <td>價格*數量*天數</td>
                    </tr>
                    <tr class="text-center">
                      <td colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;總金額：X+X+X <button>確認付款</button></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>     
        </div>
        </aside>
      </div>
    </div>
  </div>
  </div>
  <!-- ================ Detail page end ================ -->
  
 <%@ include file="/footer.jsp" %> 
 
 <!-- hotel-detail js by aga-->
  <script src="<%=request.getContextPath()%>/js/hotel-detail/hotel-detail.js"></script>
  
</body>
</html>