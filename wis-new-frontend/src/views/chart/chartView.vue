<template>
    <div class="components-container">
            <div class="filter-container">
                <el-button @click="openChartBING()" type="gray"><i class="el-icon-edit"></i>饼图</el-button>
                <el-button @click="openChartZHE()" type="gray"><i class="el-icon-plus"></i>折线图</el-button>
                <el-button @click="openChartZHU()" type="gray"><i class="el-icon-refresh"></i>柱状图</el-button>
                <el-button @click="openNoPermission()" type="gray"><i class="el-icon-refresh"></i>无权限</el-button>
                <el-button @click="test()" type="gray"><i class="el-icon-refresh"></i>test</el-button>
            </div>
            <!-- 柱状图 -->
            <myChart v-if="showZhuChart" :chartData="chartData" :chartType="chartType" style="height:350px;width:750px;background:#B7B7B7;"/>
            <div style="height:10px;width:100%;"></div>
            <!-- 折线图 -->
            <myChart v-if="showZheChart" :chartData="chartData1" :chartType="chartType1" style="height:350px;width:750px;background:#B7B7B7;"/>
            <div style="height:10px;width:100%;"></div>
            <!-- 饼图 -->
            <myChart v-if="showBingChart" :chartData="chartData2" :chartType="chartType2" style="height:350px;width:750px;background:#B7B7B7;"/>
    </div>
</template>
<script>
import myChart from '../chart/myChart';
export default {
    components:{
        myChart
    },
    data() {
        return {
            showZhuChart:false,
            showZheChart:false,
            showBingChart:false,
            chartType:'zhu',
            chartType1:'zhe',
            chartType2:'bing',
            chartData:{
                title:'图表',
                xTitle:'数量',
                yTitle:'人员',
                xData:[12,55,86,12,58,66,44,55,1,56,26],
                yData:['AAA','BBB','CCC','DDD','EEE','FFF','GGG','HHH','III','JJJ','KKK'],
            },
            chartData1:{
                title:'图表',
                xTitle:'数量',
                yTitle:'人员',
                xData:[12,55,86,12,58,66],
                yData:['AAA','BBB','CCC','DDD','EEE','FFF'],
            },
            chartData2:{
                title:'图表',
                xTitle:'数量',
                yTitle:'人员',
                xData:[66,55,86,21],
                yData:['AAA','BBB','CCC','DDD'],
            }
        }
    },
    methods: {
        openChartZHU(){
            this.showZhuChart = !this.showZhuChart;
        },
        openChartZHE(){
            this.showZheChart = !this.showZheChart;
        },
        openChartBING(){
            this.showBingChart = !this.showBingChart;
        },
        openNoPermission(){
            this.$router.push({path:'/noPermission'});
        },
        test(){
            this.$api.file.file.test({
            }).then(response => {
                console.log(response.data);
            }).catch(error => {
                console.log(error);
            });
        },
    },
    mounted() {

    }
}
</script>
<style>
</style>
