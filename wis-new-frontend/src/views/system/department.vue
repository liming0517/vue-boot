<template>
        <div class="components-container">
        <div class="filter-container">
            <el-input v-model="tableQuery.deptCode" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="部门编号"></el-input>
            <el-input v-model="tableQuery.cname" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="部门名"></el-input>
            <el-select v-model="tableQuery.state" clearable placeholder="请选择用户状态">
                <el-option v-for="item in state" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
            <el-button style="margin-left: 10px;" @click="handleFilter" type="gray"><i class="el-icon-search"></i></el-button>
            <el-button @click="handleReset" type="gray"><i class="el-icon-refresh"></i></el-button>
            <el-button @click="showDialog('create')" type="gray"><i class="el-icon-plus"></i> 新增部门</el-button>
        </div>

        <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="部门编号" prop="deptCode" align="center"></el-table-column>
            <el-table-column label="部门名" prop="cname" align="center"></el-table-column>
            <el-table-column label="状态" prop="state" align="center">
                <template slot-scope="scope">                                      <!-- statusMap对应方法名 -->
                    <el-tag :type="scope.row.state | stateFilter">{{scope.row.state | statusMap}}</el-tag>
                </template>                     <!-- stateFilter对应方法名 -->
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button size="small" @click="deptMeunManage(scope.row.deptCode)" type="primary" icon="setting">菜单</el-button>
                    <el-button size="small" @click="showDialog('update', scope.row)" type="gray"><i class="el-icon-edit"></i> </el-button>
                    <el-button size="small" @click="deleteRecord(scope.row.deptCode)" type="danger"><i class="el-icon-delete"></i> </el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="pagination-container">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="tableQuery.currentPage"
                :page-sizes="[10, 20, 50]" :page-size="tableQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>

        <el-dialog :title="formTitle" :visible.sync="formVisible">
            <el-form class="small-space" :model="createdItem" :rules="createdRules" label-position="left" label-width="70px" style='width: 60%; margin-left:0px;'>
                <el-form-item label="部门编号">
                    <el-input v-model="createdItem.deptCode" placeholder="请填写部门编号" v-bind:disabled="formTitle!='新建部门'" ></el-input>
                </el-form-item>
                <el-form-item label="部门名" prop="cname">
                    <el-input name="cname" v-model="createdItem.cname" autoComplete="on" placeholder="请填写部门名" ></el-input>
                </el-form-item>
                <el-form-item label="用户状态">
                    <el-select v-model="createdItem.state" clearable placeholder="请选择用户状态">
                        <el-option v-for="item in state" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="formVisible = false">取 消</el-button>
                <el-button type="warning" :loading="formSubmiting" @click="commitForm">确 定</el-button>
            </div>
        </el-dialog>
        <menu-manage ref="menuManage"> </menu-manage>
    </div>
</template>

<script>
import { confirmBox } from 'utils/message';                       //引入某个页面中的某个方法
import { isEmail, isPhone } from 'utils/validate';
import MenuManage from 'views/system/roleOperate/DeptMenuManage';
export default {
    components: {
        MenuManage,
    },
    data() {
         const validateCName = (rule, value, callback) => {              //判断编辑页面输入的信息是否有问题
            if (value=="") {
                callback(new Error('部门名不能为空'));
            } else {
                callback();
            }
        };
        return {
            tableQuery: {                      //设置页面跳转导航条的相关信息
                limit: 10,
                currentPage: 1,
                deptCode: null,
                cname: null,
                state: null
            },
            createdRules: {
                cname: [  //绑定具体name
                    { required: false, trigger: 'blur', validator: validateCName }
                ]
            },
            total: null,
            tableData: [],
            state: [                    //下拉框显示的值和value值
                {
                    label: '正常',
                    value: "Y"
                },
                {
                    label: '停用',
                    value: "N"
                }
            ],
            tableLoading: true,
            formTitle: null,
            formVisible: false,
            formSubmiting: false,
            createdItem: {
                deptCode: null,
                cname: null,
                state: null,
            },
            deletedForm: {
                deptCode: null
            },

        };
    },
    filters: {
        stateFilter(state) {
            const statusMap = {       //设置状态框框对应的颜色
                'Y': 'primary',      //  绿
                'N': 'danger',       //  红
            };
            return statusMap[state]
        },
        statusMap(state) {
            const statusMap = {
                'Y': '正常',        //设置状态的其他别名
                'N': '停用',
            };
            return statusMap[state]
        },
        roleNameMap(roleId) {
            const roleNameMap = {};
            this.roleList.forEach(function (role) {
                roleNameMap.push()
            })
        }
    },

    mounted() {

        this.getUserList();
    },
    methods: {
        deptMeunManage(id) {
            this.$refs.menuManage.getDeptMenu(id);
        },
        getUserList() {                    //初始加载页面数据和查询时加载的方法
            this.tableLoading = true;
            this.$api.system.department.list({         //加载了api里的一个页面中的数据
                data: this.tableQuery
            }).then(response => {
                this.total = response.data.data.total;
                this.tableData = response.data.data.list;
                this.tableLoading = false;
            }).catch(error => {
                this.tableLoading = false;
                console.log(error);
            });
        },
        handleFilter() {                       //查询使用方法
            this.getUserList();
        },
        handleReset() {
            this.tableQuery.limit = 10;
            this.tableQuery.currentPage = 1;
            this.tableQuery.deptCode = null;
            this.tableQuery.cname = null;
            this.tableQuery.state = null;
            this.formSubmiting = false;
            this.formVisible = false;
            this.getUserList();
        },
        handleSizeChange(val) {
            this.tableQuery.limit = val;
            this.getUserList();
        },
        handleCurrentChange(val) {
            this.tableQuery.currentPage = val;
            this.getUserList();
        },
        clearDialog() {            //清空值
            this.createdItem.deptCode = null;
            this.createdItem.cname = null;
            this.createdItem.state = null;

        },
        showDialog(type, row = null) {
            this.formVisible = true;
            this.clearDialog();
            if (type == 'create') {
                this.formTitle = "新建部门";           //修改或者新增的小窗口的标题
            } else {
                this.formTitle = "修改部门";
                //这里的createdItem里的数据大小格式也要和列表返回的一样，不然无法提交
                this.createdItem.deptCode = row.deptCode;   //这里row的数据和列表的数据大小格式一样
                this.createdItem.cname = row.cname;
                this.createdItem.state = row.state;
            }
        },
        commitForm() {                                //保存信息的方法
            this.formSubmiting = true;
            this.$api.system.department.save({
              data: this.createdItem
            }).then(response => {
                this.formSubmiting = false;
                this.formVisible = false;
                this.$notify({
                    title: '成功',
                    message: '保存成功',
                    type: 'success',
                    duration: 1500,
                });
                this.handleFilter();
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
        },            //这里的ID
        deleteRecord(DeptCode) {                                //删除信息的方法
            confirmBox('部门').then(() => {
                         //这里的deptCode要和列表返回的deptCode大小一致
                this.deletedForm.deptCode = DeptCode;//等于这里的ID
                this.$api.system.department.delete({
                    data: this.deletedForm
                }).then(response => {
                    this.$notify({
                        title: '成功',
                        message: '删除成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.handleFilter();
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

    },
};
</script>
