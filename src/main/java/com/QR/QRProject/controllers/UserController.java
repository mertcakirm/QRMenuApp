package com.QR.QRProject.controllers;


import com.QR.QRProject.dtos.UserDto;
import com.QR.QRProject.dtos.UserDtoIU;
import com.QR.QRProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
