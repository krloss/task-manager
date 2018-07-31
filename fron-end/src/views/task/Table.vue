<template>
	<app-table :superCtrl="selfCtrl" @refreshTable="findAll" @editItem="editItem" @deleteItem="remove" />
</template>

<script>
import AppTable from '../../components/AppTable.vue';

export default (function() {
	var selfCtrl = {
		dataTable:{
			headers:[
				{text:'Id', value:'id', align:'left'},
				{text:'Name', value:'name', class:"text-xs-right"},
				{text:'Completed', value:'completed', class:"text-xs-right", sortable:false},
				{text:'Created At', value:'createdAt', class:"text-xs-right"},
				{text:'Plan', value:'plan', class:"text-xs-right", sortable:false},
				{text:'Actions', class:"text-xs-right", sortable:false}
			],
			items:[]
		}
	};

	return {
		props:['superCtrl'],
		components:{ AppTable },
		data:function() { return {selfCtrl:selfCtrl}; },
		created:function() {
			var self = this;

			this.superCtrl.table = this;
			this.findAll();
			this.$on('refreshTable',function() {
				self.findAll();
			});
		},
		computed:{
			http:function() { return this.$appResource('tasks'); }
		},
		methods:{
			findAll:function() {
				this.http.get().then(function(response) {
					selfCtrl.dataTable.items = response.body.content;
				});
			},
			editItem:function(id) {
				this.superCtrl.form.$emit('editItem',id);
			},
			remove:function(id) {
				var self = this;

				this.http.delete({id:id}).then(function(response) {
					self.findAll();
				});
			}
		}
	}
})();
</script>
