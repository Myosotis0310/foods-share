<template>
  <div class="main-content" style="width: 50%">
    <img :src="foods.cover" alt="" style="width: 100%; border-radius: 5px">
    <div style="margin: 20px 0; font-size: 22px; font-weight: bold">{{ foods.name }}</div>
    <div style="display: flex; margin-bottom: 30px">
      <div style="display: flex; align-items: center; flex: 1">
        <img :src="foods.avatar" alt="" style="width: 30px; height: 30px; border-radius: 50%">
        <span style="margin-left: 5px">{{ foods.userName }}</span>
      </div>
      <div style="flex: 1; display: flex; align-items: center; justify-content: flex-end">
        <span style="font-size: 20px; color: orange; font-weight: bold; margin-right: 2px">{{ foods.count }}</span> 浏览
        <span style="font-size: 20px; color: orange; font-weight: bold; margin-right: 2px; margin-left: 30px">{{ foods.collectCount }}</span> 收藏

        <el-button icon="el-icon-star-on" style="margin-left: 30px; color: orange" v-if="foods.userCollect" @click="collect">已收藏</el-button>
        <el-button icon="el-icon-star-off" style="margin-left: 30px" v-else @click="collect">收藏</el-button>
      </div>
    </div>

    <div style="font-size: 14px; color: #666; line-height: 24px; margin: 30px 0">{{ foods.descr }}</div>

    <div style="margin: 30px 0">
      <div style="margin: 20px 0; font-size: 22px; font-weight: bold">{{ foods.name }}的原料</div>
      <div style="display: flex; flex-wrap: wrap; background-color: #e9ffe2">
        <div v-for="(item, index) in materialList" :key="index" style="display: flex; padding: 10px; width: 50%;"
          :class="{ 'bt' : index !== materialList.length - 1 && index !== materialList.length - 2, 'br' : index % 2 === 0 }">
          <div style="flex: 1">{{ item.name }}</div>
          <div>{{ item.value }}</div>
        </div>
      </div>
    </div>


    <div style="margin: 30px 0">
      <div style="margin: 20px 0; font-size: 22px; font-weight: bold">{{ foods.name }}的做法</div>
      <div style="display: flex; margin-bottom: 10px" v-for="(item, index) in stepList" :key="index">
        <img :src="item.img" style="width: 100px; height: 100px">
        <div style="padding-left: 10px">
          <div style="font-size: 18px; font-weight: bold; margin-bottom: 10px">步骤 {{ index + 1 }}</div>
          <div>{{ item.text }}</div>
        </div>
      </div>
    </div>

    <div style="margin: 30px 0">
      <div style="margin: 20px 0; font-size: 22px; font-weight: bold">{{ foods.name }}的技巧</div>
      <div>{{ foods.skills }}</div>
    </div>

    <div style="margin: 30px 0; color: #666; font-size: 12px">发布时间：{{ foods.time }}</div>

    <div style="margin: 50px 0">
      <Comment :fid="id" module="菜谱" />
    </div>

    <Footer />

  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Comment from "@/components/Comment";
export default {
  name: "FoodDetail",
  components: {Comment, Footer},
  data() {
    return {
      id: this.$route.query.id,  // 菜谱ID
      foods: {},
      materialList: [],
      stepList: [],
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    this.updateCount()

  },
  methods: {
    updateCount() {
      this.$request.put('/foods/updateCount/' + this.id).then(res => {
        this.load()
      })
    },
    collect() {
      this.$request.post('/collect/set', { fid: this.id, userId: this.user.id, module: '菜谱' }).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    load() {
      this.$request.get('/foods/selectById/' + this.id).then(res => {
        this.foods = res.data || {}
        this.materialList = JSON.parse(this.foods?.material || '[]')
        this.stepList = JSON.parse(this.foods?.steps || '[]')
        if (this.materialList.length && this.materialList.length % 2 !== 0) {   // this.materialList.length % 2 !== 0
          this.materialList.push({})
        }
      })
    }
  }
}
</script>

<style scoped>
.br {
  border-right: 1px solid #ccc;
}

.bt {
  border-bottom: 1px solid #ccc;
}
.comment-active {
  color: #2a60c9;
}
</style>