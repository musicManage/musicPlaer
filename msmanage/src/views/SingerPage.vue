<template>
 <div class="table">
   <div class="container">
     <div class="handle-box">
       <el-input v-model="select_word" size="mini" placeholder="请输入歌手名" class="handle-input" clearable>
       </el-input>
       <el-button type="primary" size="mini" @click="selectSingerList">搜索</el-button>
      <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌手</el-button>
     </div>
   </div>
   <el-table
       size="mini" border style="width: 100%;height: 600px" :data="tableData">
     <el-table-column
         prop="pic"
         label="歌手头像"
         width="110"
         align="center">
       <template slot-scope="scope">
         <div class="singer-img">
           <img :src="getUrl(scope.row.pic)" style="width: 100%"/>
         </div>
         <el-upload :action="updatePic(scope.row.id)" :before-upload="beforeUpdate" :on-success="updateSuccess">
           <el-button size="mini" >更新图片</el-button>
         </el-upload>
       </template>
     </el-table-column>
     <el-table-column prop="name" label="歌手" width="120" align="center"></el-table-column>
     <el-table-column label="性别" width="50" align="center">
       <template slot-scope="scope">
         {{changeSex(scope.row.sex)}}
       </template>
     </el-table-column>
     <el-table-column label="生日" width="120" align="center">
       <template slot-scope="scope">
         {{attachBirth(scope.row.birth)}}
       </template>
     </el-table-column>
     <el-table-column prop="location" label="地区" width="100" align="center"></el-table-column>
     <el-table-column label="简介">
       <template slot-scope="scope">
         <p style="height:100px;overflow-y:scroll">{{scope.row.introduction}}</p>
       </template>
     </el-table-column>
   </el-table>
   <div class="pagination">
       <span class="demonstration">调整每页显示条数</span>
       <el-pagination
           @size-change="handleSizeChange"
           @current-change="handleCurrentChange"
           :current-page.sync="currentPage2"
           :page-sizes="[100, 200, 300, 400]"
           :page-size="100"
           layout="sizes, prev, pager, next"
           :total="1000">
       </el-pagination>
   </div>

<!--   添加歌手-->
   <el-dialog title="添加歌手" :visible.sync="centerDialogVisible" width="400px" center>
     <el-form :model="registerForm" ref="registerForm" label-width="80px">
       <el-form-item prop="name" label="歌手名" size="mini">
         <el-input v-model="registerForm.name" placeholder="歌手名"></el-input>
       </el-form-item>
       <el-form-item prop="sex" label="性别" size="mini">
         <el-radio-group v-model="registerForm.sex">
           <el-radio :label="1">男</el-radio>
           <el-radio :label="0">女</el-radio>
           <el-radio :label="2">组合</el-radio>
           <el-radio :label="3">未知</el-radio>
         </el-radio-group>
       </el-form-item>
       <el-form-item prop="birth" label="生日" size="mini">
         <el-date-picker type="date" v-model="registerForm.birth" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
       </el-form-item>
       <el-form-item prop="location" label="地区" size="mini">
         <el-input v-model="registerForm.location" placeholder="地区"></el-input>
       </el-form-item>
       <el-form-item prop="name" label="简介" size="mini">
         <el-input type="textarea" v-model="registerForm.introduction" placeholder="简介"></el-input>
       </el-form-item>
     </el-form>
     <span slot="footer">
       <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="addSinger">添加</el-button>
     </span>
   </el-dialog>
 </div>
</template>

<script>
import {allSinger, setSinger} from "@/api/index";
import {mixin} from "@/mixins";
export default {
  mixins:[mixin],
  data() {
    return {
      centerDialogVisible:false,
      registerForm: {
        name:'',
        sex:'',
        pic:'',
        birth:'',
        location:'',
        introduction:'',
      },
      tableData:[],
      select_word:'',
      singerList:[],
      pageSize: 5,
    }
  },
  created() {
    this.getData();
  },
  watch: {
    singerList() {
      if (this.select_word != ''){
        this.tableData = [];
        for (let item of this.singerList){
          this.tableData.push(item);
        }
      } else {
        this.getData();
      }
    }
  },
  methods:{
    getData(){
      this.tableData=[];
      allSinger().then(res => {
        this.tableData = res.data;
      })
    },
    addSinger(){
      let params = new URLSearchParams();
      params.append('name',this.registerForm.name);
      params.append('sex',this.registerForm.sex);
      params.append('pic',"img/singerPic/user.jpg");
      params.append('birth',this.registerForm.birth);
      params.append('location',this.registerForm.location);
      params.append('introduction',this.registerForm.introduction);

      setSinger(params)
          .then(res => {
            if (res.code==1){
              this.$notify({
                title: '添加成功',
                type: 'success',
              });
              this.registerForm.name='';
              this.registerForm.sex='';
              this.registerForm.birth='';
              this.registerForm.location='';
              this.registerForm.introduction='';
              this.getData();
            } else {
              this.$notify({
                title: '添加失败',
                type: 'error',
              });
            }
          })
          .catch(err => {
            console.log(err);
          });
      this.centerDialogVisible = false;
    },
    updatePic(id){
      return `${this.$store.state.HOST}/singer/pic/update/${id}`;
    },
    selectSingerList(){
      let param = new URLSearchParams();
      param.append('name',this.select_word);
      axios.post("/singer/name",param).then(res => {
        this.singerList = res.data;
      })

    }
  }
}
</script>

<style scoped>
.table {
  min-width: 800px;
}
.container{
  padding: 20px;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.handle-box {
  margin-bottom: 20px;
}
.singer-img {
  width: 100%;
  height: 80px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
.handle-input {
  width: 300px;
  display: inline-block;
}
</style>
