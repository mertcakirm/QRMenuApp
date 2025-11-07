package com.QR.QRProject.dtos.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String TableName;
    private String status;
    private Double totalPrice;
    private List<OrderItemDto> items;
}
