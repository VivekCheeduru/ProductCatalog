package org.example.spring.productcatalog.Models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
}
