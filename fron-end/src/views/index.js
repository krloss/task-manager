import Logo from '../assets/logo.png';

export default {
	template:'<div> <img :src="logo"> <h1>{{ msg }}</h1> </div>',
	data:function(){ return {logo:Logo, msg:'Welcome to Your Vue.js App'}; }
}
