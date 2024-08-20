package com.api.insurances.backend.core.gateway;

import com.api.insurances.backend.configuration.exception.InsuranceException;
import com.api.insurances.backend.core.entity.CustomerResponse;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081/api/customers", name = "api-customers")
public interface CustomersGateway {

    Logger logger = LoggerFactory.getLogger(CustomersGateway.class);

    @Retry(name = "api-customer-retry")
    @CircuitBreaker(name = "api-customer-circuit-breaker", fallbackMethod = "fallback")
    @GetMapping("/{cpf}")
    CustomerResponse getCustomerById(@PathVariable("cpf") String cpf);

    default CustomerResponse fallback(String cpf, Throwable t) {
        logger.error("Failed to fetch customer with CPF {}: {}", cpf, t.getMessage());
        if (t instanceof FeignException) {
            FeignException feignException = (FeignException) t;

            if (feignException.status() == 404) {
                throw new InsuranceException("Customer with CPF " + cpf + " not found.");
            } else if (feignException.status() == 400) {
                logger.warn("Bad Request for CPF {}: {}", cpf, feignException.getMessage());
                throw new InsuranceException("Service customer unavailable.");
            }

            logger.error("Error fetching customer: {}", feignException.getMessage());
            throw new InsuranceException("Error fetching customer: " + feignException.getMessage(), feignException);
        }
        return CustomerResponse.builder().build();
    }
}
