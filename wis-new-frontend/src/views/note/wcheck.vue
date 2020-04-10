<!--
descreption:笔记本列表界面
creater: lewl
time:20190820
-->
<template>
    <!-- 界面按钮区域-->
    <div class="containers-container">
        <div class="filter-container">
            <!--<el-form id="uploadForm" enctype="multipart/form-data">
                <el-input type="file" name="filename" class="fileinput btn-lg" @change="test"/>
                <el-button type="button" @click="onUpload" class="fileinput_button btn btn-lg btn-success">导入</el-button>
            </el-form> -->

            <form id="uploadForm" enctype="multipart/form-data">
                <input type="file" name="filename">
                <el-button type="button" @click="onUpload" class="fileinput_button btn btn-lg btn-success">导入</el-button>
            </form>
            <!--el-input v-model="tableQuery.id" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="ID"></el-input>
            <el-button style="margin-left: 10px;" @click="getList()" type="primary"><i class="el-icon-search"></i>查询</el-button>
            <el-button @click="deleteRecord(deletedForm.id)" type="primary"><i class="el-icon-delete"></i>删除</el-button-->
            <el-button @click="showDialog('xj')" type="primary"><i class="el-icon-info"></i>新建</el-button>
            <el-button @click="showDialog('xg',createdForm)" type="primary"><i class="el-icon-info"></i>编辑</el-button>
            <el-button @click="getExcel()" type="primary"><i class="el-icon-info"></i>导出</el-button>
        </div>
        <!-- 界面列表区域-->
        <el-table :data="tableT" @row-click="handlecellclick" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="编号" prop="id" align="center"></el-table-column>
            <el-table-column label="日期时间" prop="time" align="center"></el-table-column>
            <el-table-column label="员工编号" prop="usercode" align="center"></el-table-column>
            <el-table-column label="员工姓名" prop="username" align="center"></el-table-column>
            <el-table-column label="上班时间" prop="stime" align="center"></el-table-column>
            <el-table-column label="下班时间" prop="etime" align="center"></el-table-column>
            <el-table-column label="出勤说明" prop="outdesc" align="center"></el-table-column>
            <el prop="creater" align="center"></el>
            <el prop="creattime" align="center"></el>
        </el-table>
        <el-dialog :title="formTitle" :visible.sync="formVisible" >
            <el-form class="small-space" :model="createdForm" label-position="left" label-width="70px" style='width: 60%; margin-left:0px;'>
                <el-form-item label="编号">
                    <el-input v-model="createdForm.id" placeholder="编号" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="日期时间">
                    <el-input v-model="createdForm.time" placeholder="请填写标题"></el-input>
                </el-form-item>
                <el-form-item label="员工编号">
                    <el-input v-model="createdForm.usercode" placeholder="请填写内容"></el-input>
                </el-form-item>
                <el-form-item label="员工姓名">
                    <el-input v-model="createdForm.username" placeholder="请填写状态"></el-input>
                </el-form-item>
                <el-form-item label="上班时间">
                    <el-input v-model="createdForm.stime" placeholder="请填写状态"></el-input>
                </el-form-item>
                <el-form-item label="下班时间">
                    <el-input v-model="createdForm.etime" placeholder="请填写状态"></el-input>
                </el-form-item>
                <el-form-item label="出勤说明">
                    <el-input v-model="createdForm.outdesc" placeholder="请填写状态"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="formVisible = false">取 消</el-button>
                <el-button type="primary" :loading="formSubmiting" @click="commitForm">确 认</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { confirmBox } from 'utils/message';
    export default {
        data() {
            return {
                tableQuery: {
                    limit: 10,
                    currentPage: 1,
                    id: null,
                },
                total: null,
                tableT: [],
                tableLoading: true,
                formTitle: null,
                formVisible: false,
                formSubmiting: false,
                createdForm: {
                    id: null,
                    time: null,
                    usercode: null,
                    username: null,
                    stime: null,
                    etime: null,
                    outdesc: null,
                },
                deletedForm: {
                    id: null
                },
                };
        },
        mounted() {
            this.getList();
        },
        methods: {
            getList() {//查询方法
                this.tableLoading = true;
                this.$api.system.wcheck.cx({
                    data: this.tableQuery
                }).then(response => {
                    this.total = response.data.data.total;
                    this.tableT = response.data.data.cx;
                    this.tableLoading = false;
                }).catch(error => {
                    this.tableLoading = false;
                    console.log(error);
                });
            },
            handlecellclick(val) {
                this.deletedForm.id=val.id
                this.createdForm=val
            },
            deleteRecord(id) {
                confirmBox('考勤信息').then(() => {
                    this.$api.system.wcheck.sc({
                        data: this.deletedForm
                    }).then(response => {
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 1500,
                        });
                        this.getList();
                        console.log(response);
                    }).catch(error => {
                        this.formSubmiting = false;
                        this.$notify({
                            title: '错误',
                            message: error,
                            type: 'error',
                            duration: 3000,
                        });
                        console.log(error);
                    });
                });
            },
            //清空dilog
            clearDialog() {
                this.createdForm.id = null;
                this.createdForm.time = null;
                this.createdForm.usercode = null;
                this.createdForm.username = null;
                this.createdForm.stime = null;
                this.createdForm.etime = null;
                this.createdForm.outdesc = null;
            },
            showDialog(type,row){//调用显示dilog
                console.log(type);
                this.formVisible = true;//显示dilog
                if (type == 'xj') {//判断新建
                    this.clearDialog();//调用清空
                    this.formTitle = "新增笔记本信息";
                } else {//判断编辑
                    this.formTitle = "修改笔记本信息";
                    if(row.id==null){
                        alert('未选中记录')
                        this.formVisible = false;//显示dilog
                    }
                }
                this.getList();
            },
            commitForm() {
                this.formSubmiting = true;
                //this.createdForm.creattime=new Date().getFullYear()+'-'+new Date().getMonth()+'-'+new Date().getDay()
                this.$api.system.wcheck.save({
                    data: this.createdForm
                }).then(response => {
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '保存成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.getList();
                    console.log(response);
                }).catch(error => {
                    this.formSubmiting = false;
                    this.$notify({
                        title: '错误',
                        message: error,
                        type: 'error',
                        duration: 3000,
                    });
                    console.log(error);
                });
            },onUpload: function (e) {
                this.$api.system.wcheck.import({
                    data: new FormData($('#uploadForm')[0]),
                }).then(response => {
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '导入成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.getList();
                }).catch(error => {
                    console.log(error);
                });
            },getExcel() {
                this.$api.system.wcheck.export({
                    data: this.tableQuery,
                }).then(response => {
                     require.ensure([], () => {
                         const { export_json_to_excel } = require('../../vendor/Export2Excel')
                         const tHeader = ['编号', '日期时间','员工编号','员工姓名','上班时间','下班时间','出勤说明']
                         const filterVal = ['id','time','usercode', 'username','stime','etime','outdesc']
                         const list = response.data.data.cx
                         const data = this.formatJson(filterVal, list)
                         export_json_to_excel(tHeader, data, '考勤表')
                     }).catch(err => {
                         console.log("导出错误！");
                   });
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '导出成功',
                        type: 'success',
                        duration: 1500,
                    });
                }).catch(error => {
                    console.log(error);
                });

            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            },
            expt(){
                const data = [{A:'1111',B:'2222'},{A:'1111',B:'2222'},{A:'1111',B:'2222'}]
                const header = {header: ['A','B'] }
                const xlsxName='1111'
                const outputXlsxFile = (data,header,xlsxName) => {

                    const ws = XLSX.utils.json_to_sheet(data, header)

                    const wb = XLSX.utils.book_new()

                    XLSX.utils.book_append_sheet(wb, ws, xlsxName)

                    XLSX.writeFile(wb, xlsxName + ".xlsx")

                }

                Vue.prototype.$outputXlsxFile
                    = outputXlsxFile

                Vue.prototype.$getExcelFileData = getExcelFileData
            }
        }
    };
</script>
