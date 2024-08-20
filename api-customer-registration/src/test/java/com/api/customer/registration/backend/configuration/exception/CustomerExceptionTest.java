package com.api.customer.registration.backend.configuration.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerExceptionTest {

    @Test
    @DisplayName("Exception is created with a message and cause")
    public void test_exceptionWithMessageAndCause() {
        String message = "Customer error occurred";
        Throwable cause = new RuntimeException("Root cause");
        CustomerException exception = new CustomerException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Null message is handled correctly")
    public void test_nullMessageHandling() {
        Throwable cause = new RuntimeException("Root cause");
        CustomerException exception = new CustomerException(null, cause);

        assertNull(exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Exception is created with a valid message")
    public void test_exceptionWithValidMessage() {
        String message = "Valid exception message";
        CustomerException exception = new CustomerException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Exception is created with a null message")
    public void test_exceptionWithNullMessage() {
        String message = null;
        CustomerException exception = new CustomerException(message);
        assertNull(exception.getMessage());
    }

}