package com.workintech.s19challenge.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> handleException(GlobalException globalException){
        log.error("Global exception occured! Exception details: " + globalException.getMessage());
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(globalException.getHttpStatus().value(),
                globalException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,globalException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse>handleException(Exception exception){
GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
return new ResponseEntity<>(globalErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
