export const mixin = {
    methods:{
        //根据相对地址获取绝对地址
        getUrl(url){
            return `${this.$store.state.HOST}/${url}`
        },
        changeSex(value){
            if(value == 0){
                return '女';
            }
            if(value == 1){
                return '男';
            }
            if(value == 2){
                return '组合';
            }
            if(value == 3){
                return '不明';
            }
            return value;
        },
        //获取生日
        attachBirth(val){
            return String(val).substr(0,10);
        },
    }
}
