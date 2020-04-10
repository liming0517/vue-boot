<template>
    <div class="chartDialog">
        <div v-if="showZhe" id="myChart2" :style="{width: '100%', height: '100%'}"></div>
        <div v-if="showBing" id="myChart3" :style="{width: '100%', height: '100%'}"></div>
        <div v-if="showZhu" id="myChart1" :style="{width: '100%', height: '100%'}"></div>
    </div>
</template>

<script>
    export default {
         data() {
                return {
                    showZhu:true,
                    showZhe:true,
                    showBing:true
                }
            },
        methods: {
            drawLine(){
                this.chartTitle = this.chartData.title;
                // 基于准备好的dom，初始化echarts实例
                if(this.chartType=="zhu"){
                    let myChart1 = this.$echarts.init(document.getElementById('myChart1'));
                    myChart1.setOption({
                        color: ['#3398DB'], //柱体颜色
                        title: { text: this.chartData.title },
                        tooltip: {},
                        xAxis: {
                            type: 'category',
                            data:this.chartData.yData
                        },
                        yAxis: {type: 'value'},
                        series: [{
                            name: this.chartData.yTitle+' / '+this.chartData.xTitle,
                            type: 'bar',
                            data: this.chartData.xData
                        }]
                    });
                }else if(this.chartType=="zhe"){
                    let myChart2 = this.$echarts.init(document.getElementById('myChart2'));
                    myChart2.setOption({
                        title: { text: this.chartData.title },
                        tooltip: {
                            trigger: 'axis'
                        },
                        xAxis: {
                            type: 'category',
                            data: this.chartData.yData
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            name:this.chartData.xTitle,
                            type:'line',
                            data: this.chartData.xData
                        }]
                    });
                }else if(this.chartType=="bing"){
                    let myChart3 = this.$echarts.init(document.getElementById('myChart3'));
                    let size = this.chartData.yData.length;
                    let seriesData = [];
                    let selected = {};
                    for (let i=0;i<size;i++){
                        seriesData.push({
                            name: this.chartData.yData[i],
                            value: this.chartData.xData[i]
                        });
                        selected[i] = true;
                    }
                    myChart3.setOption({
                        title : {
                            text: this.chartData.title,
                            //subtext: '副标题',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            type: 'scroll',
                            orient: 'vertical',
                            right: 100,
                            top: 20,
                            bottom: 10,
                            data: this.chartData.yData,
                            selected: selected
                        },
                        series : [
                            {
                                name: this.chartData.yTitle+" / "+this.chartData.xTitle+"( 比例 )",
                                type: 'pie',
                                radius : '60%',
                                center: ['40%', '60%'],
                                data: seriesData,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    });
                }
            }
        },
        mounted() {
            if(this.chartType=="zhu"){
                this.showZhe = false;
                this.showBing = false;
            }else if(this.chartType=="zhe"){
                this.showzhu = false;
                this.showBing = false;
            }else if(this.chartType=="bing"){
                this.showzhu = false;
                this.showZhe = false;
            }
            this.drawLine();
        },
        props: {
            chartData: {
                type: Object,
                default () {
                    return null;
                }
            },
            chartType: {
                type: String,
                default () {
                    return null;
                }
            },
        },
        watch:{
            chartData(newValue, oldValue) {
                this.chartData = newValue;
                this.drawLine();
            }
        }
    }
</script>

<style>
    .chartDialog{
        width: 100%;
        height: 100%;
    }
</style>
