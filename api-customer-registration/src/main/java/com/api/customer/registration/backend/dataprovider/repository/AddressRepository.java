package com.api.customer.registration.backend.dataprovider.repository;

import com.api.customer.registration.backend.dataprovider.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}

