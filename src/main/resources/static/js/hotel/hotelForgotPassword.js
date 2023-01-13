$(".ForgotPasswordForHotel").on("click", function (e) {
    e.preventDefault();
    $("div.hotelPasswordChange").fadeIn();
});
$("button.btn_modal_close").on("click", function () {
    $("div.hotelPasswordChange").fadeOut();
});