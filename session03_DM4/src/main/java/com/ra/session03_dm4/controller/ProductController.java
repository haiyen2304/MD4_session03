package com.ra.session03_dm4.controller;

import com.ra.session03_dm4.model.dto.ProductDTO;
import com.ra.session03_dm4.model.entity.Product;
import com.ra.session03_dm4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@PageableDefault(page = 0, size = 2,sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
                                                         @RequestParam(defaultValue = "") String search,
                                                         @RequestParam(required = false) Double minPrice,
                                                         @RequestParam(required = false) Double maxPrice,
                                                         @RequestParam(defaultValue = "none") String sortOption) {

        Page<Product> productList=productService.findAll(pageable,search,minPrice,maxPrice,sortOption);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Product> createProduct(@ModelAttribute ProductDTO productDTO) {
        Product paroductNew = productService.save(productDTO);
        return new ResponseEntity<>(paroductNew, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getProduct(@PathVariable Long id) {
        Product product =productService.getProduct(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        Map<String,String> error = new HashMap<>();
        error.put("message", "Product not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Product> updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO productDTO) {
        Product productUpdate=  productService.save(productDTO,id);
        return new ResponseEntity<>(productUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Product> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
