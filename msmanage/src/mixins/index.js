
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
                return '未知';
            }
            return value;
        },
        //获取生日
        attachBirth(val){
            return String(val).substring(0,10);
        },
        //上传图片校验
        beforeUpdate(file) {
            const isImg = (file.type === 'image/jpeg')||(file.type === 'image/png');
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isImg) {
                this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
            } else if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }

            return isImg && isLt2M;
        },
        //上传成功后的事务处理
        updateSuccess(res){
            let code = res.code;
            let _this = this;
            if (res.code == 1){
                _this.getDate();
                _this.$notify({
                    title: '上传成功',
                    type: 'success',
                });
            } else {
                _this.$notify({
                    type:'error',
                    title:'上传失败',
                })
            }
        }
    }
}
