<template>
	<v-container xs4 align-center justify-center> <v-layout column wrap>
		<!-- Data Table //-->
		<v-flex> <div id="app-table">
			<v-data-table :headers="dataTable.headers" :items="dataTable.items" hide-actions class="elevation-1">
				<!-- Table Body //-->
				<template slot="items" slot-scope="props">
					<td v-for="it in headersWithValue" :class="it.class">
						{{props.item[it.value]}}
					</td>
					<td class="justify-center layout px-0">
						<v-btn icon class="mx-0" @click="editItem(props.item)">
							<v-icon color="green darken-2">edit</v-icon>
						</v-btn>
						<v-btn icon class="mx-0" @click="deleteItem(props.item)">
							<v-icon color="red darken-2">delete</v-icon>
						</v-btn>
					</td>
				</template>
				<template slot="no-data">
					<v-btn color="primary" @click="initialize">Reset</v-btn>
				</template>
			</v-data-table>
		</div> </v-flex>

		<!-- Pagination //-->
		<v-flex> <div id="app-pagination" class="text-xs-center">
			<v-pagination :length="15" v-model="pagination.page" :total-visible="7"></v-pagination>
		</div> </v-flex>
	</v-layout> </v-container>
</template>


<script>
export default {
	data:function() { return {
		dataTable:{
			headers:[
				{text:'Dessert (100g serving)', value:'name', align:'left', sortable:false},
				{text:'Calories', value:'calories', class:"text-xs-right"},
				{text:'Fat (g)', value:'fat', class:"text-xs-right"},
				{text:'Carbs (g)', value:'carbs', class:"text-xs-right"},
				{text:'Protein (g)', value:'protein', class:"text-xs-right"},
				{text:'Actions', class:"text-xs-right", sortable:false}
			],
			items:[],
			editedIndex:-1,
			editedItem:{name:'', calories:0, fat:0, carbs:0, protein:0},
			defaultItem:{name:'', calories:0, fat:0, carbs:0, protein:0}
		},
		pagination:{page:1}
	} },
	computed: {
		formTitle () {
			return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
		},
		headersWithValue:function() {
			return this.dataTable.headers.filter(function(it){ return it.value; });
		}
	},
	created:function() {
		// this.initialize()
	},
	methods: {
		initialize:function() {
			this.dataTable.items = [
				{name:'Frozen Yogurt', calories:159, fat:6.0, carbs:24, protein:4.0},
				{name:'Ice cream sandwich', calories:237, fat:9.0, carbs:37, protein:4.3},
				{name:'Eclair', calories:262, fat:16.0, carbs:23, protein:6.0},
				{name:'Cupcake', calories:305, fat:3.7, carbs:67, protein:4.3},
				{name:'Gingerbread', calories:356, fat:16.0, carbs:49, protein:3.9},
				{name:'Jelly bean', calories:375, fat:0.0, carbs:94, protein:0.0},
				{name:'Lollipop', calories:392, fat:0.2, carbs:98, protein:0},
				{name:'Honeycomb', calories:408, fat:3.2, carbs:87, protein:6.5},
				{name:'Donut', calories:452, fat:25.0, carbs:51, protein:4.9},
				{name:'KitKat', calories:518, fat:26.0, carbs:65, protein:7}
			]
		},
		editItem:function(item) {
			this.dataTable.editedIndex = this.items.indexOf(item)
			this.dataTable.editedItem = Object.assign({}, item)
			this.dataTable.dialog = true
		},
		deleteItem:function(item) {
			const index = this.items.indexOf(item)
			confirm('Are you sure you want to delete this item?') && this.items.splice(index, 1)
		},

		close:function() {
			this.dialog = false
			setTimeout(function() {
				this.dataTable.editedItem = Object.assign({}, this.defaultItem)
				this.dataTable.editedIndex = -1
			}, 300)
		},
		save:function() {
			if(this.dataTable.editedIndex > -1) {
				Object.assign(this.items[this.dataTable.editedIndex], this.dataTable.editedItem)
			} else {
				this.dataTable.items.push(this.dataTable.editedItem)
			}
			this.close()
		}
	}
}
</script>
