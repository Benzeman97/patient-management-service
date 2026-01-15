package com.monitoredrx.patient.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest implements Serializable {

    @Email(message = "{patient.email.format.invalid}")
    @NotBlank(message = "{patient.email.required}")
    private String email;
    @NotBlank(message = "{patient.firstName.required}")
    private String firstName;
    @NotBlank(message = "{patient.lastName.required}")
    private String lastName;
    @NotBlank(message = "{patient.address.required}")
    private String address;
    @NotBlank(message = "{patient.city.required}")
    private String city;
    @NotBlank(message = "{patient.state.required}")
    private String state;
    @NotBlank(message = "{patient.zipCode.required}")
    private String zipCode;
    @NotBlank(message = "{patient.phoneNumber.required}")
    private String phoneNumber;

}
