package com.QR.QRProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    private String address;

    private String logoUrl;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();
}