package com.QR.QRProject.controllers;


import com.QR.QRProject.dtos.LoginDto;
import com.QR.QRProject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping("checkRole")
    public String checkRole() {
        return authService.checkRole();
    }
}
