package com.QR.QRProject.controllers;

import com.QR.QRProject.dtos.table.CompanyTablesDto;
import com.QR.QRProject.services.CompanyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tables")
public class CompanyTablesController {

    @Autowired
    private CompanyTableService companyTableService;


    @GetMapping("get-all")
    public List<CompanyTablesDto> GetAllByToken() {


        return companyTableService.getCompanyTablesByCompanyId();

    }
}
