package com.api.customer.registration.backend.controller;

import com.api.customer.registration.backend.domain.dto.CustomerRequest;
import com.api.customer.registration.backend.domain.dto.CustomerRequestUpdate;
import com.api.customer.registration.backend.domain.dto.CustomerResponse;
import com.api.customer.registration.backend.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers", description = "Operations related to customer management")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Get a customer by CPF", description = "Returns a single customer by CPF",
            responses = {
                    @ApiResponse(description = "Successful operation", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))),
                    @ApiResponse(description = "Customer not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String cpf) {
        return new ResponseEntity<>(customerService.findCustomerByCpf(cpf), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Adds a new customer to the system",
            responses = {
                    @ApiResponse(description = "Customer created", responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))),
                    @ApiResponse(description = "Invalid input", responseCode = "400", content = @Content)
            })
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Update an existing customer", description = "Updates the details of an existing customer",
            responses = {
                    @ApiResponse(description = "Successful operation", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))),
                    @ApiResponse(description = "Customer not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Invalid input", responseCode = "400", content = @Content)
            })
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String cpf, @RequestBody CustomerRequestUpdate customer) {
        return new ResponseEntity<>(customerService.update(cpf, customer), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Delete a customer", description = "Deletes a customer by ID",
            responses = {
                    @ApiResponse(description = "Successful operation", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Customer not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<Void> deleteCustomer(@PathVariable String cpf) {
        customerService.deleteCustomer(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

