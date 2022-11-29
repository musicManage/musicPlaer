export const mixin ={
    methods: {
        //获取图片地址
        attachImageUrl (srcUrl){
            return `${this.$store.state.HOST}/${srcUrl}`;
        }
    }
}