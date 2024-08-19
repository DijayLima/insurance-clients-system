package com.api.customer.registration.backend.service;

import com.api.customer.registration.backend.domain.dto.CustomerRequest;
import com.api.customer.registration.backend.domain.dto.CustomerRequestUpdate;
import com.api.customer.registration.backend.domain.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customer);

    CustomerResponse update(String cpf, CustomerRequestUpdate customer);

    CustomerResponse findCustomerByCpf(String cpf);

    void deleteCustomer(String cpf);
}
