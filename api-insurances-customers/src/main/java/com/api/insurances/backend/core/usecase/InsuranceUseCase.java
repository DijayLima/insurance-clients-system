package com.api.insurances.backend.core.usecase;

import com.api.insurances.backend.configuration.exception.InsuranceException;
import com.api.insurances.backend.core.entity.CustomerResponse;
import com.api.insurances.backend.core.entity.InsuranceRequest;
import com.api.insurances.backend.core.entity.InsuranceResponse;
import com.api.insurances.backend.core.entity.InsuranceTypeResponse;
import com.api.insurances.backend.core.gateway.CustomersGateway;
import com.api.insurances.backend.core.gateway.InsuranceGateway;
import com.api.insurances.backend.dataprovider.enumeration.InsuranceType;
import com.api.insurances.backend.dataprovider.model.InsuranceHiring;
import com.api.insurances.backend.dataprovider.repository.InsuranceHiringRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsuranceUseCase implements InsuranceGateway {

    private final CustomersGateway customersGateway;
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

    private CustomerResponse consultCustomer(String cpf) {
        log.info("[consultCustomer] - consult: {}", cpf);
        return customersGateway.getCustomerById(cpf);
    }

    private InsuranceType findInsuranceById(int insuranceId) {
        return InsuranceType.findById(insuranceId);
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private double calculateInsuranceValue(InsuranceType insurance, int years) {
        if (years == 0)
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


