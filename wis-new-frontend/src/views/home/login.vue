<template>
    <div class="login-container" :style="bgImage">
        <el-form autoComplete="on" :model="loginForm" ref="loginForm" label-position="left" label-width="0px" class="card-box login-form">
            <h3 class="title">上海市松江区政务云资源申请系统</h3>
            <div class="formElement">
                <el-form-item prop="phone">
                    <el-input name="phone" type="text" prefix-icon="el-icon-user" v-model="loginForm.phone" autoComplete="on" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input name="password" type="password" prefix-icon="el-icon-lock" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on" placeholder="密码"></el-input>
                </el-form-item>
                <br>
                <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleLogin">
                    登录
                </el-button>
            </div>
        </el-form>
    </div>
</template>

<script>
import { isPhone } from 'utils/validate';
import Vue from 'vue';

export default {
    name: 'login',
    data() {
        return {
            bgImage: {
                backgroundImage: "url(" + require("../../../image/bg.png") + ")",
                backgroundRepeat: "no-repeat",
                backgroundSize: "100% 100%"
            },
            loginForm: {
                phone: '', //U13800000000
                password: '' //123456
            },
            loading: false
        }
    },
    methods: {
        handleLogin() {
            $.ajax({
                url: Vue.prototype.HOST_PROXY_ADMIN + "/user/login",
                method: "POST",
                /* crossDomain: true,
                xhrFields: {
                    withCredentials: true
                }, */
                contentType: "application/x-www-form-urlencoded",
                data: "phone=" + this.loginForm.phone + "&password=" + this.loginForm.password
            }).done(response => {
                this.loading = false;
                if (response.code === 0) {
                    console.log(response.data.token);
                    this.$message.success('登录成功');
                    localStorage.setItem('x-token', response.data.token);
                    this.$router.push({path: '/'});
                } else {
                    if (typeof response.message != 'undefined') {
                        this.$message.error(response.message);
                    } else if (response.data != null) {
                        this.$message.error(response.data.message);
                    } else
                        this.$message.error('登录失败，账号或密码错误');
                    this.$router.push({path: '/login'});
                }
            }).fail(error => {
                this.loading = false;
                this.$message.error('登录失败，网络异常');
            });
            this.$router.push({ path: '/test' });
        }
    }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 5px;
}
.formElement{
    background-color:rgba(255,255,255,0.4);
    padding:30px 30px
}
.login-container {
    @include relative;
    height: 100vh;
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
        -webkit-text-fill-color: #fff !important;
    }
    input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: #eeeeee;
        height: 47px;
    }
    .el-input {
        display: inline-block;
        height: 47px;
        width: 85%;
    }
    .svg-container {
        padding: 6px 5px 6px 15px;
        color: #889aa4;
    }

    .title {
        font-size: 26px;
        font-weight: 400;
        color: #eeeeee;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
    }

    .login-form {
        position: absolute;
        left: 0;
        right: 0;
        width: 400px;
        padding: 35px 35px 15px 35px;
        margin: 120px 80px auto auto;
    }

    .el-form-item {
        border: 1px solid rgba(255, 255, 255, 0.1);
        background: rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        color: #454545;
    }

    .forget-pwd {
        color: #fff;
    }
}
</style>
