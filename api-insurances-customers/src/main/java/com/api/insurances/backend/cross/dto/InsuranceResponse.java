package com.api.insurances.backend.cross.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model representing a customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceResponse {

    @Schema(description = "Unique identifier of the insurance", example = "10110110107")
    private String id;

    @Schema(description = "Customer", example = "John Doe")
    private CustomerResponse customer;

    @Schema(description = "Insurance's", example = "(11) 985762345")
    private InsuranceTypeResponse insurance;

    @Schema(description = "Insurance's value Monthly", example = "(11) 985762345")
    private Double valueMonthly;
    ;

    @Schema(description = "Customer's date of birth", example = "30/07/2024 12:00:00")
    private LocalDateTime dateHiring;

    @Schema(description = "Customer's address",
            example = "{ \"street\": \"Rua Exemplo\", \"number\": \"123\", \"complement\": \"Apto 101\", \"neighborhood\": \"Centro\", \"city\": \"Cidade Exemplo\", \"state\": \"EX\", \"cep\": \"12345-678\" }")
    private String status;

    @Schema(description = "Insurance's date of create", example = "30/07/2024 12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Customer's date of update", example = "30/07/2024 12:00:00")
    private LocalDateTime updateAt;
}
