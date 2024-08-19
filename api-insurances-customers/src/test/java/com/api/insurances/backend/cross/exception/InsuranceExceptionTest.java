package com.api.insurances.backend.cross.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceExceptionTest {
    
    @Test
    @DisplayName("Exception is created with a message and cause")
    public void test_exceptionWithMessageAndCause() {
        String message = "Insurance error occurred";
        Throwable cause = new RuntimeException("Root cause");
        InsuranceException exception = new InsuranceException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Null message is handled correctly")
    public void test_nullMessageHandling() {
        Throwable cause = new RuntimeException("Root cause");
        InsuranceException exception = new InsuranceException(null, cause);

        assertNull(exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Exception is created with a valid message")
    public void test_exceptionWithValidMessage() {
        String message = "Valid exception message";
        InsuranceException exception = new InsuranceException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Exception is created with a null message")
    public void test_exceptionWithNullMessage() {
        String message = null;
        InsuranceException exception = new InsuranceException(message);
        assertNull(exception.getMessage());
    }
}