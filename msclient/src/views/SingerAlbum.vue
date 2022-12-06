<template>
    <div class="singer-album">
        <div class="album-slide">
          <div class="singer-img">
            <img :src="attachImageUrl(tempList.pic)">
          </div>
          <ul class="info">
            <li v-if="singer.sex==0||singer.sex==1">{{attachSex(singer.sex)}}</li>
            <li>生日：{{attachBirth(singer.birth)}}</li>
            <li>故乡：{{singer.location}}</li>
          </ul>
        </div>
        <div class="album-content">
          <div class="intro">
            <h2>{{singer.name}}</h2>
            <br/>
            <span>{{singer.introduction}}</span>
          </div>

          <div class="content">
            <content-list :songList="listOfSongs">
              <template slot="title">歌单</template>
            </content-list>
          </div>
        </div>
    </div>
</template>

<script>
import {mixin} from "@/mixins";
import {mapGetters} from "vuex";
import {songOfSId} from "@/api/";
import ContentList from "@/components/ContentList.vue";


export default {
  name: "singer-album",
  mixins:[mixin],
  components: {
    ContentList

  },
  data(){
    return {
      singerId:'',
      singer:{},
    }
  },
  computed:{
    ...mapGetters([
        'listOfSongs',
        'tempList',
    ])
  },
  created() {
    this.singer = this.tempList;
    this.getSongOfSingerId();
    this.singerId = this.$router.params.id;
  },
  methods: {
    //根据歌手id查询歌曲
    getSongOfSingerId() {
      songOfSId(this.singerId)
          .then(res => {
            this.$store.commit('setListOfSongs',res);
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