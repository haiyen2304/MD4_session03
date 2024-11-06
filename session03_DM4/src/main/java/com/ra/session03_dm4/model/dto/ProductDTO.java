package com.ra.session03_dm4.model.dto;

import com.ra.session03_dm4.model.entity.Product;
import com.ra.session03_dm4.validate.FileNotNull;
import com.ra.session03_dm4.validate.NameExist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
//    @NameExist(message = "tên sản phẩm đã bị trùng",entityClass = Product.class,fieldName = "name")
    private String nameProduct;
    private Double price;
    private String description;
    private boolean status;
    @FileNotNull(message = "Hãy chọn ảnh")
    private MultipartFile fileImage;

//    private String oldImage;
    private Long CategoryId;

}
