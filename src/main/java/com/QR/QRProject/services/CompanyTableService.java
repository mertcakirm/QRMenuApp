package com.QR.QRProject.services;

import com.QR.QRProject.dtos.table.CompanyTablesDto;
import com.QR.QRProject.dtos.table.TableDto;

import java.util.List;

public interface CompanyTableService {
    List<CompanyTablesDto>  getCompanyTablesByCompanyId();
    TableDto saveTable(String tableName);
}
