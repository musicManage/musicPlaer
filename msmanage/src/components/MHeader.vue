<template>
 <div class="header">
   <div class="collapse-btn" @click="collapseChangse">
    <i class="el-icon-menu"></i>
   </div>
   <div class="logo">
     音乐后台管理
   </div>
 </div>
</template>

<script>
import bus from "@/assets/js/bus"
export default {
  data() {
    return{
      collapse: false
    }
  },
  methods:{
    collapseChangse(){
      this.collapse=!this.collapse;
      bus.$emit('collapse',this.collapse);
    }
  }
}
</script>

<style scoped>
.header {
  position: relative;
  box-sizing: border-box;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}
.logo {
  position: relative;
  float: left;
  line-height: 70px;
}
</style>
