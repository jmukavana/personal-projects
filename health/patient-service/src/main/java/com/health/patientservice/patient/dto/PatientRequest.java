package com.health.patientservice.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
        @NotBlank(message = "Address is required") String address,
        @NotBlank(message = "Date of birth is required") String dateOfBirth,
        @NotBlank(message = "Registered date is required") String registeredDate
) {
}
