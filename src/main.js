import Vue from 'vue';

import AppConfig from './conf';
import AppData from '../package.json';

import AppMain from './components/layouts/AppMain.vue';

window['vueApp'] = (function() {
  Vue.use(AppConfig,{appData:AppData});

  return new Vue({el:'#app',
    components:{ AppMain },
    template:'<app-main />'
  });
})();
