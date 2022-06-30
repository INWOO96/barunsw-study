<script setup>
import { provide } from 'vue'
import NavBar from '/@components/NavBar.vue'

const today = new Date().toISOString().split('T')[0]
provide('today', today)
</script>

<template>
  <NavBar v-if="$route.name !== 'login' && $route.name !== 'signup'"></NavBar>
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
