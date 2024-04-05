package com.prasiddha.insurance.mapper;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.entity.InsurancePolicy;

import java.time.LocalDateTime;

public class InsurancePolicyMapper {

    public static InsurancePolicyDto toDto(InsurancePolicy insurancePolicy) {
        if (insurancePolicy == null) {
            return null;
        }

        InsurancePolicyDto dto = new InsurancePolicyDto();
        dto.setPolicyId(insurancePolicy.getPolicyId());
        dto.setPolicyName(insurancePolicy.getPolicyName());
        dto.setPolicyStatus(insurancePolicy.getPolicyStatus());
        dto.setCoverageStartDate(insurancePolicy.getCoverageStartDate());
        dto.setCoverageEndDate(insurancePolicy.getCoverageEndDate());
        dto.setCreatedAt(insurancePolicy.getCreatedAt());
        dto.setUpdatedAt(insurancePolicy.getUpdatedAt());
        return dto;
    }

    public static InsurancePolicy toEntity(InsurancePolicyDto dto) {
        if (dto == null) {
            return null;
        }

        InsurancePolicy insurancePolicy = new InsurancePolicy();
        insurancePolicy.setPolicyName(dto.getPolicyName());
        insurancePolicy.setPolicyStatus(dto.getPolicyStatus());
        insurancePolicy.setCoverageStartDate(dto.getCoverageStartDate());
        insurancePolicy.setCoverageEndDate(dto.getCoverageEndDate());
        insurancePolicy.setCreatedAt(LocalDateTime.now()); // Handle with care, usually not set manually
        insurancePolicy.setUpdatedAt(LocalDateTime.now()); // Handle with care, usually managed by JPA
        return insurancePolicy;
    }
}
