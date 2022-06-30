<script>
import { ref, inject, onMounted } from 'vue'
import useAxios from '/@app_modules/axios.js'

export default {
	props: {
		email: {type: String, default: ''},
		type: {type: String, default: 'login'}
	},
	setup(props, context) {
		const state = inject('state')
		const { axiosGet, axiosPost } = useAxios();
		const email = ref(props.email)
		const password = ref('')
		const invalid = ref('ok')
		
	 	const onSubmit = (evt) => {
			let user = new FormData()
			user.append('username', email.value)
			user.append('password', password.value)

			axiosPost('/api/login', user, (resp) => {
				console.log('succ')
				invalid.value = 'no_email'
				
			}, (fail) => {
				console.log(fail)
			})
		} 

		return {
			onSubmit,
			state,
			email,
			password,
			invalid
		}
	}
}
</script>

<template>
	<div class="d-flex text-center mt-md-5">
		<main class="form-signin">
			<form @submit.prevent="onSubmit"> <!-- action="/api/login" method="post"> -->
				<h1 class="h3 mb-3 fw-normal">
					관리자 접속
				</h1>
				<small class="text-danger" v-if="invalid == 'no_email'">
					계정이 존재하지 않습니다.
				</small>
				<input type="text" id="username" name="username" class="form-control" placeholder="E-Mail"
					autocomplete="username" required ref="ref_email" v-model="email"/>
				<small class="text-danger" v-if="invalid == 'no_password'">
					패스워드가 일치하지 않습니다.
				</small>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password"
					autocomplete="password" required v-model="password"/>
				<div class="m-3"></div>
				<div class="checkbox mb-3">
					<input type="checkbox" />자동 로그인
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">
					Login
				</button>
				<p class="mt-5 mb-3 text-muted">오직 관리자만 입장할 수 있습니다.</p>
			</form>
		</main>
	</div>
</template>

<style scoped>
.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin > .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2

}

.form-signin input[type='email'] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin > input[type='password'] {
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>