package com.monitoredrx.patient.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response payload for errors")
public class ErrorMessageResponse implements Serializable {

    @Schema(description = "HTTP status or custom error code", example = "404")
    private int errorCode;
    @Schema(description = "Detailed error message", example = "Patient not found")
    private String errorMessage;
}
