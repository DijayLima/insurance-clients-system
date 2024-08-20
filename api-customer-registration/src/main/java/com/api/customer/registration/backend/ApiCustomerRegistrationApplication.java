package com.api.customer.registration.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiCustomerRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCustomerRegistrationApplication.class, args);
    }

}
