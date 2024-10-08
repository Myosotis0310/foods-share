<template>
  <div class="main-content" style="margin: 20px auto">
    <el-row :gutter="10" style="min-height: 400px">
      <el-col :span="6" v-for="item in tableData" :key="item.id">
        <div style="cursor: pointer; margin-bottom: 10px" @click="goDetail('/front/notebookDetail?id=' + item.id)">
          <img :src="item.cover" alt="" style="width: 100%; height: 200px; border-radius: 5px 5px 0 0">
          <div style="box-shadow: -3px 3px 3px -2px rgba(0,0,0,0.1), 3px 3px 3px -2px rgba(0,0,0,0.1); padding: 5px">
            <div style="margin-bottom: 10px; font-size: 16px" class="line1">{{ item.title }}</div>
            <div style="display: flex; align-items: center">
              <img style="width: 30px; height: 30px; border-radius: 50%" :src="item.avatar" alt="">
              <span style="color: #666; margin-left: 10px">{{ item.userName }}</span>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>

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
  name: "Notebook",
  components: {Footer},
  data() {
    return {
      pageNum: 1,
      pageSize: 8,
      total: 0,
      tableData: []
    }
  },
  created() {
    this.loadNotebookList(1)
  },
  methods: {
    goDetail(path) {
      location.href = path
    },
    loadNotebookList(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/notebook/selectPage',
          {
            params: {
              status: '审核通过',
              pageNum: this.pageNum,
              pageSize: this.pageSize
            }
          }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.loadNotebookList(pageNum)
    },
  }
}
</script>

<style scoped>

</style>