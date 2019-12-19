package ssmph.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ssmph.model.Product;
import ssmph.model.ProductImages;
import ssmph.service.ProductImagesService;
import ssmph.service.ProductService;

/**
 * ProductController
 */

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class ProductController {

	//Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "//Volumes//francisco//Descargas//productImages//";

    @Autowired
	private ProductService productService;
	
	private ProductImagesService productImagesService;
    
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
	
	@PostMapping(value="/uploadImagesProduct")
	public boolean saveProductImages(@RequestParam("file") MultipartFile file, 
	@RequestParam("nombre") String nombre,
								  RedirectAttributes redirectAttributes){

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return false;
		}

		

    	// try {
    	// 	ProductImages pr = productImagesService.saveProductImages(p);
    	// 	return pr.getIdProductImages();
    	// } catch (Exception e) {
    	// 	e.printStackTrace();
    	// 	return 0;
		// }
		
		try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

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
	
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }	

}