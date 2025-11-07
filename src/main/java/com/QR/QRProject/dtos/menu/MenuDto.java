package com.QR.QRProject.dtos.menu;

import lombok.Data;

@Data
public class MenuDto {
    private Long id;
    private String title;
    private String base64Image;
}
