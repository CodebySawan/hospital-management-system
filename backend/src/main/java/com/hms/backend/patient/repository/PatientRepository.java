package com.hms.backend.patient.repository;

import com.hms.backend.auth.entity.User;
import com.hms.backend.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByUser(User user);

    Optional<Patient> findByPhone(String phone);

    boolean existsByPhone(String phone);
}