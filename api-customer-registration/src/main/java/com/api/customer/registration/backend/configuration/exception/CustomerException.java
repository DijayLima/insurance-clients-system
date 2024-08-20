package com.api.customer.registration.backend.configuration.exception;

public class CustomerException extends RuntimeException {

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerException(String message) {
        super(message);
    }

}

