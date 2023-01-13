<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<style>
img.preview {
	width: 200px;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul>li {
	display: inline-block;
	vertical-align: top;
}
</style>
	
</head>
<body>


	<div class="container">
		<div class="row">
			<!-- 改善建議開始 -->

			<div class="col-md-9">

				<div class="card border-secondary w-20">
					<div class="card-header  bg-info sm-3">
						<h3 class="text-white">房間管理新增</h3>
						
					</div>



					<form class="row g-3"
						action="<%=request.getContextPath()%>/room/RoomServlet"
						method=post enctype="multipart/form-data">




						<br>
						<div class="form-group col-lg-12"style="margin-top: 10px">
							<tr>
								<td>飯店編號:</td>
								<td><input type="TEXT" name="hotelId"
									value="${param.hotelId}" /></td>
								<td>${errorMsgs.hotelId}</td>
							</tr>
						</div>



						<div class="form-group col-lg-9">
							<td>房間名稱:</td>
							<td><input type="text" name="roomName" 
								value="${param.roomName}" /></td>
							<td>${errorMsgs.roomName}</td>
						</div>

						</tr>

						<div class="col-md-9">
							<tr>
								<td>房間總數:</td>
								<td><input type="TEXT" name="roomTotal"
									value="${param.roomTotal}" /></td>
								<td>${errorMsgs.roomTotal}</td>
							</tr>
						</div>




						<div class="col-md-12">
							<tr>

								<td>房型狀態:</td>
								<td><input type="checkbox" name="roomStatus" value="true"
									checked="" id="roomStatus"></td>
							</tr>
						</div>




						<div class="col-md-6">
							<tr>
								<td>房型單價(NTD):</td>
								<td><input type="TEXT" name="roomPrice"
									value="${param.roomPrice}" /></td>
								<td>${errorMsgs.roomPrice}</td>

							</tr>
						</div>


						<div class="form-group col-md-12">
							<tr>
								<td>房型照片:</td>
								<td><input type="file" name="roompicPic"
									value="${param.roompicPic}" /></td>
								<td>${errorMsgs.roompicPic}</td>
						</div>


						<div class="d-grid gap-2 col-3  mx-auto">

							<button type="submit" class="btn btn-primary" name="action"
								value="insert">新增</button>
							<button type="reset" class="btn btn-secondary">重置</button>

						</div>



						
					</FORM>
					
					
					
					
					<script>
                window.addEventListener("load", function () {
                    var the_file_element = document.getElementById("the_file");
                    the_file_element.addEventListener("change", function (e) {

                        // 寫在這
                        var picture_list = document.getElementsByClassName("picture_list")[0];
                        picture_list.innerHTML = ""; // 清空

                        // 跑每個使用者選的檔案，留意 i 的部份
                        for (let i = 0; i < this.files.length; i++) {
                            let reader = new FileReader(); // 用來讀取檔案
                            reader.readAsDataURL(this.files[i]); // 讀取檔案
                            reader.addEventListener("load", function () {
                                console.log(reader.result);
                                let li_html = `
                                                           <li><img src="${reader.result}" class="preview"></li> `;
                                picture_list.insertAdjacentHTML("beforeend", li_html); // 加進節點
                            });
                        }


                    });
                });
            </script>
					
					
					
					
					
					
</body>

</html>
