<template>
  <div class="the-header">
    <div class="header-logo" @click="goHome">
      <svg class="icon">
        <use xlink:href="#icon-erji"></use>
      </svg>
      <span>music</span>
    </div>
  </div>
</template>

<script>
import {navMsg} from '../assets/data/header';
export default {
  name: 'the-header',
  methods: {
    goHome(){
      this.$router.push({path:"/"});
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../assets/css/the-header.scss";

.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
