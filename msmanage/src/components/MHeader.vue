<template>
 <div class="header">
   <!-- 折叠按钮图片-->
   <div class="collapse-btn" @click="collapseChanges">
    <i class="el-icon-menu"></i>
   </div>

   <div class="logo">
     音乐后台管理
   </div>

   <div class="header-right">
     <div class="admin-box">
       <img src="../assets/img/admin.jpg" alt=""/>
     </div>
     <el-dropdown class="admin-name" trigger="click" @command="handleCommand">
       <span class="el-dropdown-link">
         {{ userName }}
         <i class="el-icon-caret-bottom"></i>
       </span>
       <el-dropdown-menu slot="dropdown">
         <el-dropdown-item command="logout">退出登录</el-dropdown-item>
       </el-dropdown-menu>
     </el-dropdown>

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
    collapseChanges(){
      this.collapse=!this.collapse;
      bus.$emit('collapse',this.collapse);
    },
    handleCommand(command) {
      if (command === "logout"){
        localStorage.removeItem('ausername');
        this.$router.push('/');
      }
    }
  },
  computed: {
    userName() {
      return localStorage.getItem('ausername');
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
.header-right {
  float: right;
  padding-right: 50px;
  display: flex;
  height: 70px;
  align-items: center;
}
.admin-box {
  margin-left: 20px;
  padding-right: 7px;
}
.admin-box img{
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link{
  color: #ffffff;
  cursor: pointer;
}
.admin-name {
}
</style>
