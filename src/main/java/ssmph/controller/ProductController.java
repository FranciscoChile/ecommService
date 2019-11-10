package ssmph.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ssmph.model.Product;
import ssmph.service.ProductService;

/**
 * ProductController
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping(value="/products")
    public Boolean saveProduct(@RequestBody Product p){

    	try {
    		productService.saveProduct(p);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}	
    }
    
    @GetMapping(value="/products")
    public List<Product> listProducts() {
    	return productService.listProducts(); 	
    }
    
    @DeleteMapping(value="/products/{id}")
    public Boolean deleteProduct(@PathVariable long id) {
    	try {
    		productService.deleteProduct(id);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
        
    @GetMapping("/products/{id}")
	public Product getProduct(@PathVariable long id) {
		Optional<Product> product = productService.getProductById(id);

		if (!product.isPresent())
			throw new StudentNotFoundException("id-" + id);

		return product.get();
	}
    
}