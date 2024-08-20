package com.api.customer.registration.backend.dataprovider.mapper;

import com.api.customer.registration.backend.core.entity.AddressRequest;
import com.api.customer.registration.backend.core.entity.CustomerRequest;
import com.api.customer.registration.backend.core.entity.CustomerRequestUpdate;
import com.api.customer.registration.backend.core.entity.CustomerResponse;
import com.api.customer.registration.backend.dataprovider.model.Address;
import com.api.customer.registration.backend.dataprovider.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = Mappers.getMapper(CustomerMapper.class);
    }

    @Test
    @DisplayName("Request to entity mapper.")
    void toEntity() {
        var request = getCustomerRequest();
        Customer customer = customerMapper.toEntity(request);

        assertNotNull(customer);
        assertEquals(request.getCpf(), customer.getCpf());
        assertEquals(request.getName(), customer.getName());
        assertEquals(request.getPhone(), customer.getPhone());
        assertEquals(request.getDateOfBirth().toString(), customer.getDateOfBirth().toString());
        assertEquals(request.getAddress().getCep(), customer.getAddress().getCep());

    }

    @Test
    @DisplayName("Request Update to entity mapper.")
    void testToEntity() {
        String cpf = "98765432100";
        var request = getCustomerRequestUpdate();
        Customer customer = customerMapper.toEntity(cpf, request);

        assertNotNull(customer);
        assertEquals(cpf, customer.getCpf());
        assertEquals(request.getName(), customer.getName());
        assertEquals(request.getPhone(), customer.getPhone());
        assertEquals(request.getDateOfBirth().toString(), customer.getDateOfBirth().toString());
        assertEquals(request.getAddress().getCep(), customer.getAddress().getCep());
    }

    @Test
    @DisplayName("Entity to response to  mapper.")
    void toDTO() {
        var customer = getCustomer();
        CustomerResponse customerResponse = customerMapper.toDTO(customer);

        assertNotNull(customerResponse);
        assertEquals(customer.getCpf(), customerResponse.getCpf());
        assertEquals(customer.getName(), customerResponse.getName());
        assertEquals(customer.getPhone(), customerResponse.getPhone());
        assertEquals(customer.getDateOfBirth().toString(), customerResponse.getDateOfBirth().toString());
        assertEquals(customer.getAddress().getCep(), customerResponse.getAddress().getCep());
    }

    @Test
    @DisplayName("Entity to response to  mapper when number of address is not null.")
    void toDTOWhenNumberOfAddressIsNotNull() {
        var customer = getCustomer();
        var address = new Address();
        address.setStreet("Street");
        address.setNumber("123");
        customer.setAddress(address);
        CustomerResponse customerResponse = customerMapper.toDTO(customer);

        assertNotNull(customerResponse);
        assertEquals(customer.getCpf(), customerResponse.getCpf());
        assertEquals(customer.getName(), customerResponse.getName());
        assertEquals(customer.getPhone(), customerResponse.getPhone());
        assertEquals(customer.getDateOfBirth().toString(), customerResponse.getDateOfBirth().toString());
        assertEquals(customer.getAddress().getCep(), customerResponse.getAddress().getCep());
    }

    @Test
    @DisplayName("Request to entity mapper when Address is null.")
    void toEntityWhenAddressIsNull() {
        var request = getCustomerRequest();
        request.setAddress(null);
        Customer customer = customerMapper.toEntity(request);

        assertNotNull(customer);
        assertEquals(request.getCpf(), customer.getCpf());
        assertEquals(request.getName(), customer.getName());
        assertEquals(request.getPhone(), customer.getPhone());
        assertEquals(request.getDateOfBirth().toString(), customer.getDateOfBirth().toString());
        assertNull(customer.getAddress());

    }

    @Test
    @DisplayName("Request Update to entity mapper when Address is null.")
    void testToEntityWhenAddressIsNull() {
        String cpf = "98765432100";
        var request = getCustomerRequestUpdate();
        request.setAddress(null);
        Customer customer = customerMapper.toEntity(cpf, request);

        assertNotNull(customer);
        assertEquals(cpf, customer.getCpf());
        assertEquals(request.getName(), customer.getName());
        assertEquals(request.getPhone(), customer.getPhone());
        assertEquals(request.getDateOfBirth().toString(), customer.getDateOfBirth().toString());
        assertNull(customer.getAddress());
    }

    @Test
    @DisplayName("Entity to response to  mapper when Address is null.")
    void toDTOWhenAddressIsNull() {
        var customer = getCustomer();
        customer.setAddress(null);
        CustomerResponse customerResponse = customerMapper.toDTO(customer);

        assertNotNull(customerResponse);
        assertEquals(customer.getCpf(), customerResponse.getCpf());
        assertEquals(customer.getName(), customerResponse.getName());
        assertEquals(customer.getPhone(), customerResponse.getPhone());
        assertEquals(customer.getDateOfBirth().toString(), customerResponse.getDateOfBirth().toString());
        assertNull(customerResponse.getAddress());
    }


    @Test
    @DisplayName("Request to entity mapper when request is null.")
    void toEntityWhenRequestIsNull() {

        Customer customer = customerMapper.toEntity(null);

        assertNull(customer);

    }

    @Test
    @DisplayName("Request Update to entity mapper when request is null.")
    void testToEntityWhenRequestIsNull() {

        Customer customer = customerMapper.toEntity(null, null);

        assertNull(customer);
    }

    @Test
    @DisplayName("Entity to response to  mapper when entity is null.")
    void toDTOWhenEntityIsNull() {
        CustomerResponse customerResponse = customerMapper.toDTO(null);

        assertNull(customerResponse);
    }

    private static CustomerRequest getCustomerRequest() {
        return CustomerRequest.builder()
                .cpf("10110110107")
                .name("John Doe")
                .phone("(11) 985762345")
                .dateOfBirth(LocalDate.of(2000, 7, 30))
                .address(AddressRequest.builder()
                        .street("Rua Exemplo")
                        .number(123)
                        .complement("Apto 101")
                        .neighborhood("Centro")
                        .city("Cidade Exemplo")
                        .state("EX")
                        .cep("12345-678")
                        .build())
                .build();
    }

    private static Customer getCustomer() {
        Customer customerEntity = new Customer();
        customerEntity.setCpf("10110110107");
        customerEntity.setName("John Doe");
        customerEntity.setPhone("(11) 985762345");
        customerEntity.setDateOfBirth(LocalDate.of(2000, 7, 30));
        customerEntity.setAddress(new Address());
        return customerEntity;
    }

    private static CustomerRequestUpdate getCustomerRequestUpdate() {
        return CustomerRequestUpdate.builder()
                .name("Non Existing Customer")
                .phone("(11) 987654321")
                .dateOfBirth(LocalDate.of(2000, 7, 30))
                .address(AddressRequest.builder()
                        .street("Rua Exemplo")
                        .number(123)
                        .complement("Apto 101")
                        .neighborhood("Centro")
                        .city("Cidade Exemplo")
                        .state("EX")
                        .cep("12345-678")
                        .build())
                .build();
    }
}