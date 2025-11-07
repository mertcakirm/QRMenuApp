package com.QR.QRProject.dtos.order;

import com.QR.QRProject.dtos.menu.MenuItemVariantDto;
import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Integer quantity;
    private Double totalPrice;
    private MenuItemVariantDto menuItemVariantDto;
}
