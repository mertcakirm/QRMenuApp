package com.QR.QRProject.controllers;

import com.QR.QRProject.dtos.order.OrderDto;
import com.QR.QRProject.dtos.order.OrderDtoIU;
import com.QR.QRProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<OrderDto>> getOrdersByTable(@PathVariable Long tableId) {
        List<OrderDto> orders = orderService.findByTableId(tableId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDtoIU orderDtoIU) {
        OrderDto savedOrder = orderService.save(orderDtoIU);
        return ResponseEntity.ok(savedOrder);
    }
}