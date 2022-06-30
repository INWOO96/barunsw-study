import { inject } from 'vue'
import { parse } from 'vue/compiler-sfc'

export const useFilter = () => {
	const today = inject('today')
	const fnSort = (a, b) => {
		const a_date = Date.parse(a.date);
		const b_date = Date.parse(b.date);

		if (a_date > b_date) { 
			return 1;
		}
		else if (a_date < b_date) {
			return 0;
		} 
		else {
			return a.id - b.id;
		}
	}
	const getPendingTodos = (todos) => {
		return todos.filter((todo) => todo.date < today && todo.completed != 'true')
			.slice()
			.sort(fnSort);
	}	
	const getActiveTodayTodos = (todos) => {
		return todos.filter((todo) => todo.date == today && todo.completed != 'true')
			.slice()
			.sort(fnSort);
	}
	const getCompletedTodayTodos = (todos) => {
		return todos.filter((todo) => todo.date == today && todo.completed == 'true')
			.slice()
			.sort(fnSort);
	}
	const getAllTodayTodos = (todos) => {
		return getActiveTodayTodos(todos)
			.concat(getCompletedTodayTodos(todos))
			.slice()
			.sort(fnSort);
	}
	const getAllTodos = (todos) => {
		return todos.slice().sort(fnSort);
	}

	return {
		getPendingTodos,
		getActiveTodayTodos,
		getCompletedTodayTodos,
		getAllTodayTodos,
		getAllTodos
	}
}