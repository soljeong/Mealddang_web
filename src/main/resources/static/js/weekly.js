import * as echarts from 'https://cdn.jsdelivr.net/npm/echarts/dist/echarts.esm.js';

var chartDom = document.getElementById('main');
var myChart = echarts.init(chartDom);
var option;
const rawData = [
    [100, 302, 301, 334, 390, 330, 320],
    [320, 132, 101, 134, 90, 230, 210],
    [220, 182, 191, 234, 290, 330, 310],

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
    data: rawData[sid].map((d, did) => totalData[did] <= 0 ? 0 : d / totalData[did])
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
                { value: 1048, name: 'Search Engine' },
                { value: 735, name: 'Direct' },
                { value: 580, name: 'Email' },
                { value: 484, name: 'Union Ads' },
                { value: 300, name: 'Video Ads' }
            ]
        },
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
                { value: 1048, name: 'Search Engine' },
                { value: 735, name: 'Direct' },
                { value: 580, name: 'Email' },
                { value: 484, name: 'Union Ads' },
                { value: 300, name: 'Video Ads' }
            ]
        }
    ]
};

option && myChart.setOption(option);

//원그래프
// var chartDom = document.getElementById('main2');
// var myChart = echarts.init(chartDom);
// var option;

//         option = {
//             title: {
//                 text: '3대 영양소 섭취 비율',
//                 subtext: 'kcal 기준',
//                 left: 'center'
//             },
//             tooltip: {
//                 trigger: 'item',
//                 formatter: '{a} <br/>{b} : {c} ({d}%)'
//             },
//             legend: {
//                 left: 'center',
//                 top: 'bottom',
//                 data: [
//                 'rose1',
//                 'rose2',
//                 'rose3',
//                 'rose4',
//                 'rose5'
//                 ]
//             },
//             toolbox: {
//                 show: true,
//                 feature: {
//                 mark: { show: true },
//                 dataView: { show: true, readOnly: false },
//                 restore: { show: true },
//                 saveAsImage: { show: true }
//                 }
//             },
//             series: [
//                 {
//                 name: 'Radius Mode',
//                 type: 'pie',
//                 radius: [20, 140],
//                 center: ['25%', '50%'],
//                 roseType: 'radius',
//                 itemStyle: {
//                     borderRadius: 5
//                 },
//                 label: {
//                     show: false
//                 },
//                 emphasis: {
//                     label: {
//                     show: true
//                     }
//                 },
//                 data: [
//                     { value: 38.8, name: '탄수화물' },
//                     { value: 24.3, name: '단백질' },
//                     { value: 34.0, name: '지방' },
//                     { value: 2.3, name: '식이섬유' },
//                     { value: 0.6, name: '당' }
//                 ]
//                 },
//                 {
//                 name: 'Area Mode',
//                 type: 'pie',
//                 radius: [20, 140],
//                 center: ['75%', '50%'],
//                 roseType: 'area',
//                 itemStyle: {
//                     borderRadius: 5
//                 },
//                 data: [
//                     { value: 30, name: 'rose 1' },
//                     { value: 28, name: 'rose 2' },
//                     { value: 26, name: 'rose 3' },
//                     { value: 24, name: 'rose 4' },
//                     { value: 22, name: 'rose 5' },
//                     { value: 20, name: 'rose 6' },
//                     { value: 18, name: 'rose 7' },
//                     { value: 16, name: 'rose 8' }
//                 ]
//                 }
//             ]
//             };
//             option && myChart.setOption(option);
