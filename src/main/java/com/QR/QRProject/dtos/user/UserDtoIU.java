package com.QR.QRProject.dtos.user;

import com.QR.QRProject.dtos.company.CompanyDtoIU;
import lombok.Data;

@Data
public class UserDtoIU {
    private String name;
    private String password;
    private String email;
    private CompanyDtoIU company;

}

