package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.table.CompanyTablesDto;
import com.QR.QRProject.dtos.table.TableDto;
import com.QR.QRProject.entities.CompanyTable;
import com.QR.QRProject.entities.User;
import com.QR.QRProject.repositories.CompanyTableRepository;
import com.QR.QRProject.repositories.UserRepository;
import com.QR.QRProject.services.CompanyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyTableServiceImpl implements CompanyTableService {

    @Autowired
    private CompanyTableRepository companyTableRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CompanyTablesDto> getCompanyTablesByCompanyId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));


        List<CompanyTable> tables = companyTableRepository.findCompanyTablesByCompanyId(user.getCompany().getId());

        return tables.stream()
                .map(table -> {
                    CompanyTablesDto dto = new CompanyTablesDto();
                    dto.setId(table.getId());
                    dto.setTableName(table.getTableName());
                    dto.setAvailable(table.isAvailable());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TableDto saveTable(String tableName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        CompanyTable companyTable = new CompanyTable();
        companyTable.setTableName(tableName);
        companyTable.setAvailable(true);
        companyTable.setCompany(user.getCompany());

        CompanyTable result = companyTableRepository.save(companyTable);
        TableDto dto =  new TableDto();
        dto.setId(result.getId());
        dto.setTableName(result.getTableName());

        return dto;
    }

}