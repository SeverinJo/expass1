import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'

// https://vite.dev/config/
export default defineConfig({
  plugins: [svelte()],
  server: {
      proxy: {
          '/polls': 'http://127.0.0.1:8080',
          '/votes': 'http://127.0.0.1:8080',
          '/users': 'http://127.0.0.1:8080'
      }
  }
});
