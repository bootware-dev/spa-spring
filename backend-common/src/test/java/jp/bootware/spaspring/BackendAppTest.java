package jp.bootware.spaspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class BackendAppTest {

  @Autowired PasswordEncoder passwordEncoder;

  @Test
  public void encryptedPassword() {
    System.out.println(passwordEncoder.encode("password"));
  }
}
