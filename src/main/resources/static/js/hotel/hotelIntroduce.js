
window.addEventListener("load", function () {
    var the_file_element = document.getElementById("the_file");
    the_file_element.addEventListener("change", function (e) {

        // 寫在這
        var picture_list = document.getElementsByClassName("picture_list")[0];
        picture_list.innerHTML = ""; // 清空

        // 跑每個使用者選的檔案，留意 i 的部份
        for (let i = 0; i < this.files.length; i++) {
            let reader = new FileReader(); // 用來讀取檔案
//            reader.readAsDataURL(this.files[i]); // 讀取檔案
            reader.addEventListener("load", function () {
                console.log(reader.result);
                let li_html = `
                                                           <li><img src="${reader.result}" class="preview"></li> `;
                picture_list.insertAdjacentHTML("beforeend", li_html); // 加進節點
            });
        }


    });
});


$(document).ready(function () {

    //第一層選單
    $.ajax({
        url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, value) {
                console.log(key, value)
                $('#city').append('<option value="' + data[key].CityName + '">' + data[key].CityName + '</option>')
            })
        },
        error: function (data) {
            alert("fail");
        }
    });

    //第二層選單
    $("#city").change(function () {
        cityvalue = $("#city").val();  //取值
        $("#area").empty(); //清空上次的值
        $("#area").css("display", "inline"); //顯現
        $.ajax({
            url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
            type: "get",
            dataType: "json",
            success: function (data) {
	
				const targetCity = data.find((city) => city.CityName === cityvalue);
                eachval = targetCity.AreaList; //鄉鎮

                $.each(eachval, function (key, value) {
                    $('#area').append('<option value="' + key + '">' + eachval[key].AreaName + '</option>')
                });
            },
            error: function () {
                alert("fail");
            }

        });
    });
});
//選完後跳出選擇值
$("#area").change(function () {
    cityvalue = $("#city").val();  //縣市
    areavalue = $("#area").val();  //鄉鎮
    $.ajax({
        url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
        type: "get",
        dataType: "json",
        success: function (data) {
			const targetCity = data.find((city) => city.CityName === cityvalue);
			const address = targetCity.CityName + targetCity.AreaList[areavalue].AreaName
            $("#inputAddress2").val(address)
        },
        error: function () {
            alert("fail");
        }

    });

})
