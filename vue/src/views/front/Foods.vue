<template>
  <div class="main-content" style="margin: 20px auto">
    <div>
      <div v-for="type in typeList" :key="type" style="margin-bottom: 20px">
        <strong style="margin-right: 20px">{{ type }}</strong>
        <span @click="loadFoods(1, item.name)" :class="{ 'category-active' : category === item.name }" style="margin-right: 20px; cursor: pointer"
              v-for="item in categoryList.filter(v => v.type === type)">{{ item.name }}</span>
      </div>
    </div>

    <div style="min-height: 300px">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in tableData" :key="item.id" >
          <div style="cursor: pointer; margin-bottom: 10px" @click="goDetail('/front/foodDetail?id=' + item.id)">
            <img :src="item.cover" alt="" style="width: 100%; height: 150px; border-radius: 5px 5px 0 0">
            <div style="margin: 5px 0; font-size: 16px" class="line1">{{ item.name }}</div>
            <div style="color: #666">By. {{ item.userName }}</div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div style="margin: 20px 0" v-if="total">
      <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <Footer />

  </div>
</template>

<script>
import Footer from "@/components/Footer";
export default {
  name: "Foods",
  components: {Footer},
  data() {
    return {
      categoryList: [],
      typeList: [],
      category: null,
      pageNum: 1,
      pageSize: 8,
      total: 0,
      tableData: [],
      name: this.$route.query.name
    }
  },
  created() {
    this.loadCategory()
    this.loadFoods(1, null)
  },
  methods: {
    goDetail(path) {
      location.href = path
    },
    loadFoods(pageNum, category) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      if (category) this.category = category
      this.$request.get('/foods/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          status: '审核通过',
          category: this.category,
          name: this.name
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    loadCategory() {
      this.$request.get('/category/selectAll').then(res => {
        this.categoryList = res.data || []
        this.typeList = new Set(this.categoryList.map(v => v.type))
      })
    },
    handleCurrentChange(pageNum) {
      this.loadFoods(pageNum, null)
    },
  }
}
</script>

<style scoped>
.category-active {
  color: #f59300;
  font-weight: bold;
}
</style>