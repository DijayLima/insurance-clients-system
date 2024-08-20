package com.api.insurances.backend.core.gateway;

import com.api.insurances.backend.core.entity.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomersGatewayTest {

    @Mock
    private CustomersGateway customersGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get customer by id success.")
    void testGetCustomerById_Success() {
        // Arrange
        String cpf = "12345678900";
        CustomerResponse expectedResponse = new CustomerResponse();
        expectedResponse.setName("John Doe");

        when(customersGateway.getCustomerById(cpf)).thenReturn(expectedResponse);

        // Act
        CustomerResponse actualResponse = customersGateway.getCustomerById(cpf);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        verify(customersGateway, times(1)).getCustomerById(cpf);
    }

    @Test
    @DisplayName("Get customer by id fallback.")
    void testGetCustomerById_Fallback() {
        // Arrange
        String cpf = "12345678900";

        // Act
        CustomerResponse actualResponse = customersGateway.getCustomerById(cpf);

        // Assert
        assertNull(actualResponse);
    }
}