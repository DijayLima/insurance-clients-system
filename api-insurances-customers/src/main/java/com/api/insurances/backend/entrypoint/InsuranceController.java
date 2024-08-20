package com.api.insurances.backend.entrypoint;

import com.api.insurances.backend.core.entity.InsuranceRequest;
import com.api.insurances.backend.core.entity.InsuranceResponse;
import com.api.insurances.backend.core.gateway.InsuranceGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurance")
@Tag(name = "Insurance", description = "Operations related to insurance management")
public class InsuranceController {

    private final InsuranceGateway insuranceUseCase;

    public InsuranceController(InsuranceGateway insuranceUseCase) {
        this.insuranceUseCase = insuranceUseCase;
    }

    @PostMapping("/hiring")
    @Operation(summary = "Hiring a new insurance", description = "Hiring a new insurance to the customer",
            responses = {
                    @ApiResponse(description = "Insurance hired", responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsuranceResponse.class))),
                    @ApiResponse(description = "Invalid input", responseCode = "400", content = @Content)
            })
    public ResponseEntity<InsuranceResponse> createInsurance(@RequestBody InsuranceRequest insurance) {
        return new ResponseEntity<>(insuranceUseCase.hireInsurance(insurance), HttpStatus.CREATED);
    }

    @PostMapping("/simulate")
    @Operation(summary = "Simulate a new insurance", description = "Simulate a new insurance to the customer",
            responses = {
                    @ApiResponse(description = "Insurance simulation carried out", responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsuranceResponse.class))),
                    @ApiResponse(description = "Invalid input", responseCode = "400", content = @Content)
            })
    public ResponseEntity<InsuranceResponse> simulateInsurance(@RequestBody InsuranceRequest insurance) {
        return new ResponseEntity<>(insuranceUseCase.simulateInsurance(insurance), HttpStatus.OK);
    }

}

