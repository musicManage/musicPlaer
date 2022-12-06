<template>
    <div class="singer-album">
        <div class="album-slide">
          <div class="singer-img">
            <img :src="attachImageUrl(tempList.pic)">
          </div>
          <ul class="info">
            <li v-if="tempList.sex==0||tempList.sex==1">
              性别：{{attachSex(tempList.sex)}}
            </li>
            <li>
              生日：{{attachBirth(tempList.birth)}}
            </li>
            <li>
              故乡：{{tempList.location}}
            </li>
          </ul>
        </div>
        <div class="album-content">
          <div class="intro">
            <h2>{{tempList.name}}</h2>
            <br/>
            <span style="font-size: small">{{tempList.introduction}}</span>
          </div>

            <SongMsg :songMsg="singer"/>
        </div>
    </div>
</template>

<script>
import {mixin} from "@/mixins";
import {mapGetters} from "vuex";
import {songOfSId} from "@/api/";
import ContentList from "@/components/ContentList.vue";
import SongMsg from "@/components/SongMsg.vue";


export default {
  name: "singer-album",
  mixins:[mixin],
  components: {
    SongMsg,
    ContentList

  },
  data(){
    return {
      singerId:'',
      singer:[],
    }
  },
  computed:{
    ...mapGetters([
        'listOfSongs',
        'tempList',
    ])
  },
  created() {
    this.singerId = this.tempList.id;
    this.getSongOfSingerId();
  },
  methods: {
    //根据歌手id查询歌曲
    getSongOfSingerId() {
      songOfSId(this.singerId)
          .then(res => {
            this.singer = res;

            this.$store.commit('setListOfSongs',this.singer);
          })
          .catch(err => {
            console.log(err)
          })
    },
    //获取性别
    attachSex(value){
      if(value == 0){
        return '女'
      }else if(value == 1){
        return '男'
      }
      return ''
    }
  }
}

</script>

<style lang="scss" scoped>
@import "../assets/css/singer-album.scss";
</style>