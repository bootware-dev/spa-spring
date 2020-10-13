package jp.bootware.spaspring.infrastructure.auth.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserProfile {

  private String userId;
  private String email;
  private String username;
  private Set<String> roles;
}
