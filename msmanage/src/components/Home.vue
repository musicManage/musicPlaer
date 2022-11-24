<template>
 <div>
   <el-container>
     <el-header style="padding: 0" height="70px"><MHeader></MHeader></el-header>
     <el-container>
       <el-aside :width="collapse ?'70px':'150px'"><MAsideLeft></MAsideLeft></el-aside>
       <el-main><router-view></router-view></el-main>
     </el-container>
   </el-container>
 </div>
</template>

<script>
import MHeader from "@/components/MHeader";
import MAsideLeft from "@/components/MAsideLeft";
import bus from "@/assets/js/bus"
export default {
  components: {MAsideLeft, MHeader},
  comments:{
    MHeader,
    MAsideLeft
  },
  data() {
    return {
      collapse: false
    }
  },
  created() {
    bus.$on('collapse',msg =>{
      this.collapse = msg;
    })
  }
}
</script>

<style scoped>
.el-header{
  background-color: #253041;
  color: #fff;
  text-align: center;
  line-height: 100px;
}
.el-aside {
  overflow-x:hidden;
  transition: width 0.3s;
  -webkit-transition: width 0.3s;
}
</style>
