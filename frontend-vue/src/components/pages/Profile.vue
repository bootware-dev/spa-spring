<template>
  <div>
    <v-card elevation="0" tile class="mt-8 px-5">
      <div class="text-h4">User Profile</div>
      <v-row>
        <v-col>
          <div>
            <ul>
              <li>User ID: {{ user.userId }}</li>
              <li>Username: {{ user.username }}</li>
              <li>E-Mail: {{ user.email }}</li>
              <li>Roles: {{ user.roles }}</li>
            </ul>
          </div>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import AuthService from "@/modules/auth/authService";
  import {User} from "@/modules/auth/user";

  @Component
  export default class Profile extends Vue {

    private user: User = new User();

    public beforeMount() {
      if (AuthService.loggedIn()) {
        this.user = this.$store.getters.getCurrentUser;
      }
    }

    public logout() {
      AuthService.logout().then(() => {
        this.$router.push('/login');
      });
    }
  }
</script>

<style></style>
