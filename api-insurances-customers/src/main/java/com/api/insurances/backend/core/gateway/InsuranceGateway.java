package com.api.insurances.backend.core.gateway;

import com.api.insurances.backend.core.entity.InsuranceRequest;
import com.api.insurances.backend.core.entity.InsuranceResponse;

public interface InsuranceGateway {

    InsuranceResponse simulateInsurance(InsuranceRequest insuranceRequest);

    InsuranceResponse hireInsurance(InsuranceRequest insuranceRequest);

}
