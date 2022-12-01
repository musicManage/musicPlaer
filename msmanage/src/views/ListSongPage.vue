<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-tickets"></i>歌单歌曲信息
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="请输入歌曲名" class="handle-input" clearable>
        </el-input>
        <el-button type="primary" size="mini" @click="selectSingerList">搜索</el-button>
        <el-button type="primary" size="mini" @click="addSongView">添加歌曲</el-button>
      </div>
    </div>
    <el-table size="mini" ref="multipleTable" border style="width:100%" height="653px" :data="data" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="songName" label="歌手-歌名" align="center"></el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination background layout="total,prev,pager,next" :current-page="currentPage" :page-size="pageSize" :total="tableData.length"
                     @current-change="handleCurrentChange">
      </el-pagination>
    </div>

    <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" ref="registerForm" label-width="80px" action="" id="tf">
        <el-form-item prop="singerName" label="歌手名字" size="mini">
          <el-select v-model="registerForm.singerId" placeholder="请选择">
            <el-option
                v-for="item in singerList"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="songName" label="歌曲名字" size="mini" v-if="registerForm.singerId != ''">
          <el-select v-model="registerForm.songId" placeholder="请选择">
            <el-option
                v-for="item in songList"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="add">确定</el-button>
            </span>
    </el-dialog>
    <el-dialog title="删除歌曲" :visible.sync="delVisible" width="300px" center>
      <div align="center">删除不可恢复，是否确定删除？</div>
      <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="deleteRow">确定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
import { mixin } from '../mixins/index';
import {
  delListSong,
  listSongOfSongId,
  allSinger, songOfSId, addSongInList
} from '../api/index';

export default {
  mixins: [mixin],
  data(){
    return{
      centerDialogVisible: false, //添加弹窗是否显示
      delVisible: false,          //删除弹窗是否显示
      registerForm:{      //添加框
        singerId: '',     //歌手名字
        songId: ''        //歌曲名字
      },
      tableData: [],
      tempData: [],
      select_word: '',
      idx: -1,          //当前选择项
      multipleSelection: [],   //哪些项已经打勾
      songListId: '',       //歌单id
      pageSize: 15,
      currentPage: 1,
      songList:[],
      singerList:[],
    }
  },
  watch:{
    singerList() {
      if (this.select_word == ''){
        this.getData();
      } else {
        this.tableData = [];
        this.tableData = this.singerList;
      }
    },
    singerId(newV,oldV) {
      if (newV != oldV) {
        this.registerForm.songId = '';
        songOfSId(this.registerForm.singerId)
            .then(res => {
              this.songList = res.data;
            })
      };
    },
    select_word(newV){
      if (newV == ''){
        this.getData();
      }
    }
  },
  created(){
    this.songListId = this.$route.query.id;
    this.getData();
  },
  computed:{
    data() {
      return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
    },
    singerId(){
      return this.registerForm.singerId;
    },

  },
  methods:{
    getData(){
      this.tempData = [];
      this.tableData = [];
      listSongOfSongId(this.songListId).then(res => {
        this.tableData = res;
      });
    },
    addSongView() {
      this.centerDialogVisible = true;
      allSinger()
          .then(res => {
            this.singerList = res.data;
          });
    },
    //添加歌曲
    add(){
      let param = new URLSearchParams();
      param.append('songId',this.registerForm.songId);
      param.append('songListId',this.songListId);

      addSongInList(param)
          .then(res =>{
            if (res.code==1){
              this.$notify({
                title: '添加成功',
                type: 'success',
              });
              this.getData();
            } else {
              this.$notify({
                title: '添加失败',
                type: 'error',
                message: '请检查该歌曲是否已存在'
              });
            }
          })
    },
    //删除一条歌曲
    deleteRow(){
      let param = new URLSearchParams();
      param.append('id',this.idx);
      delListSong(param)
          .then(res => {
            if(res.code == 1){
              this.getData();
              this.$notify({
                title: '删除成功',
                type: 'success',
              });
            }else{
              this.$notify({
                title: '删除成功',
                type: 'error',
              });
            }
          })
          .catch(err => {
            console.log(err);
          });
      this.delVisible = false;
    },
    handleCurrentChange(val){
      //获取当前页
      this.currentPage = val;
    },
    selectSingerList(){
      const name = this.select_word;
      const id = this.songListId;
      axios.get(`/listSong/${id}/${name}`).then(res => {
        // console.log(res.data);
        this.singerList = res.data;
      })
    },
  }
}
</script>

<style scoped>
.handle-box{
  margin-bottom: 20px;
}
.song-img{
  width: 100%;
  height: 80px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
.handle-input{
  width: 300px;
  display: inline-block;
}
.pagination{
  display: flex;
  justify-content: center;
}
.play {
  position: absolute;
  z-index: 100;
  width: 80px;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  top: 18px;
  left: 15px;
}

.icon {
  width: 2em;
  height: 2em;
  color: white;
  fill: currentColor;
  overflow: hidden;
}
</style>
