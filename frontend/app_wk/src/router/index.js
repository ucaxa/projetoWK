import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProjetoView from '../views/ProjetoView.vue'
import ClienteView from '../views/ClienteView.vue'
import AtividadeView from '../views/AtividadeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/projetos',
    name: 'projetos',
    component: ProjetoView
  },
  {
    path: '/clientes',
    name: 'clientes',
    component: ClienteView
  },
  {
    path: '/atividades',
    name: 'atividades',
    component: AtividadeView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
