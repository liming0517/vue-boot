<template>
	<div class="components-container">{{shortMSG}}
		<div class="filter-container">
			<el-button @click="queryList" type="gray"><i class="el-icon-refresh"></i>后端交互</el-button>
            <el-button @click="restfulAuth" type="gray"><i class="el-icon-refresh"></i>client模拟登陆</el-button>
            <el-button @click="testRestFul" type="gray"><i class="el-icon-refresh"></i>client查询信息</el-button>
		</div>
		<el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
		    <el-table-column v-if="tableColumShow" label="客户详情" prop="sm_URL" align="center" sortable ></el-table-column>
		    <el-table-column label="编号" prop="c_ID" align="center" ></el-table-column>
		    <el-table-column label="姓名" prop="c_Name" align="center" ></el-table-column>
			<el-table-column label="状态" prop="c_State" align="center"></el-table-column>
		</el-table>
	</div>
</template>

<script>
	import { confirmBox } from 'utils/message';
	export default {
	    data() {
	        return {
				//此处为与页面元素绑定的参数
	            shortMSG:"这是custom.vue文件",
				tableColumShow:false,
				tableQuery: {  //查询窗口数据
				    limit: 10,//每页数量
				    currentPage: 1,//当前页
				    frontArg: "前端参数"
				},
				querySubmiting:false,
				queryForm:false,
				total: 0,
				tableData: [],//列表数据
				tableLoading: false//加载中
	        }
	    },
	    methods: {
			//自定义方法写在此处
			changeMSG(){
				this.shortMSG="修改后的文本内容"
			},
			queryList(){
				this.tableLoading = true;
				//后端请求api
				this.$api.custom.info.list({
					//传到后端的参数
				    data: this.tableQuery
				}).then(response => {
					//成功时的返回值
				    this.queryForm = false;
				    this.tableLoading = false;
					//查看返回值，进行相应处理
					console.log(response)
					this.tableData = response.data.data.list
					this.shortMSG = response.data.message
				}).catch(error => {
					//出错时的返回值
				    this.tableLoading = false;
					//查看错误信息
				    console.log(error);
				});
			},
            restfulAuth(){
                //后端请求api
                this.$api.custom.restful.auth({
                	//传到后端的参数
                    data: this.tableQuery
                }).then(response => {
                	//查看返回值，进行相应处理
                	console.log(response)
                    this.shortMSG = response.data.message
                    this.$message.success(this.shortMSG)
                }).catch(error => {
                	//查看错误信息
                    console.log(error);
                    this.shortMSG = error
                    this.$message.error(this.shortMSG)
                });
            },
            testRestFul(){
                //后端请求api
                this.$api.custom.restful.test({
                	//传到后端的参数
                    data: this.tableQuery
                }).then(response => {
                	//查看返回值，进行相应处理
                	console.log(response)
                    this.tableData = response.data.data.list
                    this.shortMSG = response.data.message
                    this.$message.success(this.shortMSG)
                }).catch(error => {
                	//查看错误信息
                    console.log("error:"+error);
                    this.tableData = []
                    this.shortMSG = error
                    this.$message.error(this.shortMSG)
                });
            }
	    },
	    mounted() {
	        //页面加载完成时自动执行的代码卸载此处
			this.changeMSG()
	    },

	}
</script>

<style>
</style>
