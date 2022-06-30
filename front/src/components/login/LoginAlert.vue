<script>
import { inject } from 'vue'
export default {
	props: {
		message: {type: String, default: ''}
	},
	setup(props, context) {
		const popupClear = inject('popupClear')

		return {
			popupClear
		}
	}
}
</script>

<template>
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
		<strong>{{ message }}</strong>
		<button @click="popupClear" type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</template>