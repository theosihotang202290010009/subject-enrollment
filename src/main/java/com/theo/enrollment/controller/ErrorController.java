package com.theo.enrollment.controller;

import com.theo.enrollment.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<CommonResponse<?>> responseStatusExceptionHandler(ResponseStatusException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(e.getStatusCode().value())
                .message(e.getReason())
                .build();

        return ResponseEntity.status(e.getStatusCode())
                .body(response);
    }
}
