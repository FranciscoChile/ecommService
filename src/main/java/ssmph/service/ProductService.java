package ssmph.service;

import java.util.List;
import java.util.Optional;

import ssmph.model.Product;

/**
 * ProductService
 */
public interface ProductService {

    Optional<Product> getProductById(long id);

    Boolean saveProduct(Product product);

    Boolean updateProduct(Product product);

    Boolean deleteProduct(long id);

    List<Product> findAll();
    
}