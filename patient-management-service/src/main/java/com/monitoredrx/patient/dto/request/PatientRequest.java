package com.monitoredrx.patient.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for creating or updating a patient")
public class PatientRequest implements Serializable {

    @Schema(description = "Email of the patient", example = "nafaz@gmail.com", required = true)
    @Email(message = "{patient.email.format.invalid}")
    @NotBlank(message = "{patient.email.required}")
    private String email;

    @Schema(description = "First name of the patient", example = "Nafaz", required = true)
    @NotBlank(message = "{patient.firstName.required}")
    private String firstName;

    @Schema(description = "Last name of the patient", example = "Nafeez", required = true)
    @NotBlank(message = "{patient.lastName.required}")
    private String lastName;

    @Schema(description = "Address of the patient", example = "123 Main Street", required = true)
    @NotBlank(message = "{patient.address.required}")
    private String address;

    @Schema(description = "City of the patient", example = "New York", required = true)
    @NotBlank(message = "{patient.city.required}")
    private String city;

    @Schema(description = "State of the patient", example = "NY", required = true)
    @NotBlank(message = "{patient.state.required}")
    private String state;

    @Schema(description = "ZIP code of the patient", example = "10001", required = true)
    @NotBlank(message = "{patient.zipCode.required}")
    private String zipCode;

    @Schema(description = "Phone number of the patient", example = "+1-555-1234567", required = true)
    @NotBlank(message = "{patient.phoneNumber.required}")
    private String phoneNumber;

}
