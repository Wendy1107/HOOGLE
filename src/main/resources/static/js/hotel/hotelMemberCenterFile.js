let getInImg = document.getElementById("the_file");

     getInImg.addEventListener("change", function (e) {
            let the_ul = document.getElementsByClassName("picture_list")[0]; //[ul] 加上[0]直接取得ul
            the_ul.innerHTML = "";

            //練習 2：透過 FileReader 將圖片於網頁上預覽(多選)
            for(let i = 0 ; i< this.files.length ; i++){
//               console.log("this=",this)
                let reader = new FileReader(); // 用來讀取檔案的物件
                reader.readAsDataURL(this.files[i]); // 讀取檔案
                reader.addEventListener("load", function () {
                    let li_el = `
                        <li>
                            <img src="${this.result}" class="preview">
                        </li>
                    `;
                    //抓取想要放入的HTML標籤 並將內容放入標籤內
                    let ul_el = document.getElementsByClassName("picture_list")[0]; //[ul] 加上[0]直接取得ul
                    ul_el.insertAdjacentHTML("beforeend", li_el); //位置,想放的內容
                });
            }
        });