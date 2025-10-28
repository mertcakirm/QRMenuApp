package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.CompanyDto;
import com.QR.QRProject.dtos.UserDto;
import com.QR.QRProject.dtos.UserDtoIU;
import com.QR.QRProject.entities.Company;
import com.QR.QRProject.entities.User;
import com.QR.QRProject.repositories.CompanyRepository;
import com.QR.QRProject.repositories.UserRepository;
import com.QR.QRProject.security.Sha256Util;
import com.QR.QRProject.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(user, dto);

                    if (user.getCompany() != null) {
                        CompanyDto companyDto = new CompanyDto();
                        BeanUtils.copyProperties(user.getCompany(), companyDto);
                        dto.setCompany(companyDto); // setter ile set et!
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public Optional<UserDto> findbyUserId(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(user, dto);

                    if (user.getCompany() != null) {
                        CompanyDto companyDto = new CompanyDto();
                        BeanUtils.copyProperties(user.getCompany(), companyDto);
                        dto.setCompany(companyDto);
                    }

                    return dto;
                });
    }

    @Override
    public UserDto createUser(UserDtoIU userDto) {
        if (userDto == null) return null;

        Company company = new Company();
        BeanUtils.copyProperties(userDto.getCompany(), company);
        companyRepository.save(company);

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        String hashed = Sha256Util.hash(user.getPassword());
        user.setPassword(hashed);
        user.setCompany(company);
        userRepository.save(user);

        UserDto result = new UserDto();
        BeanUtils.copyProperties(user, result);

        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        result.setCompany(companyDto);

        return result;
    }

}
