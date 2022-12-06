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
          <SongMsg :songMsg="listOfSongs"></SongMsg>
    </div>
    </div>

  </div>
</template>

<script>
import {mixin} from "@/mixins";
import {mapGetters} from "vuex";
import {listSongOfSongId} from "@/api";
import SongMsg from "@/components/SongMsg";
import SongMsg2 from "@/components/SongMsg2.vue";

export default {
  name: "song-list-album",
  mixins:[mixin],
  components: {
    SongMsg2,
    SongMsg
  },
  data(){
    return{
      songLists:[],   //当前页面需要展示的歌曲列表
      songLists2:[],   //当前页面需要展示的歌曲列表
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
    // console.log(this.tempList.id);
    this.songListsId = this.tempList.id;
    this.getSongId();
  },
  methods:{
    //获取当前歌单的歌曲列表
    getSongId(){
      listSongOfSongId(this.songListsId)
          .then(res => {
            this.songLists = res;
            let resultData = [];
            for (let i = 0; i < this.songLists.length; i++) {
              let item = JSON.parse(
                  JSON.stringify(this.songLists[i])
                      .replace(/songId/g, 'id')
                      .replace(/url/g, 'url')
                      .replace(/songPic/g, 'songPic')
                      .replace(/songName/g, 'name')
                      .replace(/singerName/g, 'singerName')
                      .replace(/lyric/g, 'lyric')
              );
              resultData.push(item);
            }
            this.$store.commit('setListOfSongs',resultData);
          })
           .catch(err =>{
             console.log(err)
          })
    },
    //根据歌曲id获取歌曲信息
    //   getSongList(id){
    //     songOfId(id)
    //         .then(res =>{
    //           this.songLists.push(res);
    //         })
    //         .catch(err =>{
    //           console.log(err)
    //         })
    //   }
    }
  }
</script>

<style lang="scss" scoped>
@import "../assets/css/song-list-album.scss";
</style>