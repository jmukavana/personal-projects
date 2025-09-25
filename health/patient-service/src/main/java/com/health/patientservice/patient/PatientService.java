package com.health.patientservice.patient;

import com.health.patientservice.grpc.BillingServiceGrpcClient;
import com.health.patientservice.kafka.KafkaProducer;
import com.health.patientservice.patient.dto.PatientRequest;
import com.health.patientservice.patient.dto.PatientResponse;
import com.health.patientservice.patient.exception.EmailAlreadyExistsException;
import com.health.patientservice.patient.exception.PatientNotFoundException;
import com.health.patientservice.patient.mapper.PatientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private  final PatientMapper mapper;
    private  final BillingServiceGrpcClient grpcClient;
    private  final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, PatientMapper mapper, BillingServiceGrpcClient grpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
        this.grpcClient = grpcClient;
        this.kafkaProducer = kafkaProducer;
    }


    public List<PatientResponse> getAllPatients() {
        var patients = patientRepository.findAll();
        return patients.stream()
                .map(mapper::toResponse)
                .toList();
    }
    public PatientResponse getPatient(UUID id) {
        var patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + id + " not found")
        );
        return mapper.toResponse(patient);
    }

    public PatientResponse createPatient(PatientRequest request) {
        if (patientRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("A patient with email " + request.email() + " already exists");
        }

        var patient= mapper.fromRequest(request);
        var savedPatient = patientRepository.save(patient);
        grpcClient.createBillingAccount(
                savedPatient.getId().toString(),
                savedPatient.getName(),
                savedPatient.getEmail()
        );
        kafkaProducer.sendEvent(savedPatient);
        return mapper.toResponse(savedPatient);
    }

    public PatientResponse updatePatient(UUID id, PatientUpdateRequest request) {
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new PatientNotFoundException("Patient with id "+id+" not found")
        );
        if (patientRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("A patient with email " + request.email() + " already exists");
        }
        patient.setName(request.name());
        patient.setEmail(request.email());
        patient.setAddress(request.address());
        patient.setDateOfBirth(LocalDate.parse(request.dateOfBirth()));
        var updatedPatient = patientRepository.save(patient);
        return mapper.toResponse(updatedPatient);


    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
