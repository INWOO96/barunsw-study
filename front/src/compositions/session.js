import { reactive, toRefs, computed } from 'vue'

export const useSession = () => {
	
	const auth = reactive({id: null})
	const setData = (id, name) => {
		localStorage.setItem('id', id)
		localStorage.setItem('name', name)

		auth.id = id
	}

	const userId = computed(() => auth.id)
	
	return {
		...toRefs(auth),
		setData,
		userId
	}
}