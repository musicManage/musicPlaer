import Vue from "vue";
import Vuex from 'vuex'
Vue.use(Vuex)

const configure= {
    state: {
        HOST:'http://127.0.0.1:8888', //后台访问地址根目录
        activeName:'' //当前选中的菜单名
    },
    //  getters: {
    // //     activeName: state => {
    // //         let activeName = state.activeName
    // //         if(!activeName){
    // //             activeName = window.sessionStorage.getItem('activeName')
    // //         }
    // //         return activeName
    // //     }
    // },
    // mutations: {
    //     setActiveName: (state, activeName) => {
    //         state.activeName = activeName
    //         window.sessionStorage.setItem('activeName',activeName)
    //     }
    // }

}
export default configure