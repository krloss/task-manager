<template>
	<app-dialog :superCtrl="selfCtrl">
		<v-container grid-list-sm class="pa-4"> <v-layout row wrap>
			<v-flex xs10 align-center justify-space-between> <v-layout align-center>
				<v-text-field prepend-icon="history" placeholder="Name"></v-text-field>
			</v-layout> </v-flex>
			<v-flex xs2>
				<v-checkbox label="Active?" v-model="checkbox"></v-checkbox>
			</v-flex>

			<v-flex xs12 v-if="tasks">
				<h3>Tasks...</h3>
				<ul>
					
				</ul>
			</v-flex>
		</v-layout> </v-container>
	</app-dialog>
</template>

<script>
import AppDialog from '../../components/AppDialog.vue';

export default {
	components:{ AppDialog },
	data:function() { return {
		checkbox:true,
		tasks:[],
		selfCtrl:{dialog:false, dialogTitle:'Plan'}
	} },
	methods:{
		tasks:function(id) {
			http.property({id:id, property:'tasks'}).then(function(response) {
				console.log('>>> '+ JSON.stringify(response));
			});
		},
		insert:function(entity) {
			http.save({},entity).then(function(response) {
				console.log('--> '+ JSON.stringify(response));
			});
		},
		update:function(id,entity) {
			http.update({id:id},entity).then(function(response) {
				console.log('>>> '+ JSON.stringify(response));
			});
		}
	},
	watch:{
		'$route.query.action':function() {
			this.selfCtrl['dialog'] = ('add' == this.$route.query.action);
		}
	}
}
</script>
