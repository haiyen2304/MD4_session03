package com.ra.session03_dm4.service.product;

import com.ra.session03_dm4.model.dto.ProductDTO;
import com.ra.session03_dm4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(Long id);
    Product save(ProductDTO productDTO);
    Product save(ProductDTO productDTO,Long id);
    void deleteProduct(Long id);
    Page<Product> findAll(Pageable pageable,String search ,Double minPrice,Double maxPrice,String sortOption);
}
