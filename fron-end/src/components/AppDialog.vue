<template>
	<v-dialog v-model="superCtrl.dialog" width="800px">
		<v-card>
			<v-card-title class="grey lighten-4 py-4 title">
				{{ superCtrl.dialogTitle }}
			</v-card-title>
			
			<slot />

			<v-card-actions>
				<!-- v-btn flat color="primary">More</v-btn //-->
				<v-spacer></v-spacer>
				<v-btn flat color="primary" @click="close">Cancel</v-btn>
				<v-btn flat @click="save">Save</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
export default {
	props:['superCtrl'],
	methods:{
		close:function() {
			this.superCtrl.dialog = false;
		},
		save:function() {
			this.$emit('save')
			this.close();
		}
	}
}
</script>
