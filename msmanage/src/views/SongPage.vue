<template>
  <div class="table">
    <div style="margin-bottom: 20px">
      <i class="el-icon-tickets"></i> 歌曲信息
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="请输入歌曲名" class="handle-input" clearable>
        </el-input>
        <el-button type="primary" size="mini" @click="selectSingerList">搜索</el-button>
        <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌曲</el-button>
      </div>
    </div>

    <el-table
        size="mini" ref="multipleTable" border style="width: 100%;height: 603px" :data="data" @selection-change="handleSelectionChange">
      <!--     多选-->
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column
          prop="pic"
          label="歌曲图片"
          width="110"
          align="center">
        <template slot-scope="scope">
          <div class="song-img">
            <img :src="getUrl(scope.row.pic)" style="width: 100%"/>
          </div>
          <div class="play" @click="setSongUrl(scope.row.url,scope.row.name)">
            <div v-if="toggle === scope.row.name">
              <svg class="icon">
                <use xlink:href="#icon-zanting"></use>
              </svg>
            </div>
            <div v-if="toggle !== scope.row.name">
              <svg class="icon">
                <use xlink:href="#icon-bofanganniu"></use>
              </svg>
            </div>

          </div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="歌名" width="120" align="center"></el-table-column>
      <el-table-column prop="introduction" label="专辑" width="150" align="center"></el-table-column>
      <el-table-column label="歌词" align="center">
        <template slot-scope="scope">
          <ul style="height: 100px;overflow-y: scroll">
            <li v-for="(item,index) in parseLyric(scope.row.lyric)" :key="index">
              {{item}}
            </li>
          </ul>
        </template>
      </el-table-column>
      <el-table-column label="资源更新" align="center" width="100">
        <template slot-scope="scope">
          <el-upload :action="updatePic(scope.row.id)" :before-upload="beforeUpdate" :on-success="updateSuccess">
            <el-button size="mini" >更新图片</el-button>
          </el-upload>
          <br/>
          <el-upload :action="uploadSongUrl(scope.row.id)" :before-upload="beforeSongUpload"
                     :on-success="handleSongSuccess">
            <el-button size="mini">更新歌曲</el-button>
          </el-upload>
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
    <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" ref="registerForm" label-width="80px" action="" id="tf">
        <div>
          <label>歌名</label>
          <el-input v-model="registerForm.name" type="text" name="name"></el-input>
        </div>
        <div>
          <label>专辑</label>
          <el-input v-model="registerForm.introduction" type="text" name="introduction"></el-input>
        </div>
        <div>
          <label>歌词</label>
          <el-input v-model="registerForm.lyric" type="textarea" name="lyric"></el-input>
        </div>
        <div>
          <label>歌曲上传</label>
          <input type="file" name="file">
        </div>
      </el-form>
      <span slot="footer">
       <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="addSong">添加</el-button>
     </span>
    </el-dialog>

    <!--   编辑歌手-->
    <el-dialog title="编辑歌曲信息" :visible.sync="editVisible" width="400px" center>
      <el-form :model="editForm" ref="registerForm" label-width="80px">
        <el-form-item prop="name" label="歌手-歌名" size="mini">
          <el-input v-model="editForm.name" placeholder="歌名"></el-input>
        </el-form-item>
        <el-form-item prop="introduction" label="专辑" size="mini">
          <el-input v-model="editForm.introduction" placeholder="专辑"></el-input>
        </el-form-item>
        <el-form-item prop="lyric" label="歌词" size="mini">
          <el-input type="textarea" v-model="editForm.lyric" placeholder="歌词"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
       <el-button size="mini" @click="editVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="editSong">保存</el-button>
     </span>
    </el-dialog>

    <!--   删除窗口-->
    <el-dialog title="删除歌曲" :visible.sync="delVisible" width="400px" center>
      <div align="center">是否确认删除</div>
      <span slot="footer">
       <el-button size="mini" @click="delVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="deleteRow">确认删除</el-button>
     </span>
    </el-dialog>
  </div>
</template>

<script>
import {mixin} from "@/mixins";
import {mapGetters} from "vuex";
import {delSong, songOfSId, updateSong} from "@/api/index";
import "@/assets/js/iconfont";

