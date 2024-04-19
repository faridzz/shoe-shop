package com.fz.onlineshoestore.service;


import com.fz.onlineshoestore.model.Shoe;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class SavePhotoService {
    @Autowired
    ResourceLoader resourceLoader;

    public String getName(Shoe shoe) {
        String defaultName = "default";
        MultipartFile shoePhotoFile = shoe.getPhotoFile();
        if (shoePhotoFile != null && shoePhotoFile.getOriginalFilename() != null) {
            String shoePhotoName = shoePhotoFile.getOriginalFilename();
            log.info("Name:.............. : " + shoePhotoName);
            return shoePhotoName;
        } else {
            log.info("Name:.............. " + defaultName);
            return defaultName;
        }
    }

    public void savePhoto(Shoe shoe) throws IOException {
        String saveLocation = ResourceUtils.getFile("classpath:img/").getAbsolutePath();
        byte[] photoBytes = shoe.getPhotoFile().getBytes();
        Files.write(Paths.get(saveLocation, getName(shoe)), photoBytes);
        log.info("Save location: " + saveLocation);

    }


}
