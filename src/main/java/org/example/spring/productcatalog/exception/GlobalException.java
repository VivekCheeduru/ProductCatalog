package org.example.spring.productcatalog.exception;

import org.example.spring.productcatalog.Models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> illegalArgument(RuntimeException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Incorrect ID kindly check");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> handleProductNotFound(ProductNotFoundException e){
        return ResponseEntity.ok()
                .body(e.getMessage());
    }
    @ExceptionHandler(ResponseNotFoundException.class)
    ResponseEntity<String> responseNotFound(ResponseNotFoundException e){
        return ResponseEntity.ok().body(e.getMessage());
    }

}
