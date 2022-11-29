<template>
  <div class="content-list">
    <ul class="section-content">
      <li class="content-item" v-for="(item,index) in contentList" :key="index">
        <div class="kuo">
          <img class="item-img":src="attachImageUrl(item.pic)">
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
/*.content-list{*/
/*  min-height: 500px;*/
/*  padding: 0 20px;*/
/*  text-align:center;*/
/*}*/

/*.section-content{*/
/*  list-style-type:none;*/
/*}*/
/*.content-item {*/
/*  width: 18%;*/
/*  margin: 20px 1%;*/
/*  overflow: hidden;*/
/*  border-radius: 4px;*/
/*  position: relative;*/
/*  display:inline;*/

/*}*/
/*.kuo img {*/
/*  width: 200px;*/
/*  height: 200px;*/
/*  margin: 10px;*/
/*  float: left;*/
/*}*/
</style>