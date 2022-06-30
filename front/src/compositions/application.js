import { reactive, computed } from 'vue'

export const useApplications = () => {
	const state = reactive({applications: []})

	const SET_DATA = (data) => {
		state.applications = data
	}

	const applications_count = computed(() => state.applications.length)
	const applications = (filter = null) => {
		if (filter) {
			return computed(() => state.applications.filter((i) => i.name == filter))
		}
		return computed(() => state.applications)
	}

	const setApplications = (data) => {
		if (data.length > 0) {
			SET_DATA(data)
		}
		else {
			console.log('data is empty')
		}
	}

	return {
		applications_count,
		applications,
		setApplications
	}
}