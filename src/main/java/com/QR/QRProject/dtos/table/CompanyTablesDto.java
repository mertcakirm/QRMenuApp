package com.QR.QRProject.dtos.table;

import lombok.Data;

@Data
public class CompanyTablesDto {
    private Long Id;
    private String tableName;
    private boolean isAvailable;
}
