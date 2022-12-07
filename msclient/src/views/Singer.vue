<template>
  <el-tabs v-model="activeName" @tab-click="getSinger" style="padding-left: 20px">
    <el-tab-pane label="全部" name="all" @click="getSinger">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>
    <el-tab-pane label="男歌手" name="man">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>
    <el-tab-pane label="女歌手" name="woman">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>
    <el-tab-pane label="组合" name="team">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>
    <el-tab-pane label="未知" name="unknown">
      <div class="section">
        <content-list :contentList="songsList"></content-list>
      </div>
    </el-tab-pane>

  </el-tabs>

</template>
<script>

import TheFooter from "@/components/TheFooter";
import contentList from "@/components/ContentList";
import {allSinger, getSingerBySex} from "@/api";

export default {
  name: 'singer',
  components: {
    TheFooter,
    contentList,
  },
  data() {
    return {
      activeName: 'all',
      songsList: []
    }
  },

  created() {
    this.getSinger();
  },

  methods: {
    getSinger() {
      if (this.activeName == 'all'){
        //获取全部歌手
        allSinger().then(res => {
          this.songsList = res.data;
        })
      } else if (this.activeName == 'man'){
        getSingerBySex(1).then(res => {
          this.songsList = res.data;
        })
      }else if (this.activeName == 'woman') {
        getSingerBySex(0).then(res => {
          this.songsList = res.data;
        })
      }else if (this.activeName == 'team') {
        getSingerBySex(2).then(res => {
          this.songsList = res.data;
        })
      }else if (this.activeName == 'unknown') {
        getSingerBySex(3).then(res => {
          this.songsList = res.data;
        })
      }
    },
  }
}

</script>

<style scoped>
li {
  list-style: none;
}
h3 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: medium;
  font-weight: 400;
  zoom: 1;
  margin-top: 10px;
  text-align: center;
  width: 100%;
  cursor: pointer;
}
ul {
  margin-right: -20px;
}
</style>

