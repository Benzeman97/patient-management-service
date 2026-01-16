package com.monitoredrx.patient.service;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.entity.Patient;
import com.monitoredrx.patient.exception.ApplicationException;
import com.monitoredrx.patient.exception.DataNotFoundException;
import com.monitoredrx.patient.repository.PatientRepository;
import com.monitoredrx.patient.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PatientServiceImplTest")
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Test
    @DisplayName("getAllPatientsTest")
    public void getAllPatientsTest() {

        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());

        when(patientRepository.findAll()).thenReturn(List.of(patient));

        List<PatientResponse> result = patientService.getAllPatients();

        assertEquals(1, result.size());
        verify(patientRepository).findAll();
    }

    @Test
    @DisplayName("getPatientsByPageTest")
    public void getPatientsByPageTest() {
        Pageable pageable = PageRequest.of(0, 16);

        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());

        Page<Patient> page = new PageImpl<>(List.of(patient));

        when(patientRepository.findAll(pageable)).thenReturn(page);

        Page<PatientResponse> result = patientService.getPatientsByPage(pageable);

        assertEquals(1, result.getTotalElements());
        verify(patientRepository).findAll(pageable);

    }

    @Test
    @DisplayName("getPatientByIdTest")
    public void getPatientByIdTest() {

        UUID id = UUID.randomUUID();
        Patient patient = new Patient();
        patient.setId(id);

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        PatientResponse response = patientService.getPatientById(id.toString());

        verify(patientRepository).findById(id);
    }

    @Test
    @DisplayName("createPatientTest")
    public void createPatientTest() {

        PatientRequest request = new PatientRequest();

        Patient savedPatient = new Patient();
        savedPatient.setId(UUID.randomUUID());

        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        PatientResponse response = patientService.createPatient(request);

        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    @DisplayName("updatePatientTest")
    public void updatePatientTest() {
        UUID id = UUID.randomUUID();

        Patient existing = new Patient();
        existing.setId(id);

        when(patientRepository.findById(id)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any(Patient.class))).thenReturn(existing);

        PatientResponse response =
                patientService.updatePatient(id.toString(), new PatientRequest());

        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    @DisplayName("deletePatientTest")
    public void deletePatientTest() {
        UUID id = UUID.randomUUID();
        Patient patient = new Patient();
        patient.setId(id);

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        patientService.deletePatient(id.toString());

        verify(patientRepository).delete(patient);
    }

    @Test
    @DisplayName("getPatientByIdTest_invalidUUID")
    public void getPatientByIdTest_invalidUUID() {
        ApplicationException ex = assertThrows(
                ApplicationException.class,
                () -> patientService.getPatientById("invalid-uuid")
        );

        assertEquals(100008, ex.getErrorCode());
    }

    @Test
    @DisplayName("getPatientByIdTest_notFound")
    public void getPatientByIdTest_notFound() {
        UUID id = UUID.randomUUID();

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> patientService.getPatientById(id.toString())
        );
    }

    }
