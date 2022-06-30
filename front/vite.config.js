import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  build: {
    outDir: '../src/main/resources/static',
  },
  server: {
    proxy: 'http://localhost:8080'
  },
  resolve: {
    alias: {
      '/@': path.resolve(__dirname, './src'),
      '/@components': path.resolve(__dirname, './src/components'),
      '/@compositions': path.resolve(__dirname, './src/compositions'),
      '/@app_modules': path.resolve(__dirname, './src/module'),
      '/@store': path.resolve(__dirname, './src/store'),
      '/@router': path.resolve(__dirname, './src/router'),
    }
  }
})
