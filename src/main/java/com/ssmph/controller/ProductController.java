package com.ssmph.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmph.model.Product;
import com.ssmph.model.ProductImages;
import com.ssmph.service.ProductImagesService;
import com.ssmph.service.ProductService;
import com.ssmph.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ProductController
 */

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ProductController {

	@Value("${ruta.imagenes.path}")
	private String rutaImagenes;

    @Autowired
	private ProductService productService;

    private StorageService storageService;

	@Autowired
	private ProductImagesService productImagesService;

    public ProductController(StorageService storageService) {
        this.storageService = storageService;
    }	

	/**
	 * Guardar Producto sin imagenes adjuntas
	 * 
	 * @param p
	 * @return
	 */
    @PostMapping(value="/products")
    public long saveProduct(@RequestBody Product p){

    	try {
    		Product pr = productService.saveProduct(p);
    		return pr.getIdProduct();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return 0;
    	}	
    }
	
	/**
	 * Guarda producto con multiples imagenes adjuntas
	 * 
	 * @param p
	 * @param files
	 */
	@PostMapping(value="/products/multiple-images")
	public void saveProductMultipleImages(@RequestParam("product") String p, 
	@RequestParam("files") MultipartFile[] files) {

    	try {
			ObjectMapper mapper = new ObjectMapper();			
			Product product = mapper.readValue(p, Product.class);
			saveProduct(product);

			Optional<Product> opt = productService.getProductById(product.getIdProduct());

			storageService.createSubDirectory(product.getSku());

			for (int i=0; i < files.length; i++) {
				MultipartFile file = files[i];

				storageService.store(product.getSku(), file);

				if (opt.isPresent()) {
					Product pp = opt.get();	
					ProductImages pi = new ProductImages();
					pi.setIdProduct(pp.getIdProduct());
					pi.setImageProductName(file.getOriginalFilename());
					pi.setSkuProduct(product.getSku());

					productImagesService.saveProductImages(pi);
				}
	

				// byte[] bytes = f.getBytes();
				// Path path = Paths.get(rutaImagenes + f.getOriginalFilename() );
				// Files.write(path, bytes);
			}
	

    	} catch (Exception e) {
    		e.printStackTrace();
    		
    	}	
	}

	/**
	 * Listado de productos
	 * 
	 * @return
	 */
    @GetMapping(value="/products")
    public List<Product> listProducts() {
    	return productService.listProducts(); 	
    }
	
	/**
	 * Elimina producto
	 * 
	 */
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
	
	/**
	 * Obtiene producto segun id
	 * 
	 * @param id
	 * @return
	 */
    @GetMapping("/products/{id}")
	public Product getProduct(@PathVariable long id) {
		Optional<Product> product = productService.getProductById(id);

		List<ProductImages> list = productImagesService.findAllProductImagesBySku(product.get().getSku());

		product.get().setProductImages(list);
		
		if (!product.isPresent())
			throw new ProductNotFoundException("id-" + id);

		return product.get();
	}

}