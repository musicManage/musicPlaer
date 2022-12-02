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

<style scoped>
#app{
  background-color: #CCCCCC;
  display: flex;
  justify-content: flex-start;
  align-items: stretch;
  flex-direction: column;
  flex-wrap: nowrap;
}

</style>
