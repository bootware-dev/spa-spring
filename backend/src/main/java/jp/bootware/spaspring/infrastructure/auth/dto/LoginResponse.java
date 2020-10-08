package jp.bootware.spaspring.infrastructure.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

  private SuccessFailure status;
  private String message;

  public enum SuccessFailure {
    SUCCESS, FAILURE
  }
}
