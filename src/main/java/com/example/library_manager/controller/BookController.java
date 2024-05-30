package com.example.library_manager.controller;

import com.example.library_manager.model.Book;
import com.example.library_manager.model.BookCategoryDto;
import com.example.library_manager.model.Category;
import com.example.library_manager.repository.BookRepository;
import com.example.library_manager.service.BookService;
import com.example.library_manager.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired private CategoryService categoryService;
    @GetMapping("/book")
    public String dashboard(Model model) {
        List<Book> books = bookService.findAll();

        model.addAttribute("books", books);

        return "/sections/book/dashboard";
    }

    @GetMapping("/book/add")
    public String add(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("bcDto", new BookCategoryDto());
        return "/sections/book/add";
    }
    @PostMapping("/book/store")
    public String store(@ModelAttribute BookCategoryDto bcDto) {
        Book book = new Book();
        book.setName(bcDto.getName());
        book.setYear(bcDto.getYear());
        book.setCover(bcDto.getCover());
        book.setCategories(bcDto.getCategories());
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/delete")
    public String delete(@RequestParam String id) {
        bookService.delete(Long.parseLong(id));
        return "redirect:/book";
    }

    @GetMapping("/book/edit")
    public String edit(@RequestParam Long id, Model model) {
        Book book = bookService.findById(id);
        BookCategoryDto bcDto = new BookCategoryDto();
        bcDto.setId(book.getId());
        bcDto.setName(book.getName());
        bcDto.setYear(book.getYear());
        bcDto.setCover(book.getCover());
        bcDto.setCategories(book.getCategories());

        model.addAttribute("bcDto", bcDto);
        model.addAttribute("categories", categoryService.findAll());
        return "/sections/book/edit";
    }

    @PostMapping("/book/update")
    public String update(@ModelAttribute BookCategoryDto bcDto) {
        System.out.println(bcDto.getCategories());
        Book book = bookService.findById(bcDto.getId());
        book.setName(bcDto.getName());
        book.setYear(bcDto.getYear());
        book.setCover(bcDto.getCover());
        book.setCategories(bcDto.getCategories());
        bookService.save(book);
        return "redirect:/book";
    }

}
