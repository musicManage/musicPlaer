<template>
  <div class="play-bar">
    <div class="kongjian">
<!--      上一首-->
      <div class="item">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi"></use>
        </svg>
      </div>
<!--      播放-->
      <div class="item">
        <svg class="icon">
          <use xlink:href="#icon-bofang"></use>
        </svg>
      </div>
<!--      下一首-->
      <div class="item">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi1"></use>
        </svg>
      </div>
<!--      歌曲图片-->
      <div class="item">
        <img :src="picUrl"/>
      </div>
<!--      播放进度-->
      <div class="playing-speed">
<!--        播放开始时间-->
        <div class="current-time">{{nowTime}}</div>
        <div class="progress-box">
          <div ref="progress" class="progress">
<!--            进度条-->
            <div ref="bg" class="bg">
              <div ref="curProgress" class="cur-progress" :style="{width:'30%'}"></div>
            </div>
<!--            拖动点-->
            <div ref="idot" class="idot" :style="{left:'30%'}"></div>
          </div>
        </div>

<!--        播放结束时间-->
        <div class="left-time">{{songTime}}</div>
<!--        音量-->
        <div class="item item-volume">
          <svg class="icon">
            <use xlink:href="#icon-yinliang1"></use>
          </svg>
        </div>
      </div>

<!--      收藏-->
      <div class="item">
        <svg class="icon">
          <use xlink:href="#icon-xihuan-shi"></use>
        </svg>
      </div>

<!--    下载-->
    <div class="item">
      <svg class="icon">
        <use xlink:href="#icon-xiazai"></use>
      </svg>
    </div>

<!--    当前播放歌曲列表-->
    <div class="item">
      <svg class="icon">
        <use xlink:href="#icon-liebiao"></use>
      </svg>
    </div>

    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {mixin} from "@/mixins";

export default {
  name: "PlayBar",
  mixins:[mixin],
  data(){
    return{
      nowTime:'00:00', //当前播放进度的时间
      songTime:'00:00', //当前歌曲总时间
    }
  },
  computed:{
    ...mapGetters([
      'id',               //歌曲id
      'url',              //歌曲地址
      'isPlay',           //播放状态
      'playButtonUrl',   //播放状态的图标
      'picUrl',            //正在播放的音乐的图片
      'title',                  //歌名
      'artist',                 //歌手名
      'duration',                //音乐时长
      'curTime',                //当前音乐的播放位置
      'showAside',            //是否显示播放中的歌曲列表
      'listIndex',            //当前歌曲在歌单中的位置
      'listOfSongs',          //当前歌单列表
      'autoNext',             //自动播放下一首
      'loginIn',              //用户是否已登录
      'userId',               //当前登录用户的id
      'isActive',             //当前播放的歌曲是否已收藏
    ])
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/play-bar.scss';
</style>