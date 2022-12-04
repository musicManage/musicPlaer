<template>
  <div class="content-list">
    <ul class="section-content">
      <li class="content-item" v-for="(item,index) in contentList" :key="index">
        <div class="kuo">
          <div class="imgTest">
            <div class="kuo2">
              <img  class="item-img" :src="attachImageUrl(item.pic)">
              <div class="el-icon-caret-right"></div>
            </div>
          </div>
            <h3 >{{item.title||item.name}}</h3>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {mixin} from "@/mixins";

export default {
  name: 'content-list',
  mixins:[mixin],
  props:['contentList'],
}
</script>

<style scoped>
.content-list{
  max-width: 1240px;
  max-height: 764px;
  margin: 0 auto;
  position: relative;
}
.content-item{
  display: inline-block;
  width: 248px;
  height: 353px;
  overflow: hidden;
  font-size: 14px;
  vertical-align: top;
}
li {
  list-style: none;
}
.kuo {
  position: relative;
  margin-right: 20px;
  display: block;
  overflow: hidden;
  margin-bottom: 15px;
  width: 228px;
  height: 256px;
}
.kuo2{
  position: relative;
 overflow: hidden;
  width: 228px;
  height: 228px;
}
.kuo2:hover {
  transform: scale(1.2);

}
.item-img {
  width: 228px;
  height: 228px;
  cursor: pointer;
}
h3 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: medium;
  font-weight: 400;
  zoom: 1;
  margin-top: 5px;
  text-align: center;
  width: 100%;
  cursor: pointer;
}
ul {
  margin-right: -20px;
}
.kuo2 .el-icon-caret-right{
  position: absolute;
  left: 40%;
  top: 40%;
  color: white;
  font-size: 50px;
  display: none;
  cursor: pointer;
}
.kuo2:hover .el-icon-caret-right{
  display: inline-block;
}
.kuo2:hover .item-img {
  -webkit-filter: brightness(0.5);
  filter: brightness(0.5);
}
.imgTest {
  overflow: hidden;
  width: 230px;
  height: 230px;
}
</style>
