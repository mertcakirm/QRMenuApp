package com.QR.QRProject.dtos.menu;

import lombok.Data;

import java.util.List;

@Data
public class MenuItemDtoIU {
    private String name;
    private String description;
    private Long menuId;
    private String base64Image;
    private List<MenuItemVariantDtoIU> variants;
}
