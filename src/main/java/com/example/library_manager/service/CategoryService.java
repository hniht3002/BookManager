package com.example.library_manager.service;

import com.example.library_manager.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    void save(Category c);
    void delete(Long id);
    void delete(Category c);

    Category findById(Long id);

    List<Category> findAllExceptId(Long id);
}
