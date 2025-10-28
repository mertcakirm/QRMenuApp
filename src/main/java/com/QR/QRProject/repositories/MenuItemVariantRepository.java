package com.QR.QRProject.repositories;

import com.QR.QRProject.entities.MenuItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemVariantRepository extends JpaRepository<MenuItemVariant, Long> {
}
