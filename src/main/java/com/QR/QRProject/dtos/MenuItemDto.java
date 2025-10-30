package com.QR.QRProject.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MenuItemDto {
    private Long id;
    private String name;
    private String description;
    private String base64Image;
    private List<MenuItemVariantDto> variants;
}
