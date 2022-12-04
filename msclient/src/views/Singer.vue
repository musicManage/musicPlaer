<template>
  <div class="singer">
    <div class="section" v-for="(item,index) in songsList" :key="index">
      <div class="section-title" style="text-align: center" >
        {{item.name}}
      </div>
      <content-list :contentList="item.list"></content-list>
    </div>
  </div>
</template>
<script>

import TheFooter from "@/components/TheFooter";
import contentList from "@/components/ContentList";
import {allSinger} from "@/api";

export default {
  name: 'singer',
  components: {
    TheFooter,
    contentList,
  },
  data() {
    return {
      songsList: [
        {name: "歌手", list: []},
      ]
    }
  },

  created() {
     this.getSinger();
  },

  methods: {
    getSinger() {          //获取前十名歌手
      allSinger().then(
          (res) => {
            this.songsList[0].list = res.data.slice(0,10);
            // console.log(res.data);
          }).catch((err) => {
        console.log(err);
      })
    }
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

