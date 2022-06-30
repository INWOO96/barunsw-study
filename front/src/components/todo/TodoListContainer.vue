<script>
import { reactive, readonly, provide, onBeforeMount } from 'vue'
import useAxios from '/@app_modules/axios.js'

import TodoListNew from '/@components/todo/TodoListNew.vue'
import TodoListMain from '/@components/todo/TodoListMain.vue'

export default {
	name: 'TodoListContainer',
	setup() {
		const { axiosGet, axiosPost } = useAxios()
		const todos = reactive([])

		provide('todos', readonly(todos))
		
		const addTodo = (job, date) => {
			const new_todo = {
				job: job,
				date: date,
				completed: false,
				request: 'I'
			}
			
			saveTodos(new_todo)
		} 
		const removeTodo = (id) => {
			saveTodos({id, request: 'D'})
		}
		const completeTodo = (id) => {
			saveTodos({id, completed: true, request: 'U'})
		}
		const loadTodos = () => {
			axiosGet('/api/getTodoList', (data) => {
				Object.assign(todos, data)
				console.log('getTodoList', data, todos)
			})
		}
		const saveTodos = (todo) => {
			axiosPost('/api/postTodo', todo, (result) => {
				loadTodos()
			})
		}

		provide('addTodo', addTodo)
		provide('removeTodo', removeTodo)
		provide('completeTodo', completeTodo)
		
		onBeforeMount(() => {
			loadTodos()
		})
	},
	components: {
		TodoListNew,
		TodoListMain
	}

}
</script>

<template>
	<todo-list-new />
	<section class="container">
		<div class="row justify-content-center m-2">
			<todo-list-main/>
		</div>
	</section>
</template>

<style scoped>

</style>