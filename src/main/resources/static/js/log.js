function displayDateFromURL() {
    // URL에서 startDate 쿼리 매개변수를 가져옵니다.
    var startDateFromURL = getQueryParam("startDate");

    // startDate가 없으면 함수를 종료합니다.
    if (!startDateFromURL) {
        return;
    }

    // URL에서 가져온 날짜와 현재 날짜를 비교합니다.
    var startDate = new Date(startDateFromURL);
    var currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0); // 시간을 0으로 설정하여 오직 날짜만 비교합니다.

    // startDate와 현재 날짜의 차이를 계산합니다.
    var timeDiff = startDate.getTime() - currentDate.getTime();
    var dayDiff = timeDiff / (1000 * 3600 * 24);

    // 현재 주의 시작(월요일)과 끝(일요일)을 계산합니다.
    var currentWeekStart = new Date(currentDate);
    currentWeekStart.setDate(currentDate.getDate() - currentDate.getDay() + (currentDate.getDay() === 0 ? -6 : 1)); // 일요일인 경우 -6을 더합니다.
    var currentWeekEnd = new Date(currentWeekStart);
    currentWeekEnd.setDate(currentWeekStart.getDate() + 6);

    // startDate가 이번 주에 포함되는지 확인하고, 섹션의 표시 여부를 결정합니다.
    var section = document.getElementById("todaySection");
    if (startDate >= currentWeekStart && startDate <= currentWeekEnd) {
        // 이번 주인 경우
        section.style.display = "block";
    } else {
        // 이번 주가 아닌 경우
        section.style.display = "none";
    }
}



document.addEventListener("DOMContentLoaded", function() {
    // 페이지 로드 시 실행
    window.onload = function() {
        displayDateFromURL();
    };

    // 이벤트 리스너 추가
    document.getElementById("prevWeekBtn").addEventListener("click", function() {
        moveWeek(-1); // 이전 주로 이동
    });

    document.getElementById("nextWeekBtn").addEventListener("click", function() {
        moveWeek(1); // 다음 주로 이동
    });
});

// 주간 이동 함수
function moveWeek(weekOffset) {
    // 현재 날짜 가져오기
    var currentDate = new Date();

    // 주간 오프셋 만큼 날짜 이동
    currentDate.setDate(currentDate.getDate() + (weekOffset * 7));

    // 이동한 주간 날짜를 URL 파라미터로 추가하여 페이지 이동
    var year = currentDate.getFullYear();
    var month = String(currentDate.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더해줍니다.
    var day = String(currentDate.getDate()).padStart(2, "0");
    var today2 =  year + "-" + month + "-" + day;
    document.getElementById("monday").value = today2;

    var url = "/user/diet/log?startDate=" + today2; 
    window.location.href = url;

    // 주간 이동에 따라 "저번주 내용" 또는 "다음주 내용"으로 변경
    var weekDescription = "";
    if (weekOffset === -1) {
        weekDescription = "저번주 내용";
    } else if (weekOffset === 1) {
        weekDescription = "다음주 내용";
    } else {
        weekDescription = "이번주 내용";
    }
}

// URL에서 쿼리 매개변수를 추출하는 함수
function getQueryParam(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}