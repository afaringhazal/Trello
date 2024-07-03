package com.trello.exception;


import com.trello.global.ErrorResponse;

public class ServiceException extends RuntimeException {

    private ErrorResponse errorResponse;

    public ServiceException() {
    }

    public ServiceException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse(){
        return this.errorResponse;
    }
}