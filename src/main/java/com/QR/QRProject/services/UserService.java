package com.QR.QRProject.services;

import com.QR.QRProject.dtos.UserDto;
import com.QR.QRProject.dtos.UserDtoIU;
import com.QR.QRProject.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserDto> findbyUserId(Long id);
    UserDto createUser(UserDtoIU userDto);
    UserDto getMe();
}
