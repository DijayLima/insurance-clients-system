package com.api.insurances.backend.controller.exceptionhandler;

import com.api.insurances.backend.cross.exception.InsuranceException;
import com.api.insurances.backend.cross.dto.ErrorDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalExceptionHandlerTest {

    @Autowired
    GlobalExceptionHandler handler;

    @Mock
    WebRequest request;

    @Test
    @DisplayName("Handles InsuranceException and returns not found status")
    public void test_handlesCustomerExceptionAndReturnsNotFoundStatus() {

        Mockito.when(request.getDescription(false)).thenReturn("uri=/test");

        InsuranceException ex = new InsuranceException("Insurance not found");
        ResponseEntity<Object> response = handler.handleCustomerNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertNotNull(errorDetails);
        assertEquals(HttpStatus.NOT_FOUND.value(), errorDetails.getStatusCode());
        assertEquals("Insurance not found", errorDetails.getMessage());
        assertEquals("uri=/test", errorDetails.getDetails());
    }

    @Test
    @DisplayName("Handles InsuranceException with null message")
    public void test_handles_customer_exception_with_null_message() {
        Mockito.when(request.getDescription(false)).thenReturn("uri=/test");

        InsuranceException ex = new InsuranceException(null);
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