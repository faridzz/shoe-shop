package com.fz.onlineshoestore.model.dto;


import lombok.Data;

import org.springframework.web.multipart.MultipartFile;


@Data
public class ShoeDto {
    private String name;

    private String description;

    private Long price;

    private MultipartFile photoFile;

}
