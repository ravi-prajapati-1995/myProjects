package com.hotel.reservation.util;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hotel.reservation.util.customException.ReservationAlreadyExistsException;
import com.hotel.reservation.util.customException.ReservationNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getConstraintViolations().stream()
        .map(constraintViolation -> constraintViolation.getPropertyPath().toString() + " - " + constraintViolation.getMessage())
        .collect(Collectors.joining(", ")));
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", ")));
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(ReservationAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleReservationAlreadyExistsException(ReservationAlreadyExistsException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setStatus(HttpStatus.CONFLICT.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ReservationNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleReservationNotFoundException(ReservationNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}

