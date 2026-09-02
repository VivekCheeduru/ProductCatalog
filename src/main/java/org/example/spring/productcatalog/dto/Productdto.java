package org.example.spring.productcatalog.dto;

import lombok.Data;

@Data
public class Productdto {
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
}
