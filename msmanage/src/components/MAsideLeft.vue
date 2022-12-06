<template>
  <div class="sidebar"
       :class="collapse ? 'noSpan':''">
    <el-menu
        :collapse="collapse"
        :default-active="activeIndex"
        class="el-menu-vertical-demo"
        background-color="#334256"
        text-color="#fff"
        active-text-color="#ffd04b"
        router>
      <template v-for="item in items">
        <template>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span>{{item.title}}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from "@/assets/js/bus"

export default {
  data() {
    return {
      collapse: false,
      items:[
        {
          icon: 'el-icon-house',
          index: "/Index",
          title: '系统首页'
        },
        {
          icon: 'el-icon-user',
          index: "/Consumer",
          title: '用户管理'
        },
        {
          icon: 'el-icon-headset',
          index: "/Singer",
          title: '歌手管理'
        },
        {
          icon: 'el-icon-document',
          index: "/SongList",
          title: '歌单管理'
        },
      ],
      activeIndex:'Index'
    }
  },
  created() {
    //通过Bus进行组件间的通信，来折叠侧边栏
    bus.$on('collapse',msg =>{
      this.collapse = msg;
    });
    this.setCurrentRoute();
  },
  watch:{
    $route(){
      this.setCurrentRoute();
    }
  },
  methods: {
    setCurrentRoute(){
      this.activeIndex = this.$route.path;
    }
  }
}
</script>

<style scoped>
.sidebar {
  display: block;
  width: 150px;
  height: 100%;
  background: #334256;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  display: none;
}
.sidebar-el-menu:not(.el-menu--collapse){
  width: 150px;
}
.sidebar >ur {
  height: 100%;
}
.el-menu {
  border-right: 0;
}
.el-menu-item {
  padding-right: 50px;
}
.noSpan .el-menu span{
  display: none;
}
</style>
