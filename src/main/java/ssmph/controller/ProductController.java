package ssmph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ssmph.model.Product;
import ssmph.service.ProductService;

/**
 * ProductController
 */

@RestController
@RequestMapping(path="/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @RequestMapping(value="/saveProduct",method=RequestMethod.POST)
    public Boolean saveProduct(@RequestBody Product p){
          return productService.saveProduct(p);
    }
}