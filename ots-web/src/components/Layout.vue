<template>
  <div>
    <app-header />
    <app-navbar />
    <app-main />
  </div>
</template>

<script>
import AppHeader from './AppHeader'
import AppNavbar from './AppNavbar'
import AppMain from './AppMain'

export default {
  name: '',

  components: { AppHeader, AppNavbar, AppMain },
  data() {
    return {}
  },

  computed: {},

  watch: {},

  beforeMount() {},

  mounted() {},

  methods: {}
}
</script>
<style scoped></style>
