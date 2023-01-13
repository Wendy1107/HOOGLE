 var do_deletion = function () { //執行刪除的function
     $.ajax({
         url: "/HOOGLE/user/UserServlet", //資料請求網址
         type: "GET",
         data: "action=logout",
         dataType: "json",
         success:
             function (data){
				if([data.check] == 1){
					console.log("1");
					$('#header ul').append('<li class="" style="cursor:pointer" id="Signin"><a class="header-request">' + [data.email] + '</a></li>')
                	$('#header ul').append('<li class="header-singin" style="cursor:pointer" id="Signout"><a style="background-color:white;"><i class="fa-solid fa-right-from-bracket"></i> 登出</a></li>')	
				} else if([data.check] == 2){
					console.log("2");
					location.href='/HOOGLE/index.jsp';
				}
	
		},
		error: function(xhr){
			alert("伺服器忙碌中");
		},
     });
 }

$('body').on('click', '#Signout', function() {
	var yes = confirm('確定登出嗎？');
	if (yes) {
		console.log('yes');
		do_deletion();
	} else {
//	    alert(':D');
	}
}); 

$('body').on('click', '#Signin', function () {
    console.log('123')
    $.ajax({
        url: "/HOOGLE/user/UserServlet", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        data: "action=checkLogin",
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success:
            function (data) {
                if ([data.check] == 1) {
                    console.log("1");
                    location.href = [data.url];
                } else if ([data.check] == 2) {
                    console.log("2");
					  $('#header').append('<li class="header-singin"><a href="/HOOGLE/user/registerForWho.jsp"><i class="fas fa-pen"></i> 註冊</a></li>');
                      $('#header').append('<li class="header-singin"><a href="/HOOGLE/user/loginForWho.jsp"><i class="fas fa-user"></i> 登入</a></li>');
                }
            },
        error: function (xhr) {
            alert("伺服器忙碌中");
        },
    });
}); 

$(document).ready(function () {
    $.ajax({
        url: "/HOOGLE/user/UserServlet", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        data: "action=checkLogin",
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success:
            function (data) {
                if ([data.check] == 1) {
                    $('#header ul').append('<li class="" style="cursor:pointer" id="Signin"><a class="header-request">' + [data.email] + '</a></li>')
                	$('#header ul').append('<li class="header-singin" style="cursor:pointer" id="Signout"><a style="background-color:white;"><i class="fa-solid fa-right-from-bracket"></i> 登出</a></li>')
                } else if ([data.check] == 2) {
					  $('#header ul').append('<li class="header-singin"><a href="/HOOGLE/user/registerForWho.jsp"><i class="fas fa-pen"></i> 註冊</a></li>');
                      $('#header ul').append('<li class="header-singin"><a href="/HOOGLE/user/loginForWho.jsp"><i class="fas fa-user"></i> 登入</a></li>');
                }
            },
        error: function (xhr) {
            alert("伺服器忙碌中");
        },
    });
});