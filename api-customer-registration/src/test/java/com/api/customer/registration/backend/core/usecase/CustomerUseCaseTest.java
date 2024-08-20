package com.api.customer.registration.backend.core.usecase;

import com.api.customer.registration.backend.configuration.exception.CustomerException;
import com.api.customer.registration.backend.core.entity.*;
import com.api.customer.registration.backend.dataprovider.mapper.CustomerMapper;
import com.api.customer.registration.backend.dataprovider.model.Address;
import com.api.customer.registration.backend.dataprovider.model.Customer;
import com.api.customer.registration.backend.dataprovider.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerUseCase customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Successfully creates a new customer when CPF does not exist")
    public void test_createCustomerSuccess() {

        var customerEntity = getCustomer();
        var customerResponse = getCustomerResponse();

        Mockito.when(customerRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        Mockito.when(customerMapper.toEntity(any())).thenReturn(customerEntity);
        Mockito.when(customerRepository.save(any())).thenReturn(customerEntity);
        Mockito.when(customerMapper.toDTO(any())).thenReturn(customerResponse);

        CustomerResponse result = customerService.createCustomer(getCustomerRequest());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("10110110107", result.getCpf());
    }

    @Test
    @DisplayName("Throws CustomerException when a customer with the given CPF already exists")
    public void test_createCustomerAlreadyExists() {

        CustomerRequest customerRequest = getCustomerRequest();

        Customer customer = getCustomer();

        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.of(customer));

        Assertions.assertThrows(CustomerException.class, () -> customerService.createCustomer(customerRequest));
    }

    @Test
    @DisplayName("Update existing customer with valid CPF and valid CustomerRequestUpdate")
    public void test_updateExistingCustomerWithValidCpf() {

        String cpf = "10110110107";
        CustomerRequestUpdate customerRequestUpdate = getCustomerRequestUpdate();
        customerRequestUpdate.setName("John Doe Updated");
        customerRequestUpdate.setPhone("(11) 987654321");
        customerRequestUpdate.setDateOfBirth(LocalDate.of(2000, 7, 30));

        Customer existingCustomer = getCustomer();

        Customer updatedCustomer = getCustomer();
        updatedCustomer.setName("John Doe Updated");
        updatedCustomer.setPhone("(11) 987654321");
        updatedCustomer.setDateOfBirth(LocalDate.of(2000, 7, 30));
        updatedCustomer.setAddress(new Address());

        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.of(existingCustomer));
        when(customerMapper.toEntity(anyString(), any())).thenReturn(updatedCustomer);
        when(customerRepository.save(any())).thenReturn(updatedCustomer);
        when(customerMapper.toDTO(any())).thenReturn(getCustomerResponseUpdated());

        CustomerResponse actualResponse = customerService.update(cpf, customerRequestUpdate);

        assertEquals(getCustomerResponseUpdated().getCpf(), actualResponse.getCpf());
        assertEquals(getCustomerResponseUpdated().getName(), actualResponse.getName());
        assertEquals(getCustomerResponseUpdated().getPhone(), actualResponse.getPhone());
        assertEquals(getCustomerResponseUpdated().getAddress().getCep(), actualResponse.getAddress().getCep());
    }

    @Test
    @DisplayName("Attempt to update a customer with a non-existing CPF")
    public void test_updateNonExistingCustomerWithInvalidCpf() {

        String cpf = "99999999999";
        CustomerRequestUpdate customerRequestUpdate = getCustomerRequestUpdate();

        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(CustomerException.class, () -> customerService.update(cpf, customerRequestUpdate));
    }

    @Test
    @DisplayName("Successfully find a customer by CPF and return the corresponding CustomerResponse")
    public void test_findCustomerByCpfSuccess() {

        String cpf = "10110110107";
        Customer customer = getCustomer();

        CustomerResponse expectedResponse = getCustomerResponse();

        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(any())).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerService.findCustomerByCpf(cpf);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CPF not found in the repository, triggering a CustomerException")
    public void test_findCustomerByCpfNotFound() {

        String cpf = "10110110107";
        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(CustomerException.class, () -> customerService.findCustomerByCpf(cpf));
    }

    @Test
    @DisplayName("Successfully deletes a customer when a valid CPF is provided")
    public void test_deleteCustomerSuccess() {

        String cpf = "10110110107";
        Customer customer = getCustomer();

        CustomerResponse expectedResponse = getCustomerResponse();

        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(any())).thenReturn(expectedResponse);

        customerService.deleteCustomer(cpf);

        Mockito.verify(customerRepository, Mockito.times(1)).deleteByCpf(cpf);
    }

    @Test
    @DisplayName("Throws an exception when the customer with the given CPF does not exist")
    public void test_deleteCustomerNotFound() {

        String cpf = "12345678900";

        Mockito.when(customerRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerException.class, () -> customerService.deleteCustomer(cpf));
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

    private static CustomerResponse getCustomerResponse() {
        return CustomerResponse.builder()
                .cpf("10110110107")
                .name("John Doe")
                .phone("(11) 985762345")
                .dateOfBirth(LocalDate.of(2000, 7, 30))
                .address(new AddressResponse())
                .build();
    }

    private static CustomerResponse getCustomerResponseUpdated() {
        return CustomerResponse.builder()
                .cpf("10110110107")
                .name("John Doe Updated")
                .phone("(11) 987654321")
                .dateOfBirth(LocalDate.of(2000, 7, 30))
                .address(new AddressResponse())
                .build();
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