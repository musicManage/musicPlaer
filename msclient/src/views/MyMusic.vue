<template>
    <div>
      <div class="loginBox" v-if="!logined">
        <div class="n-pglg">
          <div class="pic">
            <h2>登录音乐</h2>
            <el-button type="primary" class="login-btn" @click="LoginView">登录</el-button>
          </div>
        </div>
      </div>


      <div v-if="logined">
        个人界面
      </div>

    </div>
</template>

<script>

import LoginView from "@/components/LoginView";
import bus from "@/assets/js/bus";
export default {
  name: "my-music",
  components: {LoginView},
  data(){
    return{
      LoginVisible:false,
      logined:false,
      loginedForm:[],//接受登录后的信息
    }
  },
  created() {
    this.loginedStatus();
    bus.$on('logined',msg =>{
      this.logined = msg;
    })
  },
  watch: {
  },
  methods:{
    LoginView(){
      this.LoginVisible = true;
      bus.$emit('LoginVisible',this.LoginVisible);
    },
    loginedStatus(){
      if (localStorage.getItem('username')){
        this.logined = true;
      };
    },
  }
}
</script>

<style scoped>
.btn_sub{
  width: 100%;
}
.box {
  text-align: center;
  color:#999999;
}
.box span {
  cursor: pointer;
}
.box span:hover {
  cursor: pointer;
  color: black;
}
.loginBox {
  width: 980px;
  min-height: 717px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid #d3d3d3;
  border-width: 0 1px;
}
.n-pglg {
  width: 807px;
  height: 268px;
  margin: 0 auto 0;
  padding-top: 104px;
  background: url("../assets/img/img.png") no-repeat 0 104px;
}
.n-pglg h2{
  height: 100px;
  text-indent: -9999px;
}
.login-btn {
  display: block;
  width: 167px;
  height: 45px;
  margin: 102px 0 0 482px;
  background-position: 0 9999px;
}
</style>
