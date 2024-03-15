import * as echarts from 'https://cdn.jsdelivr.net/npm/echarts/dist/echarts.esm.js';

// //그래프 예시 데이터()
// var chartDom = document.getElementById('main'); // 차트를 표시할 DOM 요소를 선택합니다.
// var myChart = echarts.init(chartDom); // 해당 DOM 요소에 ECharts 인스턴스를 초기화합니다.
// var option; // 차트의 구성 옵션을 저장
// const rawData = [
//     [200, 302, 301, 334, 390, 330, 320],  //-->탄수화물 일주일 자리
//     [100, 132, 101, 134, 90, 230, 210], //-->단백질 일주일 자리
//     [300, 182, 191, 234, 290, 330, 310] //-->지방 일주일 자리
//     ];
// const totalData = [];
//     for (let i = 0; i < rawData[0].length; ++i) {
//     let sum = 0;
//     for (let j = 0; j < rawData.length; ++j) {
//     sum += rawData[j][i];
//     }
//     totalData.push(sum);
//     }
// const grid = {
//     left: 100,
//     right: 100,
//     top: 50,
//     bottom: 50
//     };
// const series = [
//     '탄수화물',
//     '단백질',
//     '지방',
//     ].map((name, sid) => {
//     return {
//     name,
//     type: 'bar',
//     stack: 'total',
//     barWidth: '60%',
//     label: {
//         show: true,
//         formatter: (params) => Math.round(params.value * 1000) / 10 + '%'
//     },
// data: rawData[sid].map((d, did) =>
//         totalData[did] <= 0 ? 0 : d / totalData[did]
//     )
//     };
//     });
// option = {
//     legend: {
//     selectedMode: false
//     },
//     grid,
//     yAxis: {
//     type: 'value'
//     },
//     xAxis: {
//     type: 'category',
//     data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
//     },
//     series
//     };

//     option && myChart.setOption(option);

//<시도 1 코드>
// var chartDom = document.getElementById('main'); // 차트를 표시할 DOM 요소를 선택합니다.
// var myChart = echarts.init(chartDom); // 해당 DOM 요소에 ECharts 인스턴스를 초기화합니다.
// var option; // 차트의 구성 옵션을 저장할 변수를 선언합니다.

// // 서버로부터 '/diet/weeklyPage' 엔드포인트로 비동기 요청을 보냅니다.
// fetch('/diet/weeklyPage')
//     .then(response => response.json()) // 응답을 JSON 형태로 파싱합니다.
//     .then(data => {
//         // 서버로부터 받은 데이터(data)를 차트 데이터로 변환하는 로직입니다.
//         // 여기서는 data가 이미 우리가 사용할 수 있는 형태(예: weekNutList)라고 가정합니다.
//         const rawData = transformData(data); // 서버로부터 받은 데이터를 변환 함수를 통해 차트에 적합한 형태로 변환합니다.

//         // 이후 차트 구성 코드...
//         // 가정한 바와 같이, rawData 혹은 변환 과정에서 생성된 다른 데이터 구조를 사용해 차트를 구성합니다.
//         // 예시로, rawData에서 계산된 총합 데이터를 차트에 추가할 수 있습니다.
//         // 이 부분에서 totalData 변수는 rawData나 변환된 데이터를 기반으로 계산된 값을 포함할 수 있습니다.
//         // 예를 들어, 주간 영양소 총합을 계산하는 로직이 필요할 수 있습니다.
        
//         // 차트 옵션 구성하기 (이 부분은 차트 유형과 필요에 따라 다를 수 있습니다)
//         option = {
//             title: {
//                 text: '주간 다이어트 차트'
//             },
//             tooltip: {},
//             legend: {
//                 data: ['영양소']
//             },
//             xAxis: {
//                 data: ["월", "화", "수", "목", "금", "토", "일"]
//             },
//             yAxis: {},
//             series: [{
//                 name: '영양소',
//                 type: 'bar', // 예시로 바 차트를 선택했습니다. 차트 유형에 따라 변경 가능합니다.
//                 data: rawData // 변환된 데이터를 사용합니다.
//             }]
//         };

//         // 구성한 옵션을 바탕으로 차트를 그립니다.
//         myChart.setOption(option);
//     }).catch(error => {
//         console.error('차트 데이터 로딩 중 오류 발생:', error);
//     });


//<시도 2 코드>
var chartDom = document.getElementById('main');
var myChart = echarts.init(chartDom);
var option;
    fetch('/diet/weeklyPage')
    .then(response => response.json())
    .then(data => {
    // 데이터베이스에서 조회한 데이터를 차트 데이터로 변환하는 로직
    const rawData = transformData(weekNutList); 
    // 이후 차트 구성 코드...
    const totalData = [weekNutList];
    for (let i = 0; i < rawData[0].length; ++i) {
        let sum = 0;
        for (let j = 0; j < rawData.length; ++j) {
        sum += rawData[j][i];
        }
        totalData.push(sum);
    }
    const grid = {
        left: 100,
        right: 100,
        top: 50,
        bottom: 50
        };
    const series = [
        '탄수화물',
        '단백질',
        '지방',
        ].map((name, sid) => {
        return {
        name,
        type: 'bar',
        stack: 'total',
        barWidth: '60%',
        label: {
            show: true,
            formatter: (params) => Math.round(params.value * 1000) / 10 + '%'
        },
    data: rawData[sid].map((d, did) =>
            totalData[did] <= 0 ? 0 : d / totalData[did]
        )
        };
        });
    option = {
        legend: {
        selectedMode: false
        },
        grid,
        yAxis: {
        type: 'value'
        },
        xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        series
        };

        option && myChart.setOption(option);
    })


// 도넛 그래프
var chartDom = document.getElementById('main2');
var myChart = echarts.init(chartDom);
var option;

option = {
    tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        left: 'center'
    },

    //권장그래프
    title: {
        text: '권장섭취량', // 제목 텍스트
        left: 'center', // 제목의 위치를 중앙으로 설정
        top: 'top', // 제목을 차트 위쪽에 위치시킴
        textStyle: { // 제목의 스타일 설정
            color: '#333', // 텍스트 색상
            fontSize: 20, // 폰트 크기
            fontWeight: 'normal' // 폰트 두께
        }
    },
    series: [
        {
            name: 'Access From',
            type: 'pie',
            radius: ['15%', '40%'],
            center: ['25%', '50%'],
            avoidLabelOverlap: false,
            padAngle: 5,
            itemStyle: {
                borderRadius: 10
            },
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                show: true,
                fontSize: 15,
                fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [
                { value: 38.8, name: '탄수화물' },
                { value: 24.3, name: '단백질' },
                { value: 34.0, name: '지방' },
                { value: 2.3, name: '식이섬유' },
                { value: 0.6, name: '당' }
            ],

        },
        //실제 섭취 그래프
        {
            name: 'Access From',
            type: 'pie',
            radius: ['15%', '40%'],
            center: ['75%', '50%'],
            avoidLabelOverlap: false,
            padAngle: 5,
            itemStyle: {
                borderRadius: 10
            },
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                show: true,
                fontSize: 15,
                fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [
                { value: 38.8, name: '탄수화물' },
                { value: 24.3, name: '단백질' },
                { value: 34.0, name: '지방' },
                { value: 2.3, name: '식이섬유' },
                { value: 0.6, name: '당' }
            ]
        }
    ]
};

option && myChart.setOption(option);