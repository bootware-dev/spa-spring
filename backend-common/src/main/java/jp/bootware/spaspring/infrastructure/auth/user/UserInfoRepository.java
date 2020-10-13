package jp.bootware.spaspring.infrastructure.auth.user;

import jp.bootware.spaspring.domain.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

  Optional<UserInfoEntity> findByEmail(String email);

  Optional<UserInfoEntity> findByUsername(String username);

  default UserInfoEntity findByEmailOrUsername(String emailOrUsername) {
    Optional<UserInfoEntity> userInfoOpt = Optional.empty();

    if(emailOrUsername.matches(".+@.+")) {
      userInfoOpt = findByEmail(emailOrUsername);
    } else {
      userInfoOpt = findByUsername(emailOrUsername);
    }

    UserInfoEntity userInfo = userInfoOpt
        .orElseThrow(() -> new UsernameNotFoundException("User not found with userId " + emailOrUsername));

    return userInfo;
  }
}
