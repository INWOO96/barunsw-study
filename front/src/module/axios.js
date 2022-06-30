import { ref } from 'vue'
import { axios } from '@bundled-es-modules/axios'

export default function () {
	const commuicating = ref(false)
	const BASE_URL = 'http://localhost:8080'
	const creatURL = (url) => {
		return url.startsWith('http') ? url : BASE_URL + url
	}

	const checkResult = (resp, onSuccess, onFailed) => {
		commuicating.value = false
		if (resp.status === 200) {
			if (onSuccess) {
				onSuccess(resp.data, resp.headers)
			}
			else {
				console.log('response success but no callback func')
			}
		} 
		else {
			if (onFailed) {
				onFailed(resp.data)
			}
			else {
				console.log('error');
			}
		}
	}

	const axiosGet = async (url, onSuccess = null, onFailed = null) => {
		commuicating.value = true
		axios.get(creatURL(url)).then((resp) => {
				checkResult(resp, onSuccess, onFailed)
			})
			.catch((err) => {
				if (onFailed) {
					onFailed(err);
				}
			})
	}

	const axiosPost = async (url, data, onSuccess = null, onFailed = null) => {
		commuicating.value = true
		axios.post(creatURL(url), data)
			.then((resp) => {
				checkResult(resp, onSuccess, onFailed)
			})
			.catch((err) => {
				if (onFailed) {
					onFailed(err);
				}
			})
	}

	return {
		axiosGet,
		axiosPost,
	}
}