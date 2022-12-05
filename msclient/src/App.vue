<template>
  <div id="app">
    <el-container>
      <el-header><TheHeader/></el-header>
      <el-main>
        <router-view class="music-content"/>
        <SongAudio/>
        <TheAside/>
        <PlayBar/>
        <scroll-top/>
      </el-main>
      <el-footer><TheFooter/></el-footer>
    </el-container>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";
import ScrollTop from "@/components/ScrollTop";
import TheFooter from "@/components/TheFooter";
import SongAudio from "@/components/SongAudio.vue";
import PlayBar from "@/components/PlayBar.vue";
import TheAside from "@/components/TheAside.vue";

export default {
    name: 'App',
    components:{
      TheAside,
      PlayBar,
      SongAudio,
      TheFooter,
      ScrollTop,
      TheHeader
    }
}
</script>

<style scoped>
#app{
  background-color: #fafafa;
  display: flex;
  justify-content: flex-start;
  align-items: stretch;
  flex-direction: column;
  flex-wrap: nowrap;
}
.el-header{
  padding: 0;
}
.el-main{
  padding: 0;
}
.el-footer{
  padding: 0;
  height: 0 !important;
}
</style>
