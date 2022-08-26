package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.beans.PhysicalProductImageInExcel;
@Repository
public interface PhysicalProductImageExcelRepository extends JpaRepository<PhysicalProductImageInExcel, Integer>{

}
