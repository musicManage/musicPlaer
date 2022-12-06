<template>
  <div>
    <el-table
        :data="songMsg"
        style="width: 100%"
        @row-dblclick="toplays"
        :row-class-name="tableRowClassName"
    >
      <el-table-column
          type="index"
          width="180"
          align="center"
      >
      </el-table-column>
      <el-table-column
          prop="name"
          label="歌名"
          width="180"
          align="center"
      >
      </el-table-column>
      <el-table-column
          prop="singerName"
          label="歌手"
          width="180"
          align="center"
      >
      </el-table-column>
      <el-table-column
          prop="introduction"
          label="专辑"
          align="center"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {mixin} from "@/mixins";

export default {
  name: "SongMsg",
  props:['songMsg'],
  mixins:[mixin],
  data(){
    return{
    }
  },
  computed:{

  },
  methods:{
    toplays(row){
      this.toplay(row.id,row.url,row.pic,row.row_index,row.name,row.singerName,row.lyric);
    },
    tableRowClassName({row, rowIndex}) {
      row.row_index = rowIndex;
    }
  }
}
</script>

<style scoped>

</style>
