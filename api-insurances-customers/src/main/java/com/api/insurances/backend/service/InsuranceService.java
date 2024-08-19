package com.api.insurances.backend.service;

import com.api.insurances.backend.cross.dto.InsuranceRequest;
import com.api.insurances.backend.cross.dto.InsuranceResponse;

public interface InsuranceService {

    InsuranceResponse simulateInsurance(InsuranceRequest insuranceRequest);
    InsuranceResponse hireInsurance(InsuranceRequest insuranceRequest);

}
