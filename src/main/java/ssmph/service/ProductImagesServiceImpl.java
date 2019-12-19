package ssmph.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmph.model.ProductImages;
import ssmph.repository.ProductImagesRepository;

/**
 * ProductServiceImpl
 */
@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    @Autowired
    private ProductImagesRepository productImagesRepository;
    
    @Transactional
    public ProductImages saveProductImages(ProductImages productImages) {
        return productImagesRepository.save(productImages);
    }

    
}