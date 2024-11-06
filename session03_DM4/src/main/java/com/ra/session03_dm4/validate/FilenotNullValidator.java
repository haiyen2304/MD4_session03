package com.ra.session03_dm4.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


// dùng để kiểm tra tính hợp lệ của tệp
// phải implements ConstraintValidator để nói cho nó biết đây là 1 Constraint
// và nó được gọi ở annotation FileNotNull, và kiểu dữ liệu là MultipartFile
public class FilenotNullValidator implements ConstraintValidator<FileNotNull, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        // vì lấy dữ liệu từ dabtabase lên , nên nó luôn có kết quả, nên size ko thể bằng 0
        return value.getSize()!=0;
    }
}
