<template>
	<app-dialog :superCtrl="dialog" @save="save">
		<v-container grid-list-sm class="pa-4"> <v-layout row wrap>
			<v-flex xs12 align-center justify-space-between> <v-layout align-center>
				<v-text-field prepend-icon="settings" v-model="entity.name" placeholder="Name"></v-text-field>
			</v-layout> </v-flex>

			<v-flex xs6>
				<v-checkbox label="Completed?" v-model="entity.completed"></v-checkbox>
			</v-flex>
			<v-flex xs6>
				<v-menu ref="menu" lazy :close-on-content-click="false" v-model="picker.menu" transition="scale-transition"
					offset-y full-width :nudge-right="40" min-width="290px" :return-value.sync="entity.createdAt">

					<v-text-field slot="activator" label="Created At" v-model="entity.createdAt" prepend-icon="event" readonly />
					<v-date-picker v-model="entity.createdAt" no-title scrollable>
						<v-spacer />
						<v-btn flat color="primary" @click="picker.menu = false">Cancel</v-btn>
						<v-btn flat color="primary" @click="$refs.menu.save(entity.createdAt)">OK</v-btn>
					</v-date-picker>
				</v-menu>
			</v-flex>

			<v-flex xs12>
				<v-select :items="plans" :filter="filterPlans" v-model="planId"
					item-text="name" item-value="id" label="Plan" autocomplete prepend-icon="history" />
			</v-flex>
		</v-layout> </v-container>
	</app-dialog>
</template>

<script>
import AppDialog from '../../components/AppDialog.vue';

export default (function() {
	var selfCtrl = {
		entity:{ completed:false },
		dialog:{ dialog:false, dialogTitle:'Task' },
		picker:{ menu:false, modal:false },
		plans:[],
		planId:null,

		clean:function() {
			selfCtrl.entity = { completed:false };
			selfCtrl.picker = { menu:false, modal:false };
			selfCtrl.planId = null;
		},
		filterPlans:function(item,queryText,itemText) {
			const hasValue = function(val) { return val != null ? val : ''; };
			const text = hasValue(item.name);
			const query = hasValue(queryText);
			return text.toString().toUpperCase().indexOf(query.toString().toUpperCase()) > -1;
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

			this.fillPlans();
		},
		computed:{
			http:function() { return this.$appResource('tasks'); },
			planHttp:function() { return this.$appResource('plans'); }
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
				if(this.planId) this.entity.plan = { id:this.planId };
				if(this.entity.id) this.update(this.entity);
				else this.insert(this.entity);
			},

			editItem:function(id) {
				var self = this;

				this.http.get({id:id}).then(function(response) {
					selfCtrl.entity = response.body;

					if(selfCtrl.entity.plan) selfCtrl.planId = selfCtrl.entity.plan.id;

					selfCtrl.dialog.dialog = true;
				});
			},

			fillPlans:function() {
				this.planHttp.get({sort:'DESC', property:'name'}).then(function(response) {
					selfCtrl.plans = response.body.content;
				});
			}
		}
	}
})();
</script>
