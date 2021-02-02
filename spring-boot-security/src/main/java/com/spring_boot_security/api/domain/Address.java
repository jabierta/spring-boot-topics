package com.spring_boot_security.api.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "address")
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue
  @Access(AccessType.PROPERTY)
  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "address")
  private String address;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;
}
