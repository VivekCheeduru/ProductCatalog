package org.example.spring.productcatalog.services;

import org.example.spring.productcatalog.Models.CashFlow;
import org.example.spring.productcatalog.Models.News;
import org.example.spring.productcatalog.Models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Product updateProductById(Product product, Long id);

    List<News> getStockNews(String stockSymbol);

    List<CashFlow> getCashFlow(String stockSymbol);
}
