package com.hms.backend.patient.controller;

import com.hms.backend.patient.dto.CreatePatientRequest;
import com.hms.backend.patient.dto.PatientResponse;
import com.hms.backend.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse createPatient(
            @RequestParam Long userId,
            @Valid @RequestBody CreatePatientRequest request) {

        return patientService.createPatient(userId, request);
    }
}