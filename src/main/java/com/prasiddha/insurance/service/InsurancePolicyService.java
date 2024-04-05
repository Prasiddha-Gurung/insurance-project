package com.prasiddha.insurance.service;

import com.prasiddha.insurance.dto.InsurancePolicyDto;

import java.util.List;

public interface InsurancePolicyService {
    InsurancePolicyDto createInsurancePolicy(InsurancePolicyDto insurancePolicyDto);

    List<InsurancePolicyDto> getAllInsurancePolicies();
}
