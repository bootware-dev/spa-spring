package jp.bootware.spaspring.infrastructure.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutResponse {

  private LogoutResponse.SuccessFailure status;
  private String message;

  public enum SuccessFailure {
    SUCCESS, FAILURE
  }
}
