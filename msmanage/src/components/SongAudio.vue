<template>
  <div class="song-audio">
    <audio id="player"
           :src="url"
           controls="controls"
           preload="true"
           @canplay="startPlay"
           @ended="ended"
    >

    </audio>
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
export default {
  name: 'song-audio',
  computed:{
    ...mapGetters([
        'id',
        'url',
        'isPlay'
    ])
  },
  watch:{
    //监听是播放还是暂停状态
    isPlay(){
      this.togglePlay();
    }
  },
  methods:{
    //获取链接后准备播放
    startPlay(){
      let player = document.querySelector('#player');
      player.play();//开始播放
    },
    //播放完成后触发
    ended(){
      this.isPlay = false;
    },
    togglePlay(){
      let player = document.querySelector('#player');
      if (this.isPlay){
        player.play();
      } else {
        player.pause();
      }
    }
  }
}
</script>

<style>
.song-audio {
  display: none;
}
</style>
