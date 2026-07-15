package com.hms.backend.patient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatientSummaryResponse {

    private Long id;

    private String name;

    private Integer age;

    private String gender;

    private String phone;
}