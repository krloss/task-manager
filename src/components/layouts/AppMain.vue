<template>
	<v-app id="inspire">
		<app-nav :superCtrl="selfCtrl" />
		<app-bar :superCtrl="selfCtrl">
			<h3 slot='barRight'>{{ $route.name }}</h3>
		</app-bar>
		<v-content> <v-container fluid fill-height> <v-layout justify-center align-center>
			<router-view name="appContent" />
			<slot />
		</v-layout> </v-container> </v-content>
		<slot name="mainBtns">
			<!-- v-btn fab bottom right color="green" dark fixed @click.stop="selfCtrl.dialog = !selfCtrl.dialog">
				<v-icon>add</v-icon>
			</v-btn //-->
		</slot>
		<router-view name="dialogContent" />
	</v-app>
</template>

<script>
import AppNav from './AppNav.vue';
import AppBar from './AppBar.vue';

export default {
	components:{ AppNav,AppBar },
	data:function() { return {
		selfCtrl:{ drawer:null }
	}; }
}
</script>
