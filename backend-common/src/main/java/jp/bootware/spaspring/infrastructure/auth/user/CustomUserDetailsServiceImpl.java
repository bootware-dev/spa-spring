package jp.bootware.spaspring.infrastructure.auth.user;

import jp.bootware.spaspring.domain.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserInfoRepository userInfoRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserInfoEntity userInfo = userInfoRepository.findByEmailOrUsername(s);
    return new CustomUserDetails(userInfo);
  }
}
