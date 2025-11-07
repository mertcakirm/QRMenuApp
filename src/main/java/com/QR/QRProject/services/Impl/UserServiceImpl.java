package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.company.CompanyDto;
import com.QR.QRProject.dtos.company.CompanyUpdateDto;
import com.QR.QRProject.dtos.user.UserDto;
import com.QR.QRProject.dtos.user.UserDtoIU;
import com.QR.QRProject.entities.Company;
import com.QR.QRProject.entities.User;
import com.QR.QRProject.repositories.CompanyRepository;
import com.QR.QRProject.repositories.UserRepository;
import com.QR.QRProject.security.Sha256Util;
import com.QR.QRProject.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public UserDto getMe() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userRepository.findById(user.getId())
                .map(u -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(u, dto);

                    if (u.getCompany() != null) {
                        CompanyDto companyDto = new CompanyDto();
                        BeanUtils.copyProperties(u.getCompany(), companyDto);
                        dto.setCompany(companyDto);
                    }

                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("User not found by id"));
    }

    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return false;

        userRepository.delete(userOpt.get());
        return true;
    }

    @Override
    public boolean updateProfilePhoto(String base64Image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = user.getCompany();
        if (company == null) {
            throw new RuntimeException("Company not found for this user");
        }

        company.setBase64Image(base64Image);
        companyRepository.save(company);

        return true;
    }

    @Override
    public CompanyDto updateCompany(CompanyUpdateDto companyDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = user.getCompany();
        if (company == null) {
            throw new RuntimeException("Company not found for this user");
        }

        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());
        company.setPhone(companyDto.getPhone());
        company.setEmail(companyDto.getEmail());

        Company savedCompany = companyRepository.save(company);

        CompanyDto dto = new CompanyDto();
        dto.setId(savedCompany.getId());
        dto.setName(savedCompany.getName());
        dto.setEmail(savedCompany.getEmail());
        dto.setPhone(savedCompany.getPhone());
        dto.setAddress(savedCompany.getAddress());
        dto.setBase64Image(savedCompany.getBase64Image());

        return dto;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String hashedOldPassword = Sha256Util.hash(oldPassword);

        if (!hashedOldPassword.equals(user.getPassword())) {
            throw new RuntimeException("Eski şifre yanlış!");
        }

        String hashedNewPassword = Sha256Util.hash(newPassword);
        user.setPassword(hashedNewPassword);
        userRepository.save(user);

        return true;
    }

}
