package com.api.insurances.backend.configuration.exception;

public class InsuranceException extends RuntimeException {

    public InsuranceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsuranceException(String message) {
        super(message);
    }

}

