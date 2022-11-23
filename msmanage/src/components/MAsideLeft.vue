<template>
  <div class="sidebar">
    <el-menu
        :collapse="collapse"
        default-active="Index"
        class="el-menu-vertical-demo"
        background-color="#334256"
        text-color="#fff"
        active-text-color="#ffd04b" router>
      <template v-for="item in items">
        <template>
          <el-menu-item :index="item.index" :key="item.index" >
            <i :class="item.icon"></i>
            {{item.title}}
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
          index: "Index",
          title: '系统首页'
        },
        {
          icon: 'el-icon-user',
          index: "Consumer",
          title: '用户管理'
        },
        {
          icon: 'el-icon-headset',
          index: "Singer",
          title: '歌手管理'
        },
        {
          icon: 'el-icon-document',
          index: "SongList",
          title: '歌单管理'
        },
      ]
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
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  background: #334256;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  display: none;
}
.sidebar-el-menu :not(el-menu--collapse){
  width: 150px;
}
.sidebar >ur {
  height: 100%;
}
</style>
