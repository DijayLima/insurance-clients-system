package com.api.insurances.backend.cross.dto;

import com.api.insurances.backend.cross.enumeration.InsuranceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model representing a customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceTypeResponse {

    @Schema(description = "Insurance's", example = "(11) 985762345")
    private InsuranceType insurance;

    @Schema(description = "Unique identifier of the insurance", example = "10110110107")
    private int id;

    @Schema(description = "Name Insurance", example = "John Doe")
    private String name;

    @Schema(description = "Description Insurance", example = "John Doe")
    private String description;
}
