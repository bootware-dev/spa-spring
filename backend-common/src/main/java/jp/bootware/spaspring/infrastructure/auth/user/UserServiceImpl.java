package jp.bootware.spaspring.infrastructure.auth.user;

import jp.bootware.spaspring.domain.entity.UserInfoEntity;
import jp.bootware.spaspring.infrastructure.auth.LoginRequest;
import jp.bootware.spaspring.infrastructure.auth.LoginResponse;
import jp.bootware.spaspring.infrastructure.auth.LogoutResponse;
import jp.bootware.spaspring.infrastructure.auth.token.Token;
import jp.bootware.spaspring.infrastructure.auth.token.TokenProvider;
import jp.bootware.spaspring.infrastructure.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

  @Autowired UserInfoRepository userInfoRepository;

  @Autowired TokenProvider tokenProvider;

  @Autowired CookieUtil cookieUtil;

  @Override
  public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken,
                                             String refreshToken) {

    String loginId = loginRequest.getLoginId();
    UserInfoEntity userInfo = userInfoRepository.findByEmailOrUsername(loginId);

    Boolean accessTokenValid = tokenProvider.validateToken(accessToken);
    Boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);

    HttpHeaders responseHeaders = new HttpHeaders();
    Token newAccessToken;
    Token newRefreshToken;
    if(!accessTokenValid && !refreshTokenValid) {
      newAccessToken = tokenProvider.generateAccessToken(userInfo.getUsername());
      newRefreshToken = tokenProvider.generateRefreshToken(userInfo.getUsername());
      addAccessTokenCookie(responseHeaders, newAccessToken);
      addRefreshTokenCookie(responseHeaders, newRefreshToken);
    }

    if(!accessTokenValid && refreshTokenValid) {
      newAccessToken = tokenProvider.generateAccessToken(userInfo.getUsername());
      addAccessTokenCookie(responseHeaders, newAccessToken);
    }

    if(accessTokenValid && refreshTokenValid) {
      newAccessToken = tokenProvider.generateAccessToken(userInfo.getUsername());
      newRefreshToken = tokenProvider.generateRefreshToken(userInfo.getUsername());
      addAccessTokenCookie(responseHeaders, newAccessToken);
      addRefreshTokenCookie(responseHeaders, newRefreshToken);
    }

    LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS,
        "Auth successful. Tokens are created in cookie.");
    return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
  }

  @Override
  public ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken) {
    Boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);
    if (!refreshTokenValid) {
      throw new IllegalArgumentException("Refresh Token is invalid!");
    }

    String currentUserEmail = tokenProvider.getUsernameFromToken(accessToken);

    Token newAccessToken = tokenProvider.generateAccessToken(currentUserEmail);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil
        .createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration())
        .toString());

    LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS,
        "Auth successful. Tokens are created in cookie.");
    return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
  }

  @Override
  public ResponseEntity<LogoutResponse> logout() {

    HttpHeaders responseHeaders = new HttpHeaders();
    addDeleteTokenCookie(responseHeaders);

    LogoutResponse logoutResponse =
        new LogoutResponse(LogoutResponse.SuccessFailure.SUCCESS, "Logout successful.");

    return ResponseEntity.ok().headers(responseHeaders).body(logoutResponse);
  }

  @Override
  public UserProfile getUserProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

    UserInfoEntity userInfo = userInfoRepository.findByEmail(customUserDetails.getUsername()).orElseThrow(
        () -> new IllegalArgumentException(
            "User not found with email " + customUserDetails.getUsername()));

    UserProfile profile = new UserProfile();
    profile.setUserId(userInfo.getId());
    profile.setUsername(userInfo.getUsername());
    profile.setEmail(userInfo.getEmail());
    profile.setRoles(profile.getRoles());
    return profile;
  }

  private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
    httpHeaders.add(HttpHeaders.SET_COOKIE,
        cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
  }

  private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
    httpHeaders.add(HttpHeaders.SET_COOKIE,
        cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
  }

  private void addDeleteTokenCookie(HttpHeaders httpHeaders) {
    httpHeaders.add(HttpHeaders.SET_COOKIE,
        cookieUtil.deleteAccessTokenCookie().toString());
    httpHeaders.add(HttpHeaders.SET_COOKIE,
        cookieUtil.deleteRefreshTokenCookie().toString());
  }
}
