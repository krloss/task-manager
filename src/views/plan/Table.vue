<template>
	<app-table :superCtrl="selfCtrl" @refreshTable="findAll" @editItem="findOne" @deleteItem="remove" />
</template>

<script>
import AppTable from '../../components/AppTable.vue';

export default (function() {
	var selfCtrl = {
		dataTable:{
			headers:[
				{text:'Id', value:'id', align:'left'},
				{text:'Name', value:'name', class:"text-xs-right"},
				{text:'Active', value:'active', class:"text-xs-right", sortable:false},
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
			this.findAll();
		},
		computed:{
			http:function() { return this.$appResource('plans'); }
		},
		methods:{
			findAll:function() {
				this.http.get().then(function(response) {
					selfCtrl.dataTable.items = response.body.content;
				});
			},
			findOne:function(id) {
				this.http.get({id:id}).then(function(response) {
					console.log('>>> '+ JSON.stringify(response.body));
				});
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
