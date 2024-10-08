<template>
  <div style="width: 50%; margin: 10px auto">
    <div class="card">
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="name" label="名称">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item prop="category" label="分类">
          <el-select v-model="form.category" style="width: 100%">
            <el-option v-for="item in categoryList" :key="item.id" :value="item.name"></el-option>
          </el-select>
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
        <el-form-item prop="descr" label="简介">
          <el-input type="textarea" v-model="form.descr" autocomplete="off" placeholder="请输入简介"></el-input>
        </el-form-item>
        <el-form-item prop="skills" label="技巧">
          <el-input type="textarea" v-model="form.skills" autocomplete="off" placeholder="请输入技巧"></el-input>
        </el-form-item>
        <el-form-item label="食材">
          <div style="display: flex; align-items: center; margin-bottom: 5px" v-for="(item, index) in materialList" :key="index">
            <el-input style="flex: 1;" v-model="item.name" placeholder="请输入食材名称"></el-input>
            <el-input style="flex: 1;  margin-left: 10px"  v-model="item.value" placeholder="请输入食材分量"></el-input>
            <i @click="delMaterial(index)" class="el-icon-delete" style="margin-left: 10px; cursor: pointer"></i>
          </div>
          <div><span style="color: #2a60c9; font-size: 14px; cursor: pointer" @click="addMaterial">添加新的食材</span></div>
        </el-form-item>
        <el-form-item label="步骤">
          <div style="display: flex; align-items: center; margin-bottom: 5px" v-for="(item, index) in stepList" :key="index">
            <el-upload
                :action="$baseUrl + '/files/upload'"
                :headers="{ token: user.token }"
                :on-success="(response, file, fileList) => handleStepImgSuccess(response, file, fileList, item)"
                :show-file-list="false"
            >
              <img v-if="item.img" :src="item.img" alt="" style="width: 50px; height: 50px">
              <el-button v-else type="primary">上传图片</el-button>
            </el-upload>
            <el-input style="flex: 1;  margin-left: 10px"  v-model="item.value" placeholder="请填写步骤"></el-input>
            <i @click="delStep(index)" class="el-icon-delete" style="margin-left: 10px; cursor: pointer"></i>
          </div>
          <div><span style="color: #2a60c9; font-size: 14px; cursor: pointer" @click="addStep">添加新的步骤</span></div>
        </el-form-item>
      </el-form>
      <div style="text-align: center; margin-top: 20px">
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddFoods",
  data() {
    return {
      id: this.$route.query.id,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      categoryList: [],
      form: {},
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
        ],
        category: [
          { required: true, message: '请选择分类', trigger: 'blur' },
        ],
        descr: [
          { required: true, message: '请输入简介', trigger: 'blur' },
        ],
        skills: [
          { required: true, message: '请输入技巧', trigger: 'blur' },
        ]
      },
      materialList: [],
      stepList: []
    }
  },
  created() {
    this.loadCategory()
    this.load()
  },
  methods: {
    load() {
      if (this.id) {
        this.$request.get('/foods/selectById/' + this.id).then(res => {
          this.form = res.data || {}
          this.materialList = JSON.parse(this.form.material || '[]')  // JSON.parse解析json字符串
          this.stepList = JSON.parse(this.form.steps || '[]')
        })
      }
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          if (!this.materialList.length) {
            this.$message.warning('食材不能为空')
            return
          }
          if (!this.stepList.length) {
            this.$message.warning('步骤不能为空')
            return
          }

          this.form.material = JSON.stringify(this.materialList)
          this.form.steps = JSON.stringify(this.stepList)  // 把json数组转成json字符串存到数据库
          this.form.userId = this.user.id
          if (this.form.id) {
            this.$request.put('/foods/update', this.form).then(res => {
              if (res.code === '200') {   // 表示操作成功
                this.$message.success('操作成功')
              } else {
                this.$message.error(res.msg)  // 弹出错误的信息
              }
            })
          } else {
            this.$request.post('/foods/add', this.form).then(res => {
              if (res.code === '200') {   // 表示操作成功
                this.$message.success('操作成功')
                location.href = '/front/addFoods?id=' + res.data.id
              } else {
                this.$message.error(res.msg)  // 弹出错误的信息
              }
            })
          }
        }
      })
    },
    delStep(index) {
      this.stepList.splice(index, 1)
    },
    addStep() {
      this.stepList.push({ img: '', text: '' })
    },
    delMaterial(index) {
      this.materialList.splice(index, 1)
    },
    addMaterial() {
      this.materialList.push({ name: '', value: '' })
    },
    loadCategory() {
      this.$request.get('/category/selectAll').then(res => {
        this.categoryList = res.data || []
      })
    },
    handleCoverSuccess(res) {
      this.form.cover = res.data
    },
    handleStepImgSuccess(response, file, fileList, item) {
      item.img = response.data
    }
  }
}
</script>

<style scoped>

</style>