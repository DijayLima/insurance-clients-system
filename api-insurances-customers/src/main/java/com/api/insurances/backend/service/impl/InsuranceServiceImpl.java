package com.api.insurances.backend.service.impl;

import com.api.insurances.backend.cross.enumeration.InsuranceType;
import com.api.insurances.backend.cross.exception.InsuranceException;
import com.api.insurances.backend.cross.dto.CustomerResponse;
import com.api.insurances.backend.cross.dto.InsuranceRequest;
import com.api.insurances.backend.cross.dto.InsuranceResponse;
import com.api.insurances.backend.cross.dto.InsuranceTypeResponse;
import com.api.insurances.backend.model.InsuranceHiring;
import com.api.insurances.backend.repository.InsuranceHiringRepository;
import com.api.insurances.backend.service.rest.CustomersServiceClient;
import com.api.insurances.backend.service.InsuranceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final CustomersServiceClient customersServiceClient;
    private final InsuranceHiringRepository insuranceHiringRepository;

    @Override
    public InsuranceResponse simulateInsurance(InsuranceRequest insuranceRequest) {
        log.info("[simulateInsurance] - begin");

        CustomerResponse customer = consultCustomer(insuranceRequest.getCustomerId());
        InsuranceType insurance = findInsuranceById(insuranceRequest.getInsurance());

        int years = calculateAge(customer.getDateOfBirth());
        double valueInsurance = calculateInsuranceValue(insurance, years);

        log.info("[simulateInsurance] - end");

        return buildInsuranceResponse(insurance, valueInsurance, customer);
    }

    @Override
    public InsuranceResponse hireInsurance(InsuranceRequest insuranceRequest) {

        log.info("[hireInsurance] - begin");

        CustomerResponse customer = consultCustomer(insuranceRequest.getCustomerId());
        InsuranceType insurance = findInsuranceById(insuranceRequest.getInsurance());

        int years = calculateAge(customer.getDateOfBirth());
        double valueInsurance = calculateInsuranceValue(insurance, years);

        InsuranceHiring insuranceHiring = InsuranceHiring.builder()
                .insurance(insurance.getId())
                .valueMonthly(valueInsurance)
                .customerId(insuranceRequest.getCustomerId())
                .build();

        log.info("[hireInsurance] - end");

        InsuranceHiring insuranceHiringSaved = insuranceHiringRepository.save(insuranceHiring);

        return buildInsuranceResponse(insurance, insuranceHiringSaved.getValueMonthly(), customer);
    }

    @CircuitBreaker(name = "api-customer-circuit-breaker", fallbackMethod = "fallback")
    public CustomerResponse consultCustomer(String cpf) {
        log.info("[consultCustomer] - consult: {}", cpf);
        return customersServiceClient.getCustomerById(cpf);
    }

    public CustomerResponse fallback(String cpf) {
        throw new InsuranceException("Service customer unavailable.");
    }

    private InsuranceType findInsuranceById(int insuranceId) {
        return InsuranceType.findById(insuranceId);
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private double calculateInsuranceValue(InsuranceType insurance, int years) {
        if(years == 0)
            years = 1;
        return insurance.getValueBase() * years;
    }

    private InsuranceResponse buildInsuranceResponse(InsuranceType insurance, double valueInsurance, CustomerResponse customer) {
        return InsuranceResponse.builder()
                .insurance(InsuranceTypeResponse.builder()
                        .insurance(insurance)
                        .id(insurance.getId())
                        .name(insurance.getName())
                        .description(insurance.getDescription())
                        .build())
                .valueMonthly(valueInsurance)
                .customer(customer)
                .build();
    }
}


