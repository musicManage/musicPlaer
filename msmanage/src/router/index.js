import Vue from 'vue'
import VueRouter from 'vue-router'
import MLogin from '../views/MLogin.vue'
import MIndex from "@/views/MIndex";
import Home from "@/components/Home";

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
        path: '/index',
        name: 'MIndex',
        component: MIndex
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
