<template>
	<v-dialog v-model="superCtrl.dialog" width="800px">
		<v-card>
			<v-card-title class="grey lighten-4 py-4 title">
				{{superCtrl.dialogTitle}}
			</v-card-title>
			
			<slot />

			<v-card-actions>
				<!-- v-btn flat color="primary">More</v-btn //-->
				<v-spacer></v-spacer>
				<v-btn flat color="primary" @click="superCtrl.dialog = false">Cancel</v-btn>
				<v-btn flat @click="superCtrl.dialog = false">Save</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
export default {
	props:['superCtrl']
}
</script>
