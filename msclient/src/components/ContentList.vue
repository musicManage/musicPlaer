<template>
  <div class="content-list">
    <ul class="section-content">
      <li class="content-item" v-for="(item,index) in contentList" :key="index">
        <div class="kuo">
          <img class="item-img":src="attachImageUrl(item.pic)">
          <h3 v-if="item.title">{{item.title}}</h3>
          <h3 v-if="item.name">{{item.name}}</h3>
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
  props:['contentList']

}
</script>

<style scoped>
.content-list{
  margin-top: 10px;
}
.content-item{
  width: 20%;
  margin-bottom: 20px;
  overflow: hidden;
}
.content-item:hover{
  transform: scale(1.1);
}

li {
  list-style: none;
}
.kuo {
  position: relative;
  margin-right: 20px;
  background-color: #fbfbfd;
  min-height: 195px;
  padding: 25px 0;
  overflow: hidden;
}
.item-img {
  display: block;
  width: 140px;
  height: 140px;
  margin-left: auto;
  margin-right: auto;
  border-radius: 126px;
}


h3 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 16px;
  font-weight: 400;
  zoom: 1;
  margin: 20px 0;
  text-align: center;
  width: 100%;
}
ul {
  margin-right: -20px;
  overflow: hidden;
  zoom: 1;
  margin-bottom: 4px;
  width: 100%;
  height: 100%;
}
</style>
