package jp.bootware.spaspring.infrastructure.auth.user;

import jp.bootware.spaspring.domain.entity.AuthorityEntity;
import jp.bootware.spaspring.domain.entity.UserAuthorityEntity;
import jp.bootware.spaspring.domain.entity.UserInfoEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {

  private final UserInfoEntity userInfo;

  private final Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(UserInfoEntity userInfo) {
    this.userInfo = userInfo;
    this.authorities = userInfo.getUserAuthoritys().stream()
        .map(UserAuthorityEntity::getAuthority)
        .map(AuthorityEntity::getName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return userInfo.getPassword();
  }

  @Override
  public String getUsername() {
    return userInfo.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return userInfo.getEnabled();
  }
}
