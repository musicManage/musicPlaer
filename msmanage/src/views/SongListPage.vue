<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="筛选关键字" class="handle-input" clearable>
        </el-input>
        <el-button type="primary" size="mini" @click="selectSongListList">搜索</el-button>
        <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌单</el-button>
      </div>
    </div>

    <el-table
        size="mini" ref="multipleTable" border style="width: 100%;height: 667px" :data="data" @selection-change="handleSelectionChange">
      <!--     多选-->
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column
          prop="pic"
          label="歌单头像"
          width="110"
          align="center">
        <template slot-scope="scope">
          <div class="songList-img">
            <img :src="getUrl(scope.row.pic)" style="width: 100%"/>
          </div>
          <el-upload :action="updatePic(scope.row.id)" :before-upload="beforeUpdate" :on-success="updateSuccess">
            <el-button size="mini" >更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题" width="120" align="center"></el-table-column>
      <el-table-column label="简介">
        <template slot-scope="scope">
          <p style="height:100px;overflow-y:scroll">{{scope.row.introduction}}</p>
        </template>
      </el-table-column>
      <el-table-column prop="style" label="风格" width="120" align="center"></el-table-column>
      <el-table-column label="歌曲管理" width="110" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="songEdit(scope.row.id)">歌曲管理</el-button>
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

    <!--   添加歌单-->
    <el-dialog title="添加歌单" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" ref="registerForm" label-width="80px">
        <el-form-item prop="title" label="标题" size="mini">
          <el-input v-model="registerForm.title" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item prop="name" label="简介" size="mini">
          <el-input type="textarea" v-model="registerForm.introduction" placeholder="简介"></el-input>
        </el-form-item>
        <el-form-item prop="style" label="风格" size="mini">
          <el-select v-model="registerForm.style" placeholder="请选择风格">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
       <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="addSongList">添加</el-button>
     </span>
    </el-dialog>

    <!--   编辑歌单-->
    <el-dialog title="编辑歌单信息" :visible.sync="editVisible" width="400px" center>
      <el-form :model="editForm" ref="registerForm" label-width="80px">
        <el-form-item prop="title" label="标题" size="mini">
          <el-input v-model="editForm.title" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item prop="name" label="简介" size="mini">
          <el-input type="textarea" v-model="editForm.introduction" placeholder="简介"></el-input>
        </el-form-item>
        <el-form-item prop="style" label="风格" size="mini">
          <el-select v-model="editForm.style" placeholder="请选择风格">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
       <el-button size="mini" @click="editVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="editsongList">保存</el-button>
     </span>
    </el-dialog>

    <!--   删除窗口-->
    <el-dialog title="删除歌单" :visible.sync="delVisible" width="400px" center>
      <div align="center">是否确认删除</div>
      <span slot="footer">
       <el-button size="mini" @click="delVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="deleteRow">确认删除</el-button>
     </span>
    </el-dialog>
  </div>
</template>

<script>
import {allSongList, delSongList, setSongList, updateSongList} from "@/api/index"
import {mixin} from "@/mixins";
export default {
  mixins:[mixin],
  data() {
    return {
      centerDialogVisible:false,//添加弹窗显示
      editVisible:false,//编辑弹窗显示
      delVisible:false,//删除弹窗是否显示
      registerForm: {
        title:'',
        introduction:'',
        pic:'',
        style:'',
      },
      editForm: {
        id:'',
        title:'',
        introduction:'',
        pic:'',
        style:'',
      },
      tableData:[],
      select_word:'',
      songListList:[],
      pageSize: 5,
      currentPage: 1,
      idx:'',//当前选择项
      multipleSelection: [],   //已选项
      options:[
        {
          value:'华语',
          label:'华语'
        },
        {
          value:'粤语',
          label:'粤语'
        },
        {
          value:'欧美',
          label:'欧美'
        },
        {
          value:'日韩',
          label:'日韩'
        },
        {
          value:'BGM',
          label:'BGM'
        },
        {
          value:'轻音乐',
          label:'轻音乐'
        },
        {
          value:'乐器',
          label:'乐器'
        },
      ],
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
    songListList() {
      if (this.select_word == ''){
        this.getData();
      } else {
        this.tableData = [];
        this.tableData = this.songListList;
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
      allSongList().then(res => {
        this.tableData = res.data;
      })
    },
    addSongList(){
      let params = new URLSearchParams();
      params.append('title',this.registerForm.title);
      params.append('style',this.registerForm.style);
      params.append('pic',"img/songListPic/default.jpg");
      params.append('introduction',this.registerForm.introduction);

      setSongList(params)
          .then(res => {
            if (res.code==1){
              this.$notify({
                title: '添加成功',
                type: 'success',
              });
              this.registerForm.title='',
              this.registerForm.introduction='',
              this.registerForm.pic='',
              this.registerForm.style='',
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
      return `${this.$store.state.HOST}/songList/update/pic/${id}`;
    },
    selectSongListList(){
      let param = new URLSearchParams();
      param.append("title",this.select_word);
      axios.post("/songList/title",param).then(res => {
        // console.log(res.data);
        this.songListList = res.data;
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
        title:row.title,
        introduction: row.introduction,
        style:row.style,
      }
    },
    editsongList(){
      let params = new URLSearchParams();
      params.append('id',this.editForm.id);
      params.append('title',this.editForm.title);
      params.append('style',this.editForm.style);
      params.append('introduction',this.editForm.introduction);

      updateSongList(params)
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
      this.$router.push({path:`/ListSong`,query:{id}});
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
.songList-img {
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

