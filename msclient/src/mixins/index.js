export const mixin ={
    methods: {
        //获取图片地址
        attachImageUrl (srcUrl){
            return `${this.$store.state.HOST}/${srcUrl}`;
        },
        //播放
        toplay: function (id,url,pic,index,songName,singerName,lyric){
            this.$store.commit('setId',id);
            this.$store.commit('setUrl',this.$store.state.HOST+url);
            this.$store.commit('setPicUrl',this.$store.state.HOST+'/'+pic);
            this.$store.commit('setListIndex',index);
            this.$store.commit('setTitle',songName);
            this.$store.commit('setArtist',singerName);
            this.$store.commit('setLyric',lyric);
        }
    }
}