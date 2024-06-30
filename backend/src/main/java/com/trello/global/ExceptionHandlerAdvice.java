package com.trello.global;

import com.trello.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> serviceExceptionHandler(ServiceException exception) {
        log.info(" error code is  : " + exception.getErrorResponse().getErrorCode());
        log.warn(" exception message : " + exception.getErrorResponse().getMessage());

        return new ResponseEntity<>(exception.getErrorResponse(), exception.getErrorResponse().getStatusCode());
    }
}