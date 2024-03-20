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
            alert('죄송하지만, 이 이미지는 분석하기 어렵네요... \n- 욜삼 -');
            // const data = response.json();
            // console.log(data);
            return ;
            }
        return response.json();
    })
    .then(data => {
        console.log(data);
        console.log(data.mdNutResults[0].originPath.originPath);

        // 서버로부터 받은 데이터를 js 글로벌 변수에 담기
        window.yoloResult = data;

        // 로딩 화면 숨기기
        document.getElementById('loadingScreen').style.display = 'none';

        // 결과 페이지로 리다이렉트하는 경우:
        window.location.href = 'http://localhost:8080/user/diet/airesult?originPath=' + data.mdNutResults[0].originPath.originPath;

        // 또는 결과 섹션을 동적으로 업데이트하는 경우:
        // document.getElementById('resultSection').style.display = 'block';
        // document.getElementById('resultSection').innerHTML = '<p>분석 결과입니다!</p>';
    })
    .catch(error => {
        console.error('[analysisPage.js][func fetchYoloResult]', error);
        document.getElementById('loadingScreen').style.display = 'none';    // 오류 발생 시 로딩 화면 숨기기
    });
}

function analyzeData(selectedDate) {
    // 로딩 화면 표시
    document.getElementById('loadingScreen').style.display = 'block';
    // 데이터 분석 비동기 작업 호출
    fetchYoloResult(selectedDate);
}

// 이미지 분석 실패시 업로드 이미지도 삭제한다.
// function failController(originPath) {
//     return fetch('/api-delete', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify({ path: originPath }) // 객체 형태로 변환
//     })
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('Alternative network response was not ok');
//         }
//         return response.json();
//     })
//     .catch(error => console.error('[analysisPage.js][func failController]', error));
// }

