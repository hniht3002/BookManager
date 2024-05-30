package com.example.library_manager.service;

import com.example.library_manager.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    void save(Book b);
    void delete(Long id);
    void delete(Book b);

    Book findById(Long id);
}
