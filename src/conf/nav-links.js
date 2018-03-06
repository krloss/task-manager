function createRoute(router,path) {
    return function(action) { router.push({path:path, query:{action:action}}); };
}

function createCrudButtons(route) { return [
	{name:'search', icon:'search', text:'Pesquisar', click:route},
	{name:'add', icon:'add', text:'Novo', click:route}
] };
function addCRUD(name,text,icon,route) {
    return {name:name, text:text, icon:icon, 'icon-alt':'keyboard_arrow_down', children:createCrudButtons(route)};
}

export default function(self) {
	return [
        {name:'home', text:'Home', icon:'phonelink', click:createRoute(self.$router,'/')},
        addCRUD('plan','Plans','history',createRoute(self.$router,'/plan')),
        addCRUD('task','Tasks','settings',createRoute(self.$router,'/task'))
    ];
}
