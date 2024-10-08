<template>
  <div class="main-content" style="margin: 20px auto">
    <div>
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in tableData" :key="item.id" >
          <div style="cursor: pointer; margin-bottom: 10px" @click="goDetail('/front/goodsDetail?id=' + item.id)">
            <img :src="item.img" alt="" style="width: 100%; height: 200px; border-radius: 5px 5px 0 0">
            <div style="margin: 10px 0; font-size: 16px" class="line1">{{ item.name }}</div>
            <div style="color: red; font-size: 18px">￥ {{ item.price }}</div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="margin: 20px 0">
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
import Footer from '@/components/Footer'
export default {
  name: "Mall",
  components: {
    Footer
  },
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 8,  // 每页显示的个数
      total: 0,
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    goDetail(path) {
      location.href = path
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/goods/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>