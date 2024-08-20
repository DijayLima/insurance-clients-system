package com.api.customer.registration.backend.core.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model representing a address")
public class AddressRequest {

    @Schema(description = "Address's street", example = "Rua Engenheiro Augusto")
    private String street;

    @Schema(description = "Address's number", example = "55")
    private Integer number;

    @Schema(description = "Address's complement", example = "Condomínio Flores Belas")
    private String complement;

    @Schema(description = "Address's neighborhood", example = "Ponte Preta")
    private String neighborhood;

    @Schema(description = "Address's city", example = "Campinas")
    private String city;

    @Schema(description = "Address's state", example = "São Paulo")
    private String state;

    @Schema(description = "Address's cep", example = "13055-333")
    private String cep;
}
