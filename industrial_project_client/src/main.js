import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import VueRouter from 'vue-router'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.config.productionTip = false
Vue.use(VueRouter)
new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
