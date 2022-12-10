import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "@/views/Home";
import MyMusic from "@/views/MyMusic";
import Singer from "@/views/Singer";
import SongList from "@/views/SongList";
import SearchPage from "@/views/SearchPage";
import SingerAlbum from "@/views/SingerAlbum";
import SongListAlbum from "@/views/SongListAlbum";
import Lyric from "@/views/Lyric.vue";
import UserSetting from "@/views/UserSetting.vue";

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
    {
        path: '/search',
        name: 'search',
        component: SearchPage
    },
    {
        path: '/singer-album/:id',
        name: 'singer-album',
        component: SingerAlbum
    },
    {
        path: '/song-list-album/:id',
        name: 'song-list-album',
        component: SongListAlbum
    },
    {
        path: '/lyric',
        name: 'lyricc',
        component: Lyric
    },{
        path: '/UserSetting',
        name: 'UserSetting',
        component: UserSetting
    },
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
