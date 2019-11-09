package ssmph.service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> findAll() throws DataAccessException {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(String.valueOf(id)) ;
    }

    @Override
    public Boolean updateProduct(Product product) {
        productRepository.save(product);

        return true;

    }

    @Override
    public Boolean deleteProduct(long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}