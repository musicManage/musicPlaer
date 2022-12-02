<template>
 <div class="table">
   <div class="container">
     <div class="handle-box">
       <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
       <el-input v-model="select_word" size="mini" placeholder="请输入歌手名" class="handle-input" clearable>
       </el-input>
       <el-button type="primary" size="mini" @click="selectSingerList">搜索</el-button>
      <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌手</el-button>
     </div>
   </div>

   <el-table
       size="mini" ref="multipleTable" border style="width: 100%;height: 667px" :data="data" @selection-change="handleSelectionChange">
<!--     多选-->
     <el-table-column type="selection" width="40"></el-table-column>
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
     <el-table-column label="歌曲管理" width="110" align="center">
       <template slot-scope="scope">
         <el-button size="mini" @click="songEdit(scope.row.id,scope.row.name)">歌曲管理</el-button>
       </template>
     </el-table-column>
     <el-table-column label="操作" width="150" align="center">
       <template slot-scope="scope">
         <el-button size="mini" type="success" @click="handleEdit(scope.row)">编辑</el-button>
         <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
       </template>
     </el-table-column>
   </el-table>
   <div class="pagination">
    <el-pagination background layout="total,prev,pager,next" :current-page="currentPage" :page-size="pageSize" :total="tableData.length"
      @current-change="handleCurrentChange">
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
         <el-select v-model="registerForm.location" placeholder="请选择地区">
           <el-option
               v-for="item in options"
               :key="item.value"
               :label="item.label"
               :value="item.value">
           </el-option>
         </el-select>
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

<!--   编辑歌手-->
   <el-dialog title="编辑歌手信息" :visible.sync="editVisible" width="400px" center>
     <el-form :model="editForm" ref="registerForm" label-width="80px">
       <el-form-item prop="name" label="歌手名" size="mini">
         <el-input v-model="editForm.name" placeholder="歌手名"></el-input>
       </el-form-item>
       <el-form-item prop="sex" label="性别" size="mini">
         <el-radio-group v-model="editForm.sex">
           <el-radio :label="1">男</el-radio>
           <el-radio :label="0">女</el-radio>
           <el-radio :label="2">组合</el-radio>
           <el-radio :label="3">未知</el-radio>
         </el-radio-group>
       </el-form-item>
       <el-form-item prop="birth" label="生日" size="mini">
         <el-date-picker type="date" v-model="editForm.birth" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
       </el-form-item>
       <el-form-item prop="location" label="地区" size="mini">
         <el-select v-model="editForm.location" placeholder="请选择地区">
           <el-option
               v-for="item in options"
               :key="item.value"
               :label="item.label"
               :value="item.value">
           </el-option>
         </el-select>
       </el-form-item>
       <el-form-item prop="name" label="简介" size="mini">
         <el-input type="textarea" v-model="editForm.introduction" placeholder="简介"></el-input>
       </el-form-item>
     </el-form>
     <span slot="footer">
       <el-button size="mini" @click="editVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="editSinger">保存</el-button>
     </span>
   </el-dialog>

<!--   删除窗口-->
   <el-dialog title="删除歌手" :visible.sync="delVisible" width="400px" center>
     <div align="center">是否确认删除</div>
     <span slot="footer">
       <el-button size="mini" @click="delVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="deleteRow">确认删除</el-button>
     </span>
   </el-dialog>
 </div>
</template>

<script>
import {allSinger, delSinger, delSongList, setSinger, updateSinger} from "@/api/index"
import {mixin} from "@/mixins";
export default {
  mixins:[mixin],
  data() {
    return {
      centerDialogVisible:false,//添加弹窗显示
      editVisible:false,//编辑弹窗显示
      delVisible:false,//删除弹窗是否显示
      registerForm: {
        name:'',
        sex:'',
        pic:'',
        birth:'',
        location:'',
        introduction:'',
      },
      editForm: {
        id:'',
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
      currentPage: 1,
      idx:'',//当前选择项
      multipleSelection: [],   //已选项
      options:[
        {
          value:'中国',
          label:'中国'
        },
        {
          value:'韩国',
          label:'韩国'
        },
        {
          value:'日本',
          label:'日本'
        },
        {
          value:'美国',
          label:'美国'
        },
        {
          value:'新加坡',
          label:'新加坡'
        },
        {
          value:'意大利',
          label:'意大利'
        },
        {
          value:'马来西亚',
          label:'马来西亚'
        },
        {
          value:'西班牙',
          label:'西班牙'
        },
      ]
    }
  },
  created() {
    this.getData();
  },
  computed: {
    data() {
        return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
    }
  },
  watch: {
    singerList() {
      if (this.select_word == ''){
        this.getData();
      } else {
        this.tableData = [];
        this.tableData = this.singerList;
      }
    },
    select_word(newV){
      if (newV == ''){
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
      param.append("name",this.select_word);
      axios.post("/singer/name",param).then(res => {
        // console.log(res.data);
        this.singerList = res.data;
      })
    },
    handleCurrentChange(val){
      //获取当前页
      this.currentPage = val;
    },
    handleEdit(row){
      this.editVisible = true;
      this.editForm = {
        id:row.id,
        name:row.name,
        sex:row.sex,
        birth: row.birth,
        location: row.location,
        introduction: row.introduction,
      };
    },
    editSinger(){
      let params = new URLSearchParams();
      params.append('id',this.editForm.id);
      params.append('name',this.editForm.name);
      params.append('sex',this.editForm.sex);
      params.append('birth',this.editForm.birth);
      params.append('location',this.editForm.location);
      params.append('introduction',this.editForm.introduction);

      updateSinger(params)
          .then(res => {
            if (res.code==1){
              this.$notify({
                title: '修改成功',
                type: 'success',
              });
              this.getData();
            } else {
              this.$notify({
                title: '修改失败',
                type: 'error',
              });
            }
          })
          .catch(err => {
            console.log(err);
          });
      this.editVisible = false;
    },
    deleteRow(){
      let param = new URLSearchParams();
      param.append("id",this.idx);
      delSongList(param)
          .then(res => {
            if (res.code==1){
              this.$notify({
                title: '删除成功',
                type: 'success',
              });
              this.getData();
            } else {
              this.$notify({
                title: '删除失败',
                type: 'error',
              });
            }
          });
      this.delVisible = false;
    },
    //转向歌曲管理页面
    songEdit(id,name){
      this.$router.push({path:`/Song`,query:{id,name}});
    },
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
.pagination {
  display: flex;
  justify-content: center;
}
</style>
