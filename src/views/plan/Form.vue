<template>
	<app-dialog :superCtrl="dialog" @save="save">
		<v-container grid-list-sm class="pa-4"> <v-layout row wrap>
			<v-flex xs10 align-center justify-space-between> <v-layout align-center>
				<v-text-field prepend-icon="history" v-model="entity.name" placeholder="Name" />
			</v-layout> </v-flex>
			<v-flex xs2>
				<v-checkbox label="Active?" v-model="entity.active" />
			</v-flex>

			<v-flex xs12 v-if="tasks && tasks.length">
				<h3>Tasks...</h3>
				<ul> </ul>
			</v-flex>
		</v-layout> </v-container>
	</app-dialog>
</template>

<script>
import AppDialog from '../../components/AppDialog.vue';

export default (function() {
	var selfCtrl = {
		tasks:[],
		entity:{ active:false },
		dialog:{ dialog:false, dialogTitle:'Plan' },
	};
	
	return {
		props:['superCtrl'],
		components:{ AppDialog },
		data:function() { return selfCtrl; },
		created:function() {
			var self = this;

			this.checkDialogByRoute();
			this.$root.$on('navChange',function(action) {
				self.checkDialog(action);
			});
		},
		computed:{
			http:function() { return this.$appResource('plans'); }
		},
		methods:{
			checkDialog:function(action) {
				this.dialog.dialog = ('search' != action);
			},
			checkDialogByRoute:function() {
				this.checkDialog(this.$route.params.action);
			},

			insert:function(entity) {
				this.http.save({},entity).then(function(response) {
					selfCtrl.entity = { };
				});
			},
			update:function(entity) {
				this.http.update({id:entity.id},entity).then(function(response) {
					selfCtrl.entity = { };
				});
			},
			save:function() {
				if(this.entity.id) this.update(this.entity);
				else this.insert(this.entity);

				this.$emit('refreshTable');
			},

			getTasks:function(id) {
				this.http.property({id:id, property:'tasks'}).then(function(response) {
					selfCtrl.tasks = response.body;
				});
			}
		}
	}
})();
</script>
