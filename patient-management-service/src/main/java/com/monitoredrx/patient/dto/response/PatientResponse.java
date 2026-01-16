package com.monitoredrx.patient.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response payload for patient data")
public class PatientResponse implements Serializable {

    @Schema(description = "Unique ID of the patient", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;
    @Schema(description = "Email of the patient", example = "nafaz@gmail.com")
    private String email;
    @Schema(description = "First name of the patient", example = "Nafaz")
    private String firstName;
    @Schema(description = "Last name of the patient", example = "Nafeez")
    private String lastName;
    @Schema(description = "Address of the patient", example = "123 Main Street")
    private String address;
    @Schema(description = "City of the patient", example = "New York")
    private String city;
    @Schema(description = "State of the patient", example = "NY")
    private String state;
    @Schema(description = "ZIP code of the patient", example = "10001")
    private String zipCode;
    @Schema(description = "Phone number of the patient", example = "+1-555-1234567")
    private String phoneNumber;
}
