<template>
	<v-app id="inspire">
		<app-nav :superCtrl="superCtrl" />
		<app-bar :superCtrl="superCtrl">
			<h3 slot='barRight'>{{$route.name}}</h3>
		</app-bar>
		<v-content> <v-container fluid fill-height> <v-layout justify-center align-center>
			<router-view name="appContent" />
			<slot />
		</v-layout> </v-container> </v-content>
		<slot name="mainBtns">
			<v-btn fab bottom right color="green" dark fixed @click.stop="superCtrl.dialog = !superCtrl.dialog">
				<v-icon>add</v-icon>
			</v-btn>
		</slot>
		<app-dialog :superCtrl="superCtrl" />
	</v-app>
</template>

<script>
import AppNav from './AppNav.vue';
import AppBar from './AppBar.vue';
import AppDialog from '../AppDialog.vue';

export default {
	components:{ AppNav,AppBar,AppDialog },
	data:function() { return {
		superCtrl:{ drawer:null, dialog:false }
	} }
}
</script>
