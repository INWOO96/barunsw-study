<script setup> 
import { reactive, toRefs, inject, ref } from 'vue'

const today = inject('today')
const addTodo = inject('addTodo')
const val_obj = reactive({job: '', date: today, today: today})
//...toRefs(val_obj)
const { job, date } = toRefs(val_obj)

</script>

<template>
	<section class="mb-5">
		<div class="container">
			<div class="row justify-content-center m-2">
				<div class="col border border-primary rounded">
					<input type="text" id="todo_input" class="form-control my-2"
						v-model="job" placeholder="여기에 할 일을 적으세요"/>
					<div class="row my-2">
						<div class="col-1 float-start">
							<input type="date" v-model="date" :min="today"/>
						</div>
						<div class="col-11">
							<button type="button" class="btn btn-primary btn-sm float-end"
								@click="addTodo(job, date); job=''; date=today;"> 작업 추가</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>