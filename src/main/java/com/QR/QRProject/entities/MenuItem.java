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
@Table(name = "menu_items")
public class MenuItem  extends BaseEntity{

    private String name;
    private String description;
    private Boolean active = true;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String base64Image;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    private List<MenuItemVariant> variants = new ArrayList<>();
}