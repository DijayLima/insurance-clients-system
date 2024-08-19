package com.api.customer.registration.backend.controller.exceptionhandler;

import com.api.customer.registration.backend.cross.exception.CustomerException;
import com.api.customer.registration.backend.domain.dto.ErrorDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler handler;

    @Mock
    WebRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Handles CustomerException and returns not found status")
    public void test_handlesCustomerExceptionAndReturnsNotFoundStatus() {

        Mockito.when(request.getDescription(false)).thenReturn("uri=/test");

        CustomerException ex = new CustomerException("Customer not found");
        ResponseEntity<Object> response = handler.handleCustomerNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertNotNull(errorDetails);
        assertEquals(HttpStatus.NOT_FOUND.value(), errorDetails.getStatusCode());
        assertEquals("Customer not found", errorDetails.getMessage());
        assertEquals("uri=/test", errorDetails.getDetails());
    }

    @Test
    @DisplayName("Handles CustomerException with null message")
    public void test_handles_customer_exception_with_null_message() {
        Mockito.when(request.getDescription(false)).thenReturn("uri=/test");

        CustomerException ex = new CustomerException(null);
        ResponseEntity<Object> response = handler.handleCustomerNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertNotNull(errorDetails);
        assertEquals(HttpStatus.NOT_FOUND.value(), errorDetails.getStatusCode());
        assertNull(errorDetails.getMessage());
        assertEquals("uri=/test", errorDetails.getDetails());
    }

    @Test
    @DisplayName("Handles general exceptions and returns INTERNAL_SERVER_ERROR status")
    public void test_handleGeneralException() {
        Exception ex = new Exception("Test Exception");
        Mockito.when(request.getDescription(false)).thenReturn("Test Description");

        ResponseEntity<Object> response = handler.handleGlobalException(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertNotNull(errorDetails);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDetails.getStatusCode());
        assertEquals("Test Exception", errorDetails.getMessage());
        assertEquals("Test Description", errorDetails.getDetails());
    }

    @Test
    @DisplayName("Handles null exception message gracefully")
    public void test_handleStringEmptyExceptionMessage() {
        Exception ex = new Exception("");
        Mockito.when(request.getDescription(false)).thenReturn("Test Description");

        ResponseEntity<Object> response = handler.handleGlobalException(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertNotNull(errorDetails);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDetails.getStatusCode());
        assertEquals("", errorDetails.getMessage());
        assertEquals("Test Description", errorDetails.getDetails());
    }


}