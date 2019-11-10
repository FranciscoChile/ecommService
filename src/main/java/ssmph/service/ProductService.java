package ssmph.service;

import java.util.List;
import java.util.Optional;

import ssmph.model.Product;

/**
 * ProductService
 */
public interface ProductService {

    Optional<Product> getProductById(long id);

    void saveProduct(Product product);

    void deleteProduct(long id);

    List<Product> listProducts();
    
}