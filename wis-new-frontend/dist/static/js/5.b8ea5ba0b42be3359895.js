webpackJsonp([5],{816:function(e,t,n){var i=n(17)(n(894),n(958),null,null,null);e.exports=i.exports},823:function(e,t,n){"use strict";function i(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"记录",t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"永久删除";return new l.a(function(i,a){r.MessageBox.confirm("此操作将"+t+e+", 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){i()}).catch(function(){n.i(r.Message)({type:"info",message:"已取消此操作"})})})}t.a=i;var a=n(82),l=n.n(a),r=n(111);n.n(r)},828:function(e,t,n){e.exports={default:n(844),__esModule:!0}},835:function(e,t,n){function i(e){n(850)}var a=n(17)(n(837),n(856),i,null,null);e.exports=a.exports},837:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n(828),a=n.n(i),l=n(826),r=n.n(l),o=n(868),s=(n.n(o),n(864)),c=(n.n(s),n(869)),u=(n.n(c),n(870)),d=(n.n(u),n(871)),m=(n.n(d),n(867)),f=(n.n(m),n(866)),p=(n.n(f),n(865));n.n(p);n(872),t.default={name:"jsonEditor",data:function(){return{jsonEditor:!1}},props:["value"],watch:{value:function(e){e!==this.jsonEditor.getValue()&&this.jsonEditor.setValue(a()(this.value,null,2))}},mounted:function(){var e=this;this.jsonEditor=r.a.fromTextArea(this.$refs.textarea,{mode:"application/json",lineNumbers:!0,matchBrackets:!0,fullScreen:!0,gutters:["CodeMirror-lint-markers"],foldGutter:!0,theme:"mdn-like",lint:!0}),this.jsonEditor.setValue(a()(this.value,null,2)),this.jsonEditor.on("change",function(t){e.$emit("changed",t.getValue()),e.$emit("input",t.getValue())})},methods:{getValue:function(){return this.jsonEditor.getValue()}}}},844:function(e,t,n){var i=n(19),a=i.JSON||(i.JSON={stringify:JSON.stringify});e.exports=function(e){return a.stringify.apply(a,arguments)}},845:function(e,t,n){t=e.exports=n(787)(!0),t.push([e.i,".CodeMirror{height:100%}.json-editor .cm-s-rubyblue span.cm-string{color:#f08047}","",{version:3,sources:["F:/wiserun_newFrame/前端源代码/wis-new-frontend/src/components/jsonEditor/index.vue"],names:[],mappings:"AACA,YACI,WAAa,CAChB,AACD,2CACI,aAAe,CAClB",file:"index.vue",sourcesContent:["\n.CodeMirror {\n    height: 100%;\n}\n.json-editor .cm-s-rubyblue span.cm-string {\n    color: #F08047;\n}\n"],sourceRoot:""}])},850:function(e,t,n){var i=n(845);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n(788)("9f2cb62c",i,!0)},856:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"json-editor"},[n("textarea",{ref:"textarea"})])},staticRenderFns:[]}},894:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n(828),a=n.n(i),l=n(63),r=n.n(l),o=n(302),s=n.n(o),c=n(835),u=n.n(c),d=n(823),m=n(300);t.default={components:{jsonEditor:u.a},data:function(){return{tableQuery:{limit:10,current_page:1,name:null,description:null},total:null,tableData:[],tableLoading:!0,formTitle:null,formVisible:!1,formSubmiting:!1,createdItem:{id:null,name:null,value:null,description:null},deletedForm:{id:null},jsonEditor:!1}},mounted:function(){this.getList()},methods:{getList:function(){var e=this;this.tableLoading=!0,this.$api.system.config.list({data:this.tableQuery}).then(function(t){e.total=t.data.data.total,e.tableData=t.data.data.list,e.tableLoading=!1}).catch(function(t){e.tableLoading=!1,console.log(t)})},handleFilter:function(){this.getList()},handleReset:function(){this.tableQuery.limit=10,this.tableQuery.current_page=1,this.tableQuery.name=null,this.getList()},handleSizeChange:function(e){this.tableQuery.limit=e,this.getList()},handleCurrentChange:function(e){this.tableQuery.current_page=e,this.getList()},clearDialog:function(){this.createdItem.id=null,this.createdItem.name=null,this.createdItem.value=null,this.createdItem.description=null,this.jsonEditor=!1},showDialog:function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null;this.formVisible=!0,this.clearDialog(),"create"==e?this.formTitle="新建配置":(this.formTitle="修改配置",s()(this.createdItem,t),n.i(m.a)(this.createdItem.value)&&(this.jsonEditor=!0,this.createdItem.value=JSON.parse(this.createdItem.value)))},commitForm:function(){var e=this;this.formSubmiting=!0,"object"==r()(this.createdItem.value)&&(this.createdItem.value=a()(this.createdItem.value)),this.$api.system.config.save({data:this.createdItem}).then(function(t){e.formSubmiting=!1,e.formVisible=!1,e.$notify({title:"成功",message:"保存成功",type:"success",duration:1500}),e.handleFilter(),console.log(t)}).catch(function(t){e.formSubmiting=!1,e.$notify({title:"错误",message:"保存失败",type:"error",duration:3e3}),console.log(t)})},deleteRecord:function(e){var t=this;n.i(d.a)("配置").then(function(){t.deletedForm.id=e,t.$api.system.config.delete({data:t.deletedForm}).then(function(e){t.$notify({title:"成功",message:"删除成功",type:"success",duration:1500}),t.handleFilter(),console.log(e)}).catch(function(e){t.formSubmiting=!1,t.$notify({title:"错误",message:"删除失败",type:"error",duration:3e3}),console.log(e)})})}}}},958:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"components-container"},[n("div",{staticClass:"filter-container"},[n("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"配置名"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleFilter(t)}},model:{value:e.tableQuery.name,callback:function(t){e.$set(e.tableQuery,"name",t)},expression:"tableQuery.name"}}),e._v(" "),n("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"配置说明"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleFilter(t)}},model:{value:e.tableQuery.desc,callback:function(t){e.$set(e.tableQuery,"desc",t)},expression:"tableQuery.desc"}}),e._v(" "),n("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary"},on:{click:e.handleFilter}},[n("i",{staticClass:"el-icon-search"})]),e._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:e.handleReset}},[n("i",{staticClass:"el-icon-refresh"})]),e._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.showDialog("create")}}},[n("i",{staticClass:"el-icon-plus"}),e._v(" 新增配置")])],1),e._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:e.tableLoading,expression:"tableLoading",modifiers:{body:!0}}],staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"拼命加载中",stripe:"",border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{label:"ID",prop:"id",align:"center",width:"100px"}}),e._v(" "),n("el-table-column",{attrs:{label:"配置名",prop:"name",align:"center",width:"270px"}}),e._v(" "),n("el-table-column",{attrs:{label:"配置值",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{staticClass:"table-big-text"},[e._v(e._s(t.row.value))])]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"说明",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{staticClass:"table-big-text"},[e._v(e._s(t.row.description))])]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"操作",align:"center",width:"180px"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(n){return e.showDialog("update",t.row)}}},[n("i",{staticClass:"el-icon-edit"})]),e._v(" "),n("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(n){return e.deleteRecord(t.row.id)}}},[n("i",{staticClass:"el-icon-delete"})])]}}])})],1),e._v(" "),n("div",{staticClass:"pagination-container"},[n("el-pagination",{attrs:{"current-page":e.tableQuery.current_page,"page-sizes":[10,20,50],"page-size":e.tableQuery.limit,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"update:currentPage":function(t){return e.$set(e.tableQuery,"current_page",t)},"update:current-page":function(t){return e.$set(e.tableQuery,"current_page",t)}}})],1),e._v(" "),n("el-dialog",{attrs:{title:e.formTitle,visible:e.formVisible,width:"35%"},on:{"update:visible":function(t){e.formVisible=t}}},[n("el-form",{staticStyle:{width:"80%","margin-left":"0px"},attrs:{model:e.createdItem,"label-position":"left","label-width":"70px"}},[n("el-form-item",{attrs:{label:"配置名"}},[n("el-input",{attrs:{placeholder:"请填写配置名"},model:{value:e.createdItem.name,callback:function(t){e.$set(e.createdItem,"name",t)},expression:"createdItem.name"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"说明"}},[n("el-input",{attrs:{placeholder:"请填写说明"},model:{value:e.createdItem.description,callback:function(t){e.$set(e.createdItem,"description",t)},expression:"createdItem.description"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"配置类型"}},[n("el-switch",{attrs:{"active-color":"#13ce66","inactive-color":"#ff4949","active-text":"JSON","inactive-text":"文案","active-value":!0,"inactive-value":!1},model:{value:e.jsonEditor,callback:function(t){e.jsonEditor=t},expression:"jsonEditor"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"配置值"}},[e.jsonEditor?[n("json-editor",{ref:"jsonEditor",staticStyle:{"line-height":"16px !important"},model:{value:e.createdItem.value,callback:function(t){e.$set(e.createdItem,"value",t)},expression:"createdItem.value"}})]:[n("el-input",{attrs:{type:"textarea",rows:5,placeholder:"请填写配置值"},model:{value:e.createdItem.value,callback:function(t){e.$set(e.createdItem,"value",t)},expression:"createdItem.value"}})]],2)],1),e._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(t){e.formVisible=!1}}},[e._v("取 消")]),e._v(" "),n("el-button",{attrs:{type:"primary",loading:e.formSubmiting},on:{click:e.commitForm}},[e._v("确 定")])],1)],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=5.b8ea5ba0b42be3359895.js.map