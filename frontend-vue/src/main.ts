import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from './plugins/vuetify';
import InputForm from "@/components/parts/InputForm.vue";
import AuthProcessor from "@/modules/auth/authProcessor";
import Header from "@/components/globals/Header.vue";
import Sidebar from "@/components/globals/Sidebar.vue";

Vue.config.productionTip = false;

Vue.component('Header', Header);
Vue.component('Sidebar', Sidebar);
Vue.component('InputForm', InputForm);


async function init() {
  await AuthProcessor.getAuthenticatedUser();
  router.beforeEach(async (to, from, next) => {
    if (to.path !== "/login" && !store.getters.getIsAuthenticated) {
      try {
        const statusCode = await AuthProcessor.refreshToken();
        if (statusCode !== 200) next("/login");
        else next();
      } catch (error) {
        next("/login");
      }
    } else {
      next();
    }
  });

  new Vue({
    router,
    store,
    // @ts-ignore
    vuetify,
    render: h => h(App)
  }).$mount("#app");
}

init();
