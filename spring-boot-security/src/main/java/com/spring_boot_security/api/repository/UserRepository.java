package com.spring_boot_security.api.repository;

import com.spring_boot_security.api.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
  User findByUserName(String userName);
}
