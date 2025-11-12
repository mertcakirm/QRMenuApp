package com.QR.QRProject.services;

import com.QR.QRProject.dtos.order.OrderDto;
import com.QR.QRProject.dtos.order.OrderDtoIU;

import java.util.List;

public interface OrderService {
    List<OrderDto> findByTableId(Long tableId);
    OrderDto save(OrderDtoIU orderDto);
}
