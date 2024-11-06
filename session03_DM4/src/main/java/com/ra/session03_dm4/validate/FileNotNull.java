package com.ra.session03_dm4.validate;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
//annotation này có thể được đặt trên một trường (field)
//ví dụ: một thuộc tính của lớp để lưu trữ tệp người dùng tải lên.
@Retention(RetentionPolicy.RUNTIME)
//Dòng này cho biết thời gian tồn tại của annotation.
@Constraint(validatedBy = {FilenotNullValidator.class})
// logic kiểm tra tính hợp lệ

public @interface FileNotNull {
    String message() default "Hình ảnh tập tin không trống";
    //Đây là tin nhắn lỗi mặc định sẽ được hiển thị
    Class<?>[] groups() default {};
    // để phân loại các ràng buộc (constraint) nếu muốn thực hiện kiểm tra ở nhiều nhóm khác nhau
    // ví dụ ở: @Constraint(validatedBy = {FilenotNullValidator.class}) có thể có nhiều Constraint
    // nên default mình sẽ dùng mặc định 1 class mình chọn
    Class<? extends Payload>[] payload() default {};
    //Phần này ít khi được sử dụng, nhưng nó cho phép bạn đính kèm các dữ liệu bổ sung
}

