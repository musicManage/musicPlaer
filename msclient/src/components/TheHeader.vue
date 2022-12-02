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
    ...mapGetters([
        'activeName'
    ])
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

<style lang="scss" scoped>
@import '../assets/css/the-header.scss';

</style>
