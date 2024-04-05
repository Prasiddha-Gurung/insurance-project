package com.prasiddha.insurance.dto;

import com.prasiddha.insurance.entity.PolicyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyDto {

    private Long policyId;
    private String policyName;
    private PolicyStatus policyStatus;
    private LocalDate coverageStartDate;
    private LocalDate coverageEndDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
