package com.hms.backend.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
}