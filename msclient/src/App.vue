<template>
  <div id="app">
    <TheHeader/>
    <router-view class="music-content"/>
    <scroll-top/>
  </div>

</template>

<script>
import TheHeader from "@/components/TheHeader";
import ScrollTop from "@/components/ScrollTop";

export default {
    name: 'App',
    components:{
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

</style>
