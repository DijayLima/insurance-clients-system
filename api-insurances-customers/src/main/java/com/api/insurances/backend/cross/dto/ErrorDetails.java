package com.api.insurances.backend.cross.dto;

import lombok.AllArgsConstructor;
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

