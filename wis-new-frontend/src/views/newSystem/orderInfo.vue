<template>
  <div class="components-container">
    <div class="filter-container">
        <form id="searchForm">
            <el-input v-model="tableQuery.orderIdSch" style="width: 200px;" placeholder="订单号"></el-input>
            <el-input v-model="tableQuery.mobileSch" style="width: 200px;" placeholder="手机号"></el-input>
            <el-input v-model="tableQuery.addressSch" style="width: 200px;" placeholder="地址"></el-input>
            <el-button style="margin-left: 10px;" @click="searchData" type="gray"><i class="el-icon-search"></i></el-button>
        </form>
    </div>
    <tree-table :tableData="tableData" @update="updateDialog" @delete="deleteRecord"/>
    <el-dialog :title="formTitle" :visible.sync="formVisible">
        <el-form class="small-space" :model="createdItem" label-position="left" label-width="100px" style="width: 100%;">
            <el-form-item label="手机号" :required="true">
                <el-input v-model="createdItem.mobile" maxlength="50"/>
            </el-form-item>
            <el-form-item label="城市名" :required="true">
                <el-input v-model="createdItem.cityName" maxlength="20"/>
            </el-form-item>
            <el-form-item label="地址" :required="true">
                <el-input v-model="createdItem.address" maxlength="100"/>
            </el-form-item>
            <el-form-item label="下单时间" :required="true">
                <el-input v-model="createdItem.createDate" maxlength="10"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="formVisible = false">取 消</el-button>
            <el-button type="warning" :loading="formSubmiting" @click="commitForm">确 定</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
import TreeTable from 'views/newSystem/tableTree/orderInfoTree';
import { confirmBox } from 'utils/message';
export default {
    components: {
        TreeTable,
    },
  data() {
    return {
      formTitle:"修改数据",
      formVisible:false,
      total:0,
      tableData: [],
      tableLoading: false,
      formSubmiting: false,
      tableQuery: {
          orderIdSch: null,
          mobileSch: null,
          addressSch: null
      },
      createdItem:{
          orderId:null,
          mobile: null,
          cityName: null,
          address: null,
          createDate:null,
      },
      deleteRow:{
          orderId: null
      }
    };
  },
  methods: {
    searchData(){
        this.loadData();
    },
    updateDialog(row){
        this.createdItem.orderId = row.orderId;
        this.createdItem.mobile = row.mobile;
        this.createdItem.cityName = row.cityName;
        this.createdItem.address = row.address;
        this.createdItem.createDate = row.createDate;
        this.formVisible = true;
    },
    commitForm() {
        if(this.createdItem.mobile==""){
            this.$notify({
                title: '提示',
                message: '请输入手机号！',
                type: 'warning',
                duration: 3000,
            });
            return;
        }
        if(this.createdItem.cityName==""){
            this.$notify({
                title: '提示',
                message: '请输入城市名！',
                type: 'warning',
                duration: 3000,
            });
            return;
        }
        if(this.createdItem.address==""){
            this.$notify({
                title: '提示',
                message: '请输入地址！',
                type: 'warning',
                duration: 3000,
            });
            return;
        }
        if(this.createdItem.createDate==""){
            this.$notify({
                title: '提示',
                message: '请输入下单时间！',
                type: 'warning',
                duration: 3000,
            });
            return;
        }
        this.formSubmiting = true;
        this.$api.newSystem.orderInfo.updateRow({
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
            this.loadData();
        }).catch(error => {
            this.formSubmiting = false;
            this.$notify({
                title: '错误',
                message: '保存失败',
                type: 'error',
                duration: 3000,
            });
            console.log(error);
        });
    },
    deleteRecord(orderId){
        confirmBox('此条数据').then(() => {
            this.deleteRow.orderId = orderId;
            this.$api.newSystem.orderInfo.deleteRow({
                data: this.deleteRow
            }).then(response => {
                if(response.data.success){
                    this.$notify({
                        title: '成功',
                        message: '删除成功',
                        type: 'success',
                        duration: 1500,
                    });
                }
                this.loadData();
            }).catch(error => {
                this.$notify({
                    title: '错误',
                    message: '删除失败',
                    type: 'error',
                    duration: 3000,
                });
                console.log(error);
            });
        });
    },
    loadData(){
        this.tableLoading = true;
        this.$api.newSystem.orderInfo.search({
            data: this.tableQuery
        }).then(response => {
            if(response.data.success){
                this.total = response.data.total;
                this.tableData = response.data.rows;
            }
            this.tableLoading = false;
        }).catch(error => {
            this.tableLoading = false;
            console.log(error);
        });
    }
  },
  mounted() {
      this.loadData();
  }
};
</script>
