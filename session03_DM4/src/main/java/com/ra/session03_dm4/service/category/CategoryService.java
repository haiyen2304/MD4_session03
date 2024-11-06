package com.ra.session03_dm4.service.category;

import com.ra.session03_dm4.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    void delete(Category category);
}
