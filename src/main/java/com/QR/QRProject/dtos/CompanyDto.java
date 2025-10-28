package com.QR.QRProject.dtos;

import lombok.Data;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String logoUrl;
}
