package com.QR.QRProject.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MenuItemDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private List<MenuItemVariantDto> variants;
}
