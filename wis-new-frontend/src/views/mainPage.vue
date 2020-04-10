<template>
    <div style="background-color: #eaedf0;height: 100%;">
        <el-row class="bg-purple-dark">
            <el-col :span="12" style="margin-top:10px;padding-left: 10px;"><b>消息通知</b></el-col>
            <el-col :span="12" style="text-align: right; padding-right: 10px;">
                <el-button @click="getMore" type="warning"><i class="el-icon-folder-add"></i> 更多</el-button>
            </el-col>
        </el-row>
        <div v-for="(item,index) in tableData" :key="item.sm_Code">
            <el-row v-if="index%3==0" style="padding:20px 20px 10px 40px">
                <el-col :span="8" class="bg-purple">
                    <div class="purple_list">
                        {{item.sm_CreateUserName}}
                        <div class="message-content">
                            {{item.sm_Content}}
                        </div>
                        <div class="message-more">
                            <el-button size="small" type="success" @click="lookOver(item.sm_Code,item.sm_URL,item.sm_BussinessCode)"><i class="el-icon-tickets"></i> 查看</el-button>
                            <el-button size="small"  type="success" @click="singRead(item.sm_Code,true)"><i class="el-icon-circle-check">已读</i></el-button>
                        </div>
                        <div class="message-date">{{item.sm_CreateTime}}</div>
                    </div>
                </el-col>
                <el-col :span="8" class="bg-purple" v-if="(index+1)<tableData.length">
                    <div class="purple_list">
                        {{tableData[index+1].sm_CreateUserName}}
                        <div class="message-content">
                            {{tableData[index+1].sm_Content}}
                        </div>
                        <div class="message-more">
                            <el-button size="small" type="success" @click="lookOver(tableData[index+1].sm_Code,tableData[index+1].sm_URL,tableData[index+1].sm_BussinessCode)"><i class="el-icon-tickets"></i> 查看</el-button>
                            <el-button size="small"  type="success" @click="singRead(tableData[index+1].sm_Code,true)"><i class="el-icon-circle-check">已读</i></el-button>
                        </div>
                        <div class="message-date">{{tableData[index+1].sm_CreateTime}}</div>
                    </div>
                </el-col>
                <el-col :span="8" class="bg-purple" v-if="(index+2)<tableData.length">
                    <div class="purple_list">
                        {{tableData[index+2].sm_CreateUserName}}
                        <div class="message-content">
                            {{tableData[index+2].sm_Content}}
                        </div>
                        <div class="message-more">
                            <el-button size="small" type="success" @click="lookOver(tableData[index+2].sm_Code,tableData[index+2].sm_URL,tableData[index+2].sm_BussinessCode)"><i class="el-icon-tickets"></i> 查看</el-button>
                            <el-button size="small"  type="success" @click="singRead(tableData[index+2].sm_Code,true)"><i class="el-icon-circle-check">已读</i></el-button>
                        </div>
                        <div class="message-date">{{tableData[index+2].sm_CreateTime}}</div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                tableData: [  //列表数据
                    //{'sm_CreateUser':'post','sm_Code':'123','sm_State':'N','sm_URL':'abc/cdef','sm_Content':'我一看 光阴故事，顿时这觉就醒了一半了','sm_CreateTime':'2019-07-28 12:34:50'},
                    //{'sm_CreateUser':'张三','sm_Code':'456','sm_State':'Y','sm_URL':'vvse/serv','sm_Content':'我哭笑不得的放下手机，却怎么也睡不着了','sm_CreateTime':'2019-07-28 12:34:50'},
                    //{'sm_CreateUser':'李四','sm_Code':'789','sm_State':'Y','sm_URL':'bjt/hrh','sm_Content':'于是去理发店修了修头发，又回寝室把皮鞋擦了擦','sm_CreateTime':'2019-07-28 12:34:50'},
                    //{'sm_CreateUser':'王五','sm_Code':'159','sm_State':'Y','sm_URL':'eges/bth','sm_Content':'我四周看了看这高档的装修','sm_CreateTime':'2019-07-28 12:34:50'},
                    //{'sm_CreateUser':'李六','sm_Code':'668','sm_State':'N','sm_URL':'hert/erssg','sm_Content':'姑娘也是一愣，轻声问了句“你是李耀？”','sm_CreateTime':'2019-07-28 12:34:50'}
                ],
                tableQuery:{
                    sm_Content:"",
                    sm_YoN:"N",
                    sm_CreateTimeStart:"",
                    sm_CreateTimeEnd:"",
                    limit: 12,
                    currentPage: 1,
                },
                read:{
                    sm_Code:null
                }
            }
        },
        methods:{
            getMore(){
                this.$router.push({path:'/message/message/message'});
            },
            lookOver(sm_code,messageUrl,bussinessCode){
                this.singRead(sm_code,false);
                let index=messageUrl.indexOf("/");
                let partPath = messageUrl.slice(0,index);
                let path = "/"+partPath+"/"+messageUrl;
                this.$router.push({path:path.substr(0,path.length-4),query: { bussinessCode:bussinessCode}});
            },
            singRead(code,prompt){
                this.read.sm_Code = code;
                this.$api.message.message.read({
                    data: this.read
                }).then(response => {
                    let succeed = response.data.data.Succeed;
                    if(prompt){
                        if(succeed=="Succees"){
                            this.getTableData();
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
            },
            getTableData(){
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
        },
        mounted() {
            this.getTableData();
        }
    }
</script>

<style>
    body,html,#app,.app-wrapper,.main-container,.app-main{
        height: auto;
    }

    .message-content{
        padding: 20px 0;
        font-size: 15px;
        width: 100%;
        text-indent: 2em;
    }
    .message-more{
        width: 100%;
        text-align: center;
    }
    .message-date{
        width: 100%;
        text-align: right;
        padding-top: 20px;
    }
</style>
