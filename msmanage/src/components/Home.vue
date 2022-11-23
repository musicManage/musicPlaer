<template>
 <div>
   <MHeader></MHeader>
   <MAsideLeft></MAsideLeft>
   <router-view></router-view>
 </div>
</template>

<script>
import MHeader from "@/components/MHeader";
import MAsideLeft from "@/components/MAsideLeft";
export default {
  components: {MAsideLeft, MHeader},
  comments:{
    MHeader,
    MAsideLeft
  }
}
</script>

<style scoped>
html,body {
  padding: 0;
  margin: 0;
}
</style>
