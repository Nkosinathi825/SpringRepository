package com.registrationandloginspring.exception;

import com.registrationandloginspring.model.dto.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ValidationError>> handleValidationErrors(MethodArgumentNotValidException exception) {

    List<ValidationError> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> new ValidationError(
                    error.getField(),
                    error.getDefaultMessage()
            )).toList();

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
  }

}
