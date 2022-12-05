<template>
  <transition name="slide-fade">
    <div class="the-aside" v-if="showAside">
      <h2 class="title">歌单列表</h2>
      <ul class="menus">
        <li v-for="(item,index) in listOfSongs"
            :key="index"
            :class="{'is-play': id===item.id}"
            @click="toplay(item.id,item.url,item.pic,item.index,item.name,item.lyric)"
        >
          {{item.singerName}}-{{item.name}}
        </li>
      </ul>
    </div>
  </transition>
</template>

<script>
import {mapGetters} from "vuex";
import {mixin} from "@/mixins";

export default {
  mixins:[mixin],
  name: "TheAside",
  computed:{
    ...mapGetters([
      'showAside', //是否显示播放歌曲列表
      'listOfSongs',       //当前歌曲列表
      'id',                //播放中的音乐id
      'loginIn',              //用户是否已登录
      'userId',               //当前登录用户的id
      'isActive',             //当前播放的歌曲是否已收藏
    ])
  },
  mounted(){
    let _this = this;
    document.addEventListener('click',function(){
      _this.$store.commit('setShowAside',false)
    },true);
    // console.log(this.listOfSongs);
  },
}
</script>

<style lang="scss" scoped>
@import "../assets/css/the-aside.scss";
</style>