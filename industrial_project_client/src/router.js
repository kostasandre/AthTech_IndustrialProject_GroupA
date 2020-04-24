import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from './components/LoginPage'
import HomePage from './components/HomePage'
import RegisterPage from './components/RegisterPage'
import AdminPage from './components/AdminPage'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes:[
        {
            path: '/login',
            name: 'login',
            component: LoginPage,
            props:true
        },
        {
            path: '/',
            name: 'home',
            component: HomePage,
            props: true
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterPage
        },
        {
            path: '/admin',
            name: 'admin',
            component: AdminPage
        }
    ]
})