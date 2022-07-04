<script setup>
import { ref, provide } from 'vue'
import NavBar from '/@components/NavBar.vue'
import Toast from '/@components/Toast.vue'

const today = new Date().toISOString().split('T')[0]
provide('today', today)

const toast = ref('')
provide('toast', toast)
</script>

<template>
  <Toast/>
  <NavBar v-if="$route.name !== 'login' && $route.name !== 'signup'"></NavBar>
  <div id="notification" />
  <router-view></router-view>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
