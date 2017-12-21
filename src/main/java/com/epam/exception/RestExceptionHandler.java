package com.epam.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.epam.controller")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<Object> handleBadRequest(final UserException ex, final WebRequest request) {
    final String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleBadRequest(final NotFoundException ex,
      final WebRequest request) {
    final String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }


}