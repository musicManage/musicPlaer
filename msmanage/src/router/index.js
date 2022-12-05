import Vue from 'vue'
import VueRouter from 'vue-router'
import MLogin from '../views/MLogin.vue'
import MIndex from "@/views/MIndex";
import Home from "@/components/Home";
import SingerPage from "@/views/SingerPage";
import SongListPage from "@/views/SongListPage";
import ConsumerPage from "@/views/ConsumerPage";
import SongPage from "@/views/SongPage";
import ListSongPage from "@/views/ListSongPage";

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
    meta: {
      requireAuth: true
    },
    children:[
      {
        path: '/Index',
        name: 'MIndex',
        component: MIndex,
        meta: {
          requireAuth: true
        },
      },
      {
        path: '/Consumer',
        name: 'Consumer',
        component: ConsumerPage,
        meta: {
          requireAuth: true
        },
      },
      {
        path: '/Singer',
        name: 'Singer',
        component: SingerPage,
        meta: {
          requireAuth: true
        },
      },
      {
        path: '/SongList',
        name: 'SongList',
        component: SongListPage,
        meta: {
          requireAuth: true
        },
      },
      {
        path: '/Song',
        name: 'Song',
        component: SongPage,
        meta: {
          requireAuth: true
        },
      },
      {
        path: '/ListSong',
        name: 'ListSong',
        component: ListSongPage,
        meta: {
          requireAuth: true
        },
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//登录拦截
router.beforeEach((to,from,next)=>{
  if (to.meta.requireAuth) {//requireAuth若为true则该路由需要判断是否登录
    if (localStorage.ausername) {//判断当前的userName数据是否存在
      next();
    }
    else {
      next({//返回登录页面
        path: '/',
        query: {redirect: to.fullPath}
      })
    }
  }
  else {
    next();
  }
})

export default router
