import Vue from 'vue'
import VueRouter from 'vue-router'
import MLogin from '../views/MLogin.vue'
import MIndex from "@/views/MIndex";
import Home from "@/components/Home";
import SingerPage from "@/views/SingerPage";
import SongListPage from "@/views/SongListPage";
import Consumer from "@/views/Consumer";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MLogin',
    component: MLogin
  }, {
    path: '/Home',
    name: 'Home',
    component: Home,
    children:[
      {
        path: '/Index',
        name: 'MIndex',
        component: MIndex
      },
      {
        path: '/Consumer',
        name: 'consumer',
        component: Consumer
      },
      {
        path: '/Singer',
        name: 'Singer',
        component: SingerPage
      },
      {
        path: '/SongList',
        name: 'SongList',
        component: SongListPage
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
