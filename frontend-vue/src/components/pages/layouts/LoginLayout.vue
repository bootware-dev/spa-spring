<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
<div>

  <Sidebar></Sidebar>

  <Header v-if="isHeaderVisible()"></Header>

  <v-main>
    <v-container fluid>
      <router-view></router-view>
    </v-container>
  </v-main>

</div>
</template>

<script lang="ts">
  import {Component, Vue} from "vue-property-decorator";
  import AuthService from "@/modules/auth/authService";
  import {User} from "@/modules/auth/user";
  import Sidebar from "@/components/globals/Sidebar.vue";
  @Component({
    components: {Sidebar}
  })
  export default class LoginLayout extends Vue {

    private user: User = new User();

    public created() {

      if (!this.isLoggedIn()) {
        this.$router.push('/login');
      } else {
        this.user = this.$store.getters.getCurrentUser;
      }
    }

    private isLoggedIn() {
      return AuthService.loggedIn();
    }

    // See https://vuetifyjs.com/en/features/breakpoints/breakpoint
    private isHeaderVisible() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
        case 'sm':
        case 'md':
          return true;
        case 'lg':
        case 'xl':
          return false;
      }
    }
  }
</script>

<style scoped>

</style>
