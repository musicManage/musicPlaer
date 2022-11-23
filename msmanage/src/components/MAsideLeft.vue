<template>
  <div class="sidebar">
    左侧菜单
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
          index: "MIndex",
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
</style>
