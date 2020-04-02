/**
 * 权限校验， Vue Router中的路由钩子函数 beforeEach(to, from , next)
 */
import router from "./router";
import { Message } from "element-ui";

router.beforeEach((to, from, next) => {
  // sd
  // console.log("路由守卫生效！")
  //  1.用户信息
  const user = localStorage.getItem("user-info");
  // console.log("user的值为："+user)
  // 如果没有获取到用户信息
  if (user == null || user == "null") {
    // 不允许访问非登录页面
    if (to.path !== "/") {
      console.log("不允许访问登录界面！");
      Message({
        message: "您还未登录，请先登录",
        type: "warning"
      });
      // 强制跳转到登录页面
      next({ path: "/" });
    } else {
      // 如果访问的就是登录页面，则什么也不做，让他访问
      next();
    }
  } else {
    next();
  }
});
