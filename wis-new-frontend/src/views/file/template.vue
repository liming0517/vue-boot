<template>
     <div class="components-container">
         <div class="components-container">
            <div class="filter-container">
                <el-button @click="getApplyList" type="gray"><i class="el-icon-refresh"></i>刷新</el-button>
                <el-button @click="openCreateDialog('create')" type="gray"><i class="el-icon-plus"></i>新建</el-button>
                <el-button @click="openCreateDialog('edit')" type="gray"><i class="el-icon-edit"></i>修改</el-button>
                <el-button @click="queryForm = true" type="gray"><i class="el-icon-search"></i>查询</el-button>
            </div>
            <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中"
                stripe border fit highlight-current-row style="width: 100%" @row-click="rowClick">
                <el-table-column v-if="tableColumShow" label="模板编号" prop="sft_Code" align="center" sortable ></el-table-column>
                <el-table-column label="申请类型" prop="sft_TemplateType" align="center"></el-table-column>
                <el-table-column label="状态" prop="sft_State" align="center" width="250px">
                    <template slot-scope="scope">
                        <span>{{scope.row.sft_State | stateMap}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" align="center" width="200px">
                    <template slot-scope="scope">
                        <el-button size="small" v-if="scope.row.sft_State=='0'" @click="changeState(scope.row)" type="success">
                            <i class="el-icon-circle-check">启用</i>
                        </el-button>
                        <el-button size="small" v-if="scope.row.sft_State=='1'" @click="changeState(scope.row)" type="danger">
                            <i class="el-icon-circle-close">禁用</i>
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
                    <el-form-item label="申请类型">
                        <el-input name="sft_TemplateType" v-model="tableQuery.sft_TemplateType" autoComplete="on" maxlength="50"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="tableQuery.sft_State" placeholder="请选择" style="width: 100%">
                            <el-option v-for="item in stateArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="queryForm = false">取 消</el-button>
                    <el-button type="warning" :loading="querySubmiting" @click="getApplyList">确 定</el-button>
                </div>
            </el-dialog>
            <el-dialog :title="formTitle" :visible.sync="createForm" :before-close="closePreview">
                <el-form class="small-space" :model="tableCreate" label-position="left" label-width="100px" style='width: 100%; margin-left:0px;'>
                    <el-form-item label="申请类型" :required=true>
                        <el-input name="sft_TemplateType" v-model="tableCreate.sft_TemplateType" autoComplete="on" maxlength="50"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="tableCreate.sft_State" placeholder="请选择" style="width: 100%">
                            <el-option v-for="item in stateArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <div v-if="tableCreate.sft_Code != null && tableCreate.sft_State=='1'">
                        <el-form :model="form">
                          <el-form-item label="上传文件" :label-width="formLabelWidth">
                            <el-upload
                              ref="uploadExcel"
                              :action="uploadPath"
                              :limit=limitNum
                              :auto-upload="true"
                              :before-upload="beforeUploadFile"
                              :on-change="fileChange"
                              :on-exceed="exceedFile"
                              :on-success="handleSuccess"
                              :on-error="handleError"
                              :on-preview="previewFile"
                              :on-remove="removeFile"
                              :before-remove="removeAlert"
                              :data="otherData"
                              :with-credentials='true'
                              accept=".doc,.docx,.xls,.xlsx,.pdf"
                              :file-list="fileArr">
                              <el-button size="small" type="success">选择文件</el-button>
                              <span style="color:red"> *只能上传格式为doc、docx、xls、xlsx、pdf 的文件</span>
                            </el-upload>
                          </el-form-item>
                          <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;</span>
                          <el-button size="small" type="info" @click="download()">下载</el-button>
                          <el-button size="small" type="warning" @click="previewPdf()">预览</el-button>
                        </el-form>
                        <div v-if="isPreview">
                            <iframe id='previewPdf' :src="pdfUrl" height="560" width="100%"></iframe>
                        </div>
                      </div>
                    <div v-else>
                        <el-form-item label="上传文件">
                            <el-button size="small" type="success" disabled>选择文件</el-button><span style="color: red;"> * 保存或启用后才能上传文件</span>
                        </el-form-item>
                    </div>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="createForm = false;isPreview = false;">取 消</el-button>
                    <el-button type="warning" :loading="querySubmiting" @click="saveCreate">确 定</el-button>
                </div>
            </el-dialog>
         </div>
    </div>
</template>

<script>
import { confirmBox } from 'utils/message';
import { isJson } from 'utils';

export default {
    data() {
        return {
            formTitle:'新建',
            createForm:false,
            tableColumShow:false,
            tableQuery: {   //查询窗口数据
                limit: 10,
                currentPage: 1,
                sft_TemplateType: null,
                sft_State: null
            },
            tableCreate: {  //编辑窗口数据
                sft_Code: null,
                sft_TemplateType: null,
                sft_State: null,
            },
            stateArr: [ //状态下拉框
                {value: '0', label: '禁用'},
                {value: '1', label: '启用'}
            ],
            querySubmiting:false,
            queryForm:false,
            total: 0,
            tableData: [],  //列表数据
            tableLoading: false,
            stateChange: {  //查询窗口数据
                sft_Code: null,
                sft_State: null
            },
            getFfile:{
               sf_BussinessCode:null
            },
            fileList:[],
            //文件上传下载参数
            limitNum: 20,
            formLabelWidth: '80px',
            form: {
              file: ''
            },
            fileID:'',
            pdfUrl:'',
            otherData:{fileID:null},
            isPreview:false,
            uploadPath:this.HOST_PROXY_ADMIN+'/file/file/upload',
            fileArr:[],
            deleteData:{
                sf_Code:null,
            }
        }
    },
    methods: {
        openCreateDialog(action){
            if(action=="edit"){
                if(this.tableCreate.sft_Code==null){
                    this.$notify({
                        title: '提示',
                        message:"请选择一条数据！",
                        type: 'warning',
                        duration: 2000,
                    });
                    return;
                }
                this.getFileArr();
                this.createForm = true;
            }else{
                this.clearCreateDialog();  //清除窗口里的数据
                this.createForm = true;
            }
        },
        getFileArr(){
            this.getFfile.sf_BussinessCode = this.tableCreate.sft_Code;
            this.$api.file.template.filelist1({
                data:this.getFfile
            }).then(response => {
                this.fileList = response.data.data.Filelist;
                this.getFileList();
            }).catch(error => {
                console.log(error);
            });
        },
        clearCreateDialog(){
            this.tableCreate.sft_Code = null;
            this.tableCreate.sft_TemplateType = null;
            this.tableCreate.sft_State = null;
        },
        changeState(row){
            if(row.sft_State=="0"){
                row.sft_State="1";
            }else if(row.sft_State=="1"){
                row.sft_State="0";
            }
            this.$api.file.template.save({
                data: row
            }).then(response => {
                let succeed = response.data.data.Succeed;
                if(succeed=="Succees"){
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
                console.log(error);
            });
        },
        saveCreate(){
             this.$api.file.template.save({
                 data: this.tableCreate
             }).then(response => {
                let succeed = response.data.data.Succeed;
                 if(succeed=="Succees"){
                     this.createForm = false;
                     this.$notify({
                         title: '提示',
                         message:"保存成功！",
                         type: 'success',
                         duration: 2000,
                     });
                     this.getApplyList();
                 }else{
                    this.$notify({
                        title: '提示',
                        message:"保存失败！",
                        type: 'error',
                        duration: 2000,
                    });
                 }
             }).catch(error => {
                 this.$notify({
                     title: '提示',
                     message:"保存失败！",
                     type: 'error',
                     duration: 2000,
                 });
                 console.log(error);
             });
        },
        getApplyList(){
            this.tableLoading = true;
            this.$api.file.template.list({
                data: this.tableQuery
            }).then(response => {
                this.total = response.data.data.total;
                this.tableData = response.data.data.list;
                this.queryForm = false;
                this.tableLoading = false;
                this.clearCreateDialog();
            }).catch(error => {
                this.tableLoading = false;
                console.log(error);
            });
        },
        rowClick(row){
            this.tableCreate.sft_Code = row.sft_Code;
            this.tableCreate.sft_TemplateType = row.sft_TemplateType;
            this.tableCreate.sft_State = row.sft_State;
        },
        handleCurrentChange(val) {
            this.tableQuery.currentPage = val;
            this.getApplyList();
        },
        handleSizeChange(val) {
            this.tableQuery.limit = val;
            this.getApplyList();
        },
        // 文件超出个数限制时的钩子
        exceedFile(files, fileList) {
          this.$notify.warning({
            title: '警告',
            message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`
          });
        },
        // 文件状态改变时的钩子
        fileChange(file, fileList) {
          this.form.file = file.raw;

        },
        // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
        /* beforeUploadFile(file) {
            this.otherData.fileID = this.tableCreate.sft_Code;
        }, */
        beforeUploadFile(file) {
            let name = file.name;
            let type = name.substring(name.lastIndexOf('.'),name.length);
            if(type==".doc"||type==".pdf"||type==".docx"||type==".xls"||type==".xlsx"){
                    this.otherData.fileID = this.tableCreate.sft_Code;
                    this.$parent.$parent.$parent.hideOrShow(); //打开遮罩层
            }else{
                file['create'] = 'true';
                return false;
            }
        },
        // 文件上传成功时的钩子
        handleSuccess(res, file, fileList) {
          this.$notify.success({
            title: '成功',
            message: `文件上传成功`
          });
          this.getFileArr(); //刷新附件列表
          this.$parent.$parent.$parent.hideOrShow(); //关闭遮罩层
        },
        // 文件上传失败时的钩子
        handleError(err, file, fileList) {
          this.$notify.error({
            title: '错误',
            message: `文件上传失败`
          });
        },
        uploadFile() {
           this.$refs.uploadExcel.submit()
        },
        download(){
            if(this.fileID==null || this.fileID==""){
                this.$notify({
                    title: '提示',
                    message:"请选择一个文件！",
                    type: 'warning',
                    duration: 2000,
                });
                return;
            }
            var blob = new Blob([],{type:'application/excel;charset=utf-8'})
            var downloadElement = document.createElement('a')
            var href = window.URL.createObjectURL(blob) //创建下载链接
           // let sendParams = Base64.encode(JSON.stringify('传递的参数')) // 可以省略  具体看业务需求
            downloadElement.href = this.HOST_PROXY_ADMIN+'/file/file/download?id='+this.fileID //'接口地址' 此处可以通过拼接字符串的方式传递参数
            document.body.appendChild(downloadElement)
            downloadElement.click() // 点击下载
            document.body.removeChild(downloadElement) // 下载完成之后移除元素
           // window.URL.removeObjectURL(href) // 释放掉blob对象
           // this.URL.removeObjectURL(href) // 释放掉blob对象
        },
        getFileList(){
            this.fileArr = [];
            setTimeout(() =>{
                for(var i = 0;i<this.fileList.length;i++){
                    let file={id:"",name:""};
                    file.id=this.fileList[i].sf_Code;
                    file.name=this.fileList[i].sf_FileName;
                    this.fileArr.push(file)
                }
            },1000);
        },
        previewFile:function(file){
            this.fileID=file.id;
        },
        closePreview(){
            this.fileID = null;
            this.isPreview = false;
            this.createForm = false;
        },
        previewPdf(){
            if(this.fileID==null || this.fileID==""){
                this.$notify({
                    title: '提示',
                    message:"请选择一个文件！",
                    type: 'warning',
                    duration: 2000,
                });
                return;
            }
            this.isPreview = true;
            //执行预览代码
            let url=this.HOST_PROXY_ADMIN+'/file/file/preview?fileID='+this.fileID
            this.pdfUrl=this.FONT_PROXY_PATH+"/static/pdf/web/viewer.html?url_file="+url
        },
        removeFile:function(file, fileList){
            this.deleteData.sf_Code = file.id;
            this.$api.file.file.delete({
              data: this.deleteData
            }).then(response => {
                let succeed = response.data.data.Succeed;
                if(succeed=="Succees"){
                    this.$notify({
                        title: '成功',
                        message: '删除成功',
                        type: 'success',
                        duration: 1500,
                    });
                }else{
                    this.$notify({
                        title: '失败',
                        message: '删除失败',
                        duration: 1500,
                    });
                }
            }).catch(error => {
                this.$notify({
                    title: '错误',
                    message: error,
                    type: 'error',
                    duration: 3000,
                });
                console.log(error);
            });
        },
        removeAlert:function(file, fileList){
            if(file.raw!=undefined && file.raw.create != undefined){  //文件格式不符，取消上传后后会对调此方法删除方法
                this.$notify({
                    title: '上传失败',
                    message: '不支持该文件格式！',
                    type: 'error',
                    duration: 3000,
                });
                this.fileArr = fileList.filter(function(element, index) {
                    return element['uid'] != file.uid;
                });
                return false;
            }
            return confirmBox(file.name).then(() => { })
        }
        /* removeAlert:function(file, fileList){
            return confirmBox(file.name).then(() => { })
        } */
    },

    filters: {
        stateMap(state) {
            const stateMap = {
                '0':'禁用',
                '1':'启用',
            };
            return stateMap[state]
        },
    },
    mounted() {
        this.getApplyList();
    },
}
</script>

<style>
</style>
