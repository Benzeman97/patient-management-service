package com.monitoredrx.patient.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_UUID(100008, "Invalid UUID format"),
    MAPPING_ERROR(100009, "Error mapping patient"),
    PATIENT_NOT_FOUND(100010, "Patient not found");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
