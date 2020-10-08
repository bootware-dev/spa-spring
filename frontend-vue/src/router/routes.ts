import Login from "../components/pages/Login.vue";
import LoginLayout from "../components/pages/layouts/LoginLayout.vue";
import Profile from "../components/pages/Profile.vue";
import MainLayout from "@/components/pages/layouts/MainLayout.vue";

const routes = [
  {
    path: "/login",
    component: MainLayout,
    children: [
      {
        path: "/",
        component: Login
      }
    ]
  },
  {
    path: "/",
    component: LoginLayout,
    redirect: "/profile",
    children: [
      {
        path: "/profile",
        component: Profile
      }
    ]
  },
  {
    path: "/*",
    redirect: "/"
  }
];

export default routes;
