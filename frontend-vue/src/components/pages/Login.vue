<template>
  <v-app>
    <v-container fill-height fluid>
      <v-layout align-center justify-center>
        <v-flex>
          <v-card class="mx-auto pa-8" elevation="0" max-width="500">
            <div class="headline">Login</div>
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
  import UUIDUtil from "@/modules/auth/uuidUtil";

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

    private csrf = new FormItem('csrf', 'Csrf')
            .withVisible(true)
            .withDisabled(() => true)
            .withType('text')

    items = [this.loginId, this.password, this.csrf];

    public beforeMount() {
      if (AuthService.loggedIn()) {
        this.$router.push('/');
      }
    }

    public login() {

      // CSRF Token 生成
      const csrfToken = UUIDUtil.generateUuid();

      // TODO Store に保持する

      AuthService
              .login(this.loginId.value, this.password.value, csrfToken)
              .then((success) => {
                if (success) {
                  this.$router.push('/');
                }
              });
    }
  }
</script>
