<template>
  <div>
    <v-navigation-drawer
        app
        dark>

      <v-list-item class="mt-4">
        <v-list-item-content>
          <v-list-item-title class="title">
            {{ user.username }}
          </v-list-item-title>
          <v-list-tile-sub-title v-if="roleDisp">
            Authority: {{ roleDisp }}
          </v-list-tile-sub-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider/>

      <v-list
          dense
          nav
      >

        <v-list-item
            v-for="item in items"
            :key="item.title"
            link
            :to="item.link"
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-divider/>

        <v-list-item
            link
            @click="logout">
          <v-list-item-content>
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script lang="ts">

  import {Component, Prop, Vue} from "vue-property-decorator";
  import AuthService from "@/modules/auth/authService";
  import {User} from "@/modules/auth/user";

  @Component
  export default class Sidebar extends Vue {

    private user: User = new User();

    private roleDisp: string = '';

    private items: any = [];

    public created() {

      this.user = this.$store.getters.getCurrentUser;

      if (AuthService.loggedIn()) {
        this.items = [
          {title: 'Profile', link: '/profile'},
        ];
      }

      if (AuthService.isAdmin()) {
        this.roleDisp = "Administrator";
      }
    }

    private logout() {
      AuthService.logout().then(() => {
        this.$router.push('/login');
      });
    }
  }
</script>

<style scoped>

</style>
