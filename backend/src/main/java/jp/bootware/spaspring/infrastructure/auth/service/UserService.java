package jp.bootware.spaspring.infrastructure.auth.service;

import jp.bootware.spaspring.infrastructure.auth.dto.LoginRequest;
import jp.bootware.spaspring.infrastructure.auth.dto.LoginResponse;
import jp.bootware.spaspring.infrastructure.auth.dto.LogoutResponse;
import jp.bootware.spaspring.infrastructure.auth.dto.UserSummary;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken,
                                      String refreshToken);

  ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

  ResponseEntity<LogoutResponse> logout();

  UserSummary getUserProfile();
}
