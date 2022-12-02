<template>
  <div class="the-header">
    <div class="header-logo" @click="goHome">
      <svg class="icon">
        <use xlink:href="#icon-erji"></use>
      </svg>
      <span>music</span>
    </div>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        router>
      <template v-for="item in items">
        <template>
          <el-menu-item :index="item.index" :key="item.index">
            <span>{{item.label}}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>

<!--    登录后的状态-->
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
import {navMsg} from '../assets/data/header';


export default {
  name: 'the-header',
  data(){
    return {
      activeIndex: '1',
      items:[//导航栏
        {
          index: "/",
          label: "首页",
          name: 'home',
        },
        {
          index: "Singer",
          label: '歌手',
          name: 'singer',
        },
        {
          index: "SongList",
          label: '歌单',
          name: 'song-list'
        },{
          index: "MyMusic",
          label: '我的音乐',
          name: 'MyMusic'
        },
      ]
    }
  },
  computed: {

  },
  created() {
    this.navMsg = navMsg;
  },
  methods: {
    goHome(){
      this.$router.push({path:"/"});
    },
    goPage(path,name) {
      this.$store.commit('setActiveName',name);
      this.$router.push({path:path});
    }
  }
}
</script>

<style scoped>
.the-header {
  position: fixed;
  display: flex;
  justify-content: flex-start;
  align-items: stretch;
  flex-direction: row;
  flex-wrap: nowrap;
  width: 100%;
  height: 60px;
  line-height: 60px;
  margin: 0;
  background-color: #fefefe;
  box-shadow: 0 0 10px rgb(0 0 0 / 40%);
  box-sizing: border-box;
  z-index: 100;
}
.header-logo {
  argin-top: auto;
  margin-left: 10px;
  font-size: 20px;
  font-weight: bold;
  white-space: nowrap;
  cursor: pointer;
}
.icon {
  width: 46.66667px;
  height: 46.66667px;
  font-size: 46.66667px;
  color: #000000;
  fill: currentColor;
  overflow: hidden;
  position: relative;
  vertical-align: middle;
}
</style>
