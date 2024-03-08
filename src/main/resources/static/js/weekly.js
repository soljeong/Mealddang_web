import * as echarts from 'https://cdn.jsdelivr.net/npm/echarts/dist/echarts.esm.js';

            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            var option;
            const rawData = [
                [100, 302, 301, 334, 390, 330, 320],
                [320, 132, 101, 134, 90, 230, 210],
                [220, 182, 191, 234, 290, 330, 310],
                [150, 212, 201, 154, 190, 330, 410],
                [820, 832, 901, 934, 1290, 1330, 1320]
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
                'Direct',
                'Mail Ad',
                'Affiliate Ad',
                'Video Ad',
                'Search Engine'
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


//원그래프
var chartDom = document.getElementById('main2');
var myChart = echarts.init(chartDom);
var option;

        option = {
            title: {
                text: 'Nightingale Chart',
                subtext: 'Fake Data',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                left: 'center',
                top: 'bottom',
                data: [
                'rose1',
                'rose2',
                'rose3',
                'rose4',
                'rose5',
                'rose6',
                'rose7',
                'rose8'
                ]
            },
            toolbox: {
                show: true,
                feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
                }
            },
            series: [
                {
                name: 'Radius Mode',
                type: 'pie',
                radius: [20, 140],
                center: ['25%', '50%'],
                roseType: 'radius',
                itemStyle: {
                    borderRadius: 5
                },
                label: {
                    show: false
                },
                emphasis: {
                    label: {
                    show: true
                    }
                },
                data: [
                    { value: 40, name: 'rose 1' },
                    { value: 33, name: 'rose 2' },
                    { value: 28, name: 'rose 3' },
                    { value: 22, name: 'rose 4' },
                    { value: 20, name: 'rose 5' },
                    { value: 15, name: 'rose 6' },
                    { value: 12, name: 'rose 7' },
                    { value: 10, name: 'rose 8' }
                ]
                },
                {
                name: 'Area Mode',
                type: 'pie',
                radius: [20, 140],
                center: ['75%', '50%'],
                roseType: 'area',
                itemStyle: {
                    borderRadius: 5
                },
                data: [
                    { value: 30, name: 'rose 1' },
                    { value: 28, name: 'rose 2' },
                    { value: 26, name: 'rose 3' },
                    { value: 24, name: 'rose 4' },
                    { value: 22, name: 'rose 5' },
                    { value: 20, name: 'rose 6' },
                    { value: 18, name: 'rose 7' },
                    { value: 16, name: 'rose 8' }
                ]
                }
            ]
            };
            
            option && myChart.setOption(option);


//신호등
    var chartDom = document.getElementById('main3');
    var myChart = echarts.init(chartDom);
    var option;

    //칼로리
    option = {
        dataset: [
        {
        source: [
            ['Product', 'Sales', 'Price', 'Year'],
            ['Cake', 123, 32, 2011],
            ['Cereal', 231, 14, 2011],
            ['Tofu', 235, 5, 2011],
            ['Dumpling', 341, 25, 2011],
            ['Biscuit', 122, 29, 2011],
            ['Cake', 143, 30, 2012],
            ['Cereal', 201, 19, 2012],
            ['Tofu', 255, 7, 2012],
            ['Dumpling', 241, 27, 2012],
            ['Biscuit', 102, 34, 2012],
            ['Cake', 153, 28, 2013],
            ['Cereal', 181, 21, 2013],
            ['Tofu', 395, 4, 2013],
            ['Dumpling', 281, 31, 2013],
            ['Biscuit', 92, 39, 2013],
            ['Cake', 223, 29, 2014],
            ['Cereal', 211, 17, 2014],
            ['Tofu', 345, 3, 2014],
            ['Dumpling', 211, 35, 2014],
            ['Biscuit', 72, 24, 2014]
        ]
        },
        {
        transform: {
            type: 'filter',
            config: { dimension: 'Year', value: 2011 }
        }
        },
        {
        transform: {
            type: 'filter',
            config: { dimension: 'Year', value: 2012 }
        }
        },
        {
        transform: {
            type: 'filter',
            config: { dimension: 'Year', value: 2013 }
        }
        }
    ],
    series: [
        {
        type: 'gauge',
        startAngle: 180,
        endAngle: 0,
        radius: 50,
        center: ['50%', '25%'],
        datasetIndex: 1
        },
        {
        type: 'gauge',
        startAngle: 180,
        endAngle: 0,
        radius: 50,
        center: ['50%', '50%'],
        datasetIndex: 2
        },
        {
        type: 'gauge',
        startAngle: 180,
        endAngle: 0,
        radius: 50,
        center: ['50%', '75%'],
        datasetIndex: 3
        }
    ],
    // Optional. Only for responsive layout:
    media: [
        {
        query: { minAspectRatio: 1 },
        option: {
            series: [
            { center: ['25%', '50%'] },
            { center: ['50%', '50%'] },
            { center: ['75%', '50%'] }
            ]
        }
        },
        {
        option: {
            series: [
            { center: ['50%', '25%'] },
            { center: ['50%', '50%'] },
            { center: ['50%', '75%'] }
            ]
        }
        }
    ]
    };
    
    option && myChart.setOption(option);

    //탄수화물
option = {
    series: [
        {
        type: 'gauge',
        startAngle: 180,
        endAngle: 0,
        center: ['750%', '50%'],
        radius: '30%',
        min: 0,
        max: 1,
        splitNumber: 8,
        axisLine: {
            lineStyle: {
            width: 6,
            color: [
                [0.25, '#FF6E76'],
                [0.5, '#FDDD60'],
                [0.75, '#58D9F9'],
                [1, '#7CFFB2']
            ]
            }
        },
        pointer: {
            icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',
            length: '12%',
            width: 20,
            offsetCenter: [0, '-60%'],
            itemStyle: {
            color: 'auto'
            }
        },
        axisTick: {
            length: 12,
            lineStyle: {
            color: 'auto',
            width: 2
            }
        },
        splitLine: {
            length: 20,
            lineStyle: {
            color: 'auto',
            width: 5
            }
        },
        axisLabel: {
            color: '#464646',
            fontSize: 20,
            distance: -60,
            rotate: 'tangential',
            formatter: function (value) {
            if (value === 0.875) {
                return 'Grade A';
            } else if (value === 0.625) {
                return 'Grade B';
            } else if (value === 0.375) {
                return 'Grade C';
            } else if (value === 0.125) {
                return 'Grade D';
            }
            return '';
            }
        },
        title: {
            offsetCenter: [0, '-10%'],
            fontSize: 8
        },
        detail: {
            fontSize: 12,
            offsetCenter: [0, '-35%'],
            valueAnimation: true,
            formatter: function (value) {
            return Math.round(value * 100) + '';
            },
            color: 'inherit'
        },
        data: [
            {
            value: 0.7,
            name: 'Grade Rating'
            }
        ]
        }
    ]
    };
    
    option && myChart.setOption(option);
    