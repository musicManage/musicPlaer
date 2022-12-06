<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-tickets"></i>收藏信息
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="请输入歌曲名" class="handle-input" clearable>
        </el-input>
        <el-button type="primary" size="mini" @click="selectSong">搜索</el-button>
      </div>
    </div>
    <el-table size="mini" ref="multipleTable" border style="width:100%" height="680px" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="name" label="歌手-歌名" align="center"></el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

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
import {mixin} from "@/mixins";
import {deleteCollection, getCollectOfUserId, selectCollectByName} from "@/api";

export default {
  name: "CollectPage",
  mixins: [mixin],
  props: ['id'],
  data(){
    return{
      delVisible: false,          //删除弹窗是否显示
      tableData: [],
      tempData: [],
      select_word: '',
      idx: -1,          //当前选择项
      multipleSelection: [],   //哪些项已经打勾
    }
  },
  watch:{
    select_word(newV){
      if (newV == ''){
        this.getData();
      }
    }
  },
  created(){
    this.getData();
  },
  methods:{
    //查询该用户所有收藏的歌曲
    getData(){
      this.tempData = [];
      this.tableData = [];
      getCollectOfUserId(this.$route.query.id).then(res => {
        this.tableData = res.data;
      })
    },
    //删除一条歌曲
    deleteRow(){
      let param = new URLSearchParams();
      deleteCollection(this.$route.query.id,this.idx.songId)
          .then(res => {
            if(res.code==1){
              this.getData();
              this.$notify.success("删除成功");
            }else{
              this.$notify.error("删除失败");
            }
          })
          .catch(err => {
            console.log(err);
          });
      this.delVisible = false;
    },
    //批量删除已经选择的项
    delAll(){
      for(let item of this.multipleSelection){
        this.handleDelete(item);
        this.deleteRow();
      }
      this.multipleSelection = [];
    },
    //查找
    selectSong(){
      selectCollectByName(this.$route.query.id,this.select_word).then(res => {
        // console.log(res.data);
        this.tempData = res.data;
        this.tableData = this.tempData;
      })
    }
  }
}
</script>

<style scoped>
.handle-box{
  margin-bottom: 20px;
}
.handle-input{
  width: 300px;
  display: inline-block;
}
</style>