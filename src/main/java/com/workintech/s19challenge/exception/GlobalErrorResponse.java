package com.workintech.s19challenge.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponse {

    private int status;
    private String message;
    private long timestamp;



}
