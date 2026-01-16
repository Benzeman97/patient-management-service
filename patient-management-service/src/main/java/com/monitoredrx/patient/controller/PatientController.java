package com.monitoredrx.patient.controller;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.service.PatientService;
import jakarta.validation.Valid;
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
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<PatientResponse>> getPatients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "16") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(patientService.getPatientsByPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable @NotBlank(message = "{patient.id.required}") String id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.createPatient(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable @NotBlank(message = "{patient.id.required}") String id,
                                                         @Valid @RequestBody PatientRequest request){
        return ResponseEntity.ok(patientService.updatePatient(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable @NotBlank(message = "{patient.id.required}") String id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
