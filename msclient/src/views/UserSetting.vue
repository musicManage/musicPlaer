<template>
<div class="box">
  <div class="userBox">
    <div class="u-title">
      <h3><span class="f-ff2">个人设置</span></h3>
    </div>

    <div class="userMsg">
      <div class="form">
      <el-form :model="editForm" ref="editForm" label-width="80px" status-icon :rules="rules">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input v-model="editForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input type="password" v-model="editForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="editForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phoneNum" label="手机号" size="mini">
          <el-input v-model="editForm.phoneNum" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱" size="mini">
          <el-input v-model="editForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker type="date" v-model="editForm.birth" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-form-item prop="location" label="地区" size="mini">
          <el-cascader
              size="mini"
              :options = "options"
              v-model="editForm.location"
              filterable>
          </el-cascader>
        </el-form-item>
        <el-form-item prop="introduction" label="个性签名" size="mini">
          <el-input type="textarea" v-model="editForm.introduction" placeholder="个性签名"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
         <el-button type="primary" size="mini" @click="editSinger(editForm)" class="submit">保存</el-button>
      </span>
      </div>

      <div class="picBox">
        <img :src="attachImageUrl(editForm.avator)">
        <el-upload :before-upload="beforeUpdate"
                   :action="updatePics(userId)"
                   :on-success="updateSuccess"
                   :show-file-list = 'false'
        >
          <el-button size="mini">
            更换头像
          </el-button>
        </el-upload>
      </div>

    </div>
  </div>
</div>
</template>

<script>
import {mapGetters} from "vuex";
import {mixin} from "@/mixins";
import {getUserById, updateConsumer, verifyUsername} from "@/api";
import {provinceAndCityData} from "element-china-area-data";
import {CodeToText, TextToCode} from "element-china-area-data/dist/app";

export default {
  name: "UserSetting",
  mixins:[mixin],
  data(){
    //检测用户名是否唯一
    const checkUsername = (rule, value, callback) => {
      if (value === ''){
        callback(new Error("请输入用户名"));
      } else if(this.editUsername != value){
        verifyUsername(value)
            .then(res => {
              if (res.code == 1) {
                callback();
              } else {
                callback(new Error('用户名已重复,请修改用户名'));
              }
            })
      } else if (this.editUsername == value){
        callback();
      } else {
        callback();
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
    return{
      editForm: {
        id:'',
        username: '',
        password: '',
        sex: '',
        phoneNum: '',
        email: '',
        birth: '',
        introduction: '',
        location: '',
      },
      options: provinceAndCityData,
      rules:{
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
      editUsername:'',
      arr:[],
      arr2:[],
    }
  },
  created() {
    this.getUSerMsg();
  },
  watch:{
    arr(newV){
      if (newV != ''){
        this.registerForm.location = `${CodeToText[newV[0]]}`+"-"+`${CodeToText[newV[1]]}`;
      }
    },
  },
  computed:{
    ...mapGetters([
        'userId'
    ])
  },
  methods:{
    getUSerMsg(){
      getUserById(this.userId)
          .then(res => {
            this.editForm = res.data;
            this.editUsername = this.editForm.username;
            this.ChangeArr1(this.editForm.location);
            this.editForm.location = this.arr2;
            this.$store.commit('setAvator',this.editForm.avator);
          })
    },
    ChangeArr1(val){
      this.arr2 = [];
      if (val) {
        let arr3 = val.split('-');
        this.arr2.push(`${TextToCode[arr3[0]].code}`);
        this.arr2.push(`${TextToCode[arr3[0]][arr3[1]].code}`);
      }
    },
    editSinger(editForm){
      let arr1 = editForm.location;
      this.editForm.location = `${CodeToText[arr1[0]]}`+"-"+`${CodeToText[arr1[1]]}`;
      let params = new URLSearchParams();
      params.append('id',this.editForm.id);
      params.append('username',this.editForm.username);
      params.append('password',this.editForm.password);
      params.append('sex',this.editForm.sex);
      params.append('phoneNum',this.editForm.phoneNum);
      params.append('email',this.editForm.email);
      params.append('birth',this.editForm.birth);
      params.append('introduction',this.editForm.introduction);
      params.append('location',this.editForm.location);

      updateConsumer(params)
          .then(res => {
            if (res.code==1){
              this.$notify({
                title: '保存成功',
                type: 'success',
              });
              this.getUSerMsg();
            } else {
              this.$notify({
                title: '保存失败',
                type: 'error',
              });
            }
          })
          .catch(err => {
            console.log(err);
          });
    },
    updatePics(id){
      console.log(`${this.$store.state.HOST}/consumer/pic/update/${id}`);
      return `${this.$store.state.HOST}/consumer/pic/update/${id}`;
    },
  }
}
</script>

<style scoped>
.box {
  font-size: 12px;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  -webkit-text-size-adjust: none;
  word-wrap: break-word;
  word-break: break-word;
  width: 980px;
  min-height: 700px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid #d3d3d3;
  border-width: 0 1px;
}
.userBox{
  font-size: 12px;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  -webkit-text-size-adjust: none;
  word-wrap: break-word;
  word-break: break-word;
  padding: 40px;
}
.u-title{
  font-size: 12px;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  -webkit-text-size-adjust: none;
  word-wrap: break-word;
  word-break: break-word;
  height: 40px;
  border-bottom: none;
}
.f-ff2{
  color: #333;
  -webkit-text-size-adjust: none;
  word-wrap: break-word;
  word-break: break-word;
  font-size: 24px;
  font-weight: normal;
  font-family: "Microsoft Yahei", Arial, Helvetica, sans-serif;
}
.form {
  font-size: 12px;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  -webkit-text-size-adjust: none;
  word-wrap: break-word;
  word-break: break-word;
  float: left;
  width: 340px;
  margin-right: 30px;
}
.submit{
  height: 31px;
  width: 80px;
  margin: 30px;
}
.picBox,.picBox img {
  float: left;
  width: 140px;
  height: 140px;
}
.el-button--mini {
  margin: 10px 30px;
}
</style>