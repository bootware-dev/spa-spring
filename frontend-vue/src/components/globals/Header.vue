<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-app-bar
        app
        dark>

      Vue.js

      <v-spacer></v-spacer>

      <v-menu
          v-if="items.length > 0"
          bottom
          left
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
              dark
              icon
              v-bind="attrs"
              v-on="on"
          >
            <v-app-bar-nav-icon/>
          </v-btn>
        </template>

        <v-list
            dense
            nav
        >
          <v-list-item
              v-for="item in items"
              :key="item.title"
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
      </v-menu>
    </v-app-bar>
  </div>
</template>

<script lang="ts">

  import {Component, Prop, Vue} from "vue-property-decorator";
  import AuthService from "@/modules/auth/authService";

  @Component
  export default class Header extends Vue {

    private items: any = [];

    created() {
      if (AuthService.loggedIn()) {
        this.items = [
          {title: 'Profile', link: '/profile'},
        ];
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
