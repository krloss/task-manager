<template>
	<v-container xs4 align-center justify-center> <v-layout column wrap>
		<!-- Data Table //-->
		<v-flex> <div id="app-table">
			<v-data-table :headers="dataTable.headers" :items="dataTable.items" hide-actions class="elevation-1">
				<!-- Table Body //-->
				<template slot="items" slot-scope="props">
					<td v-for="it in headersWithValue" :class="it.class">
						{{ props.item[it.value] }}
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
	props:['superCtrl'],
	data:function() { return {
		dataTable:this.superCtrl.dataTable,
		pagination:{page:1}
	} },
	computed: {
		headersWithValue:function() {
			return this.dataTable.headers.filter(function(it){ return it.value; });
		}
	},
	methods: {
		initialize:function() {
			this.$emit('refreshTable');
		},
		editItem:function(item) {
			this.$emit('editItem',item.id);
		},
		deleteItem:function(item) {
			confirm('Are you sure you want to delete this item?') && this.$emit('editItem',item.id);
		}
	}
}
</script>
