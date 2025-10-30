package com.QR.QRProject.repositories;

import com.QR.QRProject.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findAllByCompanyId(Long companyId);
    List<Menu> findAllByCompanyName(String companyName);
}
