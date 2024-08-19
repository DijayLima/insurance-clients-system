package com.api.insurances.backend.service.impl;

import com.api.insurances.backend.cross.enumeration.InsuranceType;
import com.api.insurances.backend.cross.exception.InsuranceException;
import com.api.insurances.backend.cross.dto.CustomerResponse;
import com.api.insurances.backend.cross.dto.InsuranceRequest;
import com.api.insurances.backend.cross.dto.InsuranceResponse;
import com.api.insurances.backend.model.InsuranceHiring;
import com.api.insurances.backend.repository.InsuranceHiringRepository;
import com.api.insurances.backend.service.rest.CustomersServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InsuranceServiceImplTest {

    @Mock
    private CustomersServiceClient customersServiceClient;

    @Mock
    private InsuranceHiringRepository insuranceHiringRepository;

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test for successful insurance contracting simulation.")
    void testSimulateInsurance() {
        // Arrange
        InsuranceRequest insuranceRequest = getInsuranceRequest();

        CustomerResponse customerResponse = getCustomerResponse();

        when(customersServiceClient.getCustomerById(anyString())).thenReturn(customerResponse);

        // Act
        InsuranceResponse insuranceResponse = insuranceService.simulateInsurance(insuranceRequest);

        // Assert
        assertNotNull(insuranceResponse);
        assertEquals("John Doe", insuranceResponse.getCustomer().getName());
        assertEquals(InsuranceType.BRONZE, insuranceResponse.getInsurance().getInsurance());
        verify(customersServiceClient, times(1)).getCustomerById(anyString());
    }

    @Test
    @DisplayName("Test for successful insurance contracting simulation when diff years equals zero.")
    void testSimulateInsuranceWhenDiffYearsEqualsZero() {
        // Arrange
        InsuranceRequest insuranceRequest = getInsuranceRequest();

        CustomerResponse customerResponse = getCustomerResponse();
        customerResponse.setDateOfBirth(LocalDate.now());

        when(customersServiceClient.getCustomerById(anyString())).thenReturn(customerResponse);

        // Act
        InsuranceResponse insuranceResponse = insuranceService.simulateInsurance(insuranceRequest);

        // Assert
        assertNotNull(insuranceResponse);
        assertEquals("John Doe", insuranceResponse.getCustomer().getName());
        assertEquals(InsuranceType.BRONZE, insuranceResponse.getInsurance().getInsurance());
        verify(customersServiceClient, times(1)).getCustomerById(anyString());
    }


    @Test
    @DisplayName("Test to successfully contract insurance.")
    void testHireInsurance() {
        // Arrange
        InsuranceRequest insuranceRequest = getInsuranceRequest();

        CustomerResponse customerResponse = getCustomerResponse();

        when(customersServiceClient.getCustomerById(anyString())).thenReturn(customerResponse);
        when(insuranceHiringRepository.save(any(InsuranceHiring.class))).thenReturn(InsuranceHiring.builder().insurance(1).valueMonthly(99.99).build());

        // Act
        InsuranceResponse insuranceResponse = insuranceService.hireInsurance(insuranceRequest);

        // Assert
        assertNotNull(insuranceResponse);
        assertEquals(99.99, insuranceResponse.getValueMonthly());
        assertEquals(InsuranceType.BRONZE, insuranceResponse.getInsurance().getInsurance());
        verify(insuranceHiringRepository, times(1)).save(any(InsuranceHiring.class));
    }


    private static CustomerResponse getCustomerResponse() {
        return CustomerResponse.builder()
                .cpf("10110110107")
                .name("John Doe")
                .phone("(11) 985762345")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
    }

    private static InsuranceRequest getInsuranceRequest() {
        return InsuranceRequest.builder()
                .customerId("10110110107")
                .insurance(1)
                .build();
    }
}
