import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from './components/LoginPage'
import HomePage from './components/HomePage'
import RegisterPage from './components/RegisterPage'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes:[
        {
            path: '/login',
            name: 'login',
            component: LoginPage
        },
        {
            path: '/',
            name: 'home',
            component: HomePage
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterPage
        }
    ]
})