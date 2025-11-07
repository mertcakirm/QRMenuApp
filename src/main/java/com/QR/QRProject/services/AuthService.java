package com.QR.QRProject.services;

import com.QR.QRProject.dtos.user.LoginDto;

public interface AuthService {
    String login(LoginDto dto);
    String checkRole();
}
