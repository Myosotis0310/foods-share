<template>
  <div>
    <div class="front-notice"><i class="el-icon-bell" style="margin-right: 2px"></i>公告：{{ top }}</div>
    <!--头部-->
    <div style="box-shadow: 0 2px 4px rgba(0,0,0,.08)">
      <div class="front-header">
        <a href="/front/home">
          <div class="front-header-left">
            <img src="@/assets/imgs/logo.png" alt="">
            <div class="title">美食分享网</div>
          </div>
        </a>
        <div class="front-header-center">
          <div @click="changePath(item.path)" class="menu-item" v-for="item in menus" :key="item.text"
               :class="{'menu-item-active' : item.path === $route.path }">{{ item.text }}
          </div>

          <div>
            <el-input v-model="name" style="width: 200px; margin-right: 10px; margin-left: 100px"
                      placeholder="请输入菜谱名称"></el-input>
            <el-button @click="search" style="background-color: #f59300; border-color: #f59300; color: #fff">搜索
            </el-button>
          </div>
        </div>
        <div class="front-header-right">
          <div v-if="!user.username">
            <el-button @click="$router.push('/login')">登录</el-button>
            <el-button @click="$router.push('/register')">注册</el-button>
          </div>
          <div v-else>
            <el-dropdown>
              <div class="front-header-dropdown">
                <img :src="user.avatar" alt="">
                <div style="margin-left: 10px">
                  <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                </div>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <a style="color: #333" href="/front/userNotebook">
                    <div>我的笔记</div>
                  </a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <a style="color: #333" href="/front/userFoods">
                    <div>我的菜谱</div>
                  </a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <a style="color: #333" href="/front/orders">
                    <div>我的订单</div>
                  </a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <a style="color: #333" href="/front/person">
                    <div>个人中心</div>
                  </a>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div @click="logout">退出</div>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view ref="child" @update:user="updateUser"/>
    </div>
  </div>

</template>

<script>

export default {
  name: "FrontLayout",

  data() {
    return {
      name: this.$route.query.name,
      top: '',
      notice: [],
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
      menus: [
        {text: '首页', path: '/front/home'},
        {text: '菜谱', path: '/front/foods'},
        {text: '笔记', path: '/front/notebook'},
        {text: '商城', path: '/front/mall'},
      ]
    }
  },

  mounted() {
    this.loadNotice()
  },
  methods: {
    search() {
      window.open('/front/foods?name=' + this.name)
    },
    changePath(path) {
      location.href = path
    },
    loadNotice() {
      this.$request.get('/notice/selectAll').then(res => {
        this.notice = res.data
        let i = 0
        if (this.notice && this.notice.length) {
          this.top = this.notice[0].content
          setInterval(() => {
            this.top = this.notice[i].content
            i++
            if (i === this.notice.length) {
              i = 0
            }
          }, 2500)
        }
      })
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },
  }

}
</script>

<style scoped>
@import "@/assets/css/front.css";

.menu-item {
  height: 50%;
  display: flex;
  align-items: center;
  margin-right: 30px;
  font-size: 16px;
  cursor: pointer;
}

.menu-item:hover {
  color: #FFB31A;
  font-weight: bold;
}

.menu-item-active {
  color: #FFB31A;
  font-weight: bold;
  border-bottom: 3px solid #FFB31A;
}
</style>