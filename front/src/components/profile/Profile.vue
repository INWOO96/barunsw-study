<script>
import { ref } from 'vue'
import { useProfile } from '/@compositions/profile'
import Card from '/@components/profile/ProfileCard.vue'
import useAxios from '/@app_modules/axios.js'

export default {
	components: {
		Card
	},
	setup() {
		const { user_data, setAboutMeData } = useProfile()
		const { axiosGet } = useAxios()

		const onSuccess = (data) => {
			console.log('success call about me ', data);
			setAboutMeData(data)
		}

		axiosGet('/api/about-me', onSuccess)

		return {
			user_data
		}
	},
}
</script>

<template>
	<div class="container pt-5">
		<div>
			<p class="fs-1 mb-0">{{ user_data.name }}</p>
		</div>
		<div>
			<p class="fs-4">{{ user_data.email}}</p>
		</div>	

		<div class="row no-gutters" v-for="(d, index) in user_data.resumes"
			:key="index">
			<Card :top="!index" :resume="d" :show="index % 2 == 1" />
			<div class="col-sm-2 text-center flex-column d-done d-sm-flex">
				<div class="row h-50">
					<div class="col" :class="[index ? 'border-right' : '']" />
					<div class="col"/>
				</div>
				<h1>
					<span class="badge badge-pill border"
						:class="[!index ? 'bg-primary text-light' : 'bg-light text-dark']">
						{{ new Date(d.date).getFullYear() }}
					</span>
				</h1>
				<div class="row h-50">
					<div class="col" :class="[index != user_data.resumes.length - 1 ? 'border-right' : '']" />
					<div class="col"/>
				</div>
			</div>
			<Card :top="!index" :resume="d" :show="index % 2 == 0" />
		</div>
	</div>
</template>