package org.example.spring.productcatalog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {

    private String message;
    public ProductNotFoundException(String message) {
        this.message=message;
    }
}
