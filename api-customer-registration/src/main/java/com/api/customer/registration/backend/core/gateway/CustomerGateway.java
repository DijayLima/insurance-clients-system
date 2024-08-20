package com.api.customer.registration.backend.core.gateway;

import com.api.customer.registration.backend.core.entity.CustomerRequest;
import com.api.customer.registration.backend.core.entity.CustomerRequestUpdate;
import com.api.customer.registration.backend.core.entity.CustomerResponse;

public interface CustomerGateway {

    CustomerResponse createCustomer(CustomerRequest customer);

    CustomerResponse update(String cpf, CustomerRequestUpdate customer);

    CustomerResponse findCustomerByCpf(String cpf);

    void deleteCustomer(String cpf);
}
