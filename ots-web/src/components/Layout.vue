<template>
<div>
 <app-header></app-header>
 <app-navbar></app-navbar>
 <app-main></app-main>
</div>
 

</template>

<script>
import AppHeader from './AppHeader'
import AppNavbar from './AppNavbar'
import AppMain from './AppMain'

  export default {
    name:'',
    props:[''],
    data () {
      return {

      };
    },

    components: {AppHeader, AppNavbar, AppMain},

    computed: {},

    beforeMount() {},

    mounted() {},

    methods: {},

    watch: {}

  }

</script>
<style scoped>

</style>