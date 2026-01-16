package com.monitoredrx.patient.exception;

import com.monitoredrx.patient.dto.response.ErrorMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorMessageResponse>  handleApplicationException(ApplicationException ex, Locale locale){
          ErrorMessageResponse errorMessage = new ErrorMessageResponse(
                  ex.getErrorCode(),messageSource.getMessage(ex.getMessage(),null,locale)
          );
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    public ResponseEntity<ErrorMessageResponse> 
}
