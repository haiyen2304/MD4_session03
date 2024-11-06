package com.ra.session03_dm4.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Value("${upload-path}")
    String uploadPath;


    @Override
    public String uploadImage(MultipartFile multipartFile) {
        // xư l upload
        // String uploadPath = servletContext.getRealPath("/uploads");

        System.out.println(uploadPath);

        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdir();
        }


        // lấy tên file
        String fileImage = multipartFile.getOriginalFilename();

        // copy file upload lên gửi vo thư mục uploads
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(uploadPath+File.separator+fileImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileImage;

    }

}
