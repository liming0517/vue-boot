<template>
    <div class="components-container">
        <div class="filter-container">
            <form id="searchForm">
                <el-input v-model="tableQuery.roleNameSch" style="width: 200px;" placeholder="角色名"></el-input>
                <el-input v-model="tableQuery.roleTypeSch" style="width: 200px;" placeholder="角色类型"></el-input>
                <el-button style="margin-left: 10px;" @click="searchData" type="gray"><i class="el-icon-search"></i></el-button>
            </form>
        </div>
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
            tableLoading:false,
            tableData:[],
            tableQuery: {
                roleNameSch: null,
                roleTypeSch: null
            },
        }
    },
    methods:{
        loadData(){
            this.tableLoading = true;
            this.$api.newSystem.resource.search({
                data: this.tableQuery
            }).then(response => {
                console.log(response.data);
                if(response.data.success){
                    this.total = response.data.total;
                    this.tableData = response.data.rows;
                }
                this.tableLoading = false;
            }).catch(error => {
                this.tableLoading = false;
                console.log(error);
            });
        },
        searchData(){

        },
  },
  mounted() {
      this.loadData();
  }
}

</script>

<style>
</style>
