package com.api.insurances.backend.cross.exception;

public class InsuranceException extends RuntimeException {

    public InsuranceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsuranceException(String message) {
        super(message);
    }

}

