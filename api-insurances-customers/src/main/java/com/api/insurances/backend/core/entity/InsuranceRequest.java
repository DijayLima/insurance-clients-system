package com.api.insurances.backend.core.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model representing a customer")
public class InsuranceRequest {

    @Schema(description = "Cpf of the customer", example = "10110110107")
    private String customerId;

    @Schema(description = "Details insurance", example = "1|2|3")
    private int insurance;

}
