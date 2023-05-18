import Vue from 'vue'
import VueRouter from "vue-router"

// Vue Router 라이브러리를 사용하기 위해
// Vue.use()를 사용하여 Vue Router 전역으로 등록
Vue.use(VueRouter)

// Vue 인스턴스 생성 및 라우터 구성
const router = new VueRouter({

    // URL을 히스토리 모드로 바꿈 (default: "hash")
    mode: "history",
    routes: [
        {
            name: 'main',
            path: '/',
            component: () => import('@/components/views/User.vue')
        },
        {
            name: 'cpu',
            path: '/computer',
            component: () => import('@/components/views/Computer.vue')
        }
    ]
});

export default router;