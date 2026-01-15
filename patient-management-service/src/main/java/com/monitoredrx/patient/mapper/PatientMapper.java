package com.monitoredrx.patient.mapper;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.entity.Patient;

public class PatientMapper {

    public static PatientResponse toResponse(Patient entity){
        PatientResponse response = new PatientResponse();
        response.setId(entity.getId().toString());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setAddress(entity.getAddress());
        response.setCity(entity.getCity());
        response.setState(entity.getState());
        response.setZipCode(entity.getZipCode());
        response.setPhoneNumber(entity.getPhoneNumber());
        return response;
    }

    public static Patient toEntity(PatientRequest request){
        Patient entity = new Patient();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setZipCode(request.getZipCode());
        entity.setPhoneNumber(request.getPhoneNumber());
        return entity;
    }

    public static Patient toEntity(PatientRequest request, Patient entity){
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setZipCode(request.getZipCode());
        entity.setPhoneNumber(request.getPhoneNumber());
        return entity;
    }
}
