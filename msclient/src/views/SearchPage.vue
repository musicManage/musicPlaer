<template>
  <div>
    <el-tabs type="border-card" v-model="activeName" stretch @tab-click="getMsg">
      <el-tab-pane label="歌曲" name="first">
        <SongMsg :songMsg="songMsg"></SongMsg>
      </el-tab-pane>
      <el-tab-pane label="歌单" name="second">
        <ContentList :contentList="SongListMsg"></ContentList>
      </el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>
import {getPostList, songOfSName} from "@/api";
import ContentList from "@/components/ContentList";
import SongList from "@/views/SongList";
import SongMsg from "@/components/SongMsg";
import bus from "@/assets/js/bus";

export default {
  name: "SearchPage",
  components: {SongMsg, SongList, ContentList},

  data(){
    return{
      activeName:'first',
      SongListMsg:[],//歌单信息存放
      songMsg:[],
    }
  },
  created() {
    bus.$on('keywords',msg =>{
      this.keywords = msg;
    });
    this.getMsg();
  },
  watch:{
    "$route.query.keywords": {
      immediate: true,
      handler() {
        this.getMsg();
      },
    },
  },
  methods:{
    getMsg(){
      if(this.activeName == 'second'){
        if(!this.$route.query.keywords){
          this.$notify.warning('您输入的内容为空');
        }else{
          getPostList(this.$route.query.keywords)
              .then(res =>{
                if(res){
                  this.SongListMsg = res;
                }else{
                  this.$notify.warning('暂无该歌曲内容');
                }
              })
        }
      } else {
        if(!this.$route.query.keywords){
          this.$notify.warning('您输入的内容为空');
        }else{
          songOfSName(this.$route.query.keywords)
              .then(res =>{
                if(res){
                  this.songMsg = res;
                }else{
                  this.$notify.warning('暂无该歌曲内容');
                }
              })
        }
      }
    },
  }
}
</script>

<style scoped>
.el-tabs {
  min-height: 100vh;
  width: 1240px;
  margin: 20px auto;
  background: white;
}
</style>
