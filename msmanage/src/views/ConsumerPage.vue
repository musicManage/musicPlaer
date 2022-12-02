<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="请输入用户名" class="handle-input" clearable>
        </el-input>
        <el-button type="primary" size="mini" @click="selectSingerList">搜索</el-button>
        <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加用户</el-button>
      </div>
    </div>

    <el-table
        size="mini" ref="multipleTable" border style="width: 100%;height: 667px" :data="data" @selection-change="handleSelectionChange">
      <!--     多选-->
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column
          prop="pic"
          label="用户头像"
          width="110"
          align="center">
        <template slot-scope="scope">
          <div class="singer-img">
            <img :src="getUrl(scope.row.avator)" style="width: 100%"/>
          </div>
          <el-upload :action="updatePic(scope.row.id)" :before-upload="beforeUpdate" :on-success="updateSuccess">
            <el-button size="mini" >更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="120" align="center"></el-table-column>
      <el-table-column label="性别" width="50" align="center">
        <template slot-scope="scope">
          {{changeSex(scope.row.sex)}}
        </template>
      </el-table-column>
      <el-table-column prop="phoneNum" label="手机号" width="120" align="center"></el-table-column>
      <el-table-column prop="email" label="电子邮箱" width="240" align="center"></el-table-column>
      <el-table-column label="生日" width="120" align="center">
        <template slot-scope="scope">
          {{attachBirth(scope.row.birth)}}
        </template>
      </el-table-column>
      <el-table-column prop="introduction" label="签名" align="center"></el-table-column>
      <el-table-column prop="location" label="地区" width="100" align="center"></el-table-column>
      <el-table-column label="收藏" width="80" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="getCollect(data[scope.$index].id)">收藏</el-button>
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

    <!--   添加用户-->
    <el-dialog title="添加用户"  :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" ref="registerForm" label-width="80px" status-icon :rules="rules">
        <el-form-item prop="username" label="用户名" size="mini" >
          <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input type="password" v-model="registerForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="registerForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phoneNum" label="手机号" size="mini">
          <el-input v-model="registerForm.phoneNum" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱" size="mini">
          <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker type="date" v-model="registerForm.birth" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-form-item prop="location" label="地区" size="mini">
          <el-cascader
            size="mini"
            :options = "options"
            v-model="arr"
            filterable>
          </el-cascader>
        </el-form-item>
        <el-form-item prop="introduction" label="个性签名" size="mini">
          <el-input type="textarea" v-model="registerForm.introduction" placeholder="个性签名"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
       <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="addConsumer">添加</el-button>
     </span>
    </el-dialog>

    <!--   编辑用户-->
    <el-dialog title="编辑歌手信息" :visible.sync="editVisible" width="400px" center>
      <el-form :model="editForm" ref="editForm" label-width="80px" status-icon :rules="rules">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input v-model="editForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input type="password" v-model="editForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item prop="sex" label="性别" size="mini">
          <el-radio-group v-model="editForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phoneNum" label="手机号" size="mini">
          <el-input v-model="editForm.phoneNum" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱" size="mini">
          <el-input v-model="editForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker type="date" v-model="editForm.birth" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-form-item prop="location" label="地区" size="mini">
          <el-cascader
              size="mini"
              :options = "options"
              v-model="editForm.location"
              filterable>
          </el-cascader>
        </el-form-item>
        <el-form-item prop="introduction" label="个性签名" size="mini">
          <el-input type="textarea" v-model="editForm.introduction" placeholder="个性签名"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
       <el-button size="mini" @click="editVisible = false">取消</el-button>
       <el-button type="primary" size="mini" @click="editSinger(editForm)">保存</el-button>
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
import {
  addConsumer,
  allConsumer,
  allSinger, consumerOfName,
  delConsumer,
  delSinger,
  delSongList,
  setSinger, updateConsumer,
  updateSinger, verifyUsername
} from "@/api/index"
import {mixin} from "@/mixins";
import {provinceAndCityData} from "element-china-area-data";
import {CodeToText, TextToCode} from "element-china-area-data/dist/app";
export default {
  mixins:[mixin],
  data() {
    //检测用户名是否唯一
    const checkUsername = (rule, value, callback) => {
      if (value === ''){
        callback(new Error("请输入用户名"));
      } else if(this.editUsername != value){
        verifyUsername(value)
            .then(res => {
              if (res.code == 1) {
                callback();
              } else {
                callback(new Error('用户名已重复,请修改用户名'));
              }
            })
      } else if (this.editUsername == value){
        callback();
      } else {
        callback();
      }
    };
    const checkMail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value){
        callback("请输入邮箱");
      } else if (mailReg.test(value)){
        callback();
      } else {
        callback("邮箱格式错误");
      }
    };
    const checkPhone = (rule, value, callback) => {
      const phoneReg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
      if (!value){
        callback("请输入手机号");
      } else if (phoneReg.test(value)){
        callback();
      } else {
        callback("请输入正确的手机号");
      }
    }
    return {
      centerDialogVisible:false,//添加弹窗显示
      editVisible:false,//编辑弹窗显示
      delVisible:false,//删除弹窗是否显示
      registerForm: {
        username: '',
        password: '',
        sex: '',
        phoneNum: '',
        email: '',
        birth: '',
        introduction: '',
        location: '',
      },
      editForm: {
        id:'',
        username: '',
        password: '',
        sex: '',
        phoneNum: '',
        email: '',
        birth: '',
        introduction: '',
        location: '',
      },
      tableData:[],
      select_word:'',
      singerList:[],
      pageSize: 5,
      currentPage: 1,
      idx:'',//当前选择项
      multipleSelection: [],   //已选项
      options: provinceAndCityData,
      arr:[],
      arr2:[],
      rules:{
        username:[
          { require:true, validator: checkUsername, trigger: 'blur' }
        ],
        email:[
          { require:true, validator: checkMail, trigger: 'blur'}
        ],
        phoneNum:[
          { require:true, validator: checkPhone, trigger: 'blur'}
        ]
      },
      editUsername:'',
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
    },
    arr(newV){
      if (newV != ''){
        this.registerForm.location = `${CodeToText[newV[0]]}`+"-"+`${CodeToText[newV[1]]}`;
      }
    },
    centerDialogVisible(newV){
      if (!newV){
        if (this.registerForm.username=='')
        this.$refs.registerForm.clearValidate();
      }
    },
    editVisible(newV){
      if (!newV){
        this.$refs.editForm.clearValidate();
      }
    }
  },
  methods:{
    getData(){
      this.tableData=[];
      allConsumer().then(res => {
        this.tableData = res.data;
      })
    },
    addConsumer(){
      let params = new URLSearchParams();
      params.append('username',this.registerForm.username);
      params.append('password',this.registerForm.password);
      params.append('sex',this.registerForm.sex);
      params.append('phoneNum',this.registerForm.phoneNum);
      params.append('email',this.registerForm.email);
      params.append('birth',this.registerForm.birth);
      params.append('location',this.registerForm.location);
      params.append('introduction',this.registerForm.introduction);

      addConsumer(params)
          .then(res => {
            if (res.code==1){
              this.$notify({
                title: '添加成功',
                type: 'success',
              });
              this.registerForm = [];
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
      return `${this.$store.state.HOST}/consumer/pic/update/${id}`;
    },
    selectSingerList(){
      consumerOfName(this.select_word).then(res => {
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
      this.ChangeArr1(row.location);
      this.editForm = {
          id:row.id,
          username: row.username,
          password: row.password,
          sex: row.sex,
          phoneNum: row.phoneNum,
          email: row.email,
          birth: row.birth,
          introduction: row.introduction,
          location: this.arr2,
        };
      this.editUsername = row.username;
    },
    editSinger(editForm){
      let arr1 = editForm.location;
      this.editForm.location = `${CodeToText[arr1[0]]}`+"-"+`${CodeToText[arr1[1]]}`;
      let params = new URLSearchParams();
      params.append('id',this.editForm.id);
      params.append('username',this.editForm.username);
      params.append('password',this.editForm.password);
      params.append('sex',this.editForm.sex);
      params.append('phoneNum',this.editForm.phoneNum);
      params.append('email',this.editForm.email);
      params.append('birth',this.editForm.birth);
      params.append('introduction',this.editForm.introduction);
      params.append('location',this.editForm.location);

      updateConsumer(params)
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
      delConsumer(param)
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
    ChangeArr1(val){
      this.arr2 = [];
      let arr3 = val.split('-');
      this.arr2.push(`${TextToCode[arr3[0]].code}`);
      this.arr2.push(`${TextToCode[arr3[0]][arr3[1]].code}`);
    },

  },
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
