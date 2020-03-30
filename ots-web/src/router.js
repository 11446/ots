import Vue from "vue";
import VueRouter from "vue-router";
import Layout from "./components/Layout"

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: '/',  // 真实的路由地址
      name: 'login',  // 仅仅是路由名称
      component: () => import('@/views/login/index')
    },
    {
      path: '/home',  // 真实的路由地址
      name: 'layout',  // 仅仅是路由名称
      component: Layout,
      redirect: '/home',
      children: [
        {
          path: '/home',
          component: ()=>import('@/views/home/index')
        }
      ]
    },

    // 文件管理
    {
      path: '/file',  // 真实的路由地址
      name: 'fileManage',  // 仅仅是路由名称
      component: Layout,
      children: [
        {
          path: '/file',
          component: () => import('@/views/file/index'),
          meta: { title: '文件列表'}
        },       

      ]
    },
    // 上传文档
    {
      path: '/upload',  // 真实的路由地址
      name: '文件管理',  // 仅仅是路由名称
      component: Layout,
      children: [
        {
          path: '/upload',
          component: () => import('@/views/file/upload'),
          name: 'upload',
          meta: { title: '上传文件' }
        }
      ]
    },
    {
      path: '/board',  // 真实的路由地址
      name: '留言板',  // 仅仅是路由名称
      component: Layout,
      children: [
        {
          path: '/board',
          component: ()=>import('@/views/board/index'),
          meta: {title: '留言板'}
        }
      ]
    },
    {
      path: '/user',  // 真实的路由地址
      name: '用户管理',  // 仅仅是路由名称
      component: Layout,
      children: [
        {
          path: '/user',
          component: ()=>import('@/views/user/index'),
          meta: {title: '用户管理'}
        }
      ]
    },
    
  ]
})

