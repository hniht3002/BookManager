package com.example.library_manager.model;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCategoryDto {
    private String name;
    private String year;
    private String cover;
    private Set<Category> categories;
}
