package ssmph.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmph.model.Product;
import ssmph.repository.ProductRepository;

/**
 * ProductServiceImpl
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Boolean saveProduct(Product product) {          
        try {
            productRepository.save(product);
            return true;
         } catch (Exception e) {
            return false;
         }     
    }
    
}