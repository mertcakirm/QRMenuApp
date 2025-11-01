package com.QR.QRProject.dtos;

import lombok.Data;

@Data
public class CompanyUpdateDto {
    private String name;
    private String email;
    private String phone;
    private String address;
}
