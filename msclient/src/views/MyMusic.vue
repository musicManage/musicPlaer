<template>
    <div>
      <div class="loginBox" v-if="!loginIn">
        <div class="n-pglg">
          <div class="pic">
            <h2>登录音乐</h2>
            <el-button type="primary" class="login-btn" @click="LoginView">登录</el-button>
          </div>
        </div>
      </div>


      <div v-if="loginIn">
        <div class="my-music">
          <div class="album-slide">
            <div class="album-img">
              <img :src="attachImageUrl(avator)">
            </div>
            <ul class="album-info">
              <li>昵称:{{username}}</li>
              <li>性别:{{userSex}}</li>
              <li>生日：{{birth}}</li>
              <li>故乡：{{location}}</li>
            </ul>
          </div>
          <div class="album-content">
            <div class="album-title">
              个性签名：{{introduction}}
            </div>
            <div class="songs-body">
              <album-content :songList="collectList">
                <template slot="title">我的收藏</template>
              </album-content>
            </div>
          </div>
        </div>
      </div>

    </div>
</template>

<script>
import {mixin} from "@/mixins";
import LoginView from "@/components/LoginView";
import bus from "@/assets/js/bus";
import {mapGetters} from "vuex";
import {getUserById} from "@/api";

export default {
  name: "my-music",
  mixins:[mixin],
  components: {LoginView},
  data(){
    return{
      LoginVisible:false,
      logined:false,
      loginedForm:[],//接受登录后的信息

      avator: '',       //用户头像
      username: '',     //昵称
      userSex: '',      //性别
      birth: '',        //生日
      location: '',     //故乡
      introduction: '', //签名
      collection: [],     //收藏的歌曲列表
      collectList: [],    //收藏的歌曲列表（带歌曲详情）
    }
  },
  created() {
    this.loginedStatus();
    this.getMsg(this.userId);
  },
  computed:{
    ...mapGetters([
        'loginIn',
        'userId',
    ])
  },
  watch: {
  },
  methods:{
    getMsg(userId){
      getUserById(userId)
          .then(res =>{
            this.avator = res.data.avator;
            this.username = res.data.username;
            if(res.data.sex==0){
              this.userSex = '女';
            }else if (res.data.sex==1){
              this.userSex = '男';
            }
            this.birth = this.attachBirth(res.data.birth);
            this.location = res.data.location;
            this.introduction = res.data.introduction;
          })
          .catch(err => {
            console.log(err);
          })
    },
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

<style lang="scss" scoped>
@import '../assets/css/my-music.scss';
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
