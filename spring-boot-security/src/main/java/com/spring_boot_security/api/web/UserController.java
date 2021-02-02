package com.spring_boot_security.api.web;

import com.spring_boot_security.api.domain.User;
import com.spring_boot_security.api.service.UserService;
import com.spring_boot_security.api.web.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  // Only accessible for admin
  @GetMapping(value = "/getUserInfo/{userId}")
  public UserInfoResponse getUserInfo(@PathVariable("userId") Integer userId) throws Exception {
    User user = userService.findById(userId);

    UserInfoResponse userInfoResponse = new UserInfoResponse();
    userInfoResponse.setFirstName(user.getFirstName());
    userInfoResponse.setLastName(user.getLastName());
    userInfoResponse.setAddress(user.getAddress().getAddress());

    return userInfoResponse;
  }

  @GetMapping(value = "/userInfo")
  public UserInfoResponse getUserInfo() {
    String userName =
        ((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getUsername();

    User user = userService.findByUserName(userName);

    UserInfoResponse userInfoResponse = new UserInfoResponse();
    userInfoResponse.setFirstName(user.getFirstName());
    userInfoResponse.setLastName(user.getLastName());
    userInfoResponse.setAddress(user.getAddress().getAddress());

    return userInfoResponse;
  }

  // Return regardless of the role to test JWT
  @GetMapping(value = "/testJWT")
  public String testJWT() {
    return "Hello World!";
  }
}
