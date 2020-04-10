<template>
    <div class='tabs-view-container'>
        <router-link class="tabs-view" v-for="tag in Array.from(visitedViews)" :to="tag.path" :key="tag.path">
            <el-tag :closable="true" :type="isActive(tag.path)"  @close='closeViewTabs(tag,$event)'>
                {{tag.name}}
            </el-tag>
        </router-link>
    </div>
</template>

<script>
export default {
    computed: {
        visitedViews() {
            return this.$store.state.app.visitedViews.slice(-6)
        }
    },
    watch: {
        $route() {
            this.addViewTabs()
        }
    },
    methods: {
        closeViewTabs(view, $event) {
            this.$store.dispatch('delVisitedViews', view)
            $event.preventDefault()
            var tabs = Array.from(this.visitedViews);
            if(tabs.length>0){
                this.$router.push({path:tabs[tabs.length-1].path});
            }else{
                this.$router.push({path:'/'});
            }
        },
        generateRoute() {
            let index = -1;
            // 找到最后一个带有名字的路由
            this.$route.matched.forEach((item, arrIndex) => {
                let itemPath = item.path;
                if(itemPath.indexOf("?") == -1){  //防止多次点击时重复添加参数字符串
                    let query = this.$route.query;
                    let paramPath = "";
                    for (var val in query) {
                        paramPath += "&"+val+"="+query[val];
                    }
                    if(paramPath.length>0){
                        paramPath = paramPath.substring(1, paramPath.length);
                        item.path += "?"+paramPath;
                    }
                }
                if (item.name){
                    index = arrIndex;
                }
            });
            if (index == -1) return false;
            return this.$route.matched[index];
        },
        addViewTabs() {
            let route = this.generateRoute();

               // console.log("======");
              //  console.log(route);
            if (route) {
                this.$store.dispatch('addVisitedViews', route);
            }
        },
        isActive(path) {
            return path === this.$route.path ? '' : 'info';
        },
    },
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.tabs-view-container {
    display: inline-block;
    vertical-align: top;
    margin-left: 10px;
    .tabs-view {
        margin-left: 10px;
    }
}
</style>
