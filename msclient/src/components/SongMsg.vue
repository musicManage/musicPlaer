<template>
  <div>
    <el-table
        :data="songMsg"
        style="width: 100%">
      <el-table-column
          prop="name"
          label="歌名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="singerName"
          label="歌名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="introduction"
          label="专辑">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "SongMsg",
  props:['songMsg']
}
</script>

<style scoped>

</style>
