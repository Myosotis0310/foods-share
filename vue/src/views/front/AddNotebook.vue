<template>
  <div style="width: 50%; margin: 10px auto">
    <div class="card">
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="title" label="标题">
          <el-input v-model="form.title" autocomplete="off" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="封面">
          <el-upload
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleCoverSuccess"
          >
            <el-button type="primary">上传封面</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <div id="editor"></div>
        </el-form-item>
      </el-form>
      <div style="text-align: center; margin-top: 20px">
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import E from "wangeditor"
export default {
  name: "addNotebook",
  data() {
    return {
      id: this.$route.query.id,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      form: {},
      rules: {
        name: [
          { required: true, message: '请输入标题', trigger: 'blur' },
        ],
      },
      editor: null
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    load() {
      if (this.id) {  // 编辑
        this.$request.get('/notebook/selectById/' + this.id).then(res => {
          this.form = res.data || {}
          this.setRichText(this.form.content || '')
        })
      } else {  // 新增
        this.setRichText('')
      }
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.form.userId = this.user.id
          this.form.content = this.editor.txt.html()
          if (this.form.id) {
            this.$request.put('/notebook/update', this.form).then(res => {
              if (res.code === '200') {   // 表示操作成功
                this.$message.success('操作成功')
              } else {
                this.$message.error(res.msg)  // 弹出错误的信息
              }
            })
          } else {
            this.$request.post('/notebook/add', this.form).then(res => {
              if (res.code === '200') {   // 表示操作成功
                this.$message.success('操作成功')
                location.href = '/front/addNotebook?id=' + res.data.id
              } else {
                this.$message.error(res.msg)  // 弹出错误的信息
              }
            })
          }
        }
      })
    },
    handleCoverSuccess(res) {
      this.form.cover = res.data
    },
    setRichText(html) {
      this.$nextTick(() => {
        this.editor = new E(`#editor`)
        this.editor.config.uploadImgServer = this.$baseUrl + '/files/editor/upload'
        this.editor.config.uploadFileName = 'file'
        this.editor.config.uploadImgHeaders = {
          token: this.user.token
        }
        this.editor.config.uploadImgParams = {
          type: 'img',
        }
        this.editor.create()  // 创建
        this.editor.txt.html(html)
      })
    },
  }
}
</script>

<style scoped>

</style>