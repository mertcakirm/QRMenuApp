package com.QR.QRProject.dtos.table;

import com.QR.QRProject.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class TableWithOrdersDto {
    private Long Id;
    private String tableName;
    private boolean isAvailable;
    private List<Order>  orders;

}
