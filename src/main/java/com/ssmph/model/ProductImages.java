package com.ssmph.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductImages {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProductImages;

    private String imageProductName;
    private String skuProduct;
    private Long idProduct;

    
    public Long getIdProductImages() {
        return idProductImages;
    }

    public void setIdProductImages(Long idProductImages) {
        this.idProductImages = idProductImages;
    }

    public String getImageProductName() {
        return imageProductName;
    }

    public void setImageProductName(String imageProductName) {
        this.imageProductName = imageProductName;
    }

    public String getSkuProduct() {
        return skuProduct;
    }

    public void setSkuProduct(String skuProduct) {
        this.skuProduct = skuProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }



    

}


