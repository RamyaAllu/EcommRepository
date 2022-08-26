package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.beans.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
 
}
