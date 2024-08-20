package com.api.customer.registration.backend.core.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model representing a customer")
public class CustomerResponse {

    @Schema(description = "Unique identifier of the customer", example = "10110110107")
    private String cpf;

    @Schema(description = "Customer's name", example = "John Doe")
    private String name;

    @Schema(description = "Customer's phone", example = "(11) 985762345")
    private String phone;

    @Schema(description = "Customer's date of birth", example = "30/07/2000")
    private LocalDate dateOfBirth;

    @Schema(description = "Customer's address",
            example = "{ \"street\": \"Rua Exemplo\", \"number\": \"123\", \"complement\": \"Apto 101\", \"neighborhood\": \"Centro\", \"city\": \"Cidade Exemplo\", \"state\": \"EX\", \"cep\": \"12345-678\" }")
    private AddressResponse address;
}
