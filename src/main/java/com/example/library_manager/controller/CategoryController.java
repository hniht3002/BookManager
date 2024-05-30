package com.example.library_manager.controller;

import com.example.library_manager.model.Category;
import com.example.library_manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value = "/category")
    public String categoryDashboard(Model model) {
        Category newCategory = new Category();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", newCategory);
        return "/sections/category/dashboard";
    }

    @PostMapping(value = "/category/create")
    public String create(@ModelAttribute("category") Category category, BindingResult result) {
        if(!result.hasErrors()) {
            categoryService.save(category);
        }

        return "redirect:/category";
    }

    @GetMapping(value = "/category/delete")
    public String delete(@RequestParam Long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }

    @GetMapping(value = "/category/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("current", categoryService.findById(id));
        model.addAttribute("categories", categoryService.findAllExceptId(id));
        return "/sections/category/edit";
    }

    @PostMapping(value = "/category/update")
    public String update(@ModelAttribute Category category) {
        Category c = categoryService.findById(category.getId());
        c.setName(category.getName());
        categoryService.save(c);
        return "redirect:/category";
    }
}
