package com.QR.QRProject.controllers;

import com.QR.QRProject.dtos.table.CompanyTablesDto;
import com.QR.QRProject.dtos.table.TableDto;
import com.QR.QRProject.services.CompanyTableService;
import com.QR.QRProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tables")
public class CompanyTablesController {

    @Autowired
    private CompanyTableService companyTableService;

    @Autowired
    private OrderService orderService;


    @GetMapping("get-all")
    public List<CompanyTablesDto> GetAllByToken() {
        return companyTableService.getCompanyTablesByCompanyId();
    }

    @DeleteMapping("clear/{tableId}")
    public boolean clearTable(@PathVariable Long tableId) {
        return companyTableService.clearTable(tableId);
    }

    @PostMapping("create")
    public TableDto createTable(@RequestParam String tableName) {
        return companyTableService.saveTable(tableName);
    }
}
