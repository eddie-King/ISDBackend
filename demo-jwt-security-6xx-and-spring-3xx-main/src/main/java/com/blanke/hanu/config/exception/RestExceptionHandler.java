package com.blanke.hanu.config.exception;


import com.blanke.hanu.config.enums.ApiResponseCode;
import com.blanke.hanu.util.BodyResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        String errorFields = errors.get(0).getDefaultMessage();
        logger.error("MethodArgumentNotValidException: " + ex.getClass().getName() + " " + ex.getMessage());
        BodyResponseDTO bodyResponse = new BodyResponseDTO(ApiResponseCode.INPUT_PARAMS_INVALID.getCode(), errorFields, null);
        return new ResponseEntity<>(bodyResponse, ApiResponseCode.INPUT_PARAMS_INVALID.getStatus());
    }
}