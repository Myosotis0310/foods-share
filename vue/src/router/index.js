import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',  // 重定向到主页
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' }, component: () => import('../views/manager/Notice') },
      { path: 'user', name: 'User', meta: { name: '用户信息' }, component: () => import('../views/manager/User') },
      { path: 'category', name: 'Category', meta: { name: '菜谱分类' }, component: () => import('../views/manager/Category') },
      { path: 'foods', name: 'Foods', meta: { name: '菜谱信息' }, component: () => import('../views/manager/Foods') },
      { path: 'notebook', name: 'Notebook', meta: { name: '美食笔记' }, component: () => import('../views/manager/Notebook') },
      { path: 'goods', name: 'Goods', meta: { name: '商品信息' }, component: () => import('../views/manager/Goods') },
      { path: 'comment', name: 'Comment', meta: { name: '评论信息' }, component: () => import('../views/manager/Comment') },
      { path: 'orders', name: 'Orders', meta: { name: '订单信息' }, component: () => import('../views/manager/Orders') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'FrontHome', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'FrontPerson', meta: { name: '个人信息' }, component: () => import('../views/front/Person') },
      { path: 'foodDetail', name: 'FoodDetail', meta: { name: '菜谱详情' }, component: () => import('../views/front/FoodDetail') },
      { path: 'notebookDetail', name: 'NotebookDetail', meta: { name: '笔记详情' }, component: () => import('../views/front/NotebookDetail') },
      { path: 'foods', name: 'FrontFoods', meta: { name: '菜谱列表' }, component: () => import('../views/front/Foods') },
      { path: 'notebook', name: 'FrontNotebook', meta: { name: '美食笔记' }, component: () => import('../views/front/Notebook') },
      { path: 'mall', name: 'Mall', meta: { name: '商城' }, component: () => import('../views/front/Mall') },
      { path: 'goodsDetail', name: 'GoodsDetail', meta: { name: '商品详情' }, component: () => import('../views/front/GoodsDetail') },
      { path: 'orders', name: 'FrontOrders', meta: { name: '商品详情' }, component: () => import('../views/front/Orders') },
      { path: 'userFoods', name: 'UserFoods', meta: { name: '我的菜谱' }, component: () => import('../views/front/UserFoods') },
      { path: 'addFoods', name: 'AddFoods', meta: { name: '我的菜谱' }, component: () => import('../views/front/AddFoods') },
      { path: 'userNotebook', name: 'UserNotebook', meta: { name: '我的笔记' }, component: () => import('../views/front/UserNotebook') },
      { path: 'addNotebook', name: 'AddNotebook', meta: { name: '我的笔记' }, component: () => import('../views/front/AddNotebook') },
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 注：不需要前台的项目，可以注释掉该路由守卫
// 路由守卫
// router.beforeEach((to ,from, next) => {
//   let user = JSON.parse(localStorage.getItem("xm-user") || '{}');
//   if (to.path === '/') {
//     if (user.role) {
//       if (user.role === 'USER') {
//         next('/front/home')
//       } else {
//         next('/home')
//       }
//     } else {
//       next('/login')
//     }
//   } else {
//     next()
//   }
// })

export default router
