package com.api.customer.registration.backend.dataprovider.mapper;

import com.api.customer.registration.backend.core.entity.CustomerRequest;
import com.api.customer.registration.backend.core.entity.CustomerRequestUpdate;
import com.api.customer.registration.backend.core.entity.CustomerResponse;
import com.api.customer.registration.backend.dataprovider.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequest dto);

    Customer toEntity(String cpf, CustomerRequestUpdate dto);

    CustomerResponse toDTO(Customer customer);
}
