<template>
	<app-dialog :superCtrl="selfCtrl">
		<v-container grid-list-sm class="pa-4"> <v-layout row wrap>
			<v-flex xs12 align-center justify-space-between> <v-layout align-center>
				<v-text-field prepend-icon="settings" placeholder="Name"></v-text-field>
			</v-layout> </v-flex>

			<v-flex xs6>
				<v-checkbox label="Completed?" v-model="checkbox"></v-checkbox>
			</v-flex>
			<v-flex xs6>
				<v-menu ref="menu" lazy :close-on-content-click="false" v-model="picker.menu" transition="scale-transition"
				offset-y full-width :nudge-right="40" min-width="290px" :return-value.sync="picker.date">

					<v-text-field slot="activator" label="Created At" v-model="picker.date" prepend-icon="event" readonly />
					<v-date-picker v-model="picker.date" no-title scrollable>
						<v-spacer />
						<v-btn flat color="primary" @click="picker.menu = false">Cancel</v-btn>
						<v-btn flat color="primary" @click="$refs.menu.save(picker.date)">OK</v-btn>
					</v-date-picker>
				</v-menu>
			</v-flex>

			<v-flex xs12>
				<v-select :items="palns" :filter="filterPlans" v-model="plan" item-text="name"
					label="Plan" autocomplete prepend-icon="history" />
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
		picker:{date:null, menu:false, modal:false},
		superCtrl:{
			dialog:true,
			dialogTitle:'Task'
		},

		plan:null,
		palns:[
			{name:'Florida', abbr:'FL', id:1},
			{name:'Georgia', abbr:'GA', id:2},
			{name:'Nebraska', abbr:'NE', id:3},
			{name:'California', abbr:'CA', id:4},
			{name:'New York', abbr:'NY', id:5}
		],
		filterPlans:function(item,queryText,itemText) {
			const hasValue = function(val) { return val != null ? val : ''; };
			const text = hasValue(item.name);
			const query = hasValue(queryText);
			return text.toString().toLowerCase().indexOf(query.toString().toLowerCase()) > -1;
		}
	} }
}
</script>
