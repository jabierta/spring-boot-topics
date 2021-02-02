package com.spring_boot_security.api.web;

import com.spring_boot_security.api.domain.User;
import com.spring_boot_security.api.security.JwtService;
import com.spring_boot_security.api.service.UserService;
import com.spring_boot_security.api.web.request.AuthenticationRequest;
import com.spring_boot_security.api.web.response.AuthenticationResponse;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final UserService userService;
  private final JwtService jwtService;

  @PostMapping("/authenticate")
  private AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest authenticationRequest) throws Unauthorized {
    User user;
    try {
      user =
          userService.authenticate(
              authenticationRequest.getUserName(),
              new String(Base64.getDecoder().decode(authenticationRequest.getPassword())));
    } catch (Exception e) {
      throw new BadCredentialsException("Wrong Password Or Username!");
    }

    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setToken(jwtService.generateToken(user));
    authenticationResponse.setUserId(user.getId());

    return authenticationResponse;
  }
}
