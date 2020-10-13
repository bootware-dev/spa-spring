package jp.bootware.spaspring.infrastructure.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserController {

  @Autowired UserService userService;

  @Transactional
  @GetMapping("/me")
  public ResponseEntity<UserProfile> me() {
    return ResponseEntity.ok(userService.getUserProfile());
  }
}
