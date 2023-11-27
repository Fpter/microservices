package com.udemy.productservice.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException {
    private String errorCode;
    public ProductServiceCustomException(String message, String code){
        super(message);
        this.errorCode = code;
    }

}
