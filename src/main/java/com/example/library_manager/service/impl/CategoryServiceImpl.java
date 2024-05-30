package com.example.library_manager.service.impl;

import com.example.library_manager.model.Category;
import com.example.library_manager.repository.CategoryRepository;
import com.example.library_manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category c) {
        categoryRepository.save(c);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(Category c) {
        categoryRepository.delete(c);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAllExceptId(Long id) {
        return categoryRepository.findAll().stream()
                .filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());
    }

}
