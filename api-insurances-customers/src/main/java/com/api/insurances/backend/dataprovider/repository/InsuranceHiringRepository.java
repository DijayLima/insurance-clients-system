package com.api.insurances.backend.dataprovider.repository;

import com.api.insurances.backend.dataprovider.model.InsuranceHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceHiringRepository extends JpaRepository<InsuranceHiring, String> {
}

