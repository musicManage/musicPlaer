<template>
  <div class="loginFormPage">
    <div class="login">
      <div class="title">{{ mTitle }}</div>
      <div class="loginBox" >
        <el-form ref="form" :rules="rules" status-icon :model="adminForm" label-width="60px">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" v-model="adminForm.username" size="medium" placeholder="用户名" inline-message="true" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="adminForm.password" type="password" size="medium" placeholder="密码" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn_sub" @click="submitForm" type="primary" round>登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {getLoginStatus} from "@/api/index";
import router from "@/router";

export default {
  data() {
    return{
      mTitle:"音乐网站后台管理",
      adminForm: {
        username:"",
        password:""
      },
      rules: {
        username: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    submitForm(){
      let params = new URLSearchParams();
      params.append("name",this.adminForm.username);
      params.append("password",this.adminForm.password);
      getLoginStatus(params)
          .then((res) => {
            if (res.code == 1){
              localStorage.setItem('ausername',this.adminForm.username);
              this.$notify({
                title: '登录成功',
                type: 'success',
                message: '欢迎进入音乐网站后台管理系统'
              });
              this.$router.push('/Index');
            }else {
              this.$notify({
                title: '用户名或密码错误',
                message:'请检查你的用户名或密码是否正确',
                type : 'error',
                showClose: true,
              });
            }
          });
      // console.log(params);
    }
  }
}
</script>

<style scoped>
.loginFormPage {
  position: absolute;
  background: url("../assets/img/aBack.jpeg");
  background-size: cover;
  width: 100%;
  height: 100%;
}
.title {
  position: relative;
  top: 30px;
  color: black;text-align: center;
  font-size: 30px;
  font-weight: 600;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
.login {
  width: 400px;
  height: 300px;
  background: white;
  margin: 250px auto;
  border-radius: 5px;
}
.loginBox {
  position: relative;
  top: 50px;
  left: -30px;
}
.btn_sub{
  width: 100%;
}
</style>
