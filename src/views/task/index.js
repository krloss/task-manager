import TaskForm from './Form.vue';
import TaskTable from './Table.vue';

const task = (function() {
	var selfCtrl = { };

	return function(components) {
		components.appContent = {
			components:{ TaskTable },
			template:'<task-table :superCtrl="selfCtrl" />',
			data:function() { return {selfCtrl:selfCtrl}; }
		};
		components.dialogContent = {
			components:{ TaskForm },
			template:'<task-form :superCtrl="selfCtrl" />',
			data:function() { return {selfCtrl:selfCtrl}; }
		};
	};
})();

export default task;
