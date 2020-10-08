<template>
  <div>
    <Header></Header>
    <router-view></router-view>
  </div>
</template>

<script lang="ts">

  import {Component, Vue} from "vue-property-decorator";
  import AuthService from "@/modules/auth/authService";

  @Component
  export default class MainLayout extends Vue {

  }
</script>

<style></style>
