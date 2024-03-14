import * as echarts from 'https://cdn.jsdelivr.net/npm/echarts/dist/echarts.esm.js';

var chartDom = document.getElementById('main');
var myChart = echarts.init(chartDom);
var option;
const rawData = [
    [monCarbo, 302, 301, 334, 390, 330, 320],
    [monProtein, 132, 101, 134, 90, 230, 210],
    [monFat, 182, 191, 234, 290, 330, 310]
    ];
const totalData = [];
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
            ]
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