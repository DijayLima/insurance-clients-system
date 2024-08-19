package com.api.insurances.backend.service.rest;

import com.api.insurances.backend.cross.dto.CustomerResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/customers", name = "api-customers")
public interface CustomersServiceClient {

    @Retry(name = "api-customer-retry")
    @CircuitBreaker(name = "api-customer-circuit-breaker", fallbackMethod = "fallback")
    @GetMapping("/{cpf}")
    CustomerResponse getCustomerById(@PathVariable("cpf") String cpf);

    default CustomerResponse fallback(String cpf, Throwable t) {
        // Lógica de fallback
        return CustomerResponse.builder().build();
    }
}
