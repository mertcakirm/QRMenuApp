package com.QR.QRProject.repositories;

import com.QR.QRProject.entities.CompanyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyTableRepository extends JpaRepository<CompanyTable, Long> {
    List<CompanyTable> findCompanyTablesByCompanyId(Long companyId);
}
