package jp.bootware.spaspring.infrastructure.auth.user;

import jp.bootware.spaspring.infrastructure.auth.LoginRequest;
import jp.bootware.spaspring.infrastructure.auth.LoginResponse;
import jp.bootware.spaspring.infrastructure.auth.LogoutResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken,
                                      String refreshToken);

  ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

  ResponseEntity<LogoutResponse> logout();

  UserProfile getUserProfile();
}
