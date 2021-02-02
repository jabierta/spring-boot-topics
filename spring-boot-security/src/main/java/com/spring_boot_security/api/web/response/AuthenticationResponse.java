package com.spring_boot_security.api.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationResponse {
  private String token;
  private Integer userId;
}
