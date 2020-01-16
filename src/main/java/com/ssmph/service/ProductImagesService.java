package com.ssmph.service;

import java.util.List;

import com.ssmph.model.ProductImages;

/**
 * ProductService
 */
public interface ProductImagesService {

    ProductImages saveProductImages(ProductImages productImages);

    List<ProductImages> findAllProductImagesBySku(String skuProduct);
    
}