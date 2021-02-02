package com.spring_boot_security.api.web.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {
  private String userName;
  private String password;
}