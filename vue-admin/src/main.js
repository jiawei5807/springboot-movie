import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import "echarts";
import ECharts from 'vue-echarts'

Vue.use(ElementUI);
Vue.component('ECharts', ECharts)

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
// 全局注册组件（也可以使用局部注册）

