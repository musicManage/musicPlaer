<template>
  <div class="play-bar" :class="{show:!toggle}">
    <div @click="toggle=!toggle" class="item-up" :class="{turn: toggle}">
      <svg class="icon">
        <use xlink:href="#icon-jiantou-xia-cuxiantiao"></use>
      </svg>
    </div>
    <div class="kongjian">
<!--      上一首-->
      <div class="item" @click="prev">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi"></use>
        </svg>
      </div>
<!--      播放-->
      <div class="item" @click="togglePlay">
        <svg class="icon">
          <use :xlink:href="playButtonUrl"></use>
        </svg>
      </div>
<!--      下一首-->
      <div class="item" @click="next">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi1"></use>
        </svg>
      </div>
<!--      歌曲图片-->
      <div class="item-img" @click="toLyric">
        <img :src="picUrl"/>
      </div>
<!--      播放进度-->
      <div class="playing-speed">
<!--        播放开始时间-->
        <div class="current-time">{{nowTime}}</div>
        <div class="progress-box">
          <div class="item-song-title">
            <div>{{this.title}}</div>
            <div>{{this.artist}}</div>
          </div>
          <div ref="progress" class="progress" @mousemove="mousemove" >
<!--            进度条-->
            <div ref="bg" class="bg" @click="updatemove">
              <div ref="curProgress" class="cur-progress" :style="{width: curLength+'%'}" ></div>
            </div>
<!--            拖动点-->
            <div ref="idot" class="idot" :style="{left:curLength+'%'}"
                 @mousedown="mousedown"
                 @mouseup="mouseup"
                 @mouseleave="mouseup"
            ></div>
          </div>
        </div>

<!--        播放结束时间-->
        <div class="left-time">{{songTime}}</div>
<!--        音量-->
        <div class="item item-volume">
          <svg v-if="volume == 0" class="icon">
            <use xlink:href="#icon-yinliangjingyinheix"></use>
          </svg>
          <svg v-else class="icon">
            <use xlink:href="#icon-yinliang1"></use>
          </svg>
          <el-slider class="volume" v-model="volume" :vertical="true"></el-slider>
        </div>
      </div>

<!--      收藏-->
      <div class="item" @click="collection">
        <svg :class="{active:isActive}" class="icon">
          <use xlink:href="#icon-xihuan-shi"></use>
        </svg>
      </div>

<!--    下载-->
    <div class="item" @click="downloadSong">
      <svg class="icon">
        <use xlink:href="#icon-xiazai"></use>
      </svg>
    </div>

<!--    当前播放歌曲列表-->
    <div class="item" @click="changeAside">
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
import {delCollect, download, setCollect} from '../api/index';

