package com.QR.QRProject.entities;

import com.QR.QRProject.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private Role role = Role.COMPANY;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}