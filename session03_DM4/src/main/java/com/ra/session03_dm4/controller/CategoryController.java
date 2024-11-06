package com.ra.session03_dm4.controller;

import com.ra.session03_dm4.model.entity.Category;
import com.ra.session03_dm4.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<Category>> index(){
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category categoryCreated = categoryService.save(category);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null){
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        Map<String,String> error = new HashMap<>();
        error.put("message", "Category not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        category.setId(id);
        Category categoryUpdated = categoryService.save(category);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Category categoryDelete= categoryService.findById(id);
        categoryService.delete(categoryDelete);
        return new ResponseEntity<>(categoryDelete, HttpStatus.NO_CONTENT);
    }
}
