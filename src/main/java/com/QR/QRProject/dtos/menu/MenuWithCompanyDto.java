package com.QR.QRProject.dtos.menu;

import lombok.Data;

import java.util.List;

@Data
public class MenuWithCompanyDto {
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;

    private List<MenuDto> items;

}