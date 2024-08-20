package com.api.insurances.backend.core.entity;

import com.api.insurances.backend.dataprovider.enumeration.InsuranceType;
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

    @Schema(description = "Unique identifier of the insurance", example = "1|2|3")
    private int id;

    @Schema(description = "Name Insurance", example = "Seguro Bronze")
    private String name;

    @Schema(description = "Description Insurance", example = "Básico")
    private String description;
}
