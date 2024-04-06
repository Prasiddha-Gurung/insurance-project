package com.prasiddha.insurance.controller;

import com.prasiddha.insurance.dto.InsurancePolicyDto;
import com.prasiddha.insurance.dto.ResponseDTO;
import com.prasiddha.insurance.exception.ResourceNotFoundException;
import com.prasiddha.insurance.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance-policy")
@CrossOrigin(origins = "http://localhost:5173")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @PostMapping
    public ResponseEntity<Object> createInsurancePolicy(@RequestBody InsurancePolicyDto insurancePolicyDto)
    {
//        InsurancePolicyDto savedInsurancePolicy = insurancePolicyService.createInsurancePolicy(insurancePolicyDto);
//        return  new ResponseEntity<>(savedInsurancePolicy, HttpStatus.CREATED);
        try {
            InsurancePolicyDto savedInsurancePolicy = insurancePolicyService.createInsurancePolicy(insurancePolicyDto);
            return new ResponseEntity<>(savedInsurancePolicy, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error and return a generic error response or a custom error object if needed
            return new ResponseEntity<>("An error occurred while creating the insurance policy.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<InsurancePolicyDto>> getAllInsurancePolicies()
    {
        List<InsurancePolicyDto> allInsurancePolicies = insurancePolicyService.getAllInsurancePolicies();
        return ResponseEntity.ok(allInsurancePolicies);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<ResponseDTO<InsurancePolicyDto>> getAllInsurancePolicies(@PathVariable Long policyId)
    {
        try {
            InsurancePolicyDto insurancePolicy = insurancePolicyService.getInsurancePolicyById(policyId);
            return ResponseEntity.ok(new ResponseDTO<InsurancePolicyDto>(insurancePolicy));
        }
        catch (ResourceNotFoundException e)
        {
            return new ResponseEntity<>(new ResponseDTO<InsurancePolicyDto>(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{policyId}")
    public ResponseEntity<ResponseDTO<InsurancePolicyDto>> updateInsurancePolicy(@PathVariable Long policyId, @RequestBody InsurancePolicyDto insurancePolicyDto)
    {
        try {
            InsurancePolicyDto insurancePolicy = insurancePolicyService.updateInsurancePolicy(policyId, insurancePolicyDto);
            return ResponseEntity.ok(new ResponseDTO<InsurancePolicyDto>(insurancePolicy));
        }
        catch (ResourceNotFoundException e)
        {
            return new ResponseEntity<>(new ResponseDTO<InsurancePolicyDto>(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }


}
