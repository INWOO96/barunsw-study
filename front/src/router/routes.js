import Profile from '/@components/profile/Profile.vue'
import Application from '/@components/application/Application.vue'
import TodoList from '/@components/todo/TodoListContainer.vue'
import Home from '/@components/blog/Home.vue'
import Admin from '/@components/admin/Admin.vue'
import Login from '/@components/login/Login.vue'
import SignUp from '/@components/login/SignUp.vue'

import { defineComponent } from 'vue'

const NotFound = defineComponent({
	template: '<div>Not Found</div>'
})

const routes = [
	{path: '/', redirect: '/profile'},
	{path: '/home', name: 'home', component: Home},
	{path: '/profile', name: 'profile', component: Profile},
	{path: '/application', name: 'application', component: Application},
	{path: '/todo', name: 'todo', component: TodoList},
	{path: '/admin', name: 'admin', component: Admin},
	{path: '/login', name: 'login', component: Login},
	{path: '/signup', name: 'signup', component: SignUp},
	{path: '/:catchAll(.*)+', component: NotFound},
]
export default routes