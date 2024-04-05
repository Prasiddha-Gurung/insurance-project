package com.prasiddha.insurance.service.impl;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.entity.InsurancePolicy;
import com.prasiddha.insurance.exception.ResourceNotFoundException;
import com.prasiddha.insurance.mapper.InsurancePolicyMapper;
import com.prasiddha.insurance.repository.InsurancePolicyRepository;
import com.prasiddha.insurance.service.InsurancePolicyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public InsurancePolicyDto getInsurancePolicyById(Long policyId)
    {
        try {
            InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy not found for this id :: " + policyId));
            return InsurancePolicyMapper.toDto(insurancePolicy);
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public InsurancePolicyDto updateInsurancePolicy(Long policyId, InsurancePolicyDto insurancePolicyDto) {

        InsurancePolicy existingPolicy = insurancePolicyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("InsurancePolicy not found for this id :: " + policyId));

        if (insurancePolicyDto.getPolicyName() != null) {
            existingPolicy.setPolicyName(insurancePolicyDto.getPolicyName());
        }
        if (insurancePolicyDto.getPolicyStatus() != null) {
            existingPolicy.setPolicyStatus(insurancePolicyDto.getPolicyStatus());
        }
        if (insurancePolicyDto.getCoverageStartDate() != null) {
            existingPolicy.setCoverageStartDate(insurancePolicyDto.getCoverageStartDate());
        }
        if (insurancePolicyDto.getCoverageEndDate() != null) {
            existingPolicy.setCoverageEndDate(insurancePolicyDto.getCoverageEndDate());
        }

        existingPolicy.setUpdatedAt(LocalDateTime.now());

        InsurancePolicy updatedPolicy = insurancePolicyRepository.save(existingPolicy);

        return InsurancePolicyMapper.toDto(updatedPolicy);
    }

}
