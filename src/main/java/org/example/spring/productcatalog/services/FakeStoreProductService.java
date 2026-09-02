package org.example.spring.productcatalog.services;

import org.example.spring.productcatalog.Models.CashFlow;
import org.example.spring.productcatalog.Models.News;
import org.example.spring.productcatalog.Models.Product;
import org.example.spring.productcatalog.dto.FakeStoreProductDatadto;
import org.example.spring.productcatalog.dto.RealTimeCashFlowResultdto;
import org.example.spring.productcatalog.dto.RealTimeNewsResultdto;
import org.example.spring.productcatalog.exception.ProductNotFoundException;
import org.example.spring.productcatalog.exception.ResponseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product getProductById(Long id) {
        String url="https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDatadto> response=restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                FakeStoreProductDatadto.class,
                id
        );
        if(response==null || response.getBody()==null){
            throw new ProductNotFoundException("Not the appropriate Input / No product found with given ID.");
        }
        FakeStoreProductDatadto resultDto=response.getBody();
        return modelMapper.map(resultDto,Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        String url="https://fakestoreapi.com/products";
        ResponseEntity<List<FakeStoreProductDatadto>> response=restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDatadto>>() {}
        );
        if(response==null||response.getBody()==null){
            return List.of();
        }
        List<FakeStoreProductDatadto> resultDto=response.getBody();
        return resultDto.stream().map(dto->{
           Product product=new Product();
           product.setId(dto.getId());
           product.setTitle(dto.getTitle());
           product.setDescription(dto.getDescription());
           product.setCategory(dto.getCategory());
           product.setPrice(dto.getPrice());
           product.setImage(dto.getImage());
           return product;
        }).toList();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProductById(Product product, Long id) {
        String url="https://fakestoreapi.com/products/{id}";
        ResponseEntity<Product> oldProduct=restTemplate.getForEntity(url,Product.class,id);
        if(oldProduct==null||oldProduct.getBody()==null) {
            throw new ProductNotFoundException("No product Found to update with the ID");
        }
    }

    @Override
    public List<News> getStockNews(String stockSymbol) {
        String url = UriComponentsBuilder.fromUriString("https://real-time-finance-data.p.rapidapi.com/stock-news")
                .queryParam("symbol", stockSymbol + ":NASDAQ")
                .queryParam("language", "en")
                .toUriString();

        RealTimeNewsResultdto resultdto = restTemplate.getForObject(url, RealTimeNewsResultdto.class);
        if (resultdto != null && resultdto.getData() != null && resultdto.getData().getNews() != null) {
            return resultdto.getData().getNews();
        }
        return List.of();
    }

    @Override
    public List<CashFlow> getCashFlow(String stockSymbol) {
        String url = UriComponentsBuilder.fromUriString("https://real-time-finance-data.p.rapidapi.com/company-cash-flow")
                .queryParam("symbol", stockSymbol + ":NASDAQ")
                .queryParam("period", "QUARTERLY")
                .queryParam("language", "en")
                .toUriString();

        RealTimeCashFlowResultdto resultdto = restTemplate.getForObject(url, RealTimeCashFlowResultdto.class);
        if (resultdto == null || resultdto.getData() == null || resultdto.getData().getCashFlows() == null) {
            return List.of();
        }
        return resultdto.getData().getCashFlows();
    }
}
