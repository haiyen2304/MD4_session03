package com.ra.session03_dm4.repository;

import com.ra.session03_dm4.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
