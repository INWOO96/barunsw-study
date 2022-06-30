<script setup>
import { ref, watch } from 'vue'
import { router } from '/@router/router.js'
import useAxios from '/@app_modules/axios.js'

const { axiosGet, axiosPost } = useAxios()
const invalid = ref('nok')
const userId = ref('')
const userPassword = ref('')
const rePassword = ref('') 
const phone = ref('')
const email = ref('')

watch([() => userId.value],
	([new_userId], [old_userId]) => {
		if (invalid.value !== 'nok' && new_userId !== old_userId) {
			invalid.value = 'nok'
		}
	},
	{immediate: true,}
)

const idCheck = () => {
	if (!userId.value) {
		alert('아이디를 입력하세요')
		return;
	}
	axiosGet('/signUp?id=' + userId.value, (resp) => {
		console.log(resp)
		if (resp) {
			invalid.value = 'ok'
		}
		else {
			invalid.value = 'invalid'
		}
	})
}
const cancel = () => { router.push('/login') }
const onSubmit = (evt) => {
	if (invalid.value == 'nok') {
		alert('아이디 중복확인을 클릭하세요')
		return false
	}
	if (invalid.value == 'invalid') {
		alert('사용할 수 없는 아이디 입니다.')
		return false
	}

	const pwdCheck 	= /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/
	const regPhone 	= /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/
	const regEmail 	= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/
	
	if (!pwdCheck.test(userPassword.value)) {
		alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.")
		return false
	}
	if (userPassword.value != rePassword.value) {
		alert("비밀번호가 일치하지 않습니다.")
		return false
	}
	if (!regPhone.test(phone.value)) {
		alert('휴대전화 형식을 맞춰 다시 입력해주세요\n ex) 010-0000-0000')
		return false
	}
	if (!regEmail.test(email.value)) {
		alert('이메일 형식에 맞춰 다시 입력해주세요\n ex) xxx@xxx.xxx')
		return false
	}

	let sign = new FormData();
	sign.append('userId', userId.value)
	sign.append('userPassword', userPassword.value)
	sign.append('phone', phone.value)
	sign.append('email', email.value)

	axiosPost('/signUp', sign, (resp) => {
		if (resp) {
			alert('가입완료 되었습니다.\n로그인 화면으로 이동합니다.')
			router.push('/login')
		}
		else {
			alert('회원 가입에 실패하였습니다.')
			location.reload()
		}
	})
}


</script>

<template>
	<div class="container">
		<div class="py-5 text-center">
			<h2>회원 가입 폼</h2>
		</div>
		<h4 class="mb-3">회원 가입</h4>
		<form @submit.prevent="onSubmit">
			<div>
				<label for="userId">아이디</label> 
				<input type="text" id="userId" name="userId" required class="form-control" placeholder="아이디를 입력하세요" v-model="userId">
				<button type="button" class="btn btn-secondary" style="margin-top:3px;" @click="idCheck">중복확인</button>
				<small class="text-danger ms-1" v-if="invalid == 'invalid'"> 사용 할 수 없는 아이디 입니다.</small>
				<small class="text-success ms-1" v-if="invalid == 'ok'">사용 가능한 아이디 입니다.</small>
			</div>
			<div>
				<label for="userPassword">비밀번호</label> 
				<input type="password" id="userPassword" required name="userPassword" class="form-control" placeholder="영문자+숫자+특수문자 조합" v-model="userPassword">
			</div>
			<div>
				<label for="rePassword">비밀번호 확인</label> 
				<input type="password" id="rePassword" required name="rePassword" class="form-control" v-model="rePassword">
			</div>
			<div>
				<label for="phone">핸드폰 번호</label> 
				<input type="tel" autocomplete="phone" required pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" id="phone" name="phone" v-model="phone" class="form-control" placeholder="핸드폰 번호를 입력하세요 ex) 010-0000-0000">
			</div>
			<div>
				<label for="email">이메일</label> 
				<input type="email" autocomplete="email" required id="email" name="email" v-model="email" ref="ref_email" class="form-control" placeholder="이메일을 입력하세요">
			</div>
			
			<div class="m-3"></div>
			<!-- <hr class="my-4"> -->
			<div class="row">
				<div class="col">
					<button class="w-100 btn btn-primary btn-lg" type="submit" >회원 등록</button>
				</div>
				<div class="col">
					<button class="w-100 btn btn-secondary btn-lg" type="button" @click="cancel">취소</button>
				</div>
			</div>

		</form>

	</div>
</template>
