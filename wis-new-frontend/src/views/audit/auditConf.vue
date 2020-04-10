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
                <el-table-column label="流程模块" prop="spc_ModelName" align="center"></el-table-column>
                <el-table-column label="版本号" prop="spc_Version" align="center"></el-table-column>
                <el-table-column label="步骤编号" prop="spc_Step" align="center"></el-table-column>
                <el-table-column label="状态" prop="spc_State" align="center">
                    <template slot-scope="scope">
                        <span>{{scope.row.spc_State | stateMap}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="配置角色" align="center">
                    <template slot-scope="scope">
                        <span v-for="(role, index) in scope.row.rolelist" :key="index">
                            <el-tag type="gray"> {{ role }} </el-tag>
                        </span>
                    </template>
                </el-table-column>
                <el-table-column label="创建人" prop="spc_CreateUser" align="center"></el-table-column>
                <el-table-column label="创建时间" prop="spc_CreateTime" align="center" sortable></el-table-column>
            </el-table>
            <div class="pagination-container">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="tableQuery.currentPage"
                    :page-sizes="[10, 20, 50]" :page-size="tableQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
            <el-dialog :title="'查询'" :visible.sync="queryForm">
                <el-form class="small-space" :model="tableQuery" label-position="left" label-width="100px" style='width: 100%; margin-left:0px;'>
                    <el-form-item label="流程模块">
                        <el-select v-model="tableQuery.spc_Model" placeholder="请选择流程模块" style="width: 100%">
                            <el-option v-for="item in modelList" :key="item.resource_model" :label="item.resource_name" :value="item.resource_model"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="版本号">
                        <el-input name="spc_Version" v-model="tableQuery.spc_Version" autoComplete="on" maxlength="2"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="tableQuery.spc_State" placeholder="请选择" style="width: 100%">
                            <el-option v-for="item in stateArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="创建人">
                        <el-input name="spc_CreateUser" v-model="tableQuery.spc_CreateUser" autoComplete="on" maxlength="50"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <div class="block">
                            <el-date-picker
                              v-model="tableQuery.spc_CreateTimeStart"
                              name="spc_CreateTimeStart"
                              align="right"
                              value-format="yyyy-MM-dd hh:mm:ss"
                              type="datetime"
                              placeholder="开始时间">
                            </el-date-picker> ---
                            <el-date-picker
                              v-model="tableQuery.spc_CreateTimeEnd"
                              name="spc_CreateTimeEnd"
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
                    <el-form-item label="流程模块" :required=true>
                        <el-select v-model="createdItem.spc_Model" placeholder="请选择流程模块" style="width: 100%">
                            <el-option v-for="item in modelList" :key="item.resource_model" :label="item.resource_name" :value="item.resource_model"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="版本号" :required=true>
                        <el-input name="spc_Version" v-model="createdItem.spc_Version" autoComplete="on" maxlength="2"></el-input>
                    </el-form-item>
                    <el-form-item label="步骤编号" :required=true>
                        <el-input name="spc_Step" v-model="createdItem.spc_Step" autoComplete="on" maxlength="2"></el-input>
                    </el-form-item>
                    <el-form-item label="配置角色" :required=true>
                        <el-select v-model="createdItem.spc_Rolelist" multiple placeholder="请选择用户角色" style="width: 100%">
                            <el-option v-for="item in roleData" :key="item.id" :label="item.name" :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="状态" :required=true>
                        <el-select v-model="createdItem.spc_State" placeholder="请选择" style="width: 100%">
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
                stateArr: [ //状态下拉框
                    {value: '0', label: '无效'},
                    {value: '1', label: '有效'}
                ],
                querySubmiting:false,
                formSubmiting:false,
                queryForm:false,
                total: 0,
                tableData: [],
                tableLoading: false,
                createForm:false,
                selectedRow:null,
                tableQuery: {  //查询窗口数据
                    limit: 10,
                    currentPage: 1,
                    spc_CreateUser:null,
                    spc_State:null,
                    spc_Model:null,
                    spc_Version:null,
                    spc_CreateTimeStart:null,
                    spc_CreateTimeEnd:null
                },
                createdItem:{
                    spc_Code:null,
                    spc_State:null,
                    spc_Model:null,
                    spc_Version:null,
                    spc_Step:null,
                    spc_Rolelist:[]
                },
                roleQuery:{
                    limit: 1000,
                    currentPage: 1,
                },
                roleData:null,
                modelList:null,
            }
        },
        methods:{
            getApplyList(){
                this.selectedRow = null;  //清空选中的行数据
                this.tableLoading = true;
                 this.$api.audit.auditConf.list({
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
                    this.$api.audit.auditConf.delete({
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
                if(this.createdItem.spc_Model==null || this.createdItem.spc_Model==""){
                    this.$notify({
                        title: '提示',
                        message: '请填选择流程模块',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.spc_Version==null || this.createdItem.spc_Version==""){
                    this.$notify({
                        title: '提示',
                        message: '请填写版本号',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.spc_Step==null || this.createdItem.spc_Step==""){
                    this.$notify({
                        title: '提示',
                        message: '请填写步骤编号',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.spc_Rolelist.length<1){
                    this.$notify({
                        title: '提示',
                        message: '请填选择配置角色',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                if(this.createdItem.spc_State==null || this.createdItem.spc_State==""){
                    this.$notify({
                        title: '提示',
                        message: '请填选择状态',
                        type: 'warning',
                        duration: 1500,
                    });
                    return;
                }
                this.formSubmiting = true;
                this.$api.audit.auditConf.update({
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
                        this.createdItem.spc_Code = this.selectedRow.spc_Code;
                        this.createdItem.spc_State = this.selectedRow.spc_State;
                        this.createdItem.spc_Model = this.selectedRow.spc_Model;
                        this.createdItem.spc_Version = this.selectedRow.spc_Version;
                        this.createdItem.spc_Step = this.selectedRow.spc_Step;
                        this.createdItem.spc_Rolelist = Object.keys(this.selectedRow.rolelist).map(key => parseInt(key));
                        this.createForm = true;
                    }
                }else if(type=="create"){
                    this.clearEditDialog();
                    this.createForm = true;
                }
            },
            clearEditDialog(){
                this.createdItem.spc_Code = null;
                this.createdItem.spc_State = null;
                this.createdItem.spc_Model = null;
                this.createdItem.spc_Version = null;
                this.createdItem.spc_Step = null;
                this.createdItem.spc_Rolelist = [];
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
