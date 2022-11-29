import Vue from 'vue'
import './plugins/axios'
import store from './store/index'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import '@/assets/icons/iconfont'
import '@/assets/icons/iconfont1'
import '@/assets/icons/iconfont2'
import '@/assets/icons/iconfont3'
import '@/assets/icons/iconfont4'
import '@/assets/css/index.scss'
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
