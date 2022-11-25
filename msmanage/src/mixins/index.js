export const mixin = {
    method:{
        //根据相对地址获取绝对地址
        getUrl(url){
            return `${this.$store.state.HOST}/${url}`;
        }
    }
}
