package com.QR.QRProject.dtos;

import lombok.Data;

@Data
public class UserDtoIU {
    private String name;
    private String password;
    private String email;
    private CompanyDtoIU company;

}

