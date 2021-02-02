package com.spring_boot_security.api.web.response;

import lombok.Data;

@Data
public class UserInfoResponse {
  private String firstName;
  private String lastName;
  private String address;
}
