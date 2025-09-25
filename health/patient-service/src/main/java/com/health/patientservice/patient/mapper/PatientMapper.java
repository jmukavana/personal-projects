package com.health.patientservice.patient.mapper;

import com.health.patientservice.patient.Patient;
import com.health.patientservice.patient.dto.PatientRequest;
import com.health.patientservice.patient.dto.PatientResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PatientMapper {
    public PatientResponse toResponse(Patient patients) {
        return new PatientResponse(
                patients.getId().toString(),
                patients.getName(),
                patients.getEmail(),
                patients.getAddress(),
                patients.getDateOfBirth().toString()
        );

    }

    public Patient fromRequest(PatientRequest request) {
        var patient = new Patient();
        patient.setEmail(request.email());
        patient.setAddress(request.address());
        patient.setDateOfBirth(LocalDate.parse(request.dateOfBirth()));
        patient.setName(request.name());
        patient.setRegisteredDate(LocalDate.parse(request.registeredDate()));
        return patient;
    }
}