export default {
  name: "PlayBar",
  mixins:[mixin],
  data(){
    return{
      nowTime:'00:00', //当前播放进度的时间
      nowTimePoint:'',
      songTime:'00:00', //当前歌曲总时间
      endTimePoint:'',
      curLength:0, //进度条位置
      progressLength: 0, //进度条长度
      mouseStartX: 0,//进度条拖拽开始位置
      tag: false,             //拖拽开始结束的标志，当开始拖拽，它的值才会变成true
      volume: 50,             //音量，默认一半
      toggle: true            //显示隐藏播放器页面
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
  },
  watch:{
    //切换播放状态图标
    isPlay(){
      if (this.isPlay){
        this.$store.commit('setPlayButtonUrl','#icon-zanting');
      } else {
        this.$store.commit('setPlayButtonUrl','#icon-bofang');
      }
    },
    curTime(){
      this.nowTimePoint = this.curTime;
      this.endTimePoint = this.duration;
      this.nowTime = this.formatSeconds(this.nowTimePoint);
      this.songTime = this.formatSeconds(this.duration);
      this.curLength = (this.curTime/this.duration)*100;
    },
    //音量变化
    volume(){
      this.$store.commit('setVolume',this.volume / 100);
    },
    //自动播放下一首
    autoNext(){
      this.next();
    }
  },
  mounted() {
    this.progressLength = this.$refs.progress.getBoundingClientRect().width;
    document.querySelector('.item-volume').addEventListener('click',function(e){
      document.querySelector('.volume').classList.toggle('show-volume');
      e.stopPropagation();
    },false);
    document.querySelector('.volume').addEventListener('click',function(e){
      e.stopPropagation();
    },false);
    document.addEventListener('click',function(){
      document.querySelector('.volume').classList.remove('show-volume');
    },false);
  },
  methods:{
    //控制音乐播放，暂停
    togglePlay() {
      if (this.isPlay){
        this.$store.commit('setIsPlay',false);
      } else {
        this.$store.commit('setIsPlay',true);
      };
    },
    //解析时间
    formatSeconds(value){
      let theTime = parseInt(value);
      let result = '';    //返回值
      let hour = parseInt(theTime / 3600);    //小时
      let minute = parseInt((theTime / 60) % 60); //分钟
      let second = parseInt(theTime % 60);        //秒
      if(hour > 0){
        if(hour < 10){
          result = '0' + hour + ":";
        }else{
          result = hour + ":";
        }
      }
      if(minute > 0){
        if(minute < 10){
          result += "0" + minute + ":";
        }else{
          result += minute + ":";
        }
      }else{
        result += "00:";
      }
      if(second > 0){
        if(second < 10){
          result += "0" + second;
        }else{
          result += second;
        }
      }else{
        result += "00";
      }
      return result;
    },
    //拖拽开始
    mousedown(e){
      this.$store.commit('setPlayButtonUrl','#icon-zanting');
      this.mouseStartX = e.clientX;
      this.tag = true;
    },
    //拖拽结束
    mouseup(){
      this.tag = false;
    },
    //拖拽中
    mousemove(e){
      if(!this.duration){
        return false;
      }
      if(this.tag){
        let movementX = e.clientX - this.mouseStartX;       //点点移动的距离
        let curLength = this.$refs.curProgress.getBoundingClientRect().width;
        let newPercent = ((movementX+curLength)/this.progressLength)*100;
        if(newPercent>100){
          newPercent = 100;
        }
        this.curLength = newPercent;
        this.mouseStartX = e.clientX;
        this.changeTime(newPercent);
      }
    },
    //更改歌曲进度
    changeTime(percent){
      let newCurTime = (percent*0.01)* this.duration;
      this.$store.commit('setChangeTime',newCurTime);
    },
    //点击播放条切换播放进度
    updatemove(e){
      if(!this.tag){
        //进度条的左侧坐标
        let curLength = this.$refs.bg.offsetLeft;
        let newPercent = ((e.clientX - curLength) / this.progressLength) * 100;
        if(newPercent>100){
          newPercent = 100;
        }else if(newPercent<0){
          newPercent = 0;
        }
        this.curLength = newPercent;
        this.changeTime(newPercent);
      }
    },
    //显示播放列表
    changeAside(){
      this.$store.commit('setShowAside',true);
    },
    //上一首
    prev(){
      if(this.listIndex != -1 && this.listOfSongs.length > 1){    //当前处于不可能状态或者只有只有一首音乐的时候不执行）
        if(this.listIndex > 0){                                 //不是第一首音乐
          this.$store.commit('setListIndex',this.listIndex - 1);  //直接返回上一首
        }else{                                                  //当前是第一首音乐
          this.$store.commit('setListIndex',this.listOfSongs.length - 1);  //切换到倒数第一首
        }
        this.toplay1(this.listOfSongs[this.listIndex].url);
      }
    },
    //下一首
    next(){
      if(this.listIndex != -1 && this.listOfSongs.length > 1){    //当前处于不可能状态或者只有只有一首音乐的时候不执行）
        if(this.listIndex < this.listOfSongs.length - 1){                                 //不是最后一首音乐
          this.$store.commit('setListIndex',this.listIndex + 1);  //直接返回下一首
        }else{                                                      //当前是最后一首音乐
          this.$store.commit('setListIndex',0);  //切换到第一首
        }
        this.toplay1(this.listOfSongs[this.listIndex].url);
      }
    },
    //播放音乐
    toplay1: function(url){
      if(url && url != this.url){
        this.$store.commit('setId',this.listOfSongs[this.listIndex].id);
        this.$store.commit('setUrl',this.$store.state.HOST+url);
        this.$store.commit('setPicUrl',this.$store.state.HOST+'/'+this.listOfSongs[this.listIndex].pic);
        this.$store.commit('setTitle',this.listOfSongs[this.listIndex].name);
        this.$store.commit('setArtist',this.listOfSongs[this.listIndex].singerName);
        this.$store.commit('setLyric',this.parseLyric(this.listOfSongs[this.listIndex].lyric));
        this.$store.commit('setIsActive',false);

        let param = new URLSearchParams();
        param.append('userId',this.userId);
        param.append('songId',this.listOfSongs[this.listIndex].id);
        if(this.loginIn){
          axios.post(`/collect/status`,param)
              .then(res =>{
                // console.log(res.data.code)
                if (res.data.code == 1){
                  this.$store.commit('setIsActive',true);
                }
              })
        }
      }
    },
    //转向歌词页面
    toLyric(){
      this.$router.push({path:`/lyric`});
    },
    //下载歌曲
    downloadSong(){
      download(this.url)
          .then(res=>{
            let content = res.data;
            let eleLink = document.createElement('a');
            eleLink.download = `${this.artist}-${this.title}.mp3`;
            eleLink.style.display = 'none';
            //把字符内容转换成blob地址
            let blob = new Blob([content]);
            eleLink.href = URL.createObjectURL(blob);
            //把链接地址加到document里
            document.body.appendChild(eleLink);
            //触发点击
            eleLink.click();
            //然后移除掉这个新加的控件
            document.body.removeChild(eleLink);
          })
          .catch(err =>{
            console.log(err);
          })
    },
    //收藏
    collection(){
      if(this.loginIn){
        let params = new URLSearchParams();
        params.append('userId',this.userId);
        params.append('type',0);
        params.append('songId',this.id);
        setCollect(params)
            .then(res =>{
              if(res.code == 1){
                this.$store.commit('setIsActive',true);
                this.$notify.success('收藏成功');
              }else if(res.code == 2){
                delCollect(params).then(res =>{
                  this.$notify.warning("取消收藏成功");
                  this.$store.commit('setIsActive',false);
                })
              }else{
                this.$notify.error('收藏失败');
              }
            })
      }else{
        this.$notify.warning('请先登录');
      }
    },
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/play-bar.scss';
* {
  user-select: none;
}
</style>