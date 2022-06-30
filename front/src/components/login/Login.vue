<script>
import { ref, onMounted } from 'vue'
import { router } from '/@router/router.js'
import { useSession } from '/@compositions/session.js'
import useAxios from '/@app_modules/axios.js'

export default {
	setup() {
		const { axiosGet, axiosPost } = useAxios();
		const { setData } = useSession()

		const email = ref('')
		const password = ref('')
		const invalid = ref('ok')
		
		const loginSuccess = (data, header) => {
			setData( header['auth'],  header['name'])
			router.push('/home')
		}
		const loginFail = (fail) => {
			if (fail.response.status == 401) {
					invalid.value = 'no_email'
			}
			else {
				invalid.value = 'err'
				console.log(fail.response)
			}
		}
 		const onSubmit = (evt) => {
			let user = new FormData()
			user.append('username', email.value)
			user.append('password', password.value)

			axiosPost('/loginProc', user, loginSuccess, loginFail)
		} 
		const signUp = () => {
			router.push('/signup')
		}

		return {
			signUp,
			onSubmit,
			email,
			password,
			invalid,
		}
	}
}
</script>

<template>
	<div class="d-flex text-center mt-md-5">
		<main class="form-signin">
			<form @submit.prevent="onSubmit"> <!-- action="/api/login" method="post"> -->
				<h1 class="h3 mb-3 fw-normal">
					LOGIN
				</h1>
				<small class="text-danger" v-if="invalid == 'no_email'">
					아이디와 비밀번호를 확인하세요.
				</small>
				<small class="text-danger" v-if="invalid == 'err'">
					로그인 금지됨.
				</small>
				<div class="form-group mb-1">
					<label>아이디</label>
					<input type="text" id="username" name="username" class="form-control" placeholder="E-Mail"
						autocomplete="username" required ref="ref_email" v-model="email"/>
				</div>
				<div class="form-group">
					<label>비밀번호</label>
					<input type="password" id="password" name="password" class="form-control" placeholder="Password"
						autocomplete="password" required v-model="password"/>
				</div>
				<div class="m-3"></div>
				<div class="checkbox mb-3">
					<input type="checkbox" />자동 로그인
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit" @keyup.enter="submit">
					로그인
				</button>
				<button class="btn btn-lg btn-primary btn-block ms-2" type="button" @click="signUp">
					회원가입
				</button>
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

.form-group {
	text-align: left;
}
</style>