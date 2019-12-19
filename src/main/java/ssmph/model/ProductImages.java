package ssmph.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductImages {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProductImages;

    private String productImage;
    private Long idProduct;

    public Long getIdProductImages() {
        return idProductImages;
    }

    public void setIdProductImages(Long idProductImages) {
        this.idProductImages = idProductImages;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }


    

}


