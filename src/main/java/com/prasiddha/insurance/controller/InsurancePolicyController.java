package com.prasiddha.insurance.controller;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.service.InsurancePolicyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance-policy")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @PostMapping
    public ResponseEntity<InsurancePolicyDto> createInsurancePolicy(@RequestBody InsurancePolicyDto insurancePolicyDto)
    {
        InsurancePolicyDto savedInsurancePolicy = insurancePolicyService.createInsurancePolicy(insurancePolicyDto);
        return  new ResponseEntity<>(savedInsurancePolicy, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InsurancePolicyDto>> getAllInsurancePolicies()
    {
        List<InsurancePolicyDto> allInsurancePolicies = insurancePolicyService.getAllInsurancePolicies();
        return  new ResponseEntity<>(allInsurancePolicies, HttpStatus.CREATED);
    }

}