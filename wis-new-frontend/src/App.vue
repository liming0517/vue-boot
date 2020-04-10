<template>
    <div id="app">
        <router-view></router-view>
        <!-- 文件上传时的遮罩层-->
        <div v-bind:class="{maskStyle:'true' ,isHidden : display}">
    		<div class='maskFont'>文件上传中,请稍等...</div>
    	</div>
    </div>
</template>

<script>
export default {
    name: 'APP',
    data(){
        return{
            display:true
        }
    },
    methods:{
        hideOrShow(){
            this.display = !this.display;
        }
    }
}
</script>

<style lang="scss">
@import './styles/index.scss'; // 全局自定义的css样式
    .isHidden{
    	display:none;
    }
    .maskStyle{
			width:100%;
			height:100%;
			overflow:hidden;
			background-color:rgba(210,224,242,0.5);
			filter:alpha(opacity=80);
			position:absolute;
			top:0px;
			left:0px;
			z-index:20000;
	}
    .maskFont{
			 font-size:16px;
			 font-weight:bold;
			 color:#2B2B2B;
			 width:200px;
			 height:100%;
			 position:absolute;
			 top:50%;
			 left:50%;
			 margin-top:-10px;
			 margin-left:-80px;
	}
</style>
