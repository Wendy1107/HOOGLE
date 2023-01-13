// 單人房
var count1 = 0;

// 定義顯示 + 符號的函數
function showPlus1() {

    // 將計數器加 1
    count1++;

    // 在文本區域中顯示計數器的值
    document.getElementById('counter1').innerHTML = count1;
}

// 定義顯示 - 符號的函數
function showMinus1() {

    // 將計數器減 1
    if (count1 > 0) {
        count1--;
    }


    // 在文本區域中顯示計數器的值
    document.getElementById('counter1').innerHTML = count1;
}



// 雙人房
var count2 = 0;

// 定義顯示 + 符號的函數
function showPlus2() {

    // 將計數器加 1
    count2++;

    // 在文本區域中顯示計數器的值
    document.getElementById('counter2').innerHTML = count2;
}

// 定義顯示 - 符號的函數
function showMinus2() {

    // 將計數器減 1
    if (count2 > 0) {
        count2--;
    }


    // 在文本區域中顯示計數器的值
    document.getElementById('counter2').innerHTML = count2;
}


// 四人房
var count3 = 0;

// 定義顯示 + 符號的函數
function showPlus3() {

    // 將計數器加 1
    count3++;

    // 在文本區域中顯示計數器的值
    document.getElementById('counter3').innerHTML = count3;
}

// 定義顯示 - 符號的函數
function showMinus3() {

    // 將計數器減 1
    if (count3 > 0) {
        count3--;
    }


    // 在文本區域中顯示計數器的值
    document.getElementById('counter3').innerHTML = count3;
}
