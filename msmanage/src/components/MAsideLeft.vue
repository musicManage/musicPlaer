<template>
  <div class="sidebar">
    <el-menu
        default-active="2"
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
export default {
  data() {
    return {
      collapse: false,
      items:[
        {
          icon: 'el-icon-document',
          index: "Index",
          title: '系统首页'
        },
        {
          icon: 'el-icon-document',
          index: "Consumer",
          title: '用户管理'
        },
        {
          icon: 'el-icon-document',
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
.sidebar-el-menu {
  width: 150px;
}
</style>
