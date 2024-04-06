package com.prasiddha.insurance;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.entity.InsurancePolicy;
import com.prasiddha.insurance.entity.PolicyStatus;
import com.prasiddha.insurance.exception.ResourceNotFoundException;
import com.prasiddha.insurance.mapper.InsurancePolicyMapper;
import com.prasiddha.insurance.repository.InsurancePolicyRepository;
import com.prasiddha.insurance.service.impl.InsurancePolicyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InsurancePolicyServiceImplTest {

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @InjectMocks
    private InsurancePolicyServiceImpl insurancePolicyService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateInsurancePolicy() {
        InsurancePolicyDto inputDto = new InsurancePolicyDto();
        inputDto.setPolicyId(1L);
        inputDto.setPolicyName("Comprehensive Health Insurance");
        inputDto.setPolicyStatus(PolicyStatus.ACTIVE);
        inputDto.setCoverageStartDate(LocalDate.of(2024, 1, 1));
        inputDto.setCoverageEndDate(LocalDate.of(2025, 12, 31));


        InsurancePolicy insurancePolicy = InsurancePolicyMapper.toEntity(inputDto);


        InsurancePolicy savedInsurancePolicy = new InsurancePolicy();

        savedInsurancePolicy.setPolicyId(1L);
        savedInsurancePolicy.setPolicyName("Comprehensive Health Insurance");
        savedInsurancePolicy.setPolicyStatus(PolicyStatus.ACTIVE);
        savedInsurancePolicy.setCoverageStartDate(LocalDate.of(2024, 1, 1));
        savedInsurancePolicy.setCoverageEndDate(LocalDate.of(2025, 12, 31));
        savedInsurancePolicy.setCreatedAt(LocalDateTime.now());
        savedInsurancePolicy.setUpdatedAt(LocalDateTime.now());

        when(insurancePolicyRepository.save(any(InsurancePolicy.class))).thenReturn(savedInsurancePolicy);

        InsurancePolicyDto resultDto = insurancePolicyService.createInsurancePolicy(inputDto);

        assertNotNull(resultDto);
        assertEquals(savedInsurancePolicy.getPolicyId(), resultDto.getPolicyId());
        verify(insurancePolicyRepository, times(1)).save(any(InsurancePolicy.class));
    }

    @Test
    void testGetAllInsurancePolicies() {

        InsurancePolicy policy = new InsurancePolicy();
        policy.setPolicyId(1L);
        policy.setPolicyName("Comprehensive Health Insurance");
        policy.setPolicyStatus(PolicyStatus.ACTIVE);
        policy.setCoverageStartDate(LocalDate.of(2024, 1, 1));
        policy.setCoverageEndDate(LocalDate.of(2025, 12, 31));
        policy.setCreatedAt(LocalDateTime.now());
        policy.setUpdatedAt(LocalDateTime.now());

        List<InsurancePolicy> policies = List.of(policy);
        when(insurancePolicyRepository.findAll()).thenReturn(policies);

        List<InsurancePolicyDto> resultDtos = insurancePolicyService.getAllInsurancePolicies();

        assertFalse(resultDtos.isEmpty());
        assertEquals(policies.size(), resultDtos.size());
    }

    @Test
    void testGetInsurancePolicyByIdFound() {
        Long policyId = 1L;
        InsurancePolicy insurancePolicy = new InsurancePolicy();

        insurancePolicy.setPolicyId(1L);
        insurancePolicy.setPolicyName("Comprehensive Health Insurance");
        insurancePolicy.setPolicyStatus(PolicyStatus.ACTIVE);
        insurancePolicy.setCoverageStartDate(LocalDate.of(2024, 1, 1));
        insurancePolicy.setCoverageEndDate(LocalDate.of(2025, 12, 31));
        insurancePolicy.setCreatedAt(LocalDateTime.now());
        insurancePolicy.setUpdatedAt(LocalDateTime.now());

        when(insurancePolicyRepository.findById(policyId)).thenReturn(Optional.of(insurancePolicy));

        InsurancePolicyDto resultDto = insurancePolicyService.getInsurancePolicyById(policyId);

        assertNotNull(resultDto);
        assertEquals(insurancePolicy.getPolicyId(), resultDto.getPolicyId());
    }

    @Test
    void testGetInsurancePolicyByIdNotFound() {
        Long policyId = 1L;
        when(insurancePolicyRepository.findById(policyId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> insurancePolicyService.getInsurancePolicyById(policyId));
    }

    @Test
    void testUpdateInsurancePolicy() {
        Long policyId = 1L;


        InsurancePolicy existingPolicy = new InsurancePolicy();

        existingPolicy.setPolicyId(1L);
        existingPolicy.setPolicyName("Comprehensive Health Insurance");
        existingPolicy.setPolicyStatus(PolicyStatus.ACTIVE);
        existingPolicy.setCoverageStartDate(LocalDate.of(2024, 1, 1));
        existingPolicy.setCoverageEndDate(LocalDate.of(2025, 12, 31));
        existingPolicy.setCreatedAt(LocalDateTime.now());
        existingPolicy.setUpdatedAt(LocalDateTime.now());

        when(insurancePolicyRepository.findById(policyId)).thenReturn(Optional.of(new InsurancePolicy()));

        when(insurancePolicyRepository.save(any(InsurancePolicy.class))).thenReturn(existingPolicy);

        InsurancePolicyDto inputDto = new InsurancePolicyDto();
        inputDto.setPolicyName("Comprehensive Health Insurance");
        inputDto.setPolicyStatus(PolicyStatus.ACTIVE);
        inputDto.setCoverageStartDate(LocalDate.of(2024, 1, 1));
        inputDto.setCoverageEndDate(LocalDate.of(2025, 12, 31));

        InsurancePolicyDto resultDto = insurancePolicyService.updateInsurancePolicy(policyId, inputDto);

        assertNotNull(resultDto);
        assertEquals(inputDto.getPolicyName(), resultDto.getPolicyName());
        assertEquals(inputDto.getPolicyStatus(), resultDto.getPolicyStatus());
        verify(insurancePolicyRepository, times(1)).save(any(InsurancePolicy.class));
    }

}
