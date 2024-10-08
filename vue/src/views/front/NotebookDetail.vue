<template>
  <div class="main-content" style="width: 50%">
    <img :src="notebook.cover" alt="" style="width: 100%; border-radius: 5px">
    <div style="margin: 20px 0; font-size: 22px; font-weight: bold">{{ notebook.name }}</div>
    <div style="display: flex; margin-bottom: 30px">
      <div style="display: flex; align-items: center;">
        <img :src="notebook.avatar" alt="" style="width: 30px; height: 30px; border-radius: 50%">
        <span style="margin-left: 5px">{{ notebook.userName }}</span>
      </div>
      <div style="flex: 1; display: flex; align-items: center; justify-content: flex-end">
        <span style="font-size: 20px; color: orange; font-weight: bold; margin-right: 2px">{{ notebook.count }}</span> 浏览
        <span style="font-size: 20px; color: orange; font-weight: bold; margin-right: 2px; margin-left: 30px">{{ notebook.collectCount }}</span> 收藏
        <span style="font-size: 20px; color: orangered; font-weight: bold; margin-right: 2px; margin-left: 30px">{{ notebook.likesCount }}</span> 点赞

        <el-button icon="el-icon-like" style="margin-left: 30px; color: orange" v-if="notebook.userLike" @click="like">已点赞</el-button>
        <el-button icon="el-icon-unlike" style="margin-left: 30px" v-else @click="like">点赞</el-button>

        <el-button icon="el-icon-star-on" style="margin-left: 10px; color: orange" v-if="notebook.userCollect" @click="collect">已收藏</el-button>
        <el-button icon="el-icon-star-off" style="margin-left: 10px" v-else @click="collect">收藏</el-button>
      </div>
    </div>

    <div class="w-e-text" style="line-height: 24px; margin-bottom: 30px">
      <div v-html="notebook.content"></div>
    </div>
    <div style="margin-bottom: 30px; font-size: 13px; color: #666">创建时间: {{  notebook.date }}</div>

    <div style="margin: 50px 0">
      <Comment :fid="id" module="笔记" />
    </div>

  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Comment from "@/components/Comment";
export default {
  name: "NotebookDetail",
  components: {Footer, Comment},
  data() {
    return {
      id: this.$route.query.id,  // 笔记ID
      notebook: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    this.updateCount()
  },
  methods: {
    like() {  // 点赞
      this.$request.post('/likes/set', { fid: this.id, userId: this.user.id, module: '笔记' }).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    collect() {
      this.$request.post('/collect/set', { fid: this.id, userId: this.user.id, module: '笔记' }).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    updateCount() {
      this.$request.put('/notebook/updateCount/' + this.id).then(res => {
        this.load()
      })
    },
    load() {
      this.$request.get('/notebook/selectById/' + this.id).then(res => {
        this.notebook = res.data || {}
      })
    },
  }
}
</script>

<style scoped>

</style>