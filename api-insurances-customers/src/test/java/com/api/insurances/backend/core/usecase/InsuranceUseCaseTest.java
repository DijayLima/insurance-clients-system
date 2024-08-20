package com.api.insurances.backend.core.usecase;

import com.api.insurances.backend.core.entity.CustomerResponse;
import com.api.insurances.backend.core.entity.InsuranceRequest;
import com.api.insurances.backend.core.entity.InsuranceResponse;
import com.api.insurances.backend.core.gateway.CustomersGateway;
import com.api.insurances.backend.dataprovider.enumeration.InsuranceType;
import com.api.insurances.backend.dataprovider.model.InsuranceHiring;
import com.api.insurances.backend.dataprovider.repository.InsuranceHiringRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class InsuranceUseCaseTest {

    @Mock
    private CustomersGateway customersGateway;

    @Mock
    private InsuranceHiringRepository insuranceHiringRepository;

    @InjectMocks
    private InsuranceUseCase insuranceService;

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

        when(customersGateway.getCustomerById(anyString())).thenReturn(customerResponse);

        // Act
        InsuranceResponse insuranceResponse = insuranceService.simulateInsurance(insuranceRequest);

        // Assert
        assertNotNull(insuranceResponse);
        assertEquals("John Doe", insuranceResponse.getCustomer().getName());
        assertEquals(InsuranceType.BRONZE, insuranceResponse.getInsurance().getInsurance());
        verify(customersGateway, times(1)).getCustomerById(anyString());
    }

    @Test
    @DisplayName("Test for successful insurance contracting simulation when diff years equals zero.")
    void testSimulateInsuranceWhenDiffYearsEqualsZero() {
        // Arrange
        InsuranceRequest insuranceRequest = getInsuranceRequest();

        CustomerResponse customerResponse = getCustomerResponse();
        customerResponse.setDateOfBirth(LocalDate.now());

        when(customersGateway.getCustomerById(anyString())).thenReturn(customerResponse);

        // Act
        InsuranceResponse insuranceResponse = insuranceService.simulateInsurance(insuranceRequest);

        // Assert
        assertNotNull(insuranceResponse);
        assertEquals("John Doe", insuranceResponse.getCustomer().getName());
        assertEquals(InsuranceType.BRONZE, insuranceResponse.getInsurance().getInsurance());
        verify(customersGateway, times(1)).getCustomerById(anyString());
    }


    @Test
    @DisplayName("Test to successfully contract insurance.")
    void testHireInsurance() {
        // Arrange
        InsuranceRequest insuranceRequest = getInsuranceRequest();

        CustomerResponse customerResponse = getCustomerResponse();

        when(customersGateway.getCustomerById(anyString())).thenReturn(customerResponse);
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