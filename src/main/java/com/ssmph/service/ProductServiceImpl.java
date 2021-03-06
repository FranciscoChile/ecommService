package com.ssmph.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssmph.model.Product;
import com.ssmph.repository.ProductRepository;

/**
 * ProductServiceImpl
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);    
    }

    @Transactional
    public List<Product> listProducts() throws DataAccessException {
        return productRepository.findAll();
    }

    @Transactional
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id) ;
    }

    @Transactional
    public void deleteProduct(long id) {
    	productRepository.deleteById(id);
    }
    
}