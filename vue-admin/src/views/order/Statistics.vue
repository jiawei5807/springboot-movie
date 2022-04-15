<template>
  <div>
    <div class="block" style="align-content: center">
      <span class="demonstration" style="margin-right: 20px">
        <el-tag>请选择一个时间范围：</el-tag>
      </span>
      <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
      >
      </el-date-picker>
      <el-button style="margin-left: 20px" type="primary" icon="el-icon-search" @click="getStatistics">查看</el-button>
    </div>
    <div>
      <e-charts class="chart"
                :option="option"
      />
    </div>
  </div>
</template>
<script>
import {GetOrderStatistics} from '@/api/order'

export default {
  name: "Statistics",
  data() {
    return {
      value1: '',
      option: {
        xAxis: {
          name: '日期',
          type: 'category',
          data: []
        },
        yAxis: {
          name: '销售额/元',
          type: 'value'
        },
        series: [{
          data: [],
          type: 'line'
        }]
      },
    };
  },
  methods: {
    getStatistics() {
      let startDate = this.value1[0];
      let endDate = this.value1[1];
      let data = {
        startDate:startDate,
        endDate:endDate,
      }
      if (typeof (startDate) !== "undefined" && typeof (endDate) !== "undefined") {
        GetOrderStatistics(data).then(res => {
          if (res.success) {
            this.option.xAxis.data = res.data.map.X;
            this.option.series[0].data = res.data.map.Y;
            this.$message.success("统计结果汇总成功！")
          }else {
            this.$message.error("请重新选择日期！");
          }
        });
      } else {
        this.$message.error("请选择日期！");
      }
    }
  }
};
</script>

<style scoped>
.chart {
  height: 700px;
}
</style>
