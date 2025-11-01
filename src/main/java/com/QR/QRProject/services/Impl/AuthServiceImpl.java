package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.LoginDto;
import com.QR.QRProject.entities.User;
import com.QR.QRProject.repositories.UserRepository;
import com.QR.QRProject.security.JwtUtil;
import com.QR.QRProject.security.Sha256Util;
import com.QR.QRProject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwt;

    @Override
    public String login(LoginDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        String hashedInput = Sha256Util.hash(dto.getPassword());
        if (!user.getPassword().equals(hashedInput)) {
            throw new RuntimeException("Şifre hatalı");
        }

        return jwt.generateToken(user);
    }

    @Override
    public String checkRole() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        var user =  userRepository.findByEmail(auth.getName());

        var roleName = user.get().getRole().toString();
        return roleName;
    }


}
