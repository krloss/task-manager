import HomeCtrl from '../views';

// Lazy Loading Routes
const plan = function() { return import(/* webpackChunkName:"plan" */'../views/plan'); };
const task = function() { return import(/* webpackChunkName:"task" */'../views/task'); };

function addCRUD(path,name,component) {
	var result = { path:path, name:name, component:component, components:{ },
		beforeEnter:function(to,from,next) {
			component().then(function(response) {
				response.default(result.components);
				result.component = null;
				next();
			});
		}
	};

	return result;
}

export default function() {
	return [
		{ path:'/', name:'Home', components:{appContent:HomeCtrl} },
		addCRUD('/plan','Plan',plan),
		addCRUD('/task','Task',task)
	];
}
