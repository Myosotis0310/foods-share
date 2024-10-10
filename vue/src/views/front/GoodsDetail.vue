<template>
  <div style="width: 50%; margin: 20px auto">
    <div style="display: flex">
      <img :src="goods.img" alt="" style="width: 50%; height: 350px; border-radius: 5px">
      <div style="padding-left: 20px">
        <div style="margin: 20px 0; font-size: 22px; font-weight: bold">{{ goods.name }}</div>
        <div style="margin: 50px 0">{{ goods.descr }}</div>
        <div style="margin: 30px 0; color: red; font-size: 22px">￥{{ goods.price }} / {{ goods.unit }}</div>
        <div style="margin: 20px 0">
          <span style="color: #666">发货地：<span style="color: #333">{{ goods.address }}</span></span>
          <span style="margin-left: 20px; color: #666">剩余：<span style="color: #333">{{ goods.store }}</span></span>
        </div>
        <div style="margin: 20px 0">
          <el-input-number :min="1" :max="10" v-model="form.num"></el-input-number>
          <el-button @click="buy" type="primary" style="background-color: #f59300; border-color: #f59300; margin-left: 10px" >立即购买</el-button>
        </div>
      </div>
    </div>

    <div style="margin: 50px 0">
      <div style="margin-bottom: 20px; font-size: 22px; font-weight: bold; text-align: center">商品详情</div>
      <div class="w-e-text">
        <div v-html="goods.content"></div>
      </div>
    </div>

    <el-dialog title="下单" :visible.sync="fromVisible" width="30%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="收货地址" prop="address">
          <el-input type="textarea" v-model="form.address" placeholder="收货地址"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="手机号码"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrder">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "GoodsDetail",
  data() {
    return {
      id: this.$route.query.id,
      goods: {},
      form: { num: 1 },
      fromVisible: false,
      rules: {
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, pattern: /^1[3-9]\d{9}$/, message: '请输入联系电话', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    /*changeStatus(row) {
      this.$request.put('/orders/cancel', row).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load(1)
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
      })
    },*/
    addOrder() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.form.goodsId = this.id
          this.$request.post('/orders/add', this.form).then(res => {
            if (res.code === '200') {
              this.$message.success('下单成功')
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    buy() {
      this.$set(this.form, 'address', "")
      this.$set(this.form, 'phone', "")
      this.fromVisible = true
    },
    load() {
      this.$request.get('/goods/selectById/' + this.id).then(res => {
        this.goods = res.data || {}
      })
    }
  }
}
</script>

<style scoped>

</style>
