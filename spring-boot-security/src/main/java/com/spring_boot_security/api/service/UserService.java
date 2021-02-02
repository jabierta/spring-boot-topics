package com.spring_boot_security.api.service;

import com.spring_boot_security.api.domain.User;
import com.spring_boot_security.api.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public User findById(Integer id) throws Exception {
    Optional<User> user = userRepository.findById(id);

    return user.orElseThrow(() -> new Exception("User Does Not Exist!"));
  }

  public User authenticate(String userName, String password) throws Exception {
    Optional<User> userOptional = Optional.ofNullable(userRepository.findByUserName(userName));
    User user = userOptional.orElseThrow(() -> new Exception("User Does Not Exist!"));

    if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
      throw new Exception("Cannot Authenticate! Wrong password/user name!");
    }

    return user;
  }

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }
}
