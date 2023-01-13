// 獲取輸入框的引用
let passwordInput = document.getElementById('newUserPassword');
let retypeInput = document.getElementById('comfirmPassword');

console.log(newUserPassword);
console.log(comfirmPassword);

// 添加事件侦听器，在用戶點擊提交按鈕時檢查密碼是否一致
let form = document.getElementById('comfirmChange');
console.log(comfirmChange);
form.addEventListener('submit', function (event) {
    let password = passwordInput.value;
    let retype = retypeInput.value;
	console.log(password);
	console.log(retype);

    // 檢查密碼是否一致
    if (password !== retype) {
        // 顯示錯誤消息
        alert('兩次輸入的密碼不一致，請重新輸入');

        // 阻止表單提交
        event.preventDefault();
    } 
});