package com.easyticket.api.repositories;

import com.easyticket.api.domain.event.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
