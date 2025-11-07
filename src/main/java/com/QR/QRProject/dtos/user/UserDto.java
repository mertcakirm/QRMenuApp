package com.QR.QRProject.dtos.user;

import com.QR.QRProject.dtos.company.CompanyDto;
import com.QR.QRProject.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private Role role;
    private CompanyDto company;
}

