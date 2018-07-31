import PlanForm from './Form.vue';
import PlanTable from './Table.vue';

const plan = (function() {
	var selfCtrl = { };

	return function(components) {
		components.appContent = {
			components:{ PlanTable },
			template:'<plan-table :superCtrl="selfCtrl" />',
			data:function() { return {selfCtrl:selfCtrl}; }
		};
		components.dialogContent = {
			components:{ PlanForm },
			template:'<plan-form :superCtrl="selfCtrl" />',
			data:function() { return {selfCtrl:selfCtrl}; }
		};
	};
})();

export default plan;
