import Vue from 'vue';

import AppConfig from './conf';
import AppData from '../package.json';
import App from './App.vue';

window['vueApp'] = (function() {
  Vue.use(AppConfig,{appData:AppData});
  return new Vue({el:'#app', render:function(h) { return h(App); }});
})();
