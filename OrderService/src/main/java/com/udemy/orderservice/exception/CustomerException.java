package com.udemy.orderservice.exception;

public class CustomerException extends RuntimeException{
    private String errorCode;
    private int status;
    public CustomerException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
