webpackJsonp([25],{791:function(e,t,i){function n(e){i(854)}var o=i(17)(i(839),i(862),n,"data-v-eb27b20c",null);e.exports=o.exports},839:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{roleId:null,resourceLoading:!1,resourceVisible:!1,formResourceSubmiting:!1,resourceData:[],checkedResourceIds:[],resourceActiveName:"0",resourceActiveIndex:0}},methods:{getRoleResource:function(e){var t=this;this.resourceVisible=!0,this.resourceLoading=!0,this.roleId=e,this.$api.system.role.getResource({data:{id:this.roleId}}).then(function(e){t.resourceData=e.data.data.list,t.checkedResourceIds=e.data.data.checked.map(function(e){return parseInt(e)}),t.resourceLoading=!1}).catch(function(e){t.resourceLoading=!1,console.log(e)})},commitResourceForm:function(){var e=this;this.formResourceSubmiting=!0,this.$api.system.role.savePrivilege({data:{roleId:this.roleId,resourceType:1,resourceIds:this.checkedResourceIds}}).then(function(t){e.formResourceSubmiting=!1,e.resourceVisible=!1,e.$notify({title:"成功",message:"保存成功",type:"success",duration:1500}),console.log(t)}).catch(function(t){e.formResourceSubmiting=!1,e.$notify({title:"错误",message:t,type:"error",duration:3e3}),console.log(t)})}}}},849:function(e,t,i){t=e.exports=i(787)(!0),t.push([e.i,".panel-menu-item[data-v-eb27b20c]{width:90%;max-height:60px;border-radius:5px;display:inline-block;margin:10px 10px 10px 0}.panel-menu-item-title[data-v-eb27b20c]{float:left;padding:5px;margin-right:10px;border-right:1px solid #ec670b}.el-checkbox-group[data-v-eb27b20c]{display:inline;margin:5px}","",{version:3,sources:["F:/wiserun_newFrame/前端源代码/wis-new-frontend/src/views/system/roleOperate/ActionManage.vue"],names:[],mappings:"AACA,kCACI,UAAW,AACX,gBAAiB,AACjB,kBAAmB,AACnB,qBAAsB,AACtB,uBAA2B,CAC9B,AACD,wCACI,WAAY,AACZ,YAAa,AACb,kBAAmB,AACnB,8BAAiC,CACpC,AACD,oCACI,eAAgB,AAChB,UAAY,CACf",file:"ActionManage.vue",sourcesContent:["\n.panel-menu-item[data-v-eb27b20c] {\n    width: 90%;\n    max-height: 60px;\n    border-radius: 5px;\n    display: inline-block;\n    margin: 10px 10px 10px 0px;\n}\n.panel-menu-item-title[data-v-eb27b20c] {\n    float: left;\n    padding: 5px;\n    margin-right: 10px;\n    border-right: 1px solid #ec670b ;\n}\n.el-checkbox-group[data-v-eb27b20c] {\n    display: inline;\n    margin: 5px;\n}\n"],sourceRoot:""}])},854:function(e,t,i){var n=i(849);"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);i(788)("dc65ae68",n,!0)},862:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-dialog",{directives:[{name:"loading",rawName:"v-loading.body",value:e.resourceLoading,expression:"resourceLoading",modifiers:{body:!0}}],attrs:{title:"角色资源管理",visible:e.resourceVisible,"element-loading-text":"玩命加载中"},on:{"update:visible":function(t){e.resourceVisible=t}}},[i("el-tabs",{model:{value:e.resourceActiveName,callback:function(t){e.resourceActiveName=t},expression:"resourceActiveName"}},e._l(e.resourceData,function(t,n){return i("el-tab-pane",{key:n,attrs:{label:t.title,name:String(n)}},e._l(t.children,function(t,n){return i("div",{key:n,staticClass:"panel-menu-item"},[i("span",{staticClass:"panel-menu-item-title"},[e._v(e._s(t.title))]),e._v(" "),i("el-checkbox-group",{model:{value:e.checkedResourceIds,callback:function(t){e.checkedResourceIds=t},expression:"checkedResourceIds"}},e._l(t.children,function(t){return i("el-checkbox",{key:t.id,attrs:{label:t.id}},[e._v(e._s(t.url))])}),1)],1)}),0)}),1),e._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(t){e.resourceVisible=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"warning",loading:e.formResourceSubmiting},on:{click:e.commitResourceForm}},[e._v("确 定")])],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=25.be9e44240408bcad91fd.js.map