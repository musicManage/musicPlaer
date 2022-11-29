<template>
  <div class="home" style="margin-top: 60px">
    <swiper/>
    <div class="section" v-for="(item,index) in songsList" :key="index">
      <div class="section-title" style="text-align: center" >
        {{item.name}}
      </div>
      <content-list :contentList="item.list"></content-list>
    </div>
  </div>
</template>

<script>
import Swiper from "@/components/Swiper";
import {allSinger,allSongList} from '../api/index';
import contentList from "@/components/ContentList";

export default {
  name: "home",
  components:{
    Swiper,
    contentList
  },
  data() {
    return {
      songsList: [
        {name:"歌单",list: []},
        {name:"歌手",list: []},
      ]
    }
  },

  created() {
    this.getSongList();
    this.getSinger();
  },

  methods:{
    getSongList() {        //获取前十条歌单
      allSongList().then(
          (res) => {
        this.songsList[0].list = res.data.slice(0,10);
      }).catch((err) => {
        console.log(err);
      })
    },
    getSinger() {          //获取前十名歌手
      allSinger().then(
          (res) => {
            this.songsList[1].list = res.data.slice(0,10);
            // console.log(res.data);
          }).catch((err) => {
        console.log(err);
      })
    }
  }
}
</script>

<style scoped>
home {
  margin-top: 60px;
}
.section {
  width: 100%;
  margin-top: 20px;
  padding: 0 120px 50px 120px;
  background-color: #ffffff;
  box-sizing: border-box;
}
.section-title {
  height: 60px;
  line-height: 60px;
  padding-top: 10px;
  font-size: 28px;
  font-weight: 500;
  text-align: center;
  color: #000000;
  box-sizing: border-box;
}
</style>