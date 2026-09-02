package org.example.spring.productcatalog.controllers;

import org.example.spring.productcatalog.Models.*;
import org.example.spring.productcatalog.ProductCatalogApplication;
import org.example.spring.productcatalog.dto.FakeStoreProductDatadto;
import org.example.spring.productcatalog.dto.FakeStoreProductdto;
import org.example.spring.productcatalog.dto.Productdto;
import org.example.spring.productcatalog.services.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long productid){
        return productService.getProductById(productid);
    }
    @PostMapping("/products")
    public Productdto createProduct(@RequestBody Productdto productdto){
      return null;
    }
    @PutMapping("/products/{id}")
    public Product updateProductById(@RequestBody Product product,@PathVariable("id") Long productid){
        return productService.updateProductById(product,productid);
    }
    @GetMapping("/company/stockNews")
    public List<News> getStockNews(@RequestParam(value = "symbol") String stock_symbol) {
        if (stock_symbol == null || stock_symbol.isBlank()) {
            return List.of();
        }
        return productService.getStockNews(stock_symbol);
    }
    @GetMapping("/company/cashFlow")
    public List<CashFlow> getCashFlow(@RequestParam(value="symbol") String stock_symbol){
        if(stock_symbol==null||stock_symbol.isBlank()){
            return List.of();
        }
        return productService.getCashFlow(stock_symbol);
    }

}
