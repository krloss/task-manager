function createRoute(navCtrl,name) {
    return function(action) {
        navCtrl.$router.push({name:name, params:{action:action}});
        navCtrl.$root.$emit('navChange',action);
    };
}

function createCrudButtons(route) { return [
	{name:'search', icon:'search', text:'Pesquisar', click:route},
	{name:'add', icon:'add', text:'Novo', click:route}
] };
function addCRUD(name,text,icon,navCtrl) {
    return {name:name, text:text, icon:icon, 'icon-alt':'keyboard_arrow_down', children:createCrudButtons(navCtrl)};
}

export default function(self) {
	return [
        {name:'home', text:'Home', icon:'phonelink', click:createRoute(self,'Home')},
        addCRUD('plan','Plans','history',createRoute(self,'Plan')),
        addCRUD('task','Tasks','settings',createRoute(self,'Task'))
    ];
}
