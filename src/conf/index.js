import VueHead from 'vue-head';
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';

import routes from './routes.js';
import resources from './resources.js';

function addTitleApp(Vue,title) {
	Vue.mixin({ head:{
		title:{ inner:title }
	} });
}
function addRouter(Vue) {
	Vue.mixin({
		router:new VueRouter({ routes:routes() })
	});
}

export default function install(Vue,args) {
	Vue.use(VueResource);
	Vue.use(VueRouter);
	Vue.use(Vuetify);
	Vue.use(VueHead,{separator:'', complement:''});

	addRouter(Vue);
	Vue.prototype.$appResource = resources(Vue);
	
	if(args && args.appData) {
		Vue.prototype.$appData = args.appData;
		addTitleApp(Vue,args.appData.title +' '+ args.appData.version);
	}
}
