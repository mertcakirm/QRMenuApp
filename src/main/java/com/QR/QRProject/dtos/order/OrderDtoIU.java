package com.QR.QRProject.dtos.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderDtoIU {
    private List<OrderItemDtoIU> items;
}
