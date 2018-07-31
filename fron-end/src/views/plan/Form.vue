<template>
	<app-dialog :superCtrl="dialog" @save="save">
		<v-container grid-list-sm class="pa-4"> <v-layout row wrap>
			<v-flex xs10 align-center justify-space-between> <v-layout align-center>
				<v-text-field prepend-icon="history" v-model="entity.name" placeholder="Name" />
			</v-layout> </v-flex>
			<v-flex xs2>
				<v-checkbox label="Active?" v-model="entity.active" />
			</v-flex>

			<v-flex xs12 v-if="entity.id">
				<v-layout row> <v-flex xs12 sm8 offset-sm2> <v-card>
					<v-toolbar color="grey lighten-4 title">
						<v-toolbar-title>Tasks</v-toolbar-title>
						<v-spacer></v-spacer>
						<v-btn icon @click="addTask(entity.id)">
							<v-icon>add</v-icon>
						</v-btn>
					</v-toolbar>
					<v-list> <template v-for="item in tasks">
						<v-divider />
						<v-list-tile @click="editTask(item.id)"> <v-list-tile-content>
							<v-list-tile-title v-html="item.name" />
						</v-list-tile-content> </v-list-tile>
					</template> </v-list>
				</v-card> </v-flex> </v-layout>
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

		clean:function() {
			selfCtrl.tasks = [ ];
			selfCtrl.entity = { active:false };
		}
	};
	
	return {
		props:['superCtrl'],
		components:{ AppDialog },
		data:function() { return selfCtrl; },
		created:function() {
			var self = this;

			this.superCtrl.form = this;
			selfCtrl.superCtrl = this.superCtrl;
			this.checkDialogByRoute();
			this.$root.$on('navChange',function(action) {
				self.checkDialog(action);
				selfCtrl.clean();
			});
			this.$on('editItem',function(id) {
				self.editItem(id);
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
					selfCtrl.clean();
					selfCtrl.superCtrl.table.$emit('refreshTable');
				});
			},
			update:function(entity) {
				this.http.update({id:entity.id},entity).then(function(response) {
					selfCtrl.clean();
					selfCtrl.superCtrl.table.$emit('refreshTable');
				});
			},
			save:function() {
				if(this.entity.id) this.update(this.entity);
				else this.insert(this.entity);
			},

			editItem:function(id) {
				var self = this;

				this.http.get({id:id}).then(function(response) {
					selfCtrl.entity = response.body;
					self.fillTasks(id);
					selfCtrl.dialog.dialog = true;
				});
			},

			fillTasks:function(id) {
				this.http.property({id:id, property:'tasks'}).then(function(response) {
					selfCtrl.tasks = response.body;
				});
			},

			addTask:function(id) {
				console.log('>>> '+ id);
			},
			editTask:function(id) {
				console.log('--> '+ id);
			}
		}
	}
})();
</script>
