package ssmph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ssmph.model.ProductImages;

/**
 * ProductRepository
 */
@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

}