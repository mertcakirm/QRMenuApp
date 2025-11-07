package com.QR.QRProject.services;

import com.QR.QRProject.dtos.order.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto>  findByTableId(Long tableId);

}
