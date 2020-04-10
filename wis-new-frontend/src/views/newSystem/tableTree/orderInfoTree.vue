<template>
    <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" border fit highlight-current-row row-key="orderId">
        <el-table-column label="订单编号" align="left">
            <template slot-scope="scope">
                <!-- <span class="tree-indent" v-for="index in scope.row.level" :key="index"></span> -->
                {{scope.row.orderId}}
            </template>
        </el-table-column>
        <el-table-column label="手机号" prop="mobile" align="center"></el-table-column>
        <el-table-column label="城市名" prop="cityName" align="center"></el-table-column>
        <el-table-column label="地址" prop="address" align="center"></el-table-column>
        <el-table-column label="下单时间" prop="createDate" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="500px">
            <template slot-scope="scope">
                <el-button size="small" @click="emitRoleUpdate(scope.row)" type="gray" icon="edit">修改</el-button>
                <el-button size="small" @click="emitRoleDelete(scope.row.orderId)" type="danger" icon="delete">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
export default {
    name: 'TreeTable',
    props: {
        tableData: {
            type: Array,
            default: () => null,
        },
    },
    computed: {
        tableLoading() {
            if (this.tableData == null) {
                return true;
            }
            return false;
        }
    },
    methods: {
        expandTable(id) {
            let indexList = this.tableData.map(item => item.id);
            let index = indexList.indexOf(id);

            this.tableData[index].isExpand = true;
            if (index + 1 == indexList.length) {
                this.tableData[index].nextId = -1;
            } else {
                this.tableData[index].nextId = this.tableData[index + 1].id;
            }

            this.tableData.splice(index + 1, 0, ...this.tableData[index].children);
        },
        foldTable(id) {
            let indexList = this.tableData.map(item => item.id);
            let index = indexList.indexOf(id);

            this.tableData[index].isExpand = false;
            if (this.tableData[index].nextId == -1) {
                this.tableData.splice(index + 1);
            } else {
                let nextIndex = indexList.indexOf(this.tableData[index].nextId);
                this.tableData.splice(index + 1, nextIndex - index - 1);
            }
        },
        emitRoleUpdate(row) {
            this.$emit('update', row);
        },
        emitRoleDelete(id) {
            this.$emit('delete', id);
        }
    }
}
</script>

<style>
.tree-indent {
    display: inline-block;
    width: 16px;
    height: 25px;
    vertical-align: top;
    overflow: hidden;
}

.expand-icon {
    position: relative;
    cursor: pointer;
    color: #666;
    transition: transform .2s ease-in-out;
    width: 16px;
    height: 25px;
}

.el-table__expanded-cell {
    padding: 0px 0px 0px 49px !important;
}
</style>
