<template>
    <div>
      <div class="loginBox">
        <div class="n-pglg">
          <div class="pic">
            <h2>登录音乐</h2>
            <el-button type="primary" class="login-btn" @click="LoginView">登录</el-button>
          </div>
        </div>

      </div>


<!--      登录窗口-->
      <el-dialog
          title="登录"
          :visible.sync="LoginVisible"
          width="25%"
      >
        <el-form ref="form" :model="loginForm" :rules="rules1">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" placeholder="用户名" v-model="loginForm.username" size="medium" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" placeholder="密码" v-model="loginForm.password" type="password" size="medium" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn_sub" @click="submitForm" type="primary" round>登录</el-button>
          </el-form-item>
          <el-form-item>
            <div class="box">
              <span @click="forgetPass">忘记密码</span>&nbsp;|&nbsp;<span @click="registerCount">注册账号</span>
            </div>
          </el-form-item>
        </el-form>
      </el-dialog>

<!--      忘记密码窗口-->
      <el-dialog
          title="忘记密码"
          :visible.sync="forgetVisible"
          width="432px"
      >
        <el-form ref="forgetForm" :model="forgetForm" :rules="rules" label-width="80px" status-icon>
          <el-form-item label="用户名" prop="username">
            <el-input placeholder="用户名" v-model="forgetForm.username" size="medium" clearable></el-input>
          </el-form-item>
          <el-form-item label="邮箱验证" prop="checkCode">
            <el-input v-model="forgetForm.checkCode" placeholder="请输入验证码" style="width: 56%"></el-input>
            <el-button style="width: 40%;margin-left: 10px" @click="sendCode" :disabled="flag">{{ this.msg }}</el-button>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input  placeholder="请输入新密码" v-model="forgetForm.password" type="password" size="medium" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input  placeholder="请再次输入密码" v-model="forgetForm.checkPass" type="password" size="medium" show-password></el-input>
          </el-form-item>
        </el-form>
        <el-button class="btn_sub" @click="updatePass" type="primary" round>确认修改</el-button>
      </el-dialog>

<!--      注册弹窗-->
      <el-dialog title="注册新用户"  :visible.sync="registerVisible" width="400px" center>
        <el-form :model="registerForm" ref="registerForm" label-width="80px" status-icon :rules="rules2">
          <el-form-item prop="username" label="用户名" size="mini" >
            <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码" size="mini">
            <el-input type="password" v-model="registerForm.password" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item prop="sex" label="性别" size="mini">
            <el-radio-group v-model="registerForm.sex">
              <el-radio :label="1">男</el-radio>
              <el-radio :label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="phoneNum" label="手机号" size="mini">
            <el-input v-model="registerForm.phoneNum" placeholder="手机号"></el-input>
          </el-form-item>
          <el-form-item prop="email" label="邮箱" size="mini">
            <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="birth" label="生日" size="mini">
            <el-date-picker type="date" v-model="registerForm.birth" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item prop="location" label="地区" size="mini">
            <el-cascader
                size="mini"
                :options = "options"
                v-model="arr"
                filterable>
            </el-cascader>
          </el-form-item>
          <el-form-item prop="introduction" label="个性签名" size="mini">
            <el-input type="textarea" v-model="registerForm.introduction" placeholder="个性签名"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer">
       <el-button size="mini" @click="registerVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="addConsumer">注册</el-button>
     </span>
      </el-dialog>

    </div>
</template>

<script>
import {provinceAndCityData} from "element-china-area-data";
import {addConsumer, getEmail, setPass, verifyEmail, verifyPass, verifyUsername} from "@/api";

