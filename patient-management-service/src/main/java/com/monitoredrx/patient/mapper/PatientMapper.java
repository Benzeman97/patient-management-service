package com.monitoredrx.patient.mapper;

import com.monitoredrx.patient.dto.request.PatientRequest;
import com.monitoredrx.patient.dto.response.PatientResponse;
import com.monitoredrx.patient.entity.Patient;

public class PatientMapper {

    public static PatientResponse toResponse(Patient entity) {
        return PatientResponse.builder()
                .id(entity.getId().toString())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .zipCode(entity.getZipCode())
                .phoneNumber(entity.getPhoneNumber())
                .build();
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
