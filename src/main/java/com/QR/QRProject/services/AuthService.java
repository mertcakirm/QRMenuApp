package com.QR.QRProject.services;

import com.QR.QRProject.dtos.LoginDto;

public interface AuthService {
    String login(LoginDto dto);
}
