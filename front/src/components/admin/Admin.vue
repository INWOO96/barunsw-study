<script>
import { ref, provide, onBeforeMount } from 'vue'
import Login from '/@components/admin/Login.vue'

export default {
	components: {
		Login
	},
	setup() {
		const state = ref('login')
		const email = ref('user')
		
		provide('state', state)

		return {
			state,
			email
		}
	}
}
</script>

<template>
	<div v-if="state == 'ok'">
		<div class="row text-right pr-sm-2">
			<small><a href="#" @click="onLogout">Logout</a></small>
		</div>
	</div>

	<div v-else-if="state == 'loading'">Loading...</div>
	<Login v-else :email="email" :type="state" @state="state = 'ok'" />
</template>