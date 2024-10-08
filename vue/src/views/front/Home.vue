<template>
  <div class="main-content">
    <el-carousel height="450px">
      <el-carousel-item v-for="item in imgs" :key="item">
        <img :src="item" alt="" style="width: 100%;">
      </el-carousel-item>
    </el-carousel>

    <div style="margin: 30px 0">
      <div style="margin-bottom: 20px; display: flex; align-items: center">
        <div style="font-weight: bold; font-size: 22px; flex: 1">每日精选菜谱</div>
        <a style="color: #2a60c9" href="/front/foods">更多>></a>
      </div>
      <div>
        <el-row :gutter="10">
          <el-col :span="6" v-for="item in recommendFoods" :key="item.id" >
            <div style="cursor: pointer" @click="goDetail('/front/foodDetail?id=' + item.id)">
              <img :src="item.cover" alt="" style="width: 100%; height: 150px; border-radius: 5px 5px 0 0">
              <div style="margin: 5px 0; font-size: 16px" class="line1">{{ item.name }}</div>
              <div style="color: #666">By. {{ item.userName }}</div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

    <div style="margin: 30px 0">
      <div style="margin-bottom: 20px; display: flex; align-items: center">
        <div style="font-weight: bold; font-size: 22px; flex: 1">美食笔记</div>
        <a style="color: #2a60c9" href="/front/notebook">更多>></a>
      </div>
      <div>
        <el-row :gutter="10">
          <el-col :span="6" v-for="item in notebookList" :key="item.id">
            <div style="cursor: pointer" @click="goDetail('/front/notebookDetail?id=' + item.id)">
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
      </div>
    </div>

    <div style="margin: 30px 0">
      <div style="margin-bottom: 20px; display: flex; align-items: center">
        <div style="font-weight: bold; font-size: 22px; flex: 1">精品商城</div>
        <a style="color: #2a60c9" href="/front/mall">更多>></a>
      </div>
      <div>
        <el-row :gutter="10">
          <el-col :span="12" v-for="item in goodsList" :key="item.id">
            <div style="display: flex; margin-bottom: 20px; cursor: pointer" @click="goDetail('/front/goodsDetail?id=' + item.id)">
              <img :src="item.img" alt="" style="width: 30%; height: 150px; border-radius: 5px">
              <div style="padding-left: 20px">
                <div style="font-size: 18px; font-weight: bold" class="line1">{{ item.name }}</div>
                <div style="margin: 40px 0; color: #666">{{ item.descr }}</div>
                <div style="color: red; font-size: 16px">{{ item.price }} / {{ item.unit }}</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

    <Footer />

  </div>
</template>

<script>

import Footer from "@/components/Footer";
export default {
  components: {Footer},
  data() {
    return {
      imgs: [
          require('@/assets/imgs/1.png'),
          require('@/assets/imgs/2.png'),
          require('@/assets/imgs/3.png'),
      ],
      recommendFoods: [],
      notebookList: [],
      goodsList: []
    }
  },
  mounted() {
    this.loadRecommendFoods()
    this.loadNotebookList()
    this.loadGoodsList()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    goDetail(path) {
      location.href = path
    },
    loadGoodsList() {
      this.$request.get('/goods/selectPage', { params: { pageSize: 4 } }).then(res => {
        this.goodsList = res.data?.list || []
      })
    },
    loadNotebookList() {
      this.$request.get('/notebook/selectPage', { params: { status: '审核通过', pageSize: 4 } }).then(res => {
        this.notebookList = res.data?.list || []
      })
    },
    loadRecommendFoods() {
      this.$request.get('/foods/selectRecommend').then(res => {
        this.recommendFoods = res.data || []
      })
    }
  }
}
</script>
