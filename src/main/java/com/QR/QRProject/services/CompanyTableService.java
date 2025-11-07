package com.QR.QRProject.services;

import com.QR.QRProject.dtos.table.CompanyTablesDto;

import java.util.List;

public interface CompanyTableService {
    List<CompanyTablesDto>  getCompanyTablesByCompanyId();
}
