package com.health.patientservice.patient;

public record PatientUpdateRequest(
        String name,
        String address,
        String dateOfBirth,
        String email
) {
}
