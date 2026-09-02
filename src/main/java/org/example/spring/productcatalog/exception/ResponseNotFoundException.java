package org.example.spring.productcatalog.exception;

import lombok.Getter;


public class ResponseNotFoundException extends RuntimeException{
    private String message;
    public ResponseNotFoundException(String message){
        this.message=message;
    }
}
