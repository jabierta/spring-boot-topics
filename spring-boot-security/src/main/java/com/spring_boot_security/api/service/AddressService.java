package com.spring_boot_security.api.service;

import com.spring_boot_security.api.domain.Address;
import com.spring_boot_security.api.repository.AddressRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
  private final AddressRepository addressRepository;

  public Address findById(Integer id) throws Exception {
    Optional<Address> address = addressRepository.findById(id);

    return address.orElseThrow(() -> new Exception("Address Does Not Exist!"));
  }
}
