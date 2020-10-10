<template>
  <v-app>
    <v-container fill-height fluid>
      <v-layout align-center justify-center>
        <v-flex>
          <v-card class="mx-auto pa-8" elevation="0" max-width="500">
            <div class="headline">Login</div>

            <!--            <input-form>-->

            <!--              <v-text-field id="loginId" type="text" placeholder="Email or Username"></v-text-field>-->
            <!--              <v-text-field id="password" type="password" placeholder="password"></v-text-field>-->

            <!--              <v-card-actions>-->
            <!--                <v-spacer></v-spacer>-->
            <!--                <v-btn dark type="submit" @click="login">Login</v-btn>-->
            <!--              </v-card-actions>-->
            <!--            </input-form>-->

            <input-form :afterValidation="login" :formItems="items" class="pt-3">
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn dark type="submit">Login</v-btn>
              </v-card-actions>
            </input-form>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import AuthService from '@/modules/auth/authService';
  import FormItem from "@/modules/form/formItem";

  @Component
  export default class Login extends Vue {

    private loginId = new FormItem('loginId', 'Email or Username')
            // .withIcon('email')
            .withRequired(true)
            .withType('text');

    private password = new FormItem('password', 'Password')
            // .withIcon('lock')
            .withRequired(true)
            .withType('password');

    items = [this.loginId, this.password];

    public beforeMount() {
      if (AuthService.loggedIn()) {
        this.$router.push('/');
      }
    }

    public login() {
      AuthService
              .login(this.loginId.value, this.password.value)
              .then((success) => {
                if (success) {
                  this.$router.push('/');
                }
              });
    }
  }
</script>