export default {
  name: "my-music",
  components: {},
  data(){
    //检测用户名是否唯一
    const checkUsername = (rule, value, callback) => {
      if (value === ''){
        callback(new Error("请输入用户名"));
      } else{
        verifyUsername(value)
            .then(res => {
              if (res.code == 1) {
                callback();
              } else {
                callback(new Error('用户名已重复,请修改用户名'));
              }
            })
      }
    };
    const checkMail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value){
        callback("请输入邮箱");
      } else if (mailReg.test(value)){
        callback();
      } else {
        callback("邮箱格式错误");
      }
    };
    const checkPhone = (rule, value, callback) => {
      const phoneReg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
      if (!value){
        callback("请输入手机号");
      } else if (phoneReg.test(value)){
        callback();
      } else {
        callback("请输入正确的手机号");
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.forgetForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    const checkName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else if (value !== '') {
        getEmail(this.forgetForm.username)
            .then(res => {
              if (res.code == 1){
                this.forgetForm.email = res.data;
                callback();
              } else {
                callback(new Error('该用户名不存在'));
              }
            })
      } else {
        callback();
      }
    };
    const checkEmailCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else if (value != this.code) {
        callback(new Error('验证码错误'));
      } else {
        callback();
      }
    };
    return{
      LoginVisible : false,//登录窗口是否可见
      forgetVisible : false, //忘记密码窗口是否可见
      registerVisible : false,//注册窗口是否可见
      loginForm:{//登录窗口
        username:'',
        password:'',
      },
      forgetForm:{
        username:'',
        password:'',
        checkPass:'',
        checkCode:'',
        email:'',
      },
      registerForm: {
        username: '',
        password: '',
        sex: 1,
        phoneNum: '',
        email: '',
        birth: '',
        introduction: '',
        location: '',
        arr:[],
      },
      rules2:{
        username:[
          { require:true, validator: checkUsername, trigger: 'blur' }
        ],
        email:[
          { require:true, validator: checkMail, trigger: 'blur'}
        ],
        phoneNum:[
          { require:true, validator: checkPhone, trigger: 'blur'}
        ]
      },
      rules1: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      },
      rules: {
        username: [
          {required: true, validator: checkName, trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
        checkPass: [
          {required: true, validator: validatePass2 ,trigger: 'blur'}
        ],
        checkCode: [
          {required: true, validator: checkEmailCode ,trigger: 'blur'}
        ]
      },
      flag:false,
      msg:"发送验证码",
      time:4,
      code:'',//验证码
      options: provinceAndCityData,//地区选择
    }
  },
  watch: {
    forgetVisible(newV){
      if (newV){
        this.forgetForm.username='';
        this.forgetForm.password='';
        this.forgetForm.email='';
        this.forgetForm.checkCode='';
        this.forgetForm.checkPass='';
        this.$refs.forgetForm.clearValidate();
      }
    },
    LoginVisible(newV){
      if (newV){
        this.loginForm.username='';
        this.loginForm.password='';
      }
    },
    arr(newV){
      if (newV != ''){
        this.registerForm.location = `${CodeToText[newV[0]]}`+"-"+`${CodeToText[newV[1]]}`;
      }
    },
  },
  methods:{
    LoginView(){
      this.LoginVisible = true;
    },
    submitForm(){
      let param = new URLSearchParams();
      param.append("username",this.loginForm.username);
      param.append("password",this.loginForm.password);

      verifyPass(param)
          .then(res => {
            if (res.code == 1){
              this.$notify.success("登录成功");
              this.LoginVisible = false;
            } else {
              this.$notify({
                title:"登录失败",
                message:"请检查账号或密码是否正确",
                type : "error"
              });
            }
          })
    },
    registerCount(){
      this.LoginVisible = false;
      this.registerVisible = true;
    },
    forgetPass(){
      this.LoginVisible = false;
      this.forgetVisible = true;
    },
    updatePass(){
      if (this.code != this.forgetForm.checkCode){
        this.$message.error("验证码错误");
      } else if (this.forgetForm.password == this.forgetForm.checkPass){
        let param = new URLSearchParams();
        param.append("username",this.forgetForm.username);
        param.append("password",this.forgetForm.password);

        setPass(param)
            .then(res => {
              if (res.code == 1){
                this.$message.success("密码修改成功 将在3s后转到登录窗口");

                setTimeout(() => {
                  this.forgetVisible = false;
                  this.LoginVisible = true;
                }, 3000);
              } else {
                this.$message.error("密码修改失败");
              }
            })
      } else {
        this.$message.error("两次输入的密码不一样，请修改");
      }
    },
    sendCode(){

      verifyEmail(this.forgetForm.email)
          .then(res => {
            this.code = res;
          });
      this.msg = "5s后再发";
      this.flag = true;
      //再次获取验证码倒计时
      const countDown = setInterval(() => {
        if (this.time < 1) {
          this.flag = false
          this.msg = '获取验证码'
          this.time = 5
          clearInterval(countDown)
        } else {
          this.flag = true
          this.msg = this.time-- + 's后重发'
        }
      }, 1000);
    },
    addConsumer(){
      let params = new URLSearchParams();
      params.append('username',this.registerForm.username);
      params.append('password',this.registerForm.password);
      params.append('sex',this.registerForm.sex);
      params.append('phoneNum',this.registerForm.phoneNum);
      params.append('email',this.registerForm.email);
      params.append('birth',this.registerForm.birth);
      params.append('location',this.registerForm.location);
      params.append('introduction',this.registerForm.introduction);

      addConsumer(params)
          .then(res => {
            if (res.code==1){
              this.$message.success("注册成功 将在1s内转到登录窗口");
              this.registerForm = [];
              this.registerVisible = false;
              setTimeout(() => {
                this.LoginVisible = true;
              }, 800);
            } else {
              this.$notify({
                title: '添加失败',
                type: 'error',
              });
            }
          })
          .catch(err => {
            console.log(err);
          });
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
