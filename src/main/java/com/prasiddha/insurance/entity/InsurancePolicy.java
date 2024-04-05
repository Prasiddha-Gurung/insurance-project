package com.prasiddha.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurance_policies")
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyIdentifier;

    @Column(nullable = false, unique = true)
    private String policyName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PolicyStatus policyStatus; // ACTIVE, INACTIVE

    @Column(nullable = false)
    private LocalDate beginningOfCoverage;

    @Column(nullable = false)
    private LocalDate endOfCoverage;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateOfCreation;

    @Column(nullable = false)
    private LocalDateTime dateOfLastUpdate;

    // Getters and Setters
}

