package com.ssmph.service;

import java.util.List;
import java.util.Optional;

import com.ssmph.model.Product;

/**
 * ProductService
 */
public interface ProductService {

    Optional<Product> getProductById(long id);

    Product saveProduct(Product product);

    void deleteProduct(long id);

    List<Product> listProducts();
    
}