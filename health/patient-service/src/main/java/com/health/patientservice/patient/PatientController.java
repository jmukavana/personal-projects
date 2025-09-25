package com.health.patientservice.patient;

import com.health.patientservice.patient.dto.PatientRequest;
import com.health.patientservice.patient.dto.PatientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")  // Added versioning
@Tag(name = "Patient", description = "Patient management API for healthcare system")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(
            summary = "Get all patients",
            description = "Retrieves a list of all patients in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all patients",
                    content = @Content(schema = @Schema(implementation = PatientResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> response = patientService.getAllPatients();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get patient by ID",
            description = "Retrieves a specific patient by their unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the patient",
                    content = @Content(schema = @Schema(implementation = PatientResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid patient ID format"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(
            @Parameter(description = "Unique identifier of the patient", required = true)
            @PathVariable UUID id
    ) {
        PatientResponse response = patientService.getPatient(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create a new patient",
            description = "Creates a new patient record in the healthcare system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Patient created successfully",
                    content = @Content(schema = @Schema(implementation = PatientResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid patient data provided"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Patient already exists"
            )
    })
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(
            @Parameter(description = "Patient information to be created", required = true)
            @Valid @RequestBody PatientRequest request
    ) {
        PatientResponse response = patientService.createPatient(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update patient information",
            description = "Updates an existing patient's information"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patient updated successfully",
                    content = @Content(schema = @Schema(implementation = PatientResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid patient data provided"
            )
    })
    @PutMapping("/{id}")  // Fixed: Removed the extra bracket "]"
    public ResponseEntity<PatientResponse> updatePatient(
            @Parameter(description = "Unique identifier of the patient", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Updated patient information", required = true)
            @Valid @RequestBody PatientUpdateRequest request  // Added @Valid annotation
    ) {
        PatientResponse response = patientService.updatePatient(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a patient",
            description = "Removes a patient record from the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Patient deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @Parameter(description = "Unique identifier of the patient", required = true)
            @PathVariable UUID id
    ) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}