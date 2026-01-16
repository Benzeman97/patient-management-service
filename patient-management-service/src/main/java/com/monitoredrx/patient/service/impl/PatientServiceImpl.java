package com.monitoredrx.patient.service.impl;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.entity.Patient;
import com.monitoredrx.patient.mapper.PatientMapper;
import com.monitoredrx.patient.repository.PatientRepository;
import com.monitoredrx.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    final private static Logger LOGGER = LogManager.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "PATIENTS", key = "#root.methodName")
    public List<PatientResponse> getAllPatients() {
        LOGGER.info("Fetching all patients");
        return patientRepository.findAll()
                .stream().map(PatientMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "PATIENTS", key = "#root.methodName + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<PatientResponse> getPatientsByPage(Pageable pageable) {
        LOGGER.info("Fetching patients - Page: {}, Size: {}", pageable.getPageNumber(), pageable.getPageSize());
        return patientRepository.findAll(pageable)
                .map(PatientMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "PATIENTS", key = "#root.methodName + '-' + #patientId")
    public PatientResponse getPatientById(String patientId) {

        UUID id;
        try {
            id = UUID.fromString(patientId);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid UUID format: {}", patientId);
            throw new ApplicationException(100008, "error.invalid.uuid.format");
        }

        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->{
                    LOGGER.error("Patient with ID {} not found", patientId);
                    throw new DataNotFoundException("error.patient.not.found");});
        LOGGER.info("Fetching Patient for Patient ID {}", patientId);
        return PatientMapper.toResponse(patient);
    }

    @Override
    @Transactional
    @CacheEvict(value = "PATIENTS", allEntries = true)
    public PatientResponse createPatient(PatientRequest request) {

        Patient patient;
        try {
            patient = PatientMapper.toEntity(request);
        } catch (Exception e) {
            LOGGER.error("Error mapping patient request to entity: {}", e.getMessage(), e);
            throw new ApplicationException(100009, "error.mapping.patient");
        }

        Patient savedPatient = patientRepository.save(patient);

        LOGGER.info("New patient has been added with patient id {}", savedPatient.getId());
        return PatientMapper.toResponse(savedPatient);
    }

    @Override
    @Transactional
    @CacheEvict(value = "PATIENTS", allEntries = true)
    public PatientResponse updatePatient(String patientId, PatientRequest request) {
        UUID id;
        try {
            id = UUID.fromString(patientId);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid UUID format: {}", patientId);
            throw new ApplicationException(100008, "error.invalid.uuid.format");
        }

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Patient with ID {} not found", patientId);
                    return new DataNotFoundException("error.patient.not.found");
                });

        try {
            patient = PatientMapper.toEntity(request, patient);
        } catch (Exception e) {
            LOGGER.error("Error mapping patient request to entity for ID {}: {}", patientId, e.getMessage(), e);
            throw new ApplicationException(100009, "error.mapping.patient");
        }

        patient = patientRepository.save(patient);

        LOGGER.info("Patient updated successfully with ID {}", patientId);
        return PatientMapper.toResponse(patient);
    }

    @Override
    @Transactional
    @CacheEvict(value = "PATIENTS", allEntries = true)
    public void deletePatient(String patientId) {
        UUID id;
        try {
            id = UUID.fromString(patientId);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid UUID format: {}", patientId);
            throw new ApplicationException(100008, "error.invalid.uuid.format");
        }

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Patient with ID {} not found", patientId);
                    throw new DataNotFoundException("error.patient.not.found");
                });

        patientRepository.delete(patient);
        LOGGER.info("Patient deleted successfully with ID {}", patientId);

    }
}
