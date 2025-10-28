package com.QR.QRProject.dtos;

import com.QR.QRProject.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private Role role;
    private CompanyDto company;
}

