package com.monitoredrx.patient.exception;

import com.monitoredrx.patient.dto.response.ErrorMessageResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.stream.Collectors;

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

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleDataNotFoundException(DataNotFoundException ex, Locale locale){
        ErrorMessageResponse errorMessage = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                messageSource.getMessage(ex.getMessage(),null,locale)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessageResponse> handleValidationException(ConstraintViolationException ex, Locale locale) {
        ErrorMessageResponse errorMessage = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                messageSource.getMessage(ex.getMessage(),null,locale)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        ErrorMessageResponse error = new ErrorMessageResponse(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> handleGenericException(Exception ex, Locale locale){
        ErrorMessageResponse errorMessage = new ErrorMessageResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                messageSource.getMessage("error.internal.server",null,locale)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
