import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "@/views/Home";
import MyMusic from "@/views/MyMusic";
import Singer from "@/views/Singer";
import SongList from "@/views/SongList";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
      {
        path: '/MyMusic',
        name: 'MyMusic',
        component: MyMusic
      },
      {
        path: '/singer',
        name: 'singer',
        component: Singer
      },
      {
        path: '/SongList',
        name: 'song-list',
        component: SongList
      },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
