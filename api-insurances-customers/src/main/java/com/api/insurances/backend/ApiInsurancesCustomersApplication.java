package com.api.insurances.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiInsurancesCustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInsurancesCustomersApplication.class, args);
	}

}