export default {
  mixins:[mixin],
  data() {
    return {
      centerDialogVisible:false,//添加弹窗显示
      editVisible:false,//编辑弹窗显示
      delVisible:false,//删除弹窗是否显示
      registerForm: {
        name: '',
        singerName: '',
        introduction: '',
        lyric: ''
      },
      editForm: {
        id:'',
        name: '',
        introduction: ' ',
        lyric: ''
      },
      tableData:[],
      select_word:'',
      singerList:[],
      pageSize: 5,
      currentPage: 1,
      idx:'',//当前选择项
      multipleSelection: [], //已选项
      singerId: '',               //歌手id
      singerName: '',             //歌手名
      toggle:false, //播放器的显示图标状态
    }
  },
  created() {
    this.singerId = this.$route.query.id;
    this.singerName = this.$route.query.name;
    this.getData();
  },
  computed: {
    data() {
      return this.tableData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize)
    },
    ...mapGetters([
        'isPlay'
    ])
  },
  watch: {
    singerList() {
      if (this.singerList == ''){
        this.getData();
      } else {
        this.tableData = [];
        this.tableData = this.singerList;
      }
    }
  },
  destroyed(){
    this.$store.commit('setIsPlay',false);
  },
  methods:{
    getData(){
      this.tableData=[];
      songOfSId(this.singerId)
          .then(res => {
            this.tableData = res.data;
          })
    },
    addSong(){
      let _this = this;
      var form = new FormData(document.getElementById('tf'));
      form.append('singerId',this.singerId);
      form.set('name',this.singerName+'-'+form.get('name'));
      if(!form.get('lyric')){
        form.set('lyric','[00:00:00]暂无歌词');
      }
      var req = new XMLHttpRequest();
      req.onreadystatechange = function(){
        //req.readyState == 4 获取到返回的完整数据
        //req.status == 200 和后台正常交互完成
        if(req.readyState == 4 && req.status == 200){
          let res = JSON.parse(req.response);
          if(res.code){
            _this.getData();
            _this.registerForm = {};
            _this.$notify({
              title : res.msg,
              type  : 'success',
            });
          }else{
            _this.$notify.error('保存失败');
          }
        }
      }
      req.open('post',`${_this.$store.state.HOST}/song/add`,false);
      req.send(form);
      _this.centerDialogVisible = false;
    },
    updatePic(id){
      return `${this.$store.state.HOST}/song/pic/update/${id}`;
    },
    uploadSongUrl(id){
      return `${this.$store.state.HOST}/song/update/file/${id}`;
    },
    selectSingerList(){
      let param = new URLSearchParams();
      param.append("name",this.select_word);
      axios.post("/song/name",param).then(res => {
        console.log(res.data);
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
        introduction: row.introduction,
        lyric: row.lyric
      }
    },
    editSong(){
      let params = new URLSearchParams();
      params.append('id',this.editForm.id);
      params.append('name',this.editForm.name);
      params.append('introduction',this.editForm.introduction);
      params.append('lyric',this.editForm.lyric);

      updateSong(params)
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
      delSong(param)
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
    //解析歌词
    parseLyric(text){
      let lines = text.split("\n");
      let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
      let result = [];
      for (let item of lines){
        let value = item.replace(pattern,'');
        result.push(value);
      }
      return result;
    },
    //上传歌曲之前的校验
    beforeSongUpload(file){
      var testMsg = file.name.substring(file.name.lastIndexOf('.') + 1);
      if(testMsg!='mp3'){
        this.$message({
          message: '上传文件只能是mp3格式',
          type: 'error'
        });
        return false;
      }
      return true;
    },
    //上传歌曲成功之后要做的工作
    handleSongSuccess(res){
      let _this = this;
      if(res.code == 1){
        _this.getData();
        _this.$notify({
          title: '上传成功',
          type: 'success'
        });
      }else{
        _this.$notify({
          title: '上传失败',
          type: 'error'
        });
      }
    },
    //切换播放音乐歌曲
    setSongUrl(url,name){
      if (this.toggle != name){
        this.toggle = name;
        this.$store.commit('setUrl',this.$store.state.HOST + url);
        this.$store.commit('setIsPlay',true);
      } else {
        this.toggle = false;
        this.$store.commit('setIsPlay',false);
      }
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
.song-img {
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
  color: #fff;
  fill: currentColor;
  overflow: hidden;
}
</style>
