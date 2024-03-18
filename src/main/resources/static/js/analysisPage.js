document.getElementById("analyzeButton").addEventListener("click", function() {
    document.querySelector(".mask").style.display = "flex"; // 로딩 화면 보이게 설정
    var selectedDate = document.getElementById("selectedDate").textContent;
    // 분석 시작 함수 호출
    analyzeData(selectedDate);
});

function fetchYoloResult(selectedDate) {
    return fetch('/api-yolo?selectedDate=' + encodeURIComponent(selectedDate))
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // 서버로부터 받은 데이터가 JSON 형태라고 가정
    })
    .then(data => {
        console.log(data); // 서버로부터 받은 데이터 처리
        // 여기에서 받은 데이터를 바탕으로 웹 페이지에 결과를 표시할 수 있음

        // 로딩 화면 숨기기
        document.getElementById('loadingScreen').style.display = 'none';

        // 결과 페이지로 이동하거나 결과 섹션 표시
        // 결과 페이지로 리다이렉트하는 경우:
        window.location.href = 'http://localhost:8080/user/diet/analysis_result_ok';

        // 또는 결과 섹션을 동적으로 업데이트하는 경우:
        // document.getElementById('resultSection').style.display = 'block';
        // document.getElementById('resultSection').innerHTML = '<p>분석 결과입니다!</p>';
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
        // 오류 발생 시 로딩 화면 숨기기
        document.getElementById('loadingScreen').style.display = 'none';
        // 오류 메시지를 사용자에게 보여줄 수 있음
    });
}

function analyzeData(selectedDate) {
    // 로딩 화면 표시
    document.getElementById('loadingScreen').style.display = 'block';

    // 데이터 분석 비동기 작업 호출
    fetchYoloResult(selectedDate);
}
