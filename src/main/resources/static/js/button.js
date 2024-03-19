document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll(".activebtn");

    buttons.forEach(function(button) {
        var keyword = encodeURIComponent(button.innerText);

        button.addEventListener("click", function() {
            window.location.href = '/user/rsrt/filter/atmosphere?keyword=' + keyword;
        });

        // 버튼 상태 유지
        var urlParams = new URLSearchParams(window.location.search);
        var currentKeyword = decodeURIComponent(urlParams.get("keyword"));

        if (currentKeyword === button.innerText) {
            button.classList.add("active");
        }
    });
});
// document.addEventListener("DOMContentLoaded", function() {
//   var buttons = document.getElementsByClassName("filterButton");

//   for (var i = 0; i < buttons.length; i++) {
//       var button = buttons[i];
//       var keyword = encodeURIComponent(button.innerText);

//       button.addEventListener("click", function() {
//           window.location.href = '/user/rsrt/filter/atmosphere?keyword=' + keyword;
//       });

//       // 버튼 상태 유지
//       var urlParams = new URLSearchParams(window.location.search);
//       var currentKeyword = decodeURIComponent(urlParams.get("keyword"));

//       if (currentKeyword === button.innerText) {
//           button.classList.add("active");
//       }
//   }
// });

// script.js

// document.addEventListener("DOMContentLoaded", function() {
//   var buttons = [
//       { id: "atmosphereButton", keyword: "분위기" },
//       { id: "anotherButton", keyword: "다른 키워드" }
//       // 다른 버튼들도 추가할 수 있습니다.
//   ];

//   for (var i = 0; i < buttons.length; i++) {
//       var button = document.getElementById(buttons[i].id);
//       var keyword = encodeURIComponent(buttons[i].keyword);

//       button.addEventListener("click", function() {
//           window.location.href = '/user/rsrt/filter/atmosphere?keyword=' + keyword;
//       });

//       // 버튼 상태 유지
//       var urlParams = new URLSearchParams(window.location.search);
//       var currentKeyword = decodeURIComponent(urlParams.get("keyword"));

//       if (currentKeyword === buttons[i].keyword) {
//           button.classList.add("active");
//       }
//   }
// });

// // 버튼 클릭 이벤트 리스너를 설정하는 함수
// function setupButtonClickListener(buttonId, keyword) {
//   document.getElementById(buttonId).addEventListener("click", function() {
//       // 모든 버튼의 클래스를 초기화합니다.
//       var allButtons = document.querySelectorAll('.btn');
//       allButtons.forEach(function(btn) {
//           btn.classList.remove("btn-warning");
//           btn.classList.add("btn-outline-warning");
//       });
      
//       // 현재 클릭된 버튼의 클래스를 업데이트합니다.
//       this.classList.remove("btn-outline-warning");
//       this.classList.add("btn-warning");
      
//       // 페이지를 이동합니다.
//       window.location.href = '/user/rsrt/filter/atmosphere?keyword=' + encodeURIComponent(keyword);
//   });
// }

// function restoreButtonState() {
//   const keyword = getKeywordFromURL();
//   if (keyword) {
//       const allButtons = document.querySelectorAll('.btn');
//       allButtons.forEach(function(btn) {
//           if (btn.getAttribute("data-keyword") === keyword) {
//               btn.classList.remove("btn-outline-warning");
//               btn.classList.add("btn-warning");
//           } else {
//               btn.classList.add("btn-outline-warning");
//               btn.classList.remove("btn-warning");
//           }
//       });
//   }
// }


// // DOM이 완전히 로드된 후에 함수들을 실행합니다.
// document.addEventListener("DOMContentLoaded", function() {
//   setupButtonClickListener("atmosphereButton", "분위기");
//   setupButtonClickListener("tasteButton", "맛있는");
//   // 여기에 더 많은 버튼에 대한 setupButtonClickListener 호출을 추가할 수 있습니다.
//   restoreButtonState();
// });
