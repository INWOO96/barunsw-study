import { createApp } from 'vue'
import { router } from '/@router/router.js'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.esm.min.js'

createApp(App).use(router).mount('#app')