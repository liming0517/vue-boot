<template>
    <div class="orderDialog">
        <div id="orderChart" :style="{width: '800px', height: '100%'}"></div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                legendArr:[],
                replyCount:[],
                questionCount:[]
            }
        },
        methods: {
            drawLine(){
                for(var i = 0;i<this.tableData.length;i++){
                    this.legendArr.push(this.tableData[i].wo_CreateUser+"\n"+this.tableData[i].wo_Name);
                    this.replyCount.push(this.tableData[i].huida);
                    this.questionCount.push(this.tableData[i].tiwen);
                }
                // 基于准备好的dom，初始化echarts实例
                var myChart=this.$echarts.init(document.getElementById('orderChart'));
                var posList = [
                    'left', 'right', 'top', 'bottom',
                    'inside',
                    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                ];

                app.configParameters = {
                    rotate: {
                        min: -90,
                        max: 90
                    },
                    align: {
                        options: {
                            left: 'left',
                            center: 'center',
                            right: 'right'
                        }
                    },
                    verticalAlign: {
                        options: {
                            top: 'top',
                            middle: 'middle',
                            bottom: 'bottom'
                        }
                    },
                    position: {
                        options: this.$echarts.util.reduce(posList, function (map, pos) {
                            map[pos] = pos;
                            return map;
                        }, {})
                    },
                    distance: {
                        min: 0,
                        max: 100
                    }
                };

                app.config = {
                    rotate: 90,
                    align: 'left',
                    verticalAlign: 'middle',
                    position: 'insideBottom',
                    distance: 15,
                    onChange: function () {
                        var labelOption = {
                            normal: {
                                rotate: app.config.rotate,
                                align: app.config.align,
                                verticalAlign: app.config.verticalAlign,
                                position: app.config.position,
                                distance: app.config.distance
                            }
                        };
                        myChart.setOption({
                            series: [{
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }]
                        });
                    }
                };


                var labelOption = {
                    normal: {
                        show: true,
                        position: app.config.position,
                        distance: app.config.distance,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        rotate: app.config.rotate,
                        formatter: '{c}  {name|{a}}',
                        fontSize: 16,
                        rich: {
                            name: {
                                textBorderColor: '#fff'
                            }
                        }
                    }
                };

                myChart.setOption({
                    color: ['#006699','#4cabce'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['提问次数', '答复次数']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        /* feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        } */
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            data: this.legendArr
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '提问次数',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            data: this.questionCount
                        },
                        {
                            name: '答复次数',
                            type: 'bar',
                            label: labelOption,
                            data: this.replyCount
                        }/* ,
                       {
                            name: 'Desert',
                            type: 'bar',
                            label: labelOption,
                            data: [150, 232, 201, 154, 190]
                        },
                        {
                            name: 'Wetland',
                            type: 'bar',
                            label: labelOption,
                            data: [98, 77, 101, 99, 40]
                        } */
                    ]
                });
            }
        },
        mounted() {
            this.drawLine();
        },
        props: {
            tableData: {
                type: Array,
                default () {
                    return [];
                }
            },
        },
        /* watch:{
            tableData(newValue, oldValue) {
                this.tableData = newValue;
                this.drawLine();
            }
        } */
    }
</script>

<style>
    .orderDialog{
        width: 900px;
        height: 350px;
    }
</style>
