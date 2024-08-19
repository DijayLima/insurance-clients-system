package com.api.customer.registration.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDetails {

    private int statusCode;
    private String message;
    private String details;
}

