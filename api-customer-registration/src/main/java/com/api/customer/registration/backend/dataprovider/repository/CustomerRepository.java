package com.api.customer.registration.backend.dataprovider.repository;

import com.api.customer.registration.backend.dataprovider.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByCpf(String cpf);

    void deleteByCpf(String cpf);
}

