import httpResource from "@/modules/http/httpResource";
import store from "../../store";
import AuthProcessor from "@/modules/auth/authProcessor";

class AuthService {

    private static INSTANCE: AuthService;

    private constructor() {}

    public static get instance(): AuthService {
        if (!this.INSTANCE) {
            this.INSTANCE = new AuthService();
        }
        return this.INSTANCE;
    }

    public loggedIn() {
        return store.getters.getIsAuthenticated;
    }

    public async login(loginId: string, password: string) {

        const loginRequest = {
            loginId: loginId,
            password: password
        };

        const response = await httpResource.post(
            "auth/login",
            loginRequest
        );

        if (response.status === 200) {
            await AuthProcessor.getAuthenticatedUser();
            return true;
        }

        return false;
    }

    public async logout() {
        AuthProcessor.performLogout();
        await httpResource.get("auth/logout");
    }

    public hasRole(role: string): boolean {
        return role == 'all' ||
            store.getters.getCurrentUser.roles.indexOf(role) >= 0;
    }

    public hasAnyRoles(roles: string[]): boolean {
        return roles.some((role) => this.hasRole(role));
    }

    public isAdmin(): boolean {
        return this.hasRole("ROLE_ADMIN");
    }
}

export default AuthService.instance;
