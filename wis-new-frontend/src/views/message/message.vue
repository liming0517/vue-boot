<template>
     <div class="components-container">
        <div class="filter-container">
            <el-button @click="getApplyList" type="gray"><i class="el-icon-refresh"></i>刷新</el-button>
            <el-button @click="queryForm = true" type="gray"><i class="el-icon-search"></i>查询</el-button>
        </div>
        <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column v-if="tableColumShow" label="通知详情" prop="sm_URL" align="center" sortable ></el-table-column>
            <el-table-column label="通知内容" prop="sm_Content" align="center"></el-table-column>
            <el-table-column label="通知日期" prop="sm_CreateTime" align="center" width="250px"></el-table-column>
            <el-table-column label="操作" align="center" width="250px">
                <template slot-scope="scope">
                    <el-button size="small" @click="lookOver(scope.row.sm_Code,scope.row.sm_URL,scope.row.sm_BussinessCode)" type="success"><i class="el-icon-tickets"></i> 查看</el-button>
                    <el-button size="small" @click="markRead(scope.row.sm_Code,scope.row.sm_YoN,true)" type="success">
                        <i class="el-icon-circle-check" v-if="scope.row.sm_YoN=='N'">标为已读</i>
                        <i class="el-icon-circle-close" v-else-if="scope.row.sm_YoN=='Y'">标为未读</i>
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-container">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="tableQuery.currentPage"
                :page-sizes="[10, 20, 50]" :page-size="tableQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
        <el-dialog :title="'查询'" :visible.sync="queryForm">
            <el-form class="small-space" :model="tableQuery" label-position="left" label-width="100px" style='width: 100%; margin-left:0px;'>
                <el-form-item label="通知内容">
                    <el-input name="RA_Code" v-model="tableQuery.sm_Content" autoComplete="on" maxlength="200"></el-input>
                </el-form-item>
                <el-form-item label="是否已读">
                    <el-select v-model="tableQuery.sm_YoN" placeholder="请选择" style="width: 100%">
                        <el-option v-for="item in stateArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="通知日期">
                    <div class="block">
                        <el-date-picker
                          v-model="tableQuery.sm_CreateTimeStart"
                          name="RA_CreateTimeStart"
                          align="right"
                          value-format="yyyy-MM-dd hh:mm:ss"
                          type="datetime"
                          placeholder="开始时间">
                        </el-date-picker> ---
                        <el-date-picker
                          v-model="tableQuery.sm_CreateTimeEnd"
                          name="RA_CreateTimeEnd"
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
    </div>
</template>

<script>
import { confirmBox } from 'utils/message';
export default {
    data() {
        return {
            tableColumShow:false,
            tableQuery: {  //查询窗口数据
                limit: 10,
                currentPage: 1,
                sm_Content: null,
                sm_YoN: null,
                sm_CreateTimeStart: null,
                sm_CreateTimeEnd: null,
            },
            stateArr: [ //状态下拉框
                {value: 'Y', label: '已读'},
                {value: 'N', label: '未读'}
            ],
            querySubmiting:false,
            queryForm:false,
            total: 0,
            tableData: [  //列表数据
                //{'sm_Code':'123','sm_State':'N','sm_URL':'abc/cdef','sm_Content':'我一看 光阴故事，顿时这觉就醒了一半了','sm_CreateTime':'2019-07-28 12:34:50'},
                //{'sm_Code':'456','sm_State':'Y','sm_URL':'vvse/serv','sm_Content':'我哭笑不得的放下手机，却怎么也睡不着了','sm_CreateTime':'2019-07-28 12:34:50'},
                //{'sm_Code':'789','sm_State':'Y','sm_URL':'bjt/hrh','sm_Content':'于是去理发店修了修头发，又回寝室把皮鞋擦了擦','sm_CreateTime':'2019-07-28 12:34:50'},
                //{'sm_Code':'159','sm_State':'Y','sm_URL':'eges/bth','sm_Content':'我四周看了看这高档的装修','sm_CreateTime':'2019-07-28 12:34:50'},
                //{'sm_Code':'668','sm_State':'N','sm_URL':'hert/erssg','sm_Content':'姑娘也是一愣，轻声问了句“你是李耀？”','sm_CreateTime':'2019-07-28 12:34:50'}
            ],
            tableLoading: false,
            read:{
                sm_Code:null
            }
        }
    },
    methods: {
        lookOver(sm_Code,messageUrl,bussinessCode){
            this.markRead(sm_Code,'N',false);
            let index=messageUrl.indexOf("/");
            let partPath = messageUrl.slice(0,index);
            let path = "/"+partPath+"/"+messageUrl;
            //alert(path.substr(0,path.length-4)+"?"+bussinessCode);
            this.$router.push({path:path.substr(0,path.length-4),query: { bussinessCode:bussinessCode}});
        },
        getApplyList(){
            if(this.tableQuery.sm_CreateTimeStart>this.tableQuery.sm_CreateTimeEnd){
                this.$notify({
                    title: '提示',
                    message:"开始时间不能晚于结束时间！",
                    type: 'warning',
                    duration: 2000,
                });
                return;
            }
            this.tableLoading = true;
            this.$api.message.message.list({
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
        markRead(sm_Code,sm_YoN,prompt){
            this.read.sm_Code = sm_Code;
            if(sm_YoN=='N'){
                this.$api.message.message.read({
                    data: this.read
                }).then(response => {
                    let succeed = response.data.data.Succeed;
                    if(prompt){
                        if(succeed=="Succees"){
                            this.getApplyList();
                            this.$notify({
                                title: '提示',
                                message:"修改成功！",
                                type: 'success',
                                duration: 2000,
                            });
                        }else{
                           this.$notify({
                               title: '提示',
                               message:"修改失败！",
                               type: 'error',
                               duration: 2000,
                           });
                        }
                    }
                }).catch(error => {
                    if(prompt){
                        this.$notify({
                           title: '提示',
                           message:"修改失败！",
                           type: 'error',
                           duration: 2000,
                       });
                   }
                });
            }else if(sm_YoN=='Y'){
                this.$api.message.message.unread({
                    data: this.read
                }).then(response => {
                    let succeed = response.data.data.Succeed;
                    if(succeed=="Succees"){
                        this.getApplyList();
                        this.$notify({
                            title: '提示',
                            message:"修改成功！",
                            type: 'success',
                            duration: 2000,
                        });
                    }else{
                       this.$notify({
                           title: '提示',
                           message:"修改失败！",
                           type: 'error',
                           duration: 2000,
                       });
                    }
                }).catch(error => {
                    this.$notify({
                       title: '提示',
                       message:"修改失败！",
                       type: 'error',
                       duration: 2000,
                   });
                });
            }
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
    },

}
</script>

<style>
</style>
