<script>
import { ref, onMounted, computed } from 'vue'
import { useApplications } from '/@compositions/application'
import useAxios from '/@app_modules/axios'
//import AppCard from '/@components/application/AppCard.vue'

export default {
	setup() {
		const { axiosGet } = useAxios()
		const { applications_count, applications, setApplications } = useApplications()

		const selected_appl_id = ref(-1)

		const onClickApplication = (data) => {
			selected_appl_id.value = data.id
		}

		onMounted(() => {
			if (!applications_count.value) {
				axiosGet('/api/applications', function(data) {
					console.log(data);
					setApplications(data)
				})
			}
		})

		return {
			applications_count,
			applications: applications(),
			onClickApplication,
			selected_appl_id,
		}
	}
}
</script>

<template>
	<p class="m-2">
		총 {{ applications_count }} 개의 어플리케이션 중 
		{{ applications.length }} 개가 보여집니다.
	</p>
	<hr />
	<div class="row row-cols-1 row-cols-sm-3 g-2 m-0">
		<div class="col" v-for="data in applications" :key="data.id">
			<div class="card h-100 border-2" :class="{ 'border-primary': selected_appl_id == data.id }">
				<img :src="data.image" class="card-img-top" alt="Image" />
				<div class="card-body">
					<h5 class="card-title">{{ data.name }}</h5>
					<p class="card-text"><pre>{{ data.content }}</pre></p>
					<p class="card-text" v-if="data.url != null">
						<a :href="data.url" @click="onClickApplication(data)" target="_blank">클릭</a>
					</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">{{ data.date }}에 등록 됨</small>
				</div>
			</div>
		</div>
	</div>
</template>