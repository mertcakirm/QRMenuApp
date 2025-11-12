package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.menu.MenuItemVariantDto;
import com.QR.QRProject.dtos.order.OrderDto;
import com.QR.QRProject.dtos.order.OrderDtoIU;
import com.QR.QRProject.dtos.order.OrderItemDto;
import com.QR.QRProject.entities.MenuItemVariant;
import com.QR.QRProject.entities.Order;
import com.QR.QRProject.entities.OrderItem;
import com.QR.QRProject.repositories.MenuItemVariantRepository;
import com.QR.QRProject.repositories.OrderRepository;
import com.QR.QRProject.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemVariantRepository menuItemVariantRepository;


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

    @Override
    @Transactional
    public OrderDto save(OrderDtoIU orderDtoIU) {
        Order order = new Order();
        order.setStatus("Pending");

        List<OrderItem> orderItems = orderDtoIU.getItems().stream().map(itemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(itemDto.getQuantity());

            MenuItemVariant variant = menuItemVariantRepository.findById(itemDto.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Variant not found with ID: " + itemDto.getVariantId()));

            double totalPrice = variant.getPrice() * itemDto.getQuantity();
            orderItem.setTotalPrice(totalPrice);

            orderItem.setMenuItemVariant(variant);
            orderItem.setOrder(order);

            return orderItem;
        }).toList();

        double totalOrderPrice = orderItems.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        order.setTotalPrice(totalOrderPrice);

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        OrderDto result = new OrderDto();
        result.setId(savedOrder.getId());
        result.setStatus(savedOrder.getStatus());
        result.setTotalPrice(savedOrder.getTotalPrice());

        if (savedOrder.getTable() != null) {
            result.setTableName(savedOrder.getTable().getTableName());
        }

        result.setItems(savedOrder.getOrderItems().stream().map(orderItem -> {
            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setId(orderItem.getId());
            itemDto.setQuantity(orderItem.getQuantity());
            itemDto.setTotalPrice(orderItem.getTotalPrice());

            MenuItemVariant variant = orderItem.getMenuItemVariant();
            if (variant != null) {
                MenuItemVariantDto variantDto = new MenuItemVariantDto();
                variantDto.setId(variant.getId());
                variantDto.setName(variant.getName());
                variantDto.setPrice(variant.getPrice());
                itemDto.setMenuItemVariantDto(variantDto);
            }

            return itemDto;
        }).toList());

        return result;
    }

}
