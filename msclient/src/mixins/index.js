export const mixin ={
    methods: {
        //获取图片地址
        attachImageUrl (srcUrl){
            return `${this.$store.state.HOST}/${srcUrl}`;
        },
        //播放
        toplay: function (id,url){
            this.$store.commit('setId',id);
            this.$store.commit('setUrl',this.$store.state.HOST+url);
        }
    }
}