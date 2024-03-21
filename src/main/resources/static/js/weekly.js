import * as echarts from 'https://cdn.jsdelivr.net/npm/echarts/dist/echarts.esm.js';

fetch('/api-weekly')
.then(response => response.json()).then(data => {
    const rawData = data.weekNutList;
    console.log(rawData);
    // const rawData = [
    //     [200, 100, 100],
    //     [302, 132, 182],
    //     [301, 101, 191],
    //     [334, 134, 234],
    //     [390, 90, 290],
    //     [330, 230, 330],
    //     [320, 210, 310]
    // ];

    const rawData2 = data.weekTotal;
    console.log(rawData2);


    // 주간 영양섭취표
    // const kcalRow = document.getElementById('kcal');
    // const kcalValueTd = kcalRow.getElementsByTagName('td')[1];
    // kcalValueTd.textContent = rawData2[0];

    // const carbohydratesRow = document.getElementById('carbohydrates');
    // const carbohydratesValueTd = carbohydratesRow.getElementsByTagName('td')[1];
    // carbohydratesValueTd.textContent = rawData2[1];
    
    // const proteinRow = document.getElementById('protein');
    // const proteinValueTd = proteinRow.getElementsByTagName('td')[1];
    // proteinValueTd.textContent = rawData2[2];

    // const fatRow = document.getElementById('fat');
    // const fatValueTd = fatRow.getElementsByTagName('td')[1];
    // fatValueTd.textContent = rawData2[3];


    // 막대그래프
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;
    
    const reorganizedData = [];
    const totalData = [];

    for (let i = 0; i < rawData.length; ++i) {
        let sum = 0;
        const dailyData = []; // 각 요일별 합계를 저장할 배열
        for (let j = 0; j < rawData[0].length; ++j) {
            sum += rawData[i][j];
            if (!reorganizedData[j]) {
                reorganizedData[j] = [];
            }
            reorganizedData[j].push(rawData[i][j]);
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
            data: reorganizedData[sid].map((d, did) =>
            totalData[did] <= 0 ? 0 : d / totalData[did])
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

        // 도넛 그래프
        var chartDom2 = document.getElementById('main2');
        var myChart2 = echarts.init(chartDom2);
        var option2;

        option2 = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '5%',
                left: 'center'
            },
            
            title: {
                text: '3대 영양소 섭취 비율 비교', // 제목 텍스트
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
                    name: '권장 비율',
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
                        { value: 40, name: '탄수화물' },
                        { value: 25, name: '단백질' },
                        { value: 35, name: '지방' }
                    ],

                },
                {
                    name: '실섭취 비율',
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
                        { value: rawData2[1], name: '탄수화물' },
                        { value: rawData2[2], name: '단백질' },
                        { value: rawData2[3], name: '지방' }
                    ]
                }
        ],
        //각 그래프 별 제목넣어주기
        graphic: [
            {
                type: 'text',
                left: '22%', // 첫 번째 그래프의 중심 위치에 맞추어 조정하세요
                top: '20%', // 조정이 필요할 수 있습니다.
                style: {
                    text: '<권장 비율>',
                    textAlign: 'center',
                    fill: '#333',
                    fontSize: 18
                }
            },
            {
                type: 'text',
                left: '71%', // 두 번째 그래프의 중심 위치에 맞추어 조정하세요
                top: '20%', // 조정이 필요할 수 있습니다.
                style: {
                    text: '<실섭취 비율>',
                    textAlign: 'center',
                    fill: '#333',
                    fontSize: 18
                }
            }
        ]
        
        };

    option2 && myChart2.setOption(option2);
    
})