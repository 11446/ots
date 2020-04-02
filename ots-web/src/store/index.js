import Vue from 'vue'
import Vuex from 'vuex'
// 引入用户信息全局的状态
import userinfo from './modules/userinfo'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    userinfo
  }
})

// 在这里导出之后，就可以在别的组件里使用store的状态了
export default store
