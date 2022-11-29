<template>
  <div id="app">
    <TheHeader/>
    <router-view class="music-content"/>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";
export default {
    name: 'App',
    components:{
      TheHeader
    }
}
</script>

<style  lang="scss" scoped>
    @import './assets/css/app.scss';


</style>
