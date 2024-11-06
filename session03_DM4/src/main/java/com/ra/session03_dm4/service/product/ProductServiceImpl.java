package com.ra.session03_dm4.service.product;

import com.ra.session03_dm4.model.dto.ProductDTO;
import com.ra.session03_dm4.model.entity.Product;
import com.ra.session03_dm4.repository.ProductRepository;
import com.ra.session03_dm4.service.category.CategoryService;
import com.ra.session03_dm4.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final CategoryService categoryService;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FileService fileService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(ProductDTO productDTO) {
        String image = fileService.uploadImage(productDTO.getFileImage());
        Product product = new Product();
        product.setNameProduct(productDTO.getNameProduct());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(image);
        product.setStatus(productDTO.isStatus());
        //where category
        product.setCategory(categoryService.findById(productDTO.getCategoryId())); // thuc thi dung repository cua category findById()
        return productRepository.save(product);
    }

    @Override
    public Product save(ProductDTO productDTO, Long id) {
        // ban dau khoi tao image
        String image = null;
        // neu fileImage khac rong (ton tai) thi upload
        if (!productDTO.getFileImage().isEmpty()) {
            // bước 1:upload file =>cần tạo file =>fileService
            image = fileService.uploadImage(productDTO.getFileImage());
        } else {
            // neu khong ton tai thi lay lai anh cua dua vao getById()
            image = getProduct(id).getImage();
        }
        Product product=new Product();
        product.setId(id);

        product.setNameProduct(productDTO.getNameProduct());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStatus(productDTO.isStatus());
        product.setCategory(categoryService.findById(productDTO.getCategoryId())); // thuc thi dung repository cua category findById()

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable,String search, Double minPrice, Double maxPrice, String sortOption) {
        //bước 1:xét sort mặc định là id
        Sort sort= Sort.by(Sort.Direction.ASC,sortOption.equals("none") ? "id" : sortOption);
        //bước 2: xử lý giá trị của softOption nếu là asc thì ...desc thì...
        if("asc".equalsIgnoreCase(sortOption)){
            sort = sort.descending();
        }else if("desc".equalsIgnoreCase(sortOption)){
            sort = sort.ascending();
        }
        //bước 3: cập nhập lại pageable
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);

        //bước 4: kieerm tra nếu không cso các filter khác thì đ mặc định
        if(search.isBlank() && minPrice==null && maxPrice==null){
            return productRepository.findAll(pageable);
        }
        // bước 5:phân trang có tham số
        productRepository.findProductsWithFilters(pageable,search,minPrice,maxPrice,sortOption);

        return null;
    }
}
