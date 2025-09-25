package com.health.patientservice.patient.dto;

public record PatientResponse(
        String id,
        String name,
        String email,
        String address,
        String dateOfBirth

) {
}
