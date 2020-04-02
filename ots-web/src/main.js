import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import App from "./App.vue";
import router from "./router";
import ViewUI from "view-design";
import "view-design/dist/styles/iview.css";
import "./permission";
import "./assets/iconfont/iconfont.css";

// 引入ElementUI组件库
Vue.use(ElementUI);
// 引入ViewUI组件库
Vue.use(ViewUI);
// 消息提示的环境配置为生产环境
Vue.config.productionTip = false;

// 加载进度条，路由跳转之前
router.beforeEach((to, from, next) => {
  ViewUI.LoadingBar.start();
  next();
});
// 路由跳转之后
router.afterEach(() => {
  ViewUI.LoadingBar.finish();
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
