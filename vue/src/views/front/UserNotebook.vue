<template>
  <div style="margin: 20px auto; width: 50%">
    <div style="display: flex; margin-bottom: 20px">
      <div style="flex: 1; display: flex; align-items: center; line-height: 14px">
        <div @click="load('notebook')" :class="{ 'active' : current === 'notebook' }" style="cursor: pointer; padding-right: 10px; color: #666; border-right: 2px solid #333">我发布的笔记</div>
        <div @click="load('collect')" :class="{ 'active' : current === 'collect' }" style="cursor: pointer; padding-left: 10px; color: #666;">我收藏的笔记</div>
      </div>
      <el-button style="border-color: #333; color: #333" @click="$router.push('/front/addNotebook')">发布新笔记</el-button>
    </div>

    <div>
      <el-row :gutter="10">
        <el-col :span="8" v-for="item in notebookList" :key="item.id" >
          <div style="cursor: pointer; margin-bottom: 20px" @click="goDetail('/front/notebookDetail?id=' + item.id)">
            <img :src="item.cover" alt="" style="width: 100%; height: 150px; border-radius: 5px 5px 0 0">
            <div style="box-shadow: -3px 3px 3px -2px rgba(0,0,0,0.1), 3px 3px 3px -2px rgba(0,0,0,0.1); padding: 5px">
              <div style="display: flex; align-items: center">
                <div style="flex: 1; width: 0; font-size: 18px" class="line1">{{ item.title }}</div>
                <el-tag v-if="current === 'notebook'" size="mini">{{ item.status }}</el-tag>
              </div>
              <div style="color: #666; font-size: 13px; margin-top: 10px; display: flex; align-items: center">
                <div v-if="current === 'notebook'">{{ item.date }}</div>
                <div v-else>{{ item.userName }}</div>
                <div style="flex: 1; text-align: right">
                  <span v-if="current === 'notebook'" @click.stop="edit(item.id)" style="margin-right: 10px; cursor: pointer"><i class="el-icon-edit"></i>编辑</span>
                  <span v-if="current === 'notebook'" @click.stop="del(item.id)" style="cursor: pointer"><i class="el-icon-delete"></i>删除</span>
                  <span v-if="current === 'collect'" @click.stop="delCollect(item.id)" style="cursor: pointer"><i class="el-icon-delete"></i>取消收藏</span>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script>
export default {
  name: "UserFoods",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      current: 'notebook',
      notebookList: []
    }
  },
  created() {
    this.load('notebook')
  },
  methods: {
    delCollect(fid) {
      this.$confirm('您确定取消吗？', '确认取消', {type: "warning"}).then(response => {
        this.$request.delete('/collect/userDelete/', {
          data: { fid: fid, userId: this.user.id, module: '笔记' }
        }).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(this.current)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    edit(id) {
      this.$router.push('/front/addNotebook?id=' + id)
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/notebook/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(this.current)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        });
      }).catch(() => {
      });
    },
    goDetail(path) {
      location.href = path
    },
    load(type) {
      this.current = type
      let url = '/notebook/selectAll'
      if (type === 'collect') {
        url = '/notebook/selectCollect'
      }
      this.$request.get(url).then(res => {
        this.notebookList = res.data || []
      });
    }
  }
}
</script>

<style scoped>
.active {
  color: #333 !important;
  font-weight: bold;
}
</style>