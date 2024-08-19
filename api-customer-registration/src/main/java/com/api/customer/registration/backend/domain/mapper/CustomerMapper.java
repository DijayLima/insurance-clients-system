package com.api.customer.registration.backend.domain.mapper;

import com.api.customer.registration.backend.domain.dto.CustomerRequest;
import com.api.customer.registration.backend.domain.dto.CustomerRequestUpdate;
import com.api.customer.registration.backend.domain.dto.CustomerResponse;
import com.api.customer.registration.backend.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    Customer toEntity(CustomerRequest dto);

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.phone", target = "phone")
    @Mapping(source = "dto.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "dto.address", target = "address")
    Customer toEntity(String cpf, CustomerRequestUpdate dto);

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    CustomerResponse toDTO(Customer customer);
}
