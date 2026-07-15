package com.hms.backend.patient.service;

import com.hms.backend.auth.entity.User;
import com.hms.backend.auth.repository.UserRepository;
import com.hms.backend.common.exception.ResourceAlreadyExistsException;
import com.hms.backend.common.exception.ResourceNotFoundException;
import com.hms.backend.patient.dto.CreatePatientRequest;
import com.hms.backend.patient.dto.PatientResponse;
import com.hms.backend.patient.entity.Patient;
import com.hms.backend.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository,
                          UserRepository userRepository) {

        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public PatientResponse createPatient(Long userId,
                                         CreatePatientRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (patientRepository.findByUser(user).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Patient profile already exists");
        }

        if (patientRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException(
                    "Phone number already exists");
        }

        Patient patient = new Patient();

        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setAddress(request.getAddress());
        patient.setUser(user);

        patientRepository.save(patient);

        return PatientResponse.builder()
                .id(patient.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(patient.getAge())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .address(patient.getAddress())
                .build();
    }

}