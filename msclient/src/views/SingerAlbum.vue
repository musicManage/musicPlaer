<template>
    <div class="singer-album">
        <div class="album-slide">
          <div class="singer-img">
            <img :src="attachImageUrl(singer.pic)">
          </div>
          <ul class="">

          </ul>
        </div>
    </div>
</template>

<script>
import {mixin} from "@/mixins";
import {mapGetters} from "vuex";
import {songOfSId,songOfId} from "@/api";


export default {
  name: "singer-album",
  mixins:[mixin],

  components: {

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
    this.singerId = this.$router.params.id;
    this.singer = this.tempList;
    this.getSongOfSingerId();
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
    attach(value){
      if(value==0){
        return '女'
      }
    }
  }
}

</script>

<style lang="scss" scoped>
@import "../assets/css/singer-album.scss";
</style>