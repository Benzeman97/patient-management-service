package com.monitoredrx.patient.controller;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/patients")
@Validated
@Tag(name = "Patients", description = "APIs for managing patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved patient list")
    public ResponseEntity<List<PatientResponse>> getPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/pages")
    @Operation(summary = "Get patients with pagination", description = "Retrieve patients with page number and size")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paged list"),
            @ApiResponse(responseCode = "400", description = "Invalid page or size parameters")
    })
    public ResponseEntity<Page<PatientResponse>> getPatients(
            @Parameter(description = "Page number (0-based)", example = "0")
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "{error.invalid.page.number}") int page,
            @Parameter(description = "Page size", example = "16")
            @RequestParam(defaultValue = "16") @Min(value = 1, message = "{error.invalid.size}") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(patientService.getPatientsByPage(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Retrieve a patient by their unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    public ResponseEntity<PatientResponse> getPatient(
            @PathVariable @NotBlank(message = "{patient.id.required}") String id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new patient", description = "Add a new patient record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Patient created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PatientResponse> createPatient(
            @Valid @RequestBody PatientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.createPatient(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient", description = "Update patient details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable @NotBlank(message = "{patient.id.required}") String id,
            @Valid @RequestBody PatientRequest request){
        return ResponseEntity.ok(patientService.updatePatient(id,request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Delete a patient by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Patient deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    public ResponseEntity<Void> deletePatient(
            @PathVariable @NotBlank(message = "{patient.id.required}") String id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
