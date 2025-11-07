package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.menu.MenuItemVariantDto;
import com.QR.QRProject.dtos.order.OrderDto;
import com.QR.QRProject.dtos.order.OrderItemDto;
import com.QR.QRProject.entities.MenuItemVariant;
import com.QR.QRProject.entities.Order;
import com.QR.QRProject.repositories.OrderRepository;
import com.QR.QRProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<OrderDto> findByTableId(Long tableId) {
        List<Order> orders = orderRepository.findByTableId(tableId);

        return orders.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setStatus(order.getStatus());
            orderDto.setTotalPrice(order.getTotalPrice());

            if (order.getTable() != null) {
                orderDto.setTableName(order.getTable().getTableName());
            }

            List<OrderItemDto> itemDtos = order.getOrderItems().stream().map(orderItem -> {
                OrderItemDto itemDto = new OrderItemDto();
                itemDto.setId(orderItem.getId());
                itemDto.setQuantity(orderItem.getQuantity());
                itemDto.setTotalPrice(orderItem.getTotalPrice());

                if (orderItem.getMenuItemVariant() != null) {
                    MenuItemVariant variant = orderItem.getMenuItemVariant();
                    MenuItemVariantDto variantDto = new MenuItemVariantDto();
                    variantDto.setId(variant.getId());
                    variantDto.setName(variant.getName());
                    variantDto.setPrice(variant.getPrice());
                    itemDto.setMenuItemVariantDto(variantDto);
                }

                return itemDto;
            }).toList();

            orderDto.setItems(itemDtos);
            return orderDto;
        }).toList();
    }
}
