<template>
  <app-main>
    <div v-if="vue">
      <img src="./assets/logo.png">
      <h1>{{ msg }}</h1>
      <router-view />
    </div>
    <app-table v-else />
  </app-main>
</template>

<script>
import AppMain from './components/layouts/AppMain.vue';
import AppTable from './components/AppTable.vue';

export default {
  components:{ AppMain,AppTable },
  name:'app',
  data:function() {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>
