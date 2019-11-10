package ssmph.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Transactional
    public void saveProduct(Product product) {
    	productRepository.save(product);
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