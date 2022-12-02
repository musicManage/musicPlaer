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
      <div class="search">
        <form action="">
          <input>
          <!-- placeholder 占位符 内容输入自动清除默认值 -->
          <input type="submit" value="" >
        </form>
      </div>
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
/*搜索框部分*/
.search {
  width: 410px;   /*360 50 */
  height: 60px;
  float: right;
  padding-top: 5px;
}
.search input { /*属性选择器 type为text文本框*/
  width: 300px;
  height: 20px;
  padding-left: 20px;
  float: left;
  padding-top: 10px;
  margin-top: 10px;
  border-style:solid hidden solid solid;
  border-radius: 15px 0px 0px 15px;
}

.search input[type=submit] { /*属性选择器 type为submit文本框*/
  width: 50px;
  height: 34px;
  float: left;
  padding-top: 10px;
  margin-top: 10px;
  background: #00a4ff url(../assets/img/search.png)  center no-repeat;
  border-style:solid solid solid hidden;
  border-radius: 0px 15px 15px 0px;
}
input {
  outline:none;
}

/*搜索框部分结束*/

</style>
