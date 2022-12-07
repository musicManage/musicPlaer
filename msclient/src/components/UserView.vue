<template>
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
        <SongMsg :songMsg="collection"></SongMsg>
      </div>
    </div>


  </div>
</template>

<script>
import {mixin} from "@/mixins";
import LoginView from "@/components/LoginView.vue";
import {mapGetters} from "vuex";
import {getCollectOfUserId, getUserById} from "@/api";
import SongMsg from "@/components/SongMsg.vue";


export default {
  name: "UserView",
  mixins:[mixin],
  components: {SongMsg, LoginView},
  data(){
    return{
      avator: '',       //用户头像
      username: '',     //昵称
      userSex: '',      //性别
      birth: '',        //生日
      location: '',     //故乡
      introduction: '', //签名
      collection: [],     //收藏的歌曲列表
    }
  },
  created() {

  },
  mounted() {
    this.getMsg(this.userId);
    this.getCollection(this.userId);
  },
  computed:{
    ...mapGetters([
      'loginIn',
      'userId',
      'listOfSongs'
    ])
  },
  watch: {
  },
  methods:{
    getMsg(userId){
      if (this.loginIn) {
        getUserById(userId)
            .then(res => {
              this.avator = res.data.avator;
              this.username = res.data.username;
              if (res.data.sex == 0) {
                this.userSex = '女';
              } else if (res.data.sex == 1) {
                this.userSex = '男';
              }
              this.birth = this.attachBirth(res.data.birth);
              this.location = res.data.location;
              this.introduction = res.data.introduction;
            })
            .catch(err => {
              console.log(err);
            })
      }
    },
    //获取我的收藏列表
    getCollection(userId){
      getCollectOfUserId(userId)
          .then(res =>{
            let resultData = res.data;
            for (let i = 0; i < resultData.length; i++) {
              let item = JSON.parse(
                  JSON.stringify(resultData[i])
                      .replace(/songId/g, 'id')
              );
              this.collection.push(item);
            }
            this.$store.commit('setListOfSongs',this.collection);
          })
          .catch(err => {
            console.log(err);
          })
    },
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/my-music.scss';
</style>