<script setup>
import { ref, computed } from 'vue'

const user_name = localStorage.getItem('name')
const menu = ref('home')
const menus = [
	{key: 'home', value: '홈', url: '/home', position: 'left'},
	{key: 'app', value: '어플리케이션', url: '/application', position: 'left'},
	{key: 'todo', value: '메모장', url: '/todo', position: 'left'},
	{key: 'me', value: '프로필', url: '/profile', position: 'right'},
	{key: 'logout', value: '로그아웃', url: '/logout', position: 'right'},
	//{key: 'admin', value: '관리자', url: '/admin', position: 'right'},
]
const leftMenus = computed(() => menus.filter((i) => i.position == 'left'))
const rightMenus = computed(() => menus.filter((i) => i.position == 'right'))

const menu_category = [
	{id: 1, me_auto: true, value: leftMenus.value},
	{id: 2, me_auto: false, value: rightMenus.value}
]

</script>

<template>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">BarunSW</a>
			<button class="navbar-toggle" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggle-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul :class="{ 'navbar-nav': true, 'me-auto': menu.me_auto }" v-for="menu in menu_category" :key="menu.id">
					<li class="nav-item" v-for="menu_object in menu.value" :key="menu_object.key">
						<div v-if="menu_object.key != 'logout'">
							<router-link :to="menu_object.url" class="nav-link">{{ menu_object.value }}</router-link>
						</div>
						<div v-else>
							<a class="nav-link" href="/logout">{{ menu_object.value }}</a>
						</div>
						<!-- <a :class="{ 'nav-link': true, active: menu == menu_object.key }" @click="onMovePage($event, menu_object)" href="#">
							{{ menu_object.value }}
						</a> -->
					</li>
				</ul>
			</div>
		</div>
	</nav>	
</template>
