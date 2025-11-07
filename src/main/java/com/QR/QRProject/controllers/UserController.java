package com.QR.QRProject.controllers;


import com.QR.QRProject.dtos.company.CompanyDto;
import com.QR.QRProject.dtos.company.CompanyUpdateDto;
import com.QR.QRProject.dtos.user.PasswordChangeDto;
import com.QR.QRProject.dtos.user.UserDto;
import com.QR.QRProject.dtos.user.UserDtoIU;
import com.QR.QRProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public List<UserDto> findAll() {

        System.out.println("testtttt");
        return userService.findAll();
    }

    @GetMapping("/get/{id}")
    public UserDto findById(@PathVariable Long id){
        return userService.findbyUserId(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/get-me")
    public UserDto findMe(){
        return userService.getMe();
    }

    @PostMapping
    public UserDto create(@RequestBody UserDtoIU userDtoIU){
        return userService.createUser(userDtoIU);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("Kullanıcı ve bağlı şirket + menüler silindi.");
        } else {
            return ResponseEntity.status(404).body("Kullanıcı bulunamadı.");
        }
    }

    @PutMapping("/photo")
    public ResponseEntity<?> updateProfilePhoto(@RequestBody String base64Image) {
        boolean updated = userService.updateProfilePhoto(base64Image);

        if (updated) {
            return ResponseEntity.ok("Logo başarıyla güncellendi");
        } else {
            return ResponseEntity.status(500).body("Logo güncellenemedi");
        }
    }

    @PutMapping("/update/company")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyUpdateDto companyDto) {
        CompanyDto updated = userService.updateCompany(companyDto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDto dto) {
        boolean result = userService.changePassword(dto.getOldPassword(), dto.getNewPassword());
        if (result) {
            return ResponseEntity.ok("Şifre başarıyla değiştirildi.");
        }
        return ResponseEntity.badRequest().body("Şifre değiştirilemedi.");
    }
}
