<template>
  <el-tabs v-model="activeName" @tab-click="getSinger" style="padding-left: 20px">
    <el-tab-pane label="全部" name="all" @click="getSinger">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>
    <el-tab-pane label="男歌手" name="man" @click="getManSinger">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>
    <el-tab-pane label="女歌手" name="woman">女歌手</el-tab-pane>
  </el-tabs>

</template>
<script>

import TheFooter from "@/components/TheFooter";
import contentList from "@/components/ContentList";
import {allSinger, getSingerBySex} from "@/api";

export default {
  name: 'singer',
  components: {
    TheFooter,
    contentList,
  },
  data() {
    return {
      activeName: 'all',
      songsList: []
    }
  },

  created() {
     this.getSinger();
  },

  methods: {
    getSinger() {
      if (this.activeName == 'all'){
        //获取全部歌手
        allSinger().then(res => {
          this.songsList = res.data;
        })
      } else if (this.activeName == 'man'){
        getSingerBySex(1).then(res => {
          this.songsList = res.data;
        })
      }


    },
  }
}
// methods:{
// goAlbum(item,type){
// this.$store.commit("setTempList".item);
//   if(type){         //歌手
//   this.$router.push({path:`singer-album/${item.id}`});
//       } else {           //歌单
//     this.$router.push({path:`song-list-album/${item.id}`});
//        }
//     }
//   }

</script>

<style scoped>
.content-list{
  max-width: 1240px;
  max-height: 764px;
  margin: 0 auto;
  position: relative;
}
.content-item{
  display: inline-block;
  width: 248px;
  height: 353px;
  overflow: hidden;
  font-size: 14px;
  vertical-align: top;
}
li {
  list-style: none;
}
.kuo {
  position: relative;
  margin-right: 20px;
  display: block;
  overflow: hidden;
  margin-bottom: 15px;
}
.kuo2{
  position: relative;
  overflow: hidden;
  width: 230px;
  height: 230px;
}
.item-img {
  width: 228px;
  height: 228px;
  cursor: pointer;

}
.item-img:hover {
  transform: scale(1.2);
  transition: transform .75s;
  overflow: hidden;
  -webkit-filter: brightness(0.5);
  filter: brightness(0.5);
}
h3 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: medium;
  font-weight: 400;
  zoom: 1;
  margin-top: 10px;
  text-align: center;
  width: 100%;
  cursor: pointer;
}
ul {
  margin-right: -20px;
}
</style>

