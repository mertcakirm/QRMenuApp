package com.QR.QRProject.dtos.company;

import lombok.Data;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String base64Image;
}
