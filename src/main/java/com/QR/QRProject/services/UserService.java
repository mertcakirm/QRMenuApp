package com.QR.QRProject.services;

import com.QR.QRProject.dtos.CompanyDto;
import com.QR.QRProject.dtos.CompanyUpdateDto;
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
    boolean deleteUser(Long userId);
    boolean updateProfilePhoto(String base64Image);
    CompanyDto updateCompany(CompanyUpdateDto companyDto);
    boolean changePassword(String oldPassword, String newPassword);
}
