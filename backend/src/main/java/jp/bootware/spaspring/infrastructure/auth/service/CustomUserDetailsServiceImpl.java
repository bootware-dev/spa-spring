package jp.bootware.spaspring.infrastructure.auth.service;

import jp.bootware.spaspring.infrastructure.auth.dto.CustomUserDetails;
import jp.bootware.spaspring.infrastructure.auth.model.UserInfo;
import jp.bootware.spaspring.infrastructure.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserInfo userInfo = userRepository.findByEmailOrUsername(s);
    return new CustomUserDetails(userInfo);
  }
}
