import Vue from 'vue'
import Vuex from 'vuex'
import configure from "@/store/configure";
import user from "@/store/user";
import song from "@/store/song";

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    HOST:'http://127.0.0.1:8888'
  },
  modules: {
    configure,
    user,
    song
  }
})
export default store
