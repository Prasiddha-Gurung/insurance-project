package com.prasiddha.insurance.repository;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.entity.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
}

