<template>
    <div class="components-container">
         <div class="components-container">
            <div class="filter-container">
                <el-button @click="getApplyList" type="gray"><i class="el-icon-refresh"></i>刷新</el-button>
                <el-button @click="showInfo('create')" type="gray"><i class="el-icon-plus"></i>新建</el-button>
                <el-button @click="showInfo('edit')" type="gray"><i class="el-icon-edit"></i>修改</el-button>
                <el-button @click="deleteConf()" type="gray"><i class="el-icon-delete"></i>删除</el-button>
                <el-button @click="queryForm = true" type="gray"><i class="el-icon-search"></i>查询</el-button>
            </div>
            <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row
                style="width: 100%" @row-click="rowClick">
                <el-table-column label="通知内容" prop="smc_Content" align="center"></el-table-column>
                <el-table-column label="关联模块" prop="smc_ModelName" align="center"></el-table-column>
                <el-table-column label="关联模块的操作" align="center">
                    <template slot-scope="scope">
                        <span v-for="(operate, index) in scope.row.operatelist" :key="index">
                            {{ operate }}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column label="状态" prop="smc_State" align="center">
                    <template slot-scope="scope">
                        <span>{{scope.row.smc_State | stateMap}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="详情地址" prop="smc_URL" align="center"></el-table-column>
                <el-table-column label="被通知人" align="center">
                    <template slot-scope="scope">
                        <span v-for="(role, index) in scope.row.notifiedlist" :key="index">
                            <el-tag type="gray"> {{ role }} </el-tag>
                        </span>
                    </template>
                </el-table-column>
                <el-table-column label="创建人" prop="smc_CreateUser" align="center"></el-table-column>
                <el-table-column label="创建时间" prop="smc_CreateTime" align="center" sortable></el-table-column>
                <el-table-column label="版本" prop="smc_Version" align="center"></el-table-column>
            </el-table>
            <div class="pagination-container">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="tableQuery.currentPage"
                    :page-sizes="[10, 20, 50]" :page-size="tableQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
            <el-dialog :title="'查询'" :visible.sync="queryForm">
                <el-form class="small-space" :model="tableQuery" label-position="left" label-width="100px" style='width: 100%; margin-left:0px;'>
                    <el-form-item label="关联模块">
                        <!-- <el-input name="smc_Model" v-model="tableQuery.smc_Model" autoComplete="on" maxlength="50"></el-input> -->
                        <el-select v-model="tableQuery.smc_Model" placeholder="请选择" style="width: 100%"  @change="modelChange">
                            <el-option v-for="item in modelList" :key="item.resource_model" :label="item.resource_name" :value="item.resource_model"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="版本">
                        <el-input name="smc_Version" v-model="tableQuery.smc_Version" autoComplete="on" maxlength="2"></el-input>
                    </el-form-item>
                    <el-form-item label="创建人">
                        <el-input name="smc_CreateUser" v-model="tableQuery.smc_CreateUser" autoComplete="on" maxlength="50"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <div class="block">
                            <el-date-picker
                              v-model="tableQuery.smc_CreateTimeStart"
                              name="smc_CreateTimeStart"
                              align="right"
                              value-format="yyyy-MM-dd hh:mm:ss"
                              type="datetime"
                              placeholder="开始时间">
                            </el-date-picker> ---
                            <el-date-picker
                              v-model="tableQuery.smc_CreateTimeEnd"
                              name="smc_CreateTimeEnd"
                              align="right"
                              value-format="yyyy-MM-dd hh:mm:ss"
                              type="datetime"
                              placeholder="结束时间">
                            </el-date-picker>
                          </div>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="queryForm = false">取 消</el-button>
                    <el-button type="warning" :loading="querySubmiting" @click="getApplyList">查 询</el-button>
                </div>
            </el-dialog>
            <el-dialog :title="'配置编辑'" :visible.sync="createForm">
                <el-form class="small-space" :model="createdItem" label-position="left" label-width="100px" style='width: 100%; margin-left:0px;'>
                    <el-form-item label="通知内容" :required=true>
                        <el-input name="smc_Content" v-model="createdItem.smc_Content" autoComplete="on" maxlength="200"></el-input>
                    </el-form-item>
                    <el-form-item label="关联模块" :required=true>
                        <el-select v-model="createdItem.smc_Model" placeholder="请选择" style="width: 100%"  @change="modelChange">
                            <el-option v-for="item in modelList" :key="item.resource_model" :label="item.resource_name" :value="item.resource_model"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="模块操作" :required=true>
                        <el-select v-model="createdItem.smc_Operate" placeholder="请选择" style="width: 100%">
                            <el-option v-for="item in operatList" :key="item.operate_id" :label="item.operate_operate" :value="item.operate_id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="详情地址" :required=true>
                        <el-input name="smc_URL" v-model="createdItem.smc_URL" autoComplete="on" maxlength="200"></el-input>
                    </el-form-item>
                    <el-form-item label="版本号" :required=true>
                        <el-input name="smc_Version" v-model="createdItem.smc_Version" autoComplete="on" maxlength="2"></el-input>
                    </el-form-item>
                    <el-form-item label="被通知人" :required=true>
                        <el-select v-model="createdItem.smc_Notifiedlist" multiple placeholder="请选择用户角色" style="width: 100%">
                            <el-option v-for="item in roleData" :key="item.id" :label="item.name" :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="状态" :required=true>
                        <el-select v-model="createdItem.smc_State" placeholder="请选择" style="width: 100%">
                            <el-option v-for="item in stateArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="createForm = false">取 消</el-button>
                    <el-button type="warning" :loading="formSubmiting" @click="saveClassInfo">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>
<script>
    import { mapGetters } from 'vuex';
    import { confirmBox } from 'utils/message';
    export default {
        data() {
            return {
                tableColumShow: false, //控制表格列的显示与隐藏
                selectedRow: null, //列表选中的行数据
                stateArr: [ //状态下拉框
                    {value: '0', label: '无效'},
                    {value: '1', label: '有效'}
                ],
                tableQuery: {  //查询窗口数据
                    limit: 10,
                    currentPage: 1,
                    smc_Model: null,
                    smc_Version: null,
                    smc_CreateUser: null,
                    smc_CreateTimeStart: null,
                    smc_CreateTimeEnd: null,
                },
                querySubmiting:false,
                formSubmiting:false,
                queryForm:false,
                total: 0,
                tableData: [],
                tableLoading: false,
                createForm:false,
                createdItem: {     //新建、修改窗口数据
                    smc_State:null,
                    smc_Content:null,
                    smc_Model:null,
                    smc_Operate:null,
                    smc_URL:null,
                    smc_Version:null,
                    smc_Notifiedlist:[]
                },
                roleQuery:{
                    limit: 1000,
                    currentPage: 1,
                    operate_Model:null
                },
                roleData:null,
                operatList:null,
                modelList:null
            }
        },
        methods:{
            getApplyList(){
                this.selectedRow = null;  //清空选中的行数据
                this.tableLoading = true;
                 this.$api.message.messageconfig.list({
                    data: this.tableQuery
                }).then(response => {
                    this.total = response.data.data.total;
                    this.tableData = response.data.data.list;
                    this.queryForm = false;
                    this.tableLoading = false;
                }).catch(error => {
                    this.tableLoading = false;
                    console.log(error);
                });
            },
            modelChange(){
                this.roleQuery.operate_Model = this.createdItem.smc_Model;
                this.getOperatelist();
            },
            showInfo(type){
                if(type=="edit"){
                    if(this.selectedRow==null){
                        this.$notify({
                            title: '提示',
                            message: '请选择一条数据！',
                            type: 'warning',
                            duration: 2000,
                        });
                    }else{
                        this.roleQuery.operate_Model = this.selectedRow.smc_Model;
                        this.getOperatelist();
                        this.createdItem.smc_Code = this.selectedRow.smc_Code;
                        this.createdItem.smc_State = this.selectedRow.smc_State;
                        this.createdItem.smc_Content = this.selectedRow.smc_Content;
                        this.createdItem.smc_Model = this.selectedRow.smc_Model;
                        this.createdItem.smc_Operate = this.selectedRow.smc_Operate;
                        this.createdItem.smc_URL = this.selectedRow.smc_URL;
                        this.createdItem.smc_Version = this.selectedRow.smc_Version;
                        this.createdItem.smc_Notifiedlist = Object.keys(this.selectedRow.notifiedlist).map(key => parseInt(key));
                        this.createForm = true;
                    }
                }else if(type=="create"){
                    this.clearEditDialog();
                    this.createForm = true;
                }
            },
            clearEditDialog(){
                this.createdItem.smc_Code = null;
                this.createdItem.smc_State = null;
                this.createdItem.smc_Content = null;
                this.createdItem.smc_Model = null;
                this.createdItem.smc_URL = null;
                this.createdItem.smc_Operate = null;
                this.createdItem.smc_Version = null;
                this.createdItem.smc_Notifiedlist = [];
            },
            deleteConf(){
                if(this.selectedRow==null){
                    this.$notify({
                        title: '提示',
                        message: '请选择一条数据！',
                        type: 'warning',
                        duration: 2000,
                    });
                    return;
                }
                confirmBox('此配置').then(() => {
                    this.$api.message.messageconfig.delete({
                        data: this.selectedRow
                    }).then(response => {
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 1500,
                        });
                        this.getApplyList();
                        this.selectedRow = null;
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
            saveClassInfo(){
                if(this.createdItem.smc_Content==null || this.createdItem.smc_Content==""){
                    this.$notify({
                        title: '提示',
                        message: '请填写通知内容',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.smc_Model==null || this.createdItem.smc_Model==""){
                    this.$notify({
                        title: '提示',
                        message: '请选择关联模块',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.smc_Operate==null || this.createdItem.smc_Operate==""){
                    this.$notify({
                        title: '提示',
                        message: '请填写模块操作',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.smc_URL==null || this.createdItem.smc_URL==""){
                    this.$notify({
                        title: '提示',
                        message: '请填写详情地址',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.smc_Version==null || this.createdItem.smc_Version==""){
                    this.$notify({
                        title: '提示',
                        message: '请填写版本号',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.smc_Notifiedlist.length<1){
                    this.$notify({
                        title: '提示',
                        message: '请选择被通知人',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.smc_State==null || this.createdItem.smc_State==""){
                    this.$notify({
                        title: '提示',
                        message: '请选择状态',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                this.formSubmiting = true;
                this.$api.message.messageconfig.update({
                  data: this.createdItem
                }).then(response => {
                    let result = response.data.data.Succeed;
                    if(result=="Succees"){
                        this.formSubmiting = false;
                        this.createForm = false;
                        this.$notify({
                            title: '成功',
                            message: '保存成功',
                            type: 'success',
                            duration: 1500,
                        });
                        this.getApplyList();
                    }else{
                        this.$notify({
                            title: '错误',
                            message: "保存失败",
                            type: 'error',
                            duration: 3000,
                        });
                    }
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
            },
            getRoleList(){
                 this.$api.system.role.all({
                    data: this.roleQuery
                }).then(response => {
                    this.roleData = response.data.data;
                }).catch(error => {
                    console.log(error);
                });
            },
            getOperatelist(){
                this.createdItem.smc_Operate = null;
                this.$api.message.operate.operatelist({
                    data: this.roleQuery
                }).then(response => {
                    this.operatList = response.data.data.list;
                }).catch(error => {
                    console.log(error);
                });
            },
            getModelList(){
                this.$api.system.menu.modelList({
                    data: this.roleQuery
                }).then(response => {
                    this.modelList = response.data.data.list;
                }).catch(error => {
                    console.log(error);
                });
            },
            rowClick(row){
                this.selectedRow = row;
            },
            handleCurrentChange(val) {
                this.tableQuery.currentPage = val;
                this.getApplyList();
            },
            handleSizeChange(val) {
                this.tableQuery.limit = val;
                this.getApplyList();
            },
        },
        mounted() {
            this.getApplyList();
            this.getRoleList();
            this.getModelList();
        },
        filters: {
            stateMap(state) {
                const stateMap = {
                    '0':'无效',
                    '1':'有效'
                };
                return stateMap[state]
            },
        }
    }
</script>

<style>
</style>
