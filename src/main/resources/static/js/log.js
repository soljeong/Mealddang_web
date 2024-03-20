// document.addEventListener("DOMContentLoaded", function() {
//     // 페이지 로드 시 실행
//     window.onload = function() {
//         displayDateFromURL();
//     };

//     // 이벤트 리스너 추가
//     document.getElementById("prevWeekBtn").addEventListener("click", function() {
//         moveWeek(-1); // 이전 주로 이동
//     });

//     document.getElementById("nextWeekBtn").addEventListener("click", function() {
//         moveWeek(1); // 다음 주로 이동
//     });
// });

// // 주간 이동 함수
// function moveWeek(weekOffset) {
//     // 현재 날짜 가져오기
//     var currentDate = new Date();

//     // 주간 오프셋 만큼 날짜 이동
//     currentDate.setDate(currentDate.getDate() + (weekOffset * 7));

//     // 이동한 주간 날짜를 URL 파라미터로 추가하여 페이지 이동
//     var year = currentDate.getFullYear();
//     var month = String(currentDate.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더해줍니다.
//     var day = String(currentDate.getDate()).padStart(2, "0");
//     var today2 =  year + "-" + month + "-" + day;
//     document.getElementById("selectedDate2").value = today2;

//     var url = "/user/diet/log?date=" + today2;
//     window.location.href = url;
// }

// function getMondayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -6 : 1); // 월요일로 이동하기 위한 날짜 차이 계산
//     const monday = new Date(date.setDate(diff)); // 월요일 날짜 객체 생성
//     return monday;
// }

// // URL에서 쿼리 매개변수를 추출하는 함수
// function getQueryParam(name) {
//     const urlParams = new URLSearchParams(window.location.search);
//     return urlParams.get(name);
// }






// function getMondayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -7 : 1); // 화요일로 이동하기 위한 날짜 차이 계산
//     const monday = new Date(date.setDate(diff)); // 화요일 날짜 객체 생성
//     return monday;
// }
// function getTuesdayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -6 : 2); // 화요일로 이동하기 위한 날짜 차이 계산
//     const tuesday = new Date(date.setDate(diff)); // 화요일 날짜 객체 생성
//     return tuesday;
// }
// function getWednesdayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -5 : 3); // 수요일로 이동하기 위한 날짜 차이 계산
//     const wednesday = new Date(date.setDate(diff)); // 수요일 날짜 객체 생성
//     return wednesday;
// }
// function getThursdayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -4 : 4); // 목요일로 이동하기 위한 날짜 차이 계산
//     const thursday = new Date(date.setDate(diff)); // 목요일 날짜 객체 생성
//     return thursday;
// }
// function getFridayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -3 : 5); // 금요일로 이동하기 위한 날짜 차이 계산
//     const friday = new Date(date.setDate(diff)); // 금요일 날짜 객체 생성
//     return friday;
// }
// function getSaturdayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -2 : 6); // 토요일로 이동하기 위한 날짜 차이 계산
//     const saturday = new Date(date.setDate(diff)); // 토요일 날짜 객체 생성
//     return saturday;
// }
// function getSundayFromDate(date) {
//     const dayOfWeek = date.getDay(); // 현재 날짜의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)
//     const diff = date.getDate() - dayOfWeek + (dayOfWeek === 0 ? -1 : 7); // 일요일로 이동하기 위한 날짜 차이 계산
//     const sunday = new Date(date.setDate(diff)); // 일요일 날짜 객체 생성
//     return sunday;
// }
// function getLocaldate(date){
//     var year = date.getFullYear();
//     var month = String(date.getMonth() + 1).padStart(2, "0");
//     var day = String(date.getDate()).padStart(2, "0");
//     var monday =  year + "-" + month + "-" + day;   
//     return monday;
// }


// // URL에서 date 정보를 가져와서 화면에 표시하는 함수
// function displayDateFromURL() {
//     var ssdate;

//     // URL에서 date 정보 추출
//     const dateParam = getQueryParam('date');
//     if (dateParam) {
//         // 화면에 표시
//         document.getElementById('ssdate').innerText = dateParam;
//         ssdate = new Date(dateParam);
//     } else {
//         var currentDate = new Date();
//         console.log("currentDate:", currentDate);
//         // 이동한 주간 날짜를 URL 파라미터로 추가하여 페이지 이동
//         var tmpday = getLocaldate(currentDate);
//         document.getElementById('ssdate').innerText = tmpday;
//         ssdate = new Date(tmpday);
//     }


//     console.log("ssdate:", ssdate); // ssdate를 콘솔에 출력


//     // ssdate 값이 있을 때 mondate 값을 계산하여 설정
//     if (ssdate) {
//         const monday = getMondayFromDate(ssdate);
//         const tuesday = getTuesdayFromDate(ssdate);
//         const wednesday = getWednesdayFromDate(ssdate);
//         const thursday = getThursdayFromDate(ssdate);
//         const friday = getFridayFromDate(ssdate);
//         const saturday = getSaturdayFromDate(ssdate);
//         const sunday = getSundayFromDate(ssdate);

//         document.getElementById('mondayDate').innerText = monday;
//         document.getElementById('tuesdayDate').innerText = tuesday;
//         document.getElementById('wednesdayDate').innerText = wednesday;
//         document.getElementById('thursdayDate').innerText = thursday;
//         document.getElementById('fridayDate').innerText = friday;   
//         document.getElementById('saturdayDate').innerText = saturday;
//         document.getElementById('sundayDate').innerText = sunday;
//     }
// }