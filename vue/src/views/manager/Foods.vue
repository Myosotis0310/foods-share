<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入名称查询" style="width: 200px" v-model="name"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column label="封面">
          <template v-slot="scope">
            <el-image :src="scope.row.cover" style="width: 50px; height: 30px; border-radius: 5px"
                      :preview-src-list="[scope.row.cover]"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="descr" label="简介"  show-overflow-tooltip></el-table-column>
        <el-table-column prop="skills" label="技巧" show-overflow-tooltip></el-table-column>
        <el-table-column label="查看原料" width="100">
          <template v-slot="scope">
            <el-button size="mini" @click="viewMaterial(scope.row.material)">查看原料</el-button>
          </template>
        </el-table-column>
        <el-table-column label="查看步骤" width="100">
          <template v-slot="scope">
            <el-button size="mini" @click="viewSteps(scope.row.steps)">查看步骤</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="userId" label="作者ID"></el-table-column>
        <el-table-column prop="userName" label="作者"></el-table-column>
        <el-table-column prop="time" label="上传时间"></el-table-column>
        <el-table-column prop="count" label="浏览量"></el-table-column>
        <el-table-column prop="status" label="审核状态"></el-table-column>
        <el-table-column label="审核" align="center" width="220" fixed="right">
          <template v-slot="scope">
            <el-button :disabled="scope.row.status !== '待审核'" type="primary" size="mini" @click="changeStatus(scope.row, '审核通过')">通过</el-button>
            <el-button :disabled="scope.row.status !== '待审核'" type="danger" size="mini" @click="changeStatus(scope.row, '审核拒绝')">拒绝</el-button>
            <el-button :disabled="scope.row.status === '待审核'" type="info" size="mini" @click="changeStatus(scope.row, '待审核')">重置</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="80" fixed="right">
          <template v-slot="scope">
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>

    <el-dialog title="原料" :visible.sync="materialVisible" width="30%" :close-on-click-modal="false" destroy-on-close>
      <div style="padding: 0 40px">
        <div v-for="(item, index) in materialList" :key="index" style="display: flex; padding: 10px 0; margin-bottom: 10px; border-bottom: 1px solid #eee">
          <div style="flex: 1">{{ item.name }}</div>
          <div style="flex: 1">{{ item.value }}</div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="materialVisible = false">关 闭</el-button>
      </div>
    </el-dialog>

    <el-dialog title="步骤" :visible.sync="stepVisible" width="30%" :close-on-click-modal="false" destroy-on-close>
      <div style="padding: 0 40px">
        <div v-for="(item, index) in stepList" :key="index" style="display: flex; padding: 10px 0; margin-bottom: 10px; border-bottom: 1px solid #eee">
          <div style="flex: 1; text-align: center"><img :src="item.img" alt="" style="width: 100px; height: 100px"></div>
          <div style="flex: 1">
            <div style="margin-bottom: 10px; font-weight: bold">步骤{{ index + 1 }}</div>
            <div>{{ item.text }}</div>
          </div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="stepVisible = false">关 闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Foods",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
      materialVisible: false,
      materialList: [],
      stepVisible: false,
      stepList: []
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    changeStatus(row, status) {
      row.status = status
      this.$request.put('/foods/update', row).then(res => {
        if (res.code === '200') {   // 表示操作成功
          this.$message.success('操作成功')
          this.load(1)
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
      })
    },
    viewMaterial(material) {
      this.materialList = JSON.parse(material || '[]')
      this.materialVisible = true
    },
    viewSteps(steps) {
      this.stepList = JSON.parse(steps || '[]')
      this.stepVisible = true
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/foods/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/foods/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/foods/selectPage', {
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
    reset() {
      this.name = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>
