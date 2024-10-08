<template>
  <div>
    <div class="card" style="padding: 15px; margin-bottom: 10px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>

    <el-row :gutter="10">
      <el-col :span="12">
        <div class="card">
          <div style="width: 100%; height: 400px" id="bar"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="card">
          <div style="width: 100%; height: 400px" id="pie"></div>
        </div>
      </el-col>
    </el-row>

    <div style="display: flex; margin: 10px 0">
      <div style="width: 50%;" class="card">
        <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>
        <div >
          <el-timeline  reverse slot="reference">
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">
              <el-popover
                  placement="right"
                  width="200"
                  trigger="hover"
                  :content="item.content">
                <span slot="reference">{{ item.title }}</span>
              </el-popover>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

const barOption = {
  title: {
    text: '数据趋势图',
    left: 'center'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    left: 'left'
  },
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '菜谱',
      data: [],
      type: 'bar',
      smooth: true
    },
    {
      name: '笔记',
      data: [],
      type: 'bar',
      smooth: true
    }
  ]
}

const pieOption = {
  title: {
    text: '菜谱分类统计',
    subtext: '比例图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      type: 'pie',
      center: ['50%', '60%'],
      radius: '50%',
      data: [],
      label: {
        show: true,
        formatter(param) {
          return param.name + ' (' + param.percent + '%)';
        }
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}

export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      notices: []
    }
  },
  mounted() {
    this.$request.get('/notice/selectAll').then(res => {
      this.notices = res.data || []
    })

    // 柱状图
    let barDom = document.getElementById('bar');
    let barChart = echarts.init(barDom);

    this.$request.get('/foods/selectCount').then(res => {
      barOption.xAxis.data = res.data?.map(v => v.date)
      barOption.series[0].data = res.data?.map(v => v.count)

      this.$request.get('/notebook/selectCount').then(res => {
        barOption.series[1].data = res.data?.map(v => v.count)
        barChart.setOption(barOption)
      })
    })


    // 饼图
    let pieDom = document.getElementById('pie');
    let pieChart = echarts.init(pieDom);
    this.$request.get('/foods/selectCategoryCount').then(res => {
      pieOption.series[0].data = res.data || []  //  [{name: 'xxxx', value: 10}]
      pieChart.setOption(pieOption)
    })
  }
}
</script>
