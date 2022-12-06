<template>
  <div class="song-list-album">
    <div class="album-slide">
      <div class="album-img">
          <img :src="attachImageUrl(tempList.pic)">
      </div>
      <div class="album-info">
        <h2>简介：</h2>
        <br/>
        <span style="font-size:15px">
          {{tempList.introduction}}
        </span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
          <p>{{tempList.title}}</p>
      </div>
      <div class="songs-body" style="padding-top: 20px">
      <el-tabs type="border-card" v-model="activeName" stretch @tab-click="getMsg">
        <el-tab-pane label="歌曲" name="first">
          <SongMsg :songMsg="songMsg"></SongMsg>
        </el-tab-pane>
        <el-tab-pane label="歌单" name="second">
          <ContentList :contentList="SongListMsg"></ContentList>
        </el-tab-pane>
      </el-tabs>
    </div>
    </div>

  </div>
</template>

<script>
import {mixin} from "@/mixins";
import {mapGetters} from "vuex";
import {listSongOfSongId,songOfSId,songOfId} from "@/api";
import SongMsg from "@/components/SongMsg";

export default {
  name: "song-list-album",
  mixins:[mixin],
  components: {
    SongMsg
  },
  data(){
    return{
      songLists:[],   //当前页面需要展示的歌曲列表
      songListsId:'', //前面传来的歌单id
    }
  },
  computed:{
    ...mapGetters([
        'listOfSongs', //当前播放列表
        'tempList',    //当前歌单对象
    ])
  },
  created() {
    this.songListsId = this.$router.params.id;
    this.getSongId();
  },
  methods:{
    //获取当前歌单的歌曲列表
    getSongId(){
      listSongOfSongId(this.songListsId)
          .then(res => {
            for(let item of res){
                this.getSongList(item.songId);
            }
            this.$store.commit('setListOfSongs',this.songLists);
          })
           .catch(err =>{
             console.log(err)
          })
    },
    //根据歌曲id获取歌曲信息
      getSongList(id){
        songOfId(id)
            .then(res =>{
              this.songLists.push(res);
            })
            .catch(err =>{
              console.log(err)
            })
      }
    }
  }
</script>

<style lang="scss" scoped>
@import "../assets/css/song-list-album.scss";
</style>