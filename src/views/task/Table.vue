<template>
	<app-table :superCtrl="selfCtrl" />
</template>

<script>
import AppTable from '../../components/AppTable.vue';

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

export default {
	props:['superCtrl'],
	components:{ AppTable },
	data:function() { return {
		selfCtrl:selfCtrl,
		http:this.$appResource('tasks')
	}; },
	created:function() {
		this.findAll();
		/*
		this.selfCtrl['editItem'] = this.findOne;
		this.selfCtrl['deleteItem'] = this.delete;
		*/
	},
	methods:{
		findAll:function() {
			this.http.get().then(function(response) {
				selfCtrl.dataTable.items = response.body.content;
			});
		},
		findOne:function(id) {
			http.get({id:id}).then(function(response) {
				this.superCtrl['entity'] = response.body.content;
			});
		},
		delete:function(id) {
			this.http.delete({id:id}).then(function(response) {
				this.findAll();
			});
		}
	}
}
</script>
