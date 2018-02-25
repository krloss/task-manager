const home = {template:'<div> <h1>Home</h1> </div>'}

// Lazy Loading Routes
const foo = function() { return import(/* webpackChunkName:"group-foo" */'./Foo.vue'); };
const bar = function() { return import(/* webpackChunkName:"group-foo" */'./Bar.vue'); };

// { path:'/', name:'Home', components:{appContent:List} }
export default function() {
	return [
		{ path:'/', name:'Home', component:home },
		{ path:'/foo', name:'Foo', component:foo },
		{ path:'/bar', name:'Bar', component:bar }
	];
}
