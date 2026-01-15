package com.monitoredrx.patient.service;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

  List<PatientResponse> getAllPatients();
  List<PatientResponse> getPatientsByPage(Pageable pageable);
  PatientResponse getPatientById(String id);
  PatientResponse createPatient(PatientRequest request);
  PatientResponse updatePatient(String id, PatientRequest request);
  void deletePatient(String id);

}
