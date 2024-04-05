package com.prasiddha.insurance.service.impl;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.entity.InsurancePolicy;
import com.prasiddha.insurance.mapper.InsurancePolicyMapper;
import com.prasiddha.insurance.repository.InsurancePolicyRepository;
import com.prasiddha.insurance.service.InsurancePolicyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    private InsurancePolicyRepository insurancePolicyRepository;
    @Override
    public InsurancePolicyDto createInsurancePolicy(InsurancePolicyDto insurancePolicyDto)
    {
        InsurancePolicy insurancePolicy = InsurancePolicyMapper.toEntity(insurancePolicyDto);
        InsurancePolicy savedInsurancePolicy = insurancePolicyRepository.save(insurancePolicy);
        return InsurancePolicyMapper.toDto(savedInsurancePolicy);
    }

    @Override
    public List<InsurancePolicyDto> getAllInsurancePolicies()
    {
        return insurancePolicyRepository.findAll().stream()
                .map(policy-> InsurancePolicyMapper.toDto(policy))
                .collect(Collectors.toList());
    }

}
